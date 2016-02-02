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

/*global define*/
/*jslint plusplus: true*/

/**
 * Helpers module.
 */

define({
    name: 'helpers/timer',
    def: function helpersTimer() {
        'use strict';

        /**
         * Divisor table used by the splitTime.
         *
         * The array describes the ratio between two consecutive digits
         * on the stopwatch.
         * Eg. the 1st digit (tens of minutes) described by divs[0]
         * is 6 times less worth than the 2nd digit (ones of minutes).
         * @type {array}
         */
        var divs = [6, 10, 6, 10, 10, 10];

        /**
         * Calculate digits for the timer.
         *
         * @param {number} ms Milliseconds since the start.
         * @return {array} Int-indexed array with data for the stopwatch.
         */
        function Time(ms) {
            if (ms === undefined) {
                return;
            }
            var r = 0,
                i = divs.length;

            if (ms < 0) {
            	return;
            }

            this.input = ms;

            r = Math.floor(ms / 10); // we're not interested in milliseconds

            while (i--) {
                // Calculates digits from right to the left, one at a time.
                //
                // 'r' is the remaining time in current units (eg. in seconds
                // on the 3rd interaction or in minutes on the 5th one)
                // 'divs' describe the ratio between digits on the stopwatch.
                //
                // In order to get the current digit, the remainder 'r' from
                // the previous step is modulo-divided by the value (ratio)
                // of the next (higher) unit.
                r = (r - (this[i] = r % divs[i])) / divs[i];
            }

            this.length = divs.length;
            return this;
        }

        /**
         * Returns 0 if given value is falsy. Otherwise,
         * given value will be returned.
         *
         * @param {*} value
         * @return {*}
         */
        function getValue(value) {
            return value || 0;
        }

        Time.prototype = [];

        /**
         * Convert Time to a string.
         * @return {string}
         */
        Time.prototype.toString = function Time_toString() {
            var str = '';

            str += getValue(this[0]);
            str += getValue(this[1]);
            str += ':';
            str += getValue(this[2]);
            str += getValue(this[3]);
            str += '.';
            str += getValue(this[4]);
            str += getValue(this[5]);

            return str;
        };

        return {
            Time: Time
        };
    }
});
