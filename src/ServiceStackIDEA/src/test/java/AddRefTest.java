import com.intellij.mock.MockProjectEx;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.openapi.wm.impl.TestWindowManager;
import com.intellij.testFramework.PlatformLiteFixture;
import net.servicestack.idea.*;
import org.apache.http.client.utils.URIBuilder;

import java.net.MalformedURLException;
import java.net.URISyntaxException;


public class AddRefTest extends PlatformLiteFixture {

    private TestWindowManager windowManager;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        initApplication();
        Extensions.registerAreaClass("IDEA_PROJECT", null);
        myProject = new MockProjectEx(getTestRootDisposable());

        windowManager = new TestWindowManager();
        getApplication().registerService(WindowManager.class, windowManager);
    }

    public void testCreateUrl() throws MalformedURLException, URISyntaxException {
        INativeTypesHandler javaNativeTypesHanlder = new JavaNativeTypesHandler();
        INativeTypesHandler kotlinNativeTypesHandler = new KotlinNativeTypesHandler();
        URIBuilder javaUriBuilder = javaNativeTypesHanlder.getUrl("techstacks.io");
        assertEquals(javaUriBuilder.build().toString(),"http://techstacks.io/types/java");

        URIBuilder kotlinUriBuilder = kotlinNativeTypesHandler.getUrl("techstacks.io");
        assertEquals(kotlinUriBuilder.build().toString(),"http://techstacks.io/types/kotlin");
    }

    public void testTypeHandlersLanguageName() {
        INativeTypesHandler java = new JavaNativeTypesHandler();
        INativeTypesHandler kotlin = new KotlinNativeTypesHandler();
        INativeTypesHandler typeScriptD = new TypeScriptNativeTypesHandler();
        INativeTypesHandler typeScript = new TypeScriptConcreteNativeTypesHandler();

        assertEquals(java.getLanguageUrlName(),"java");
        assertEquals(kotlin.getLanguageUrlName(),"kotlin");
        assertEquals(typeScriptD.getLanguageUrlName(),"typescript.d");
        assertEquals(typeScript.getLanguageUrlName(),"typescript");
    }
}
