/*
 * Copyright (c) 2015 Samsung Electronics Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*global navigator, tau, document, define, setTimeout*/

/**
 * StopWatch page module
 */

define({
    name: 'views/stopWatchPage',
    requires: [
        'core/event',
        'models/timer',
        'core/template',
        'helpers/timer'
    ],
    def: function viewsStopWatchPage(req) {
        'use strict';

        /**
         * Progress bar start angle in radians.
          * @type {number}
          */
        var INDICATOR_START_ANGLE = -Math.PI / 2,

            /**
             * Device height.
             * Used for swipe.
             * @type {number}
             */
            DEVICE_HEIGHT = 360,

            /**
             * Indicator vibration duration (ms).
             * @type {number}
             */
            VIBRATION_DURATION= 100,

            /**
             * Indicator restore timeout (ms).
             * @type {number}
             */
            INDICATOR_RESTORE_TIMEOUT = 500,

            /**
             * Core event module.
             * @type {Module}
             */
            e = req.core.event,

            /**
             * Core template  module.
             * @type {Module}
             */
            tpl = req.core.template,

            /**
             * Timer model module.
             * @type {Module}
             */
            Timer = req.models.timer.Timer,

            /**
             * Timer helper module..
             * @type {Module}
             */
            Time = req.helpers.timer.Time,


            /**
             * Timer model module.
             * @type {null|Object}
             */
            timer = null,

            /**
             * Initialization flag.
             * @type {boolean}
             */
            initialised = false,

            /**
             * Canvas context.
             * @type {null|CanvasContext}
             */
            roundProgressCanvasContext = null,

            /**
             * Laps list page id attribute.
             * @type {string}
             */
            pageId = 'stopwatch-page',

            /**
             * Main page element.
             * @type {DOMElement}
             */
            stopWatchPage = document.getElementById(pageId),


            /**
             * Total time element (minutes).
             * @type {DOMElement}
             */
            roundProgressCanvas = document.getElementById('path-canvas'),

            /**
             * Digit elements.
             * @type {DOMCollection}
             */
            digits = document.querySelectorAll('.digit[id]'),
            
            isCircle = tau.support.shape.circle,

            /**
             * time out ? true : false;
             */
            isFinished = false;

        /**
         * Show the stopwatch page.
         */
        function show() {
            tau.changePage(stopWatchPage);
            
        }

        /**
         * Show buttons.
         * @param {string} status Status can be ready, paused, running.
         */
        function showButtons(status) {
        	examTitle.innerText = examList[gIndex].title;
            var buttons = document.getElementsByClassName('stopwatch-btn'),
                buttonsElementsCount = buttons.length - 1,
                i = 0;

            status = status || timer.status;

            for (i = buttonsElementsCount; i >= 0; i -= 1) {
                buttons[i].classList.add('hidden');
            }

            buttons = document.getElementsByClassName('stopwatch-' + status);
            buttonsElementsCount = buttons.length - 1;
            for (i = buttonsElementsCount; i >= 0; i -= 1) {
                buttons[i].classList.remove('hidden');
            }
        }

        /**
         * Repaints progress bar.
         * @param {number} ts Total time in milliseconds.
         */
        function redrawCanvas(ts) {
        	
        	var ratio = ts/examList[gIndex].time;
        	if(ratio>0.9 && ratio<1 ){
        		roundProgressCanvasContext.strokeStyle = 'rgb(240, 27, 0)';
        	}else if(ratio>0.8 && ratio<0.9){
        		roundProgressCanvasContext.strokeStyle = 'rgb(255,185,0)';
        	}else{
        		roundProgressCanvasContext.strokeStyle = 'rgb(0, 195, 113)';
        	}
        	
            roundProgressCanvasContext.clearRect(0, 0, 360, 360); // start x, start y, width, height
            roundProgressCanvasContext.beginPath();
            // x1, y1, r, start angle, end angle
            roundProgressCanvasContext.arc(
                180,
                180,
                180,
                INDICATOR_START_ANGLE,
                INDICATOR_START_ANGLE +
                (ratio*Math.PI*2)
            );
            roundProgressCanvasContext.stroke();
        }


        /**
         * Refresh timer.
         * @return {array} Array of digits.
         */
        function refreshTimer() {
        		/**
                 * Total time in miliseconds.
                 * @type {number}
                 */
                var elapsedTimestamp = timer.getTimeElapsed(),
                	
                    /**
                     * Array of digits.
                     * @type {number[]}
                     */
                    time = new Time(elapsedTimestamp)
                    if(time.length == 0){
                    	console.log('stop');
                    	examList.length > gIndex+1 ? (gIndex += 1) : (gIndex = 0);
                    	timer.reset();
                    	timer.run();
                    	showButtons();
                    }
                    /**
                     * Length of Array of digits.
                     */
                    var i =  time.length - 3;
                
                    for (i; i >= 0; i -= 1) {
                        digits[i].innerText = time[i];
                    }

                    var progressElapsed = examList[gIndex].time - elapsedTimestamp;
                    redrawCanvas(progressElapsed);
                return time;
        }

        /**
         * Restores indicator.
         */
        function restoreIndicator() {
            /* jshint validthis: true */
            this.classList.remove('active');
        }

        /**
         * Triggers indicator for desired time.
         */
        function triggerIndicator() {
            /* jshint validthis: true */
            var indicatorClass = '.' +
                this.getAttribute('indicator') + '-indicator',
                indicatorElement =
                document.querySelector(indicatorClass);

            indicatorElement.classList.add('active');
            if (navigator.hasOwnProperty('vibrate')) {
                navigator.vibrate(VIBRATION_DURATION);
            }
            setTimeout(
                restoreIndicator.bind(indicatorElement),
                INDICATOR_RESTORE_TIMEOUT
            );
        }

        /**
         * Binds indicator trigger on click.
         */
        function bindIndicator() {
            /* jshint validthis: true */
            this.addEventListener('click', triggerIndicator);
        }

        /**
         * Start the timer.
         * @param {Event} ev Event.
         */
        function start(ev) {
            ev.preventDefault();
            timer.run();
            showButtons();
        }

        /**
         * Resets Stopwatch.
         * Sets default texts.
         * Works when the timer is stopped (paused).
         */
        function reset() {
            timer.reset();
            refreshTimer();
            showButtons();
        }


        /**
         * Stop actually pauses the timer.
         * @param {Event} e
         */
        function stop(e) {
            e.preventDefault();
            timer.pause();
            refreshTimer();
            showButtons();
        }

        /**
         * Restores main page zoom.
         */
        function restoreZoom() {
            stopWatchPage.classList.remove('zoom-out');
        }
        /**
         * 
         */
        function rotaryDetentHandler() 
		   {
		      /* Get the rotary direction */
		      var direction = event.detail.direction;

		      if (direction === "CW") 
		      {
		         /* Right direction */
		    	  navigator.vibrate(100);
		    	  examList.length > gIndex+1 ? (gIndex+=1) : (gIndex=0);
		    	  console.log(gIndex);
		    	  timer.reset();
		    	  timer.run();
		    	  showButtons();
		    	  
		      } 
		      else if (direction === "CCW") 
		      {
		         /* Left direction */
		    	  navigator.vibrate(100);
		    	  gIndex != 0 ? (gIndex-=1) : (gIndex=0);
		    	  console.log(gIndex);
		    	  timer.reset();
		    	  timer.run();
		    	  showButtons();
		      }
		   }

        /**
         * Binds events to all interactive elements in application.
         */
        function bindEvents() {
            // start (when zeroed, ready to run)
            document.getElementById('stopwatch-start-btn').addEventListener(
                'click',
                start
            );

            // stop, lap (when running)
            document.getElementById('stopwatch-stop-btn').addEventListener(
                'click',
                stop
            );

            // restart, reset (when stopped, ie. paused)
            document.getElementById('stopwatch-restart-btn').addEventListener(
                'click',
                start
            );

            // reset stopwatch
            document.getElementById('stopwatch-reset-btn').addEventListener(
                'click',
                reset
            );

            // Binding indicators to all buttons.
            Array.prototype.slice.apply(
                document.querySelectorAll('.main-control button')
            ).map(function bindButton(element) {
                bindIndicator.call(element);
            });

            // restore zoom.
            document.getElementById('stopwatch-content-timer')
                .addEventListener('click', restoreZoom);
            
            // rotary btn
            if(isCircle)
            document.addEventListener("rotarydetent", rotaryDetentHandler);

            e.listeners({
                'views.stopWatchPage.show': show
            });

        }

        /**
         * Initialises and sets up the canvas.
         */
        function initCanvas() {
        	if(isCircle)
        	tizen.power.request("SCREEN", "SCREEN_NORMAL");
        	console.log('initCanvas()');
            roundProgressCanvasContext = roundProgressCanvas.getContext('2d');
            roundProgressCanvasContext.strokeStyle = 'rgb(0, 195, 113)';
            roundProgressCanvasContext.lineWidth = 180;
        }

        /**
         * Initialize the stopwatch - timer and events.
         * Returns true if any action was performed, false otherwise.
         *
         * @return {boolean}
         */
        function initStopWatch() {
            if (initialised) {
                return false;
            }

            /**
             * Bind tick event listener.
             */
            e.listeners({
                'models.timer.tick': refreshTimer
            });

            // init model
            timer = new Timer(10, 'tick');

            // set up canvas element
            initCanvas();

            // init UI by binding events
            bindEvents();

            // initialization status
            initialised = true;
            return true;
        }

        return {
            init: initStopWatch
        };

    }
});
