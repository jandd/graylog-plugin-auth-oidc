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

// eslint-disable-next-line no-unused-vars
import webpackEntry from 'webpack-entry';

import packageJson from '../../package.json';
import {PluginManifest, PluginStore} from 'graylog-web-plugin';
import OIDCConfiguration from "./OIDCConfiguration";

PluginStore.register(new PluginManifest(packageJson, {
    authenticatorConfigurations: [
        {
            name: 'oidc',
            displayName: 'OpenID Connect',
            description: 'Creates and authenticates users based on OpenID Connect identity tokens',
            canBeDisabled: true,
            component: OIDCConfiguration,
        },
    ]
}));