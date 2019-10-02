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

import java.net.URI;
import java.util.Collections;
import java.util.Set;

import org.graylog2.plugin.PluginMetaData;
import org.graylog2.plugin.ServerStatus;
import org.graylog2.plugin.Version;

/**
 * Meta data for OIDC authentication plugin.
 *
 * @author Jan Dittberner
 * @since 3.1.0
 */
public class OIDCAuthMetaData implements PluginMetaData {
    private static final String PLUGIN_PROPERTIES = "info.dittberner.graylog-plugin-auth-oidc/graylog-plugin" +
            ".properties";

    @Override
    public String getUniqueId() {
        return "info.dittberner.graylog.auth.oidc.OIDCAuthPlugin";
    }

    @Override
    public String getName() {
        return "OpenID Connect authentication provider";
    }

    @Override
    public String getAuthor() {
        return "Jan Dittberner";
    }

    @Override
    public URI getURL() {
        return URI.create("https://github.com/jandd/graylog-plugin-auth-oidc");
    }

    @Override
    public Version getVersion() {
        return Version.fromPluginProperties(this.getClass(), PLUGIN_PROPERTIES, "version", Version.from(0, 0, 0,
                "unknown"));
    }

    @Override
    public String getDescription() {
        return "OpenID Connect authentication provider";
    }

    @Override
    public Version getRequiredVersion() {
        return Version.fromPluginProperties(this.getClass(), PLUGIN_PROPERTIES, "graylog.version",
                Version.CURRENT_CLASSPATH);
    }

    @Override
    public Set<ServerStatus.Capability> getRequiredCapabilities() {
        return Collections.emptySet();
    }
}
