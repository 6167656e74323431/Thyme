# Thyme ![Travis Build Status](https://travis-ci.org/educationallydesigned/Thyme.svg?branch=master)
Thyme is an educational game to assist teenagers in time management, and tracking. All data related to this program is hosted on our github page which can be accessed via [educationallydesigned.ml](http://educationallydesigned.ml).

## Usage
If you are trying to run this from a provided disc, ThymeLauncher.jar should be provided in the base directory. This jar will work with most versions of JRE, and it will ensure that the main game, Thyme.jar is launched in the proper JRE version. If the launcher is not provided, it may be found at our github page [here](https://github.com/educationallydesigned/ThymeLauncher). The game can still be run on its own, provided it is done so in JRE 7. For our deployment to disk, we used [this version of JRE](https://www.enigmatic.network/file_share/view/b0d35ead-a20e-4a09-ac28-b638538fc619).

## Building
The program can be built using Gradle (which is included in the source code), in Java 7 or Java 8. On windows the command `gradlew.bat desktop:dist` will build the program. The Jar will be located in the `desktop/build/` folder upon successful compilation. **Note** Due to copyright issuses, many images cannot be provided on the repo, and the build may fail.

