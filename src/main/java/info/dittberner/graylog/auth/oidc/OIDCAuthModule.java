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

package info.dittberner.graylog.auth.oidc;

import com.google.inject.Scopes;
import info.dittberner.graylog.auth.oidc.audit.OIDCAuthAuditEventTypes;
import org.graylog2.plugin.PluginModule;

/**
 * OpenID Connect authentication module.
 *
 * @author Jan Dittberner
 * @since 3.1.0
 */
public class OIDCAuthModule extends PluginModule {
    @Override
    protected void configure() {
        authenticationRealmBinder().addBinding(OIDCAuthRealm.NAME).to(OIDCAuthRealm.class).in(Scopes.SINGLETON);
        addRestResource(OIDCConfigResource.class);
        addRestResource(OIDCCallBackResource.class);
        addPermissions(OIDCAuthPermissions.class);
        addAuditEventTypes(OIDCAuthAuditEventTypes.class);
    }
}
