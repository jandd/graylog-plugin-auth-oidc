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

import static org.graylog2.plugin.security.Permission.*;

import java.util.Collections;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import org.graylog2.plugin.security.Permission;
import org.graylog2.plugin.security.PluginPermissions;

/**
 * Permissions for the OpenID Connect authentication plugin.
 *
 * @author Jan Dittberner
 * @since 3.1.0
 */
public class OIDCAuthPermissions implements PluginPermissions {
    public static final String CONFIG_READ = "oidcauthconfig:read";
    public static final String CONFIG_UPDATE = "oidcauthconfig:edit";

    private final ImmutableSet<Permission> permissions = ImmutableSet.of(
            create(CONFIG_READ, "Read OpenID Connect authenticator config"),
            create(CONFIG_UPDATE, "Update OpenID Connection authenticator config")
    );

    @Override
    public Set<Permission> permissions() {
        return permissions;
    }

    @Override
    public Set<Permission> readerBasePermissions() {
        return Collections.emptySet();
    }
}
