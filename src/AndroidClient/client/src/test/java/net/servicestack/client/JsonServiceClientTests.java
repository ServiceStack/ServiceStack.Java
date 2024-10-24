//  Copyright (c) 2013-present ServiceStack, Inc. All rights reserved.

package net.servicestack.client;

import junit.framework.TestCase;
import net.servicestack.client.ConnectionFilter;
import net.servicestack.client.JsonServiceClient;

import net.servicestack.client.WebServiceException;
import net.servicestack.client.tests.TestDtos;
import test.dtos.*;

import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class JsonServiceClientTests extends TestCase {

    public JsonServiceClientTests() {
        Log.setInstance(new LogProvider("", true));
    }
    //10.0.2.2 = loopback
    //http://developer.android.com/tools/devices/emulator.html
    JsonServiceClient client = new JsonServiceClient("https://test.servicestack.net");

    public void test_can_GET_HelloAll(){
        Hello request = new Hello()
            .setName("World");

        HelloResponse response = client.get(request);

        assertEquals("Hello, World!", response.getResult());
    }

    public void test_can_use_request_filter() {
        final Boolean[] passTest = {false};
        JsonServiceClient localTestClient = new JsonServiceClient("https://test.servicestack.net/");

        localTestClient.RequestFilter = new ConnectionFilter() {
            @Override
            public void exec(HttpURLConnection conn) {
                passTest[0] = true;
            }
        };

        Hello request = new Hello()
                .setName("World");

        HelloResponse response = localTestClient.get(request);

        assertEquals("Hello, World!", response.getResult());
        assertTrue(passTest[0]);
    }

    public void test_does_process_missing_service_correctly() {
        JsonServiceClient localTestClient = new JsonServiceClient("https://blazordiffusion.com/");

        try {
            localTestClient.get(new EchoTypes());
            fail("Should throw");
        } catch (WebServiceException ex) {
            assertEquals(ex.getStatusCode(), 404);
        }
    }

    public void test_can_serialize_dates_correctly_via_get_request() {
        JsonServiceClient client = new JsonServiceClient("https://test.servicestack.net/");

        EchoTypes request = new EchoTypes();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2015);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date dateRepresentation = cal.getTime();
        Date date = dateRepresentation;

        request.setDateTime(date);
        EchoTypes response = client.get(request);
        assertTrue(response != null);
        assertEquals(request.getDateTime(),response.getDateTime());
    }

    public void test_can_change_basePath() {
        JsonServiceClient client = new JsonServiceClient("https://test.servicestack.net/");
        assertEquals("https://test.servicestack.net/api/", client.getReplyUrl());

        client.setBasePath();
        assertEquals("https://test.servicestack.net/json/reply/", client.getReplyUrl());

        client.setBasePath("api");
        assertEquals("https://test.servicestack.net/api/", client.getReplyUrl());
        client.setBasePath("/api");
        assertEquals("https://test.servicestack.net/api/", client.getReplyUrl());
        client.setBasePath("/api/");
        assertEquals("https://test.servicestack.net/api/", client.getReplyUrl());
    }

    public void test_Can_Post_file_with_Request() {
        try {
            TestDtos.SpeechToText request = new TestDtos.SpeechToText();
            request.setTag("ztag");
            request.setRefId("zid");

            byte[] fileBytes = "Hello World".getBytes(StandardCharsets.UTF_8);
            FileUpload[] files = new FileUpload[]{
                    new FileUpload(
                            "audio",         // fieldName
                            "test.txt",      // fileName
                            "text/plain",    // contentType
                            fileBytes        // data
                    )
            };

            TestDtos.GenerationResponse response = client.postFilesWithRequest(
                    "/api/SpeechToText",
                    request,
                    files,
                    TestDtos.GenerationResponse.class
            );

            List<TestDtos.TextOutput> outputs = response.getTextOutputs();

            assertNotNull("Response should not be null", response);
            assertNotNull("Text outputs should not be null", outputs);
            assertEquals("Should match expected output", "audio, Audio 11, test.txt, text/plain", outputs.get(0).getText());
            assertEquals("Should match expected tag", "Tag ztag", outputs.get(1).getText());
            assertEquals("Should match expected refId", "RefId zid", outputs.get(2).getText());

        } catch (Exception e) {
            fail("Error during test: " + e.getMessage());
        }
    }

    public void test_Can_Post_Multiple_files_with_Request() {
        try {
            TestDtos.TestFileUploads request = new TestDtos.TestFileUploads();
            request.setId(1);
            request.setRefId("zid");

            byte[] textFileBytes = "Hello World".getBytes(StandardCharsets.UTF_8);
            byte[] markdownFileBytes = "## Heading".getBytes(StandardCharsets.UTF_8);

            FileUpload[] files = new FileUpload[]{
                    new FileUpload(
                            "audio",         // fieldName
                            "test.txt",      // fileName
                            "text/plain",    // contentType
                            textFileBytes    // data
                    ),
                    new FileUpload(
                            "content",       // fieldName
                            "test.md",       // fileName
                            "text/markdown", // contentType
                            markdownFileBytes // data
                    )
            };

            TestDtos.TestFileUploadsResponse response = client.postFilesWithRequest(
                    "/api/TestFileUploads",
                    request,
                    files,
                    TestDtos.TestFileUploadsResponse.class
            );

            assertNotNull("Response should not be null", response);
            assertEquals("Id should match", Optional.of(1), response.getId());
            assertEquals("RefId should match", "zid", response.getRefId());
            assertEquals("Should have correct number of files", 2, response.getFiles().size());

            // Verify first file
            TestDtos.UploadInfo file1 = response.getFiles().get(0);
            assertEquals("First file name should match", "audio", file1.getName());
            assertEquals("First filename should match", "test.txt", file1.getFileName());
            assertEquals("First file content length should match", Optional.of("Hello World".length()), file1.getContentLength());
            assertEquals("First file content type should match", "text/plain", file1.getContentType());

            // Verify second file
            TestDtos.UploadInfo file2 = response.getFiles().get(1);
            assertEquals("Second file name should match", "content", file2.getName());
            assertEquals("Second filename should match", "test.md", file2.getFileName());
            assertEquals("Second file content length should match", Optional.of("## Heading".length()), file2.getContentLength());
            assertEquals("Second file content type should match", "text/markdown", file2.getContentType());

        } catch (Exception e) {
            fail("Error during test: " + e.getMessage());
        }
    }

// Async versions would not be needed in Java as the API is already based on blocking calls
// If async behavior is needed, it would typically be handled by the calling code using
// CompletableFuture or other async patterns external to these tests
}
