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

import React from 'react';
import Reflux from 'reflux';

import {Row, Col, Button, Alert} from 'components/graylog';
import {Input} from 'components/bootstrap'

import {PageHeader, Spinner} from 'components/common';
import OIDCAuthActions from './OIDCAuthActions';
import OIDCAuthStore from './OIDCAuthStore';

import StoreProvider from 'injection/StoreProvider';

const RolesStore = StoreProvider.getStore('Roles');

import ObjectUtils from 'util/ObjectUtils';

class OIDCConfiguration extends React.Component {
    state = {};

    componentDidMount() {
        OIDCAuthActions.config().then(config => this.setState({config}));
        RolesStore.loadRoles().done(roles => {
            this.setState({roles: roles.map(role => role.name)});
        });
    }

    _saveSettings = (ev) => {
        ev.preventDefault();
        OIDCAuthActions.saveConfig(this.state.config);
    };

    _setSetting = (attribute, value) => {
        const newState = {};

        const settings = ObjectUtils.clone(this.state.config);
        settings[attribute] = value;
        newState.config = settings;
        this.setState(newState);
    };

    _bindChecked = (ev, value) => {
        this._setSetting(ev.target.name, typeof value === 'undefined' ? ev.target.checked : value);
    };

    _bindValue = (ev) => {
        this._setSetting(ev.target.name, ev.target.value);
    };

    render() {
        let content;
        if (!this.state.config || !this.state.roles) {
            content = <Spinner/>;
        } else {
            const roles = this.state.roles.map((role) => <option key={"default-group-" + role}
                                                                 value={role}>{role}</option>);
            content = (
                <Row>
                    <Col lg={8}>
                        <form id="oidc-config-form" className="form-horizontal" onSubmit={this._saveSettings}>
                            <fieldset>
                                <legend className="col-sm-12">Field configuration</legend>
                                <Input type="text" id="username_field" labelClassName="col-sm-3"
                                       wrapperClassName="col-sm-9" placeholder="username" label="Username field"
                                       value={this.state.config.username_field}
                                       help="ID token field containing the trusted username of the Graylog user"
                                       onChange={this._bindValue} required/>
                            </fieldset>
                            <fieldset>
                                <legend className="col-sm-12">User creation</legend>
                                <Input type="text" id="fullname_field" labelClassName="col-sm-3"
                                       wrapperClassName="col-sm-9" placeholder="fullname" label="Full Name Field"
                                       value={this.state.config.fullname_field}
                                       help="ID token field containing the full name of the user to create (defaults to the user name)."
                                       onChange={this._bindValue}/>
                                <Input type="text" id="email_field" labelClassName="col-sm-3"
                                       wrapperClassName="col-sm-9" placeholder="email" label="Email Field"
                                       value={this.state.config.fullname_field}
                                       help="ID token field containing the email address of the user to create."
                                       onChange={this._bindValue}/>
                                <Input id="default_group" labelClassName="col-sm-3" wrapperClassName="col-sm-9"
                                       label="Default User Role"
                                       help="The default Graylog role determines whether a user created can access the entire system, or has limited access.">
                                    <Row>
                                        <Col sm={6}>
                                            <select id="default_group" name="default_group" className="form-control"
                                                    required
                                                    value={this.state.config.default_group || 'Reader'}
                                                    onChange={this._bindValue}>
                                                {roles}
                                            </select>
                                        </Col>
                                    </Row>
                                </Input>
                            </fieldset>
                            <fieldset>
                                <legend className="col-sm-12">Store settings</legend>
                                <div className="form-group">
                                    <Col sm={9} smOffset={3}>
                                        <Button type="submit" bsStyle="success">Save OIDC settings</Button>
                                    </Col>
                                </div>
                            </fieldset>
                        </form>
                    </Col>
                </Row>
            );
        }

        return (
            <div>
                <PageHeader title="OpenID Connect Configuration" subpage>
                    <span>Configuration page for the OpenID Connection authenticator.</span>
                    {null}
                </PageHeader>
                {content}
            </div>
        );
    }
}

export default OIDCConfiguration;