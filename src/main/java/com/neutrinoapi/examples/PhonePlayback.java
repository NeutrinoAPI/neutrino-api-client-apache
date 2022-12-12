package com.neutrinoapi.examples;

import com.google.gson.JsonObject;
import com.neutrinoapi.client.APIResponse;
import com.neutrinoapi.client.NeutrinoAPIClient;
import java.util.HashMap;
import java.util.Map;

public class PhonePlayback {

    public static void main(String[] args) {

        NeutrinoAPIClient neutrinoAPI = new NeutrinoAPIClient("<your-user-id>", "<your-api-key>");
        Map<String, String> params = new HashMap<>();

        // The phone number to call. Must be in valid international format
        params.put("number", "+12106100045");

        // Limit the total number of calls allowed to the supplied phone number, if the limit is reached
        // within the TTL then error code 14 will be returned
        params.put("limit", "3");

        // A URL to a valid audio file. Accepted audio formats are:
        // • MP3
        // • WAV
        // • OGG You can use the following MP3 URL for testing:
        //   https://www.neutrinoapi.com/test-files/test1.mp3
        params.put("audio-url", "https://www.neutrinoapi.com/test-files/test1.mp3");

        // Set the TTL in number of days that the 'limit' option will remember a phone number (the default
        // is 1 day and the maximum is 365 days)
        params.put("limit-ttl", "1");

        APIResponse response = neutrinoAPI.phonePlayback(params);
        if (response.getData().isPresent()) {
            JsonObject data = response.getData().get();
            System.out.println("API Response OK: ");
            
            // True if the call is being made now
            System.out.printf("calling: %s%n", data.get("calling"));
            
            // True if this a valid phone number
            System.out.printf("number-valid: %s%n", data.get("number-valid"));
            
        } else {
            // API request failed, you should handle this gracefully!
            System.err.printf("API Error: %s, Error Code: %d, HTTP Status Code: %d%n", response.getErrorMessage(), response.getErrorCode(), response.getHttpStatusCode());
            response.getErrorCause().ifPresent(cause -> System.err.printf("Error Caused By: %s%n", cause));
        }
    }
}
