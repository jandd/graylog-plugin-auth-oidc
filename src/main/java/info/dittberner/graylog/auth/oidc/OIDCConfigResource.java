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

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.graylog2.plugin.cluster.ClusterConfigService;
import org.graylog2.plugin.rest.PluginRestResource;
import org.graylog2.shared.rest.resources.RestResource;

/**
 * REST endpoint for configuring the OpenID Connect authentication plugin.
 *
 * @author Jan Dittberner
 * @since 3.1.0
 */
@Api(value = "OIDC/Config")
@Path("/config")
@Produces(MediaType.APPLICATION_JSON)
@RequiresAuthentication
public class OIDCConfigResource extends RestResource implements PluginRestResource {
    private ClusterConfigService clusterConfigService;

    @Inject
    private OIDCConfigResource(ClusterConfigService clusterConfigService) {
        this.clusterConfigService = clusterConfigService;
    }

    @GET
    @RequiresPermissions(OIDCAuthPermissions.CONFIG_READ)
    public OIDCAuthConfig get() {
        final OIDCAuthConfig config = clusterConfigService.getOrDefault(OIDCAuthConfig.class, OIDCAuthConfig.defaultConfig());
        return config.toBuilder().build();
    }
}
