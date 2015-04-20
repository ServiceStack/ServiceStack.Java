## AndroidStudio ServiceStack Plugin

ServiceStackIDEA is a plugin for AndroidStudio to make working with ServiceStack services easier when building Android apps. 

Using this plugin, you can add a ServiceStack reference to your existing Android application which generates required POJOs for interactive with the specific ServiceStack server.

![](https://github.com/ServiceStack/Assets/raw/master/img/servicestackidea/android-context-menu.png)

Once added, the plugin also adds a reference to the ServiceStack.Android library which uses the ServiceStack.Client Java library to to make HTTP requests. 

```
AndroidServiceClient client = new AndroidServiceClient("http://techstacks.io");
client.getAsync(new AppOverview(), new AsyncResult<AppOverviewResponse>() {
                @Override
                public void success(AppOverviewResponse response){
                    appOverviewResponse = response;
                    onUpdate(DataType.AppOverview);
                }
            });
```

This reduces the amount of code required when interacting with ServiceStack web services and also reduces guesswork around request and response types by using the generated POJOs.

![](https://github.com/ServiceStack/Assets/raw/master/img/servicestackidea/android-client-example.gif)

## Installing the ServiceStack Plugin for Android Studio

To install the ServiceStack plugin..

1. [download it here](https://plugins.jetbrains.com/plugin/download?pr=androidstudio&updateId=19465) and save it locally.
2. In Android Studio Go File -> Settings
3. Search for "Install Plugin"
4. Select "Install Plugin From Disk"
5. Locate the downloaded plugin and 

![](https://github.com/ServiceStack/Assets/raw/34925d1b1b1b1856c451b0373139c939801d96ec/img/servicestackidea/android-plugin-install.gif)

#### Add a ServiceStack Reference

To add a ServiceStack reference, right click on a file or folder inside your project module.
> Alternatively, select the module/package folder/Java file and press **Ctrl+Alt+Shift+R**.

![](https://github.com/ServiceStack/Assets/raw/master/img/servicestackidea/android-dialog.png)

If you right click of an existing Java class (with a package) or package folder, the package field will be populated automatically.

![](https://github.com/ServiceStack/Assets/raw/master/img/servicestackidea/android-dialog-example.gif)

Then provide the base URL to the ServiceStack server. This is usually the domian where your ServiceStack application is hosted.

Also, you can change the required file name for where the POJOs will be stored.

The Java file will then be added to the related package or `/src/main/java` folder of the module. If it has not already been added, the Module's `build.gradle` dependencies will be updated with the `net.servicestack:android` client dependency.

#### Update ServiceStack Reference

Once you have added a ServiceStack reference, it can be easily updated by using AndroidStudio `Intention` shortcut, usually Alt+Enter when in the file.

![](https://github.com/ServiceStack/Assets/raw/master/img/servicestackidea/android-update-example.gif)

This will read all of the used options in the commented file header to generate a new request. This can be handy if the server has been updated and you need to update your client side POJOs, or if you want to include get/set methods by default.

For documentation on all options for both server and client, see the Java Add ServiceStack Reference wiki page.

