# What is this?

This is some demo code to show how to build an izpack installer and wrap it with Launch4j using a bundled JDK.

It also creates a ProcessPanel and shows how to run the java code in the panel.

## Gradle files

The major sections are broken down into their own gradle file

### izpack.gradle

This contains anything to do with configuring and running izpack

### jdk.gradle

This contains anything to do with downloading and bundling a jdk

### launch4j.gradle

This contains anything to do with configuring and running launch4j.

### build.gradle

Each module has its own `build.gradle` file.  These are mostly self-explanatory.  The parent build.gradle is responsible for generating the overall distribution.

## How it works

It works like so:
* Run `./gradlew distZip`, this will build the required `jar` and `zip` files in the `installer` and `product` modules.
* Once successful, run `./gradlew makeInstaller` this needs to be run separately due needing the output of `distZip` from specifically the `product` project.
  * This will create installer jar file and place it in the parent `/build` folder.
* Next run `./gradlew createAllExecutables`, this will create the file `installer.exe` with the `process.jar` and the `product.zip` embedded in it
* TODO - bundle it all together in a single `zip` file that can then be delivered to a user to unzip and install.