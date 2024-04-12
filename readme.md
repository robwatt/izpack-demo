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

## How it works

It works like so:
* Build the jar file - in this case the jar file is a process that is used inside the ProcessPanel of izpack.
* Copy the izpack resources - this is anything in the installer directory and will be copied into the izpack assemble directory
* Copy the jar file from above into the izpack assemble directory
* Create the installer jar by invoking izpack gradle task
  * This has a dependency on download/unzip the JDK we will use as the bundled JDK for the executable
* Create the exe launcher based on the installer jar file - the JDK sits next to the exe, so it has a bundled JDK used to run