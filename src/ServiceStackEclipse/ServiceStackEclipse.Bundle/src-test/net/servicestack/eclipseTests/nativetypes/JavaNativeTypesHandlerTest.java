package net.servicestack.eclipseTests.nativetypes;

import net.servicestack.eclipse.nativetypes.JavaNativeTypesHandler;
import net.servicestack.eclipse.nativetypes.NativeTypesLanguage;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Layoric on 15/10/2015.
 */
public class JavaNativeTypesHandlerTest {

    private static final String optionsTest = "/* Options:\n"+
            "Date: 2015-10-15 05:08:08\n"+
            "Version: 4.046\n"+
            "BaseUrl: http://techstacks.io\n"+
            "\n"+
            "//Package: \n"+
            "//GlobalNamespace: dto\n"+
            "//AddPropertyAccessors: True\n"+
            "//SettersReturnThis: True\n"+
            "//AddServiceStackTypes: True\n"+
            "AddResponseStatus: False\n"+
            "AddImplicitVersion: \n"+
            "IncludeTypes: \n"+
            "ExcludeTypes: \n"+
            "//TreatTypesAsStrings: \n"+
            "//DefaultImports: java.math.*,java.util.*,net.servicestack.client.*,com.google.gson.annotations.*,com.google.gson.reflect.*\n"+
            "*/\n"+
            "\n"+
            "import java.math.*;\n"+
            "import java.util.*;\n"+
            "import net.servicestack.client.*;\n"+
            "import com.google.gson.annotations.*;\n"+
            "import com.google.gson.reflect.*;";

    @Test
    public void testParseComments() throws Exception {
        JavaNativeTypesHandler nativeTypesHandler = new JavaNativeTypesHandler();
        Map<String,String> result = nativeTypesHandler.parseComments(optionsTest);
        Assert.assertTrue(result.containsKey("AddResponseStatus"));
        Assert.assertTrue(result.containsKey("AddImplicitVersion"));
        Assert.assertTrue(result.containsKey("IncludeTypes"));
        Assert.assertTrue(result.containsKey("ExcludeTypes"));
        Assert.assertTrue(!result.containsKey("Package"));
    }

    @Test
    public void testGenerateUrl() throws Exception {
        JavaNativeTypesHandler nativeTypesHandler = new JavaNativeTypesHandler();
        String url = nativeTypesHandler.generateUrl("http://techstacks.io", null);
        Assert.assertEquals("http://techstacks.io/types/java", url);
        Map<String,String> options = new HashMap<String, String>();
        options.put("Package","net.servicestack.testing");
        String urlWithOptions = nativeTypesHandler.generateUrl("http://techstacks.io",
                options);
        Assert.assertEquals("http://techstacks.io/types/java?Package=net.servicestack.testing", urlWithOptions);

    }

    @Test
    public void testGetUpdatedCode() throws Exception {
        JavaNativeTypesHandler nativeTypesHandler = new JavaNativeTypesHandler();
        String code = nativeTypesHandler.getUpdatedCode("http://techstacks.io", null);
        Assert.assertTrue(code.startsWith("/* Options"));
    }

    @Test
    public void testGetTypesLanguage() throws Exception {
        JavaNativeTypesHandler nativeTypesHandler = new JavaNativeTypesHandler();
        Assert.assertEquals(NativeTypesLanguage.Java,nativeTypesHandler.getTypesLanguage());
    }

    @Test
    public void testValidateServiceStackEndpoint() throws Exception {
        JavaNativeTypesHandler nativeTypesHandler = new JavaNativeTypesHandler();
        Boolean resultTrue = nativeTypesHandler.validateServiceStackEndpoint("techstacks.io");
        Assert.assertEquals(true,resultTrue);
        Boolean resultFalse = nativeTypesHandler.validateServiceStackEndpoint("techstacks.io/foo");
        Assert.assertEquals(false, resultFalse);
        try {
            //Throws not found
            nativeTypesHandler.validateServiceStackEndpoint("google.com");
        } catch (Exception e) {
            Assert.assertTrue(e.getClass() == FileNotFoundException.class);
        }
    }

    @Test
    public void testIsFileAServiceStackReference() throws Exception {

    }

    @Test
    public void testGetRelativeTypesUrl() throws Exception {

    }
}