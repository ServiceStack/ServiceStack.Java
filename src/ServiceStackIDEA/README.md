# ServiceStackIDEA plugin
<img align="right" src="https://raw.githubusercontent.com/ServiceStack/Assets/master/img/servicestackidea/supported-ides.png" />
ServiceStackIDEA is a plugin for Jetbrains' IntelliJ based IDEs to support building client applications for ServiceStack services in the simplest possible way. Leveraging ServiceStack's NativeTypes feature, ServiceStackIDEA makes it easy to work with ServiceStack DTOs by providing intuitive menus for adding ServiceStack references and importing the associated client libraries as dependencies.

ServiceStackIDEA now supports many of the most popular Jetbrains IDEs including:

- IntelliJ
    - Java, Kotlin and TypeScript
- Android Studio
	- Java and Kotlin
- WebStorm, RubyMine, PhpStorm & PyCharm
	- TypeScript


## New TypeScript Support
Since version 1.0.11, ServiceStackIDEA now supports adding new TypeScript References!

![](https://raw.githubusercontent.com/ServiceStack/Assets/master/img/servicestackidea/webstorm-add-typescript.png)

By right clicking on any folder in your Project explorer, you can add a TypeScript reference by simply providing any based URL of your ServiceStack server.

![](https://raw.githubusercontent.com/ServiceStack/Assets/7474c03bdb0ea1982db2e7be57567ad1b8a4ad38/img/servicestackidea/add-typescript-ref.png)

Once this file as been added to your project, you can update your service DTOs simply right clicking `Update Servicestack Reference` or using the light bulb action (Alt+Enter by default).

![](https://raw.githubusercontent.com/ServiceStack/Assets/master/img/servicestackidea/webstorm-update-typescript.png)

This now means you can integrate with a ServiceStack service easily from your favorite Jetbrains IDE when working with TypeScript!

#### Install ServiceStack IDEA from the Plugin repository

The ServiceStack IDEA is now available to install directly from within a supported IDE Plugins Repository, to Install Go to: 

 1. `File -> Settings...` Main Menu Item
 2. Select **Plugins** on left menu then click **Browse repositories...** at bottom
 3. Search for **ServiceStack** and click **Install plugin**
 4. Restart to load the installed ServiceStack IDEA plugin

![](https://raw.githubusercontent.com/ServiceStack/Assets/master/img/servicestackidea/android-plugin-download.gif)

### Development
Local development of the plugin requires:
 - Java SDK 1.8
 - IntelliJ Ultimate/Community 2019.2+ (ideally 2020.3+)
 
Once loaded into IntelliJ for the first time, `import gradle` project by right clicking on `build.gradle` in the Project menu.

Once imported, run the `build` task, this should try to resolve the gradle version to use.

#### Debugging
Use the gradle task `runIde` on Debug, this should launch 2019.2 of IntelliJ Community edition which is the ealiest version supported after ServiceStackIDEA 1.0.40.

This breaking change came from 2019.2+ separation of Java lang features in the `com.intellij.psi.*` packages which SSIDEA uses for IntelliJ + Android studio Java support.
