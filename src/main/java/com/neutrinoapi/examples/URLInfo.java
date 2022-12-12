package com.neutrinoapi.examples;

import com.google.gson.JsonObject;
import com.neutrinoapi.client.APIResponse;
import com.neutrinoapi.client.NeutrinoAPIClient;
import java.util.HashMap;
import java.util.Map;

public class URLInfo {

    public static void main(String[] args) {

        NeutrinoAPIClient neutrinoAPI = new NeutrinoAPIClient("<your-user-id>", "<your-api-key>");
        Map<String, String> params = new HashMap<>();

        // The URL to probe
        params.put("url", "https://www.neutrinoapi.com/");

        // If this URL responds with html, text, json or xml then return the response. This option is useful
        // if you want to perform further processing on the URL content (e.g. with the HTML Extract or HTML
        // Clean APIs)
        params.put("fetch-content", "false");

        // Ignore any TLS/SSL certificate errors and load the URL anyway
        params.put("ignore-certificate-errors", "false");

        // Timeout in seconds. Give up if still trying to load the URL after this number of seconds
        params.put("timeout", "60");

        // If the request fails for any reason try again this many times
        params.put("retry", "0");

        APIResponse response = neutrinoAPI.urlInfo(params);
        if (response.getData().isPresent()) {
            JsonObject data = response.getData().get();
            System.out.println("API Response OK: ");
            
            // The actual content this URL responded with. Only set if the 'fetch-content' option was used
            System.out.printf("content: %s%n", data.get("content"));
            
            // The encoding format the URL uses
            System.out.printf("content-encoding: %s%n", data.get("content-encoding"));
            
            // The size of the URL content in bytes
            System.out.printf("content-size: %s%n", data.get("content-size"));
            
            // The content-type this URL serves
            System.out.printf("content-type: %s%n", data.get("content-type"));
            
            // True if this URL responded with an HTTP OK (200) status
            System.out.printf("http-ok: %s%n", data.get("http-ok"));
            
            // True if this URL responded with an HTTP redirect
            System.out.printf("http-redirect: %s%n", data.get("http-redirect"));
            
            // The HTTP status code this URL responded with. An HTTP status of 0 indicates a network level issue
            System.out.printf("http-status: %s%n", data.get("http-status"));
            
            // The HTTP status message assoicated with the status code
            System.out.printf("http-status-message: %s%n", data.get("http-status-message"));
            
            // True if an error occurred while loading the URL. This includes network errors, TLS errors and
            // timeouts
            System.out.printf("is-error: %s%n", data.get("is-error"));
            
            // True if a timeout occurred while loading the URL. You can set the timeout with the request
            // parameter 'timeout'
            System.out.printf("is-timeout: %s%n", data.get("is-timeout"));
            
            // The ISO 2-letter language code of the page. Extracted from either the HTML document or via HTTP
            // headers
            System.out.printf("language-code: %s%n", data.get("language-code"));
            
            // The time taken to load the URL content in seconds
            System.out.printf("load-time: %s%n", data.get("load-time"));
            
            // A key-value map of the URL query paramaters
            System.out.printf("query: %s%n", data.get("query"));
            
            // Is this URL actually serving real content
            System.out.printf("real: %s%n", data.get("real"));
            
            // The servers IP geo-location: full city name (if detectable)
            System.out.printf("server-city: %s%n", data.get("server-city"));
            
            // The servers IP geo-location: full country name
            System.out.printf("server-country: %s%n", data.get("server-country"));
            
            // The servers IP geo-location: ISO 2-letter country code
            System.out.printf("server-country-code: %s%n", data.get("server-country-code"));
            
            // The servers hostname (PTR record)
            System.out.printf("server-hostname: %s%n", data.get("server-hostname"));
            
            // The IP address of the server hosting this URL
            System.out.printf("server-ip: %s%n", data.get("server-ip"));
            
            // The name of the server software hosting this URL
            System.out.printf("server-name: %s%n", data.get("server-name"));
            
            // The servers IP geo-location: full region name (if detectable)
            System.out.printf("server-region: %s%n", data.get("server-region"));
            
            // The document title
            System.out.printf("title: %s%n", data.get("title"));
            
            // The fully qualified URL. This may be different to the URL requested if http-redirect is true
            System.out.printf("url: %s%n", data.get("url"));
            
            // The URL path
            System.out.printf("url-path: %s%n", data.get("url-path"));
            
            // The URL port
            System.out.printf("url-port: %s%n", data.get("url-port"));
            
            // The URL protocol, usually http or https
            System.out.printf("url-protocol: %s%n", data.get("url-protocol"));
            
            // Is this a valid well-formed URL
            System.out.printf("valid: %s%n", data.get("valid"));
            
        } else {
            // API request failed, you should handle this gracefully!
            System.err.printf("API Error: %s, Error Code: %d, HTTP Status Code: %d%n", response.getErrorMessage(), response.getErrorCode(), response.getHttpStatusCode());
            response.getErrorCause().ifPresent(cause -> System.err.printf("Error Caused By: %s%n", cause));
        }
    }
}
