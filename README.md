# SSO Authentication Plugin for Graylog

[![Build Status](https://travis-ci.org/jandd/graylog-plugin-auth-oidc.svg?branch=master)](https://travis-ci.org/jandd/graylog-plugin-auth-oidc)

This plugins adds OpenID Connect authentication/authorization capabilities to Graylog. It supports automatic login and user account creation based on OpenID Connect id tokens issued by a trusted authorization server.

**Required Graylog version:** 3.1.0 and later

Version Compatibility
---------------------

| Plugin Version | Graylog Version |
| -------------- | --------------- |
| 3.1.x          | 3.1.x           |

Installation
------------

[Download the plugin](https://github.com/jandd/graylog-plugin-auth-oidc/releases)
and place the `.jar` file in your Graylog plugin directory. The plugin directory
is the `plugins/` folder relative from your `graylog-server` directory by default
and can be configured in your `graylog.conf` file.

Restart `graylog-server` and you are done.

Development
-----------

You can improve your development experience for the web interface part of your plugin
dramatically by making use of hot reloading. To do this, do the following:

* `git clone https://github.com/Graylog2/graylog2-server.git`
* `cd graylog2-server/graylog2-web-interface`
* `ln -s $YOURPLUGIN plugin/`
* `npm install && npm start`

Usage
-----

TODO: describe usage

Getting started
---------------

This project is using Maven 3 and requires Java 8 or higher.

* Clone this repository.
* Run `mvn package` to build a JAR file.
* Optional: Run `mvn jdeb:jdeb` and `mvn rpm:rpm` to create a DEB and RPM package respectively.
* Copy generated JAR file in target directory to your Graylog plugin directory.
* Restart the Graylog.

Plugin Release
--------------

- Bump version in `package.json`
- Bump parent version and `graylog.version` in pom.xml
- Change branch name for the graylog2-server checkout in `.travis.yml`

For the rest we are using the maven release plugin:

```
$ mvn release:prepare
```

(no need for `mvn release:perform`)

This sets the version numbers, creates a tag and pushes to GitHub. Travis CI will build the release artifacts and upload to GitHub automatically.
