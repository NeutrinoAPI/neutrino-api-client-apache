package com.neutrinoapi.examples;

import com.google.gson.JsonObject;
import com.neutrinoapi.client.APIResponse;
import com.neutrinoapi.client.NeutrinoAPIClient;
import java.util.HashMap;
import java.util.Map;

public class BrowserBot {

    public static void main(String[] args) {

        NeutrinoAPIClient neutrinoAPI = new NeutrinoAPIClient("<your-user-id>", "<your-api-key>");
        Map<String, String> params = new HashMap<>();

        // Delay in seconds to wait before capturing any page data, executing selectors or JavaScript
        params.put("delay", "3");

        // Ignore any TLS/SSL certificate errors and load the page anyway
        params.put("ignore-certificate-errors", "false");

        // Extract content from the page DOM using this selector. Commonly known as a CSS selector, you can
        // find a good reference here
        params.put("selector", ".button");

        // The URL to load
        params.put("url", "https://www.neutrinoapi.com/");

        // Timeout in seconds. Give up if still trying to load the page after this number of seconds
        params.put("timeout", "30");

        // Execute JavaScript on the website. This parameter accepts JavaScript as either a string
        // containing JavaScript or for sending multiple separate statements a JSON array or POST array can
        // also be used. If a statement returns any value it will be returned in the 'exec-results'
        // response. You can also use the following specially defined user interaction functions:
        // sleep(seconds); Just wait/sleep for the specified number of seconds. click('selector'); Click on
        // the first element matching the given selector. focus('selector'); Focus on the first element
        // matching the given selector. keys('characters'); Send the specified keyboard characters. Use
        // click() or focus() first to send keys to a specific element. enter(); Send the Enter key. tab();
        // Send the Tab key.
        params.put("exec", "[click('#button-id'), sleep(1), click('.class'), keys('1234'), enter()]");

        // Override the browsers default user-agent string with this one
        params.put("user-agent", "");

        APIResponse response = neutrinoAPI.browserBot(params);
        if (response.getData().isPresent()) {
            JsonObject data = response.getData().get();
            System.out.println("API Response OK: ");
            
            // The complete raw, decompressed and decoded page content. Usually will be either HTML, JSON or XML
            System.out.printf("content: %s%n", data.get("content"));
            
            // The size of the returned content in bytes
            System.out.printf("content-size: %s%n", data.get("content-size"));
            
            // Array containing all the elements matching the supplied selector
            System.out.printf("elements:%n");
            data.getAsJsonArray("elements").forEach(jsonElement -> {
                JsonObject item = jsonElement.getAsJsonObject();
                System.out.printf("%n");
                // The 'class' attribute of the element
                System.out.printf("    class: %s%n", item.get("class"));
                // The 'href' attribute of the element
                System.out.printf("    href: %s%n", item.get("href"));
                // The raw HTML of the element
                System.out.printf("    html: %s%n", item.get("html"));
                // The 'id' attribute of the element
                System.out.printf("    id: %s%n", item.get("id"));
                // The plain-text content of the element with normalized whitespace
                System.out.printf("    text: %s%n", item.get("text"));
            });
            
            // Contains the error message if an error has occurred ('is-error' will be true)
            System.out.printf("error-message: %s%n", data.get("error-message"));
            
            // If you executed any JavaScript this array holds the results as objects
            System.out.printf("exec-results:%n");
            data.getAsJsonArray("exec-results").forEach(jsonElement -> {
                JsonObject item = jsonElement.getAsJsonObject();
                System.out.printf("%n");
                // The result of the executed JavaScript statement. Will be empty if the statement returned nothing
                System.out.printf("    result: %s%n", item.get("result"));
                // The JavaScript statement that was executed
                System.out.printf("    statement: %s%n", item.get("statement"));
            });
            
            // The redirected URL if the URL responded with an HTTP redirect
            System.out.printf("http-redirect-url: %s%n", data.get("http-redirect-url"));
            
            // The HTTP status code the URL returned
            System.out.printf("http-status-code: %s%n", data.get("http-status-code"));
            
            // The HTTP status message the URL returned
            System.out.printf("http-status-message: %s%n", data.get("http-status-message"));
            
            // True if an error has occurred loading the page. Check the 'error-message' field for details
            System.out.printf("is-error: %s%n", data.get("is-error"));
            
            // True if the HTTP status is OK (200)
            System.out.printf("is-http-ok: %s%n", data.get("is-http-ok"));
            
            // True if the URL responded with an HTTP redirect
            System.out.printf("is-http-redirect: %s%n", data.get("is-http-redirect"));
            
            // True if the page is secured using TLS/SSL
            System.out.printf("is-secure: %s%n", data.get("is-secure"));
            
            // True if a timeout occurred while loading the page. You can set the timeout with the request
            // parameter 'timeout'
            System.out.printf("is-timeout: %s%n", data.get("is-timeout"));
            
            // The ISO 2-letter language code of the page. Extracted from either the HTML document or via HTTP
            // headers
            System.out.printf("language-code: %s%n", data.get("language-code"));
            
            // The number of seconds taken to load the page (from initial request until DOM ready)
            System.out.printf("load-time: %s%n", data.get("load-time"));
            
            // The document MIME type
            System.out.printf("mime-type: %s%n", data.get("mime-type"));
            
            // Map containing all the HTTP response headers the URL responded with
            System.out.printf("response-headers: %s%n", data.get("response-headers"));
            
            // Map containing details of the TLS/SSL setup
            System.out.printf("security-details: %s%n", data.get("security-details"));
            
            // The HTTP servers hostname (PTR/RDNS record)
            System.out.printf("server-hostname: %s%n", data.get("server-hostname"));
            
            // The HTTP servers IP address
            System.out.printf("server-ip: %s%n", data.get("server-ip"));
            
            // The document title
            System.out.printf("title: %s%n", data.get("title"));
            
            // The requested URL. This may not be the same as the final destination URL, if the URL redirects
            // then it will be set in 'http-redirect-url' and 'is-http-redirect' will also be true
            System.out.printf("url: %s%n", data.get("url"));
            
            // Structure of url-components
            System.out.printf("url-components: %s%n", data.get("url-components"));
            
            // True if the URL supplied is valid
            System.out.printf("url-valid: %s%n", data.get("url-valid"));
            
        } else {
            // API request failed, you should handle this gracefully!
            System.err.printf("API Error: %s, Error Code: %d, HTTP Status Code: %d%n", response.getErrorMessage(), response.getErrorCode(), response.getHttpStatusCode());
            response.getErrorCause().ifPresent(cause -> System.err.printf("Error Caused By: %s%n", cause));
        }
    }
}
