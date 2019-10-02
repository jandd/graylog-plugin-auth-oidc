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

import java.util.Collection;

import edu.emory.mathcs.backport.java.util.Collections;
import org.graylog2.plugin.Plugin;
import org.graylog2.plugin.PluginMetaData;
import org.graylog2.plugin.PluginModule;

/**
 * Graylog OpenID Connect authentication plugin main class.
 *
 * @author Jan Dittberner
 * @since 3.1.0
 */
public class OIDCAuthPlugin implements Plugin {
    @Override
    public PluginMetaData metadata() {
        return new OIDCAuthMetaData();
    }

    @Override
    public Collection<PluginModule> modules() {
        return Collections.singletonList(new OIDCAuthModule());
    }
}
