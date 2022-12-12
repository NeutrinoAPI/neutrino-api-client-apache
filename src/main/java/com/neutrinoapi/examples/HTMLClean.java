package com.neutrinoapi.examples;

import com.neutrinoapi.client.APIResponse;
import com.neutrinoapi.client.NeutrinoAPIClient;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class HTMLClean {

    public static void main(String[] args) {

        Path tmpFile;
        try {
            tmpFile = Files.createTempFile("html-clean-", ".txt");
        } catch (IOException ex) {
            System.err.printf("Failed to create temp file: %s%n", ex);
            return;
        }

        NeutrinoAPIClient neutrinoAPI = new NeutrinoAPIClient("<your-user-id>", "<your-api-key>");
        Map<String, String> params = new HashMap<>();

        // The level of sanitization, possible values are: plain-text: reduce the content to plain text only
        // (no HTML tags at all) simple-text: allow only very basic text formatting tags like b, em, i,
        // strong, u basic-html: allow advanced text formatting and hyper links basic-html-with-images: same
        // as basic html but also allows image tags advanced-html: same as basic html with images but also
        // allows many more common HTML tags like table, ul, dl, pre
        params.put("output-type", "plain-text");

        // The HTML content. This can be either a URL to load from, a file upload or an HTML content string
        params.put("content", "<div>Some HTML to clean...</div><script>alert()</script>");

        APIResponse response = neutrinoAPI.htmlClean(params, tmpFile);
        if (response.getFile().isPresent()) {
            Path outputFile = response.getFile().get();
            // API request successful, print out the file path
            System.out.printf("API Response OK, output saved to: %s, content type: %s%n", outputFile, response.getContentType());
        } else {
            // API request failed, you should handle this gracefully!
            System.err.printf("API Error: %s, Error Code: %d, HTTP Status Code: %d%n", response.getErrorMessage(), response.getErrorCode(), response.getHttpStatusCode());
            response.getErrorCause().ifPresent(cause -> System.err.printf("Error Caused By: %s%n", cause));
        }
    }
}
