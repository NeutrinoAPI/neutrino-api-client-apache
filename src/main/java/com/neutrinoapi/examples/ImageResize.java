package com.neutrinoapi.examples;

import com.neutrinoapi.client.APIResponse;
import com.neutrinoapi.client.NeutrinoAPIClient;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ImageResize {

    public static void main(String[] args) {

        Path tmpFile;
        try {
            tmpFile = Files.createTempFile("image-resize-", ".png");
        } catch (IOException ex) {
            System.err.printf("Failed to create temp file: %s%n", ex);
            return;
        }

        NeutrinoAPIClient neutrinoAPI = new NeutrinoAPIClient("<your-user-id>", "<your-api-key>");
        Map<String, String> params = new HashMap<>();

        // The width to resize to (in px) while preserving aspect ratio
        params.put("width", "32");

        // The output image format, can be either png or jpg
        params.put("format", "png");

        // The URL or Base64 encoded Data URL for the source image (you can also upload an image file
        // directly in which case this field is ignored)
        params.put("image-url", "https://www.neutrinoapi.com/img/LOGO.png");

        // The height to resize to (in px) while preserving aspect ratio. If you don't set this field then
        // the height will be automatic based on the requested width and images aspect ratio
        params.put("height", "32");

        APIResponse response = neutrinoAPI.imageResize(params, tmpFile);
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
