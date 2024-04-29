# What is this?

This is some demo code to show how to build an izpack installer and wrap it with Launch4j using a bundled JDK.

It also creates a ProcessPanel and shows how to run the java code in the panel.

## Modules

There are 3 modules in this demo. I had originally created a single module demo, but it was too simple for what I think
many people would probably want to do with this.

In that end, I expanded on the demo and created a 2 module system. The first module is code for the installer. The
second module is the product the user is trying to install.

### Installer

This module is designed to hold any custom Java code for the installer; such as the ProcessPanel; or any listener code
required.

### Uninstaller

This module is designed to hold any custom Java code for the uninstallation process. The reason for a separate module is
due to the way IzPack builds the uninstaller.jar at the end of the process. If the jar is large this operation will
take a significant amount of time. Therefore, it is best to make a module dedicated to this operation which will allow
you to have a much smaller (hopefully) jar.

It would be in the best interest to make sure that you don't add large dependencies since they would have to be added to
this jar.

Note: I have noticed that for an uninstaller.jar of around 25MB, it took about 2-3 minutes to complete the last step; in
which there is no progress indication to the user...making them think the installation process has crashed.

### Product

This module is designed to hold any Java code used as the actual product. For brevity, I built a sample Spring Boot
application.

This is the code that will be unzipped and installed in the target install directory the user chooses by the installer.

## IzPack Resources

The main `installer.xml` is stored in the `\izpack` folder, with any resources that the XML refers to in
the `izpack\resources` folder.

When configuring your installer you need an `installer.xml` and possibly several other resources files. All of these
files are stored in the `/izpack` directory.

While the main `installer.xml` is stored here, it is copied to a location in the build folder and used from there. This
is because the `installer.xml` has some tokens that need to be replaced by Gradle such as the `BUILD_FOLDER` and the
`VERSION`.

## Gradle files

The major sections are broken down into their own gradle file

### izpack.gradle

This contains anything to do with configuring and running izpack

### jdk.gradle

This contains anything to do with downloading and bundling a jdk

### launch4j.gradle

This contains anything to do with configuring and running launch4j.

### build.gradle

Each module has its own `build.gradle` file. These are mostly self-explanatory. The parent build.gradle is responsible
for generating the overall distribution.

## How it works

It works like so:

* Run `./gradlew clean assembleDist`, this will build the required `jar` and `zip` files in the `installer`
  and `product` modules.
* Once successful, run `./gradlew makeInstaller` this needs to be run separately due needing the output
  of `assembleDist` from specifically the `product` project.
    * This will create installer jar file and place it in the parent `/build` folder.
    * This will also create the installer.exe as a final task and place it in the `launch4j` folder
* Next run `./gradlew packageDistribution`. This will package up the installer and the jdk as a single zip file.

## What does this installer do?

While the bulk of this code is to create a demo on how to integrate IzPack with Launch4j in a reasonably complext
scenario, it was also important to attempt to add some of the more confusing items from IzPack.

The installer also demonstrates the following:
* Adding the application to the Windows Application list
* Create a shortcut on the desktop/program list if user desires