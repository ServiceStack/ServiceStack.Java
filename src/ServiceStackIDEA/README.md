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