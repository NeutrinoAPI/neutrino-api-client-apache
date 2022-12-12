package com.neutrinoapi.examples;

import com.neutrinoapi.client.APIResponse;
import com.neutrinoapi.client.NeutrinoAPIClient;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class HTMLRender {

    public static void main(String[] args) {

        Path tmpFile;
        try {
            tmpFile = Files.createTempFile("html-render-", ".pdf");
        } catch (IOException ex) {
            System.err.printf("Failed to create temp file: %s%n", ex);
            return;
        }

        NeutrinoAPIClient neutrinoAPI = new NeutrinoAPIClient("<your-user-id>", "<your-api-key>");
        Map<String, String> params = new HashMap<>();

        // The document margin (in mm)
        params.put("margin", "0");

        // Inject custom CSS into the HTML. e.g. 'body { background-color: red;}'
        params.put("css", "");

        // If rendering to an image format (PNG or JPG) use this image width (in pixels)
        params.put("image-width", "1024");

        // The footer HTML to insert into each page. The following dynamic tags are supported: {date},
        // {title}, {url}, {pageNumber}, {totalPages}
        params.put("footer", "");

        // Which format to output, available options are: PDF, PNG, JPG
        params.put("format", "PDF");

        // Set the zoom factor when rendering the page (2.0 for double size, 0.5 for half size)
        params.put("zoom", "1");

        // The document title
        params.put("title", "");

        // The HTML content. This can be either a URL to load from, a file upload or an HTML content string
        params.put("content", "<h1>TEST DOCUMENT</h1><p>Hello, this is a test page...</p>");

        // Set the PDF page width explicitly (in mm)
        params.put("page-width", "");

        // Timeout in seconds. Give up if still trying to load the HTML content after this number of seconds
        params.put("timeout", "300");

        // The document right margin (in mm)
        params.put("margin-right", "0");

        // Render the final document in grayscale
        params.put("grayscale", "false");

        // The document left margin (in mm)
        params.put("margin-left", "0");

        // Set the document page size, can be one of: A0 - A9, B0 - B10, Comm10E, DLE or Letter
        params.put("page-size", "A4");

        // Number of seconds to wait before rendering the page (can be useful for pages with animations etc)
        params.put("delay", "0");

        // Ignore any TLS/SSL certificate errors
        params.put("ignore-certificate-errors", "false");

        // Set the PDF page height explicitly (in mm)
        params.put("page-height", "");

        // If rendering to an image format (PNG or JPG) use this image height (in pixels). The default is
        // automatic which dynamically sets the image height based on the content
        params.put("image-height", "");

        // The header HTML to insert into each page. The following dynamic tags are supported: {date},
        // {title}, {url}, {pageNumber}, {totalPages}
        params.put("header", "<div style='width: 100%; font-size: 8pt;'>{pageNumber} of {totalPages} - {date}</div>");

        // The document top margin (in mm)
        params.put("margin-top", "0");

        // The document bottom margin (in mm)
        params.put("margin-bottom", "0");

        // Set the document to landscape orientation
        params.put("landscape", "false");

        APIResponse response = neutrinoAPI.htmlRender(params, tmpFile);
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
