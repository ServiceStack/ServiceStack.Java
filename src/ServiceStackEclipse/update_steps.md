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