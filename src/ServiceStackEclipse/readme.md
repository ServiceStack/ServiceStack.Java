### Eclipse Integration with ServiceStack

![](https://raw.githubusercontent.com/ServiceStack/Assets/master/img/wikis/eclipse-header.png)

The **ServiceStackEclipse** plugin enables cross-platform [Add ServiceStack Reference](https://github.com/ServiceStack/ServiceStack/wiki/Add-ServiceStack-Reference) integration with Eclipse on Windows, OSX and Linux.

### Install from Eclipse Marketplace

To install, search for **ServiceStack** in the Eclipse Marketplace at `Help -> Eclipse Marketplace`:

![](https://raw.githubusercontent.com/ServiceStack/Assets/master/img/servicestackeclipse/ss-eclipse-install-win.gif)

Find the **ServiceStackEclipse** plugin, click **Install** and follow the wizard to the end, restarting to launch Eclipse with the plugin loaded!

> **ServiceStackEclipse** is best used with Java Maven Projects where it automatically adds the **ServiceStack.Java** client library to your Maven Dependencies and when your project is set to **Build Automatically**, are then downloaded and registered, so you're ready to start consuming ServiceStack Services with the new `JsonServiceClient`

### Add ServiceStack Reference

Just like the [support in Android Studio](https://github.com/ServiceStack/ServiceStack/wiki/Java-Add-ServiceStack-Reference) you can right-click on a Java Package to open the **Add ServiceStack Reference...** dialog from the Context Menu:

![](https://raw.githubusercontent.com/ServiceStack/Assets/master/img/servicestackeclipse/add-reference-demo.gif)

Complete the dialog to add the remote Servers generated Java DTO's to your selected Java package and the `net.servicestack.client` dependency to your Maven dependencies.

### Update ServiceStack Reference

Updating a ServiceStack Reference works as normal where you can change any of the available options in the header comments, save, then right-click on the file in the File Explorer and click on **Update ServiceStack Reference** in the Context Menu:
 
![](https://raw.githubusercontent.com/ServiceStack/Assets/master/img/servicestackeclipse/update-reference-demo.gif)

### Using the plugin without Maven

ServiceStack references can still be added without the use of a Maven project. However, two dependencies will have to added to your Eclipse project for the generated Java to compile. Both JARs are available to download from JCenter or MavenCentral.

1. [`net.servicestack:client`](http://search.maven.org/#search%7Cga%7C1%7Cnet.servicestack)
2. [`com.google.code.gson:gson:2.11.0`](http://search.maven.org/#artifactdetails%7Ccom.google.code.gson%7Cgson%7C2.3.1%7Cjar)

Once you have downloaded these jars, add them to a folder in your project, eg `/lib`.

![](https://raw.githubusercontent.com/ServiceStack/Assets/7bf496d493b05f4f18ece4c8aab270eb8ecc930b/img/servicestackeclipse/without-maven.png)

Then add these dependencies to your Eclipse project.
1. Right click -> Properties on your project in Eclipse.
2. Select `Java Build Path`.
3. Select the `Libraries` tab.
4. Click `Add JAR`.
5. Select both required libraries.

## [ServiceStack Java Documentation](https://github.com/ServiceStack/ServiceStack/wiki/Java-Add-ServiceStack-Reference#update-servicestack-reference)

For further information on the different code-generation configuration options available and examples of using the `JsonServiceClient` refer to the [Java ServiceStack Reference Documentation](https://github.com/ServiceStack/ServiceStack/wiki/Java-Add-ServiceStack-Reference#update-servicestack-reference).