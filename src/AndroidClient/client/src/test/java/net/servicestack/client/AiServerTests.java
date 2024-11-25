package net.servicestack.client;

import junit.framework.TestCase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import net.servicestack.openai.dtos.*;  // You'll need to generate these DTOs

public class AiServerTests extends TestCase {
    private JsonServiceClient client;
    private static final String ENV_AI_SERVER_URL = "AI_SERVER_URL";
    private static final String ENV_AI_SERVER_API_KEY = "AI_SERVER_API_KEY";
    private static final String ENV_RUN_AI_TESTS = "RUN_AI_TESTS";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Skip tests if RUN_AI_TESTS is not set
        if (System.getenv(ENV_RUN_AI_TESTS) == null) {
            System.out.println("Skipping AI Server tests. Set RUN_AI_TESTS=1 to run these tests...");
            return;
        }

        String serverUrl = System.getenv(ENV_AI_SERVER_URL);
        String apiKey = System.getenv(ENV_AI_SERVER_API_KEY);

        if (serverUrl == null || apiKey == null) {
            throw new RuntimeException(
                    "AI_SERVER_URL and AI_SERVER_API_KEY environment variables are required. " +
                            "Please set these before running the AI server tests."
            );
        }

        client = new JsonServiceClient(serverUrl);
        client.setBearerToken(apiKey);
    }

    public void test_Can_SpeechToText_with_File() throws IOException {
        // Skip if not running AI tests
        if (System.getenv(ENV_RUN_AI_TESTS) == null) return;

        // Create request DTO
        SpeechToText request = new SpeechToText();

        // Read test audio file from test resources
        File audioFile = new File("src/test/resources/aiserver/test_audio.wav");
        assertTrue("Test audio file must exist", audioFile.exists());

        byte[] fileBytes = Files.readAllBytes(audioFile.toPath());

        // Create file upload
        UploadFile upload = new UploadFile(
                "audio",              // field name
                "test_audio.wav",     // filename
                "audio/wav",          // content type
                fileBytes            // file bytes
        );

        // Send request with file
        GenerationResponse response = client.postFilesWithRequest(
                "/api/SpeechToText",
                request,
                new UploadFile[]{upload},
                GenerationResponse.class
        );

        // Verify response
        assertNotNull("Response should not be null", response);
        assertNotNull("Text outputs should not be null", response.getTextOutputs());
        assertTrue("Should have 2 text outputs", response.getTextOutputs().size() == 2);

        // Get both text outputs
        String textWithTimestamps = response.getTextOutputs().get(1).getText();
        String textOnly = response.getTextOutputs().get(0).getText();

        // Basic validation
        assertNotNull("Text with timestamps should not be null", textWithTimestamps);
        assertNotNull("Text only should not be null", textOnly);

        // Print results
        System.out.println("\nSpeech to Text Results:");
        System.out.println("Text with timestamps: " + textWithTimestamps);
        System.out.println("Text only: " + textOnly);
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        if (client != null) {
            // Clean up any resources if needed
        }
    }
}