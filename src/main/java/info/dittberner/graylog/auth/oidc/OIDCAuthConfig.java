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

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

/**
 * TODO: document type/package
 *
 * @author Jan Dittberner
 * @since TODO: document first version
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_OIDCAuthConfig.Builder.class)
@JsonAutoDetect
public abstract class OIDCAuthConfig {
    public static Builder builder() {
        return new AutoValue_OIDCAuthConfig.Builder();
    }

    public abstract Builder toBuilder();

    public static OIDCAuthConfig defaultConfig() {
        return builder().usernameField("username").emailField("email").fullnameField("givenName").autoCreateUser(true).build();
    }

    @JsonProperty("username_field")
    public abstract String usernameField();

    @JsonProperty("fullname_field")
    public abstract String fullnameField();

    @JsonProperty("email_field")
    public abstract String emailField();

    @JsonProperty("auto_create_user")
    public abstract boolean autoCreateUser();


    @AutoValue.Builder
    public static abstract class Builder {
        abstract OIDCAuthConfig autoBuild();

        public OIDCAuthConfig build() {
            return autoBuild();
        }

        @JsonProperty("username_field")
        public abstract Builder usernameField(String usernameField);

        @JsonProperty("fullname_field")
        public abstract Builder fullnameField(@Nullable String fullnameField);

        @JsonProperty("email_field")
        public abstract Builder emailField(@Nullable String emailField);

        @JsonProperty("auto_create_user")
        public abstract Builder autoCreateUser(boolean autoCreateUser);
    }
}
