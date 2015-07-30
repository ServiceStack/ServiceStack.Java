### Publishing changes for ServiceStackEclipse
1. Make desired changes to net.servicestack.eclipse project - Currently built with Eclipse Luna EE
2. Ensure you update the version in the main project's `plugin.xml`.
2. Open `product.product` to update the version and make any other metadata changes in the `feature` project.
3. In the feature project, use the Eclipse Product export wizard to package the plugin to the 'updatesite' project directory.
4. Open the 'site.xml' in the updatesite project and click `Add feature`
5. Search for the new version of the plugin and add.
6. Select the newly added product and click Build.
7. Commit chnages, including out JARs in feature and update site.
8. Push changes, Eclipse marketplace looks at GitHub directly to new versions.

If there is a problem with BinTray's 'Sync to Maven Central' functionality which happens, manually release new version for ServiceStackEclipse. This can happen if the Sync decides to use the AAR instead of the JAR file. 

1. Download artifacts from BinTray's release that goes to JCentre
2. Goto https://oss.sonatype.org/ and login
3. Click `Staging Upload`
4. Select Upload POM and Artifacts. 
5. Upload the POM at the top
6. Upload .jar, javadoc and sources including their associated asc.
7. Wait for staging repository to be dropped

Clients that have tried to use a version that was missing the required JAR on Maven Central via ServiceStackEclipse will need to clear out their .m2 cache in /home/{UserName}/.m2 directory under net/servicestack/client
