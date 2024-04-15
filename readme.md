# What is this?

This is some demo code to show how to build an izpack installer and wrap it with Launch4j using a bundled JDK.

It also creates a ProcessPanel and shows how to run the java code in the panel.

## Modules
There are 2 modules in this demo.  I had originally created a single module demo, but it was too simple for what I think many people would probably want to do with this.

In that end, I expanded on the demo and created a 2 module system.  The first module is code for the installer.  The second module is the product the user is trying to install.

### Installer
This module is designed to hold any custom Java code for the installer; such as the ProcessPanel.

### Product
This module is designed to hold any Java code used as the actual product.  For brevity, I built a sample Spring Boot application.

This is the code that will be unzipped and installed in the target install directory the user chooses by the installer.

## IzPack Resources
When configuring your installer you need an `installer.xml` and possibly several other resources files.  All of these files are stored in the `/izpack` directory.

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
  * This will also create the installer.exe as a final task and place it in the `launch4j` folder
* Next run `./gradlew packageDistribution`.  This will package up the installer and the jdk as a single zip file.