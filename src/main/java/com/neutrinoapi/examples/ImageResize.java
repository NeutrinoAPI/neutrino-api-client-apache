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

        // The resize mode to use, we support 3 main resizing modes:
        // • scale Resize to within the width and height specified while preserving aspect ratio. In this
        //   mode the width or height will be automatically adjusted to fit the aspect ratio
        // • pad Resize to exactly the width and height specified while preserving aspect ratio and pad
        //   any space left over. Any padded space will be filled in with the 'bg-color' value
        // • crop Resize to exactly the width and height specified while preserving aspect ratio and crop
        //   any space which fall outside the area. The cropping window is centered on the original image
        params.put("resize-mode", "scale");

        // The width to resize to (in px)
        params.put("width", "32");

        // The output image format, can be either png or jpg
        params.put("format", "png");

        // The URL or Base64 encoded Data URL for the source image. You can also upload an image file
        // directly using multipart/form-data
        params.put("image-url", "https://www.neutrinoapi.com/img/LOGO.png");

        // The image background color in hexadecimal notation (e.g. #0000ff). For PNG output the special
        // value of 'transparent' can also be used. For JPG output the default is black (#000000)
        params.put("bg-color", "transparent");

        // The height to resize to (in px). If you don't set this field then the height will be automatic
        // based on the requested width and image aspect ratio
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
