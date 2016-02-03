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

/*global define, window, document, history*/

/**
 * Init page module.
 */

define({
    name: 'views/initPage',
    requires: [
        'core/event',
        'core/template',
        'core/application',
        'views/stopWatchPage',
        'core/systeminfo'
    ],
    def: function viewsInitPage(req) {
        'use strict';

        /**
         * Event module object.
         * @type {Module}
         */
        var e = req.core.event,

            /**
             * Application module object.
             * @type {Module}
             */
            app = req.core.application,

            /**
             * System info module object.
             * @type {Module}
             */
            sysInfo = req.core.systeminfo;

        /**
         * Handles tizenhwkey event.
         * @param {Event} ev
         */
        function onHardwareKeysTap(ev) {
            if (ev.keyName === 'back') {
                /*if (document.querySelector('#lap-list-page.visible')) {
                    // hide lap list if visible
                    req.views.stopWatchPage.hideLapList();
                } else {*/
            	tizen.power.release("SCREEN");
                    app.exit();
                //}
            }
        }

        /**
         * Handles core.systeminfo.battery.low event.
         *
         * Application policy implies its close in such case.
         */
        function onLowBattery() {
            app.exit();
        }

        /**
         * Handles keydown event on document element.
         * @param {Event} ev
         */
        function onKeyDown(ev) {
            if (ev.keyIdentifier.indexOf('Power') !== -1) {
                e.fire('device.powerOff');
            }
        }

        /**
         * Registers events.
         */
        function bindEvents() {
            document.addEventListener('keydown', onKeyDown);
            window.addEventListener('tizenhwkey', onHardwareKeysTap);
            sysInfo.listenBatteryLowState();
        }

        /**
         * Initializes module.
         */
        function init() {
            bindEvents();
            sysInfo.checkBatteryLowState();
        }

        e.listeners({
            'core.systeminfo.battery.low': onLowBattery
        });

        return {
            init: init
        };
    }

});
