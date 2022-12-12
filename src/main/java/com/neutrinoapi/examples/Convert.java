package com.neutrinoapi.examples;

import com.google.gson.JsonObject;
import com.neutrinoapi.client.APIResponse;
import com.neutrinoapi.client.NeutrinoAPIClient;
import java.util.HashMap;
import java.util.Map;

public class Convert {

    public static void main(String[] args) {

        NeutrinoAPIClient neutrinoAPI = new NeutrinoAPIClient("<your-user-id>", "<your-api-key>");
        Map<String, String> params = new HashMap<>();

        // The value to convert from (e.g. 10.95)
        params.put("from-value", "100");

        // The type of the value to convert from (e.g. USD)
        params.put("from-type", "USD");

        // The type to convert to (e.g. EUR)
        params.put("to-type", "EUR");

        APIResponse response = neutrinoAPI.convert(params);
        if (response.getData().isPresent()) {
            JsonObject data = response.getData().get();
            System.out.println("API Response OK: ");
            
            // The type of the value being converted from
            System.out.printf("from-type: %s%n", data.get("from-type"));
            
            // The value being converted from
            System.out.printf("from-value: %s%n", data.get("from-value"));
            
            // The result of the conversion in string format
            System.out.printf("result: %s%n", data.get("result"));
            
            // The result of the conversion as a floating-point number
            System.out.printf("result-float: %s%n", data.get("result-float"));
            
            // The type being converted to
            System.out.printf("to-type: %s%n", data.get("to-type"));
            
            // True if the conversion was successful and produced a valid result
            System.out.printf("valid: %s%n", data.get("valid"));
            
        } else {
            // API request failed, you should handle this gracefully!
            System.err.printf("API Error: %s, Error Code: %d, HTTP Status Code: %d%n", response.getErrorMessage(), response.getErrorCode(), response.getHttpStatusCode());
            response.getErrorCause().ifPresent(cause -> System.err.printf("Error Caused By: %s%n", cause));
        }
    }
}
