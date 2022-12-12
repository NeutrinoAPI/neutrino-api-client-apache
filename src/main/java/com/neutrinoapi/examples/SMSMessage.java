package com.neutrinoapi.examples;

import com.google.gson.JsonObject;
import com.neutrinoapi.client.APIResponse;
import com.neutrinoapi.client.NeutrinoAPIClient;
import java.util.HashMap;
import java.util.Map;

public class SMSMessage {

    public static void main(String[] args) {

        NeutrinoAPIClient neutrinoAPI = new NeutrinoAPIClient("<your-user-id>", "<your-api-key>");
        Map<String, String> params = new HashMap<>();

        // The phone number to send a message to
        params.put("number", "+12106100045");

        // ISO 2-letter country code, assume numbers are based in this country. If not set numbers are
        // assumed to be in international format (with or without the leading + sign)
        params.put("country-code", "");

        // Limit the total number of SMS allowed to the supplied phone number, if the limit is reached
        // within the TTL then error code 14 will be returned
        params.put("limit", "10");

        // The SMS message to send. Messages are truncated to a maximum of 150 characters for ASCII content
        // OR 70 characters for UTF content
        params.put("message", "Hello, this is a test message!");

        // Set the TTL in number of days that the 'limit' option will remember a phone number (the default
        // is 1 day and the maximum is 365 days)
        params.put("limit-ttl", "1");

        APIResponse response = neutrinoAPI.smsMessage(params);
        if (response.getData().isPresent()) {
            JsonObject data = response.getData().get();
            System.out.println("API Response OK: ");
            
            // True if this a valid phone number
            System.out.printf("number-valid: %s%n", data.get("number-valid"));
            
            // True if the SMS has been sent
            System.out.printf("sent: %s%n", data.get("sent"));
            
        } else {
            // API request failed, you should handle this gracefully!
            System.err.printf("API Error: %s, Error Code: %d, HTTP Status Code: %d%n", response.getErrorMessage(), response.getErrorCode(), response.getHttpStatusCode());
            response.getErrorCause().ifPresent(cause -> System.err.printf("Error Caused By: %s%n", cause));
        }
    }
}
