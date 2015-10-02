import com.intellij.mock.MockProjectEx;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.openapi.wm.impl.TestWindowManager;
import com.intellij.testFramework.PlatformLiteFixture;
import net.servicestack.idea.AddServiceStackRefHandler;
import net.servicestack.idea.AddServiceStackReference;
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
        URIBuilder urlBuilder = AddServiceStackRefHandler.createUrl("techstacks.io");
        assertEquals(urlBuilder.build().toString(),"http://techstacks.io/types/java/");
    }
}
