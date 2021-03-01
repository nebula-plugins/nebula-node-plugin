# Gradle Plugin for Node

![Support Status](https://img.shields.io/badge/nebula-maintenance-orange.svg)

[![Gradle Plugin Portal](https://img.shields.io/maven-metadata/v/https/plugins.gradle.org/m2/com.netflix.nebula/nebula-node-plugin/maven-metadata.xml.svg?label=gradlePluginPortal)](https://plugins.gradle.org/plugin/nebula.node)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.netflix.nebula/nebula-node-plugin/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/com.netflix.nebula/nebula-node-plugin)
![CI](https://github.com/nebula-plugins/nebula-node-plugin/actions/workflows/ci.yml/badge.svg)
![Publish](https://github.com/nebula-plugins/nebula-node-plugin/actions/workflows/publish.yml/badge.svg)
[![Apache 2.0](https://img.shields.io/github/license/nebula-plugins/nebula-node-plugin.svg)](http://www.apache.org/licenses/LICENSE-2.0)

This plugin enabled you to use a lot of [NodeJS](https://nodejs.org)-based technologies as part of your
build without having NodeJS installed locally on your system. It integrates the following NodeJS-based system
with Gradle:

* [NodeJS](https://nodejs.org)
* [Yarn](https://yarnpkg.com/)
* [Grunt](https://gruntjs.com/)
* [Gulp](https://gulpjs.com/)

It's actually 3 plugins in one:

* [Node Plugin](https://plugins.gradle.org/plugin/nebula.node) (`nebula.node`) - [See docs](docs/node.md).
* [Grunt Plugin](https://plugins.gradle.org/plugin/nebula.grunt) (`nebula.grunt`) - [See docs](docs/grunt.md)
* [Gulp Plugin](https://plugins.gradle.org/plugin/nebula.gulp) (`nebula.gulp`) - [See docs](docs/gulp.md)



## Origin

This was taken from [srs/gradle-node-plugin](https://github.com/srs/gradle-node-plugin) due to lack of support/activity and the need to support Gradle 6.x


## License

```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
