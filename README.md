Follow [@ServiceStack](https://twitter.com/servicestack) or join the [Google+ Community](https://plus.google.com/communities/112445368900682590445)
for updates, or [StackOverflow](http://stackoverflow.com/questions/ask) or the [Customer Forums](https://forums.servicestack.net/) for support.

# ServiceStack.Java

This repository contains the source for ServiceStack plugins for the leading Android Studio, IntelliJ and Eclipse Java IDE's providing Java developers a highly productive development experience for consuming Typed ServiceStack Services by leveraging [Add ServiceStack Reference](https://github.com/ServiceStack/ServiceStack/wiki/Add-ServiceStack-Reference) directly within their IDE! 

### v1.1.5 Changes

- Rename FileUpload to `UploadFile` to match other ServiceStack libraries

### v1.1.4 Changes

Added new ServiceClient APIs:

```java
<T> T postFileWithRequest(IReturn<T> request, FileUpload file);
<T> T postFileWithRequest(Object request, FileUpload file, Object responseType);
<T> T postFileWithRequest(String path, Object request, FileUpload file, Object responseType);

<T> T postFilesWithRequest(IReturn<T> request, FileUpload[] files);
<T> T postFilesWithRequest(Object request, FileUpload[] files, Object responseType);
<T> T postFilesWithRequest(String path, Object request, FileUpload[] files, Object responseType);
```

Added new AsyncServiceClient APIs:

```java
<T> void postFileWithRequestAsync(IReturn<T> request, FileUpload file, final AsyncResult<T> asyncResult);
<T> void postFileWithRequestAsync(Object request, FileUpload file, Object responseType, final AsyncResult<T> asyncResult);
<T> void postFileWithRequestAsync(String path, Object request, FileUpload file, Object responseType, final AsyncResult<T> asyncResult);

<T> void postFilesWithRequestAsync(IReturn<T> request, FileUpload[] files, final AsyncResult<T> asyncResult);
<T> void postFilesWithRequestAsync(Object request, FileUpload[] files, Object responseType, final AsyncResult<T> asyncResult);
<T> void postFilesWithRequestAsync(String path, Object request, FileUpload[] files, Object responseType, final AsyncResult<T> asyncResult);
```

### v1.1.0 Changes

Switched to use `/api` pre-defined route by default, revert to legacy `/json/reply` pre-defined route with:

```java
const client = new JsonServiceClient(baseUrl);
client.setBasePath();
```

## Install

### [Install ServiceStack IDEA Plugin on Android Studio and IntelliJ](https://github.com/ServiceStack/ServiceStack/wiki/Java-Add-ServiceStack-Reference#servicestack-idea-android-studio-plugin)
   - [Install ServiceStack IDEA from the Plugin repository](https://github.com/ServiceStack/ServiceStack/wiki/Java-Add-ServiceStack-Reference#install-servicestack-idea-from-the-plugin-repository)
   - [Download and Install ServiceStack IDEA Manually](https://github.com/ServiceStack/ServiceStack/wiki/Java-Add-ServiceStack-Reference#download-and-install-servicestack-idea-manually)

#### Issues

Please submit issues to https://github.com/ServiceStack/Issues

### [Installing ServiceStackEclipse Plugin on Eclipse](https://github.com/ServiceStack/ServiceStack.Java/tree/master/src/ServiceStackEclipse)

## Usage

### [Update ServiceStack Reference](https://github.com/ServiceStack/ServiceStack/wiki/Java-Add-ServiceStack-Reference#update-servicestack-reference)

For information on the different code-generation configuration options available and examples of using the `JsonServiceClient` refer to the [Java ServiceStack Reference Documentation](https://github.com/ServiceStack/ServiceStack/wiki/Java-Add-ServiceStack-Reference#update-servicestack-reference).

### [JsonServiceClient](https://github.com/ServiceStack/ServiceStack/wiki/Java-Add-ServiceStack-Reference#jsonserviceclient-api)

For information on using the generic Java JSON Service Client Typed API's see the [JsonServiceClient Documentation](https://github.com/ServiceStack/ServiceStack/wiki/Java-Add-ServiceStack-Reference#jsonserviceclient-api) or jump to the interested sections directly:

 - [JsonServiceClient Usage](https://github.com/ServiceStack/ServiceStack/wiki/Java-Add-ServiceStack-Reference#jsonserviceclient-usage)
 - [Custom Urls Example Usage](https://github.com/ServiceStack/ServiceStack/wiki/Java-Add-ServiceStack-Reference#custom-example-usage)
 - [AutoQuery Example Usage](https://github.com/ServiceStack/ServiceStack/wiki/Java-Add-ServiceStack-Reference#autoquery-example-usage)
 - [Android Service Client](https://github.com/ServiceStack/ServiceStack/wiki/Java-Add-ServiceStack-Reference#androidserviceclient)
   - [AsyncServiceClient API](https://github.com/ServiceStack/ServiceStack/wiki/Java-Add-ServiceStack-Reference#asyncserviceclient-api)
   - [Async API Usage](https://github.com/ServiceStack/ServiceStack/wiki/Java-Add-ServiceStack-Reference#async-api-usage)
   - [Async Error Handling](https://github.com/ServiceStack/ServiceStack/wiki/Java-Add-ServiceStack-Reference#async-error-handling)
 - [Typed Error Handling](https://github.com/ServiceStack/ServiceStack/wiki/Java-Add-ServiceStack-Reference#typed-error-handling)
 - [JsonServiceClient Error Handlers](https://github.com/ServiceStack/ServiceStack/wiki/Java-Add-ServiceStack-Reference#jsonserviceclient-error-handlers)

## [Java generated DTO Types](https://github.com/ServiceStack/ServiceStack/wiki/Java-Add-ServiceStack-Reference#java-generated-dto-types)

Contains information on how **Java Add ServiceStack Reference** ensures a high-fidelity, idiomatic translation within the constraints of Java language and its built-in libraries, where .NET Server DTO's are translated into clean, conventional Java POJO's where .NET built-in Value Types mapped to their equivalent Java data Type. Use the links below to jump directly to the sections you're interested in:

 - [.NET Attributes translated into Java Annotations](https://github.com/ServiceStack/ServiceStack/wiki/Java-Add-ServiceStack-Reference#net-attributes-translated-into-java-annotations)
 - [Terse, typed API's with IReturn interfaces](https://github.com/ServiceStack/ServiceStack/wiki/Java-Add-ServiceStack-Reference#terse-typed-apis-with-ireturn-interfaces)
 - [Getters and Setters generated for each property](https://github.com/ServiceStack/ServiceStack/wiki/Java-Add-ServiceStack-Reference#getters-and-setters-generated-for-each-property)
 - [Java Type Conversions](https://github.com/ServiceStack/ServiceStack/wiki/Java-Add-ServiceStack-Reference#java-type-conversions)
 - [Java Enums](https://github.com/ServiceStack/ServiceStack/wiki/Java-Add-ServiceStack-Reference#java-enums)
 - [Java Configuration](https://github.com/ServiceStack/ServiceStack/wiki/Java-Add-ServiceStack-Reference#java-configuration)

## Example [TechStacks Android App](https://github.com/ServiceStackApps/TechStacksAndroidApp)
To demonstrate Java Native Types in action we've ported the Swift [TechStacks iOS App](https://github.com/ServiceStackApps/TechStacksApp) to a native Java Android App to showcase the responsiveness and easy-of-use of leveraging Java Add ServiceStack Reference in Android Projects. 

[![](https://raw.githubusercontent.com/ServiceStack/Assets/master/img/release-notes/techstacks-android-app.jpg)](https://github.com/ServiceStackApps/TechStacksAndroidApp)

Checkout the [TechStacks Android App](https://github.com/ServiceStackApps/TechStacksAndroidApp) repository for a nice overview of how it leverages Java Native Types, Functional Java Utils and iOS-inspired Data Binding to easily develop services-heavy Mobile Apps.