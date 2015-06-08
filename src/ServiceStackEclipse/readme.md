## ServiceStackEclipse

To add to our client side support for Java clients, we have expanded our "Add ServiceStack Reference" functionality to the Eclipse IDE running on Windows, OSX and Linux.

This workflow works best when used in conjunction with a Maven project as it will add the ServiceStack.Java client library to your Maven dependencies. If you are using `Build Automatically` in Eclipse, these dependencies will automatically get pulled down after being added to the project and your new ServiceStack reference will build and be ready to use with the Java `JsonServiceClient`.

#### Installation from the Eclipse Marketplace

To help make this plugin discoverable, easy to install and keep upto date, this plugin has been published to the Eclipse Marketplace.

![](https://github.com/ServiceStack/Assets/raw/master/img/servicestackeclipse/ss-eclipse-install-win.gif)

#### Add/Update ServiceStack Reference

ServiceStackEclipse plugin introduces the similar workflow as the other supported IDEs by providing a simple dialog to add your server side DTOs to your client project.

![](https://github.com/ServiceStack/Assets/raw/master/img/servicestackeclipse/add-reference-demo.gif)

Like Android Studio, if the reference is added in a Maven project, the `net.servicestack.client` Maven dependency is automatically added.

The `Update ServiceStack Reference` menu is visible when you right click on the reference file in the package explorer. This will process and pass the requested options to the ServiceStack server and update the file in place.

![](https://github.com/ServiceStack/Assets/raw/master/img/servicestackeclipse/update-reference-demo.gif)
