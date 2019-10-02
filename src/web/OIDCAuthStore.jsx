/*
 * graylog-plugin-auth-oidc - Graylog OpenID Connect authentication plugin
 * Copyright © 2019 Jan Dittberner (jan@dittberner.info)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

import Reflux from 'reflux';

import OIDCAuthActions from "./OIDCAuthActions";

import UserNotification from 'util/UserNotification';
import URLUtils from 'util/URLUtils';
import fetch from 'logic/rest/FetchProvider';

const urlPrefix = '/plugins/info.dittberner.graylog.auth.oidc';

const OIDCAuthStore = Reflux.createStore({
    listenables: [OIDCAuthActions],

    getInitialState() {
        return {
            config: undefined,
        };
    },

    _errorHandler(message, title, cb) {
        return (error) => {
            let errorMessage;
            try {
                errorMessage = error.additional.body.message;
            } catch (e) {
                errorMessage = error.message;
            }
            UserNotification.error(`${message}: ${errorMessage}`, title);
            if (cb) {
                cb(error);
            }
        };
    },

    _url(path) {
        return URLUtils.qualifyUrl(`${urlPrefix}${path}`);
    },

    config() {
        const promise = fetch('GET', this._url('/config'));

        promise.then((response) => {
            this.trigger({config: response});
        }, this._errorHandler('Fetching config failed', 'Could not retrieve OpenID Connect authenticator config'));

        OIDCAuthActions.config.promise(promise);
    },

    saveConfig(config) {
        const promise = fetch('PUT', this._url('/config'), config);

        promise.then((response) => {
            this.trigger({config: response});
            UserNotification.success('OpenID Connect configuration has been updated successfully');
        }, this._errorHandler('Updating OpenID Connect config failed', 'Unable to update OpenID Connect authentication config'));

        OIDCAuthActions.saveConfig.promise(promise);
    },
});

export default OIDCAuthStore;