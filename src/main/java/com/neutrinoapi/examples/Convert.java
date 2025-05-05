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

        // Convert using the rate on a historical date, accepted date formats are: YYYY-MM-DD, YYYY-MM,
        // YYYY. Historical rates are stored with daily granularity so the date format YYYY-MM-DD is
        // preferred for the highest precision. If an invalid date or a date too far into the past is
        // supplied then the API will respond with 'valid' as false and an empty 'historical-date'
        params.put("historical-date", "");

        APIResponse response = neutrinoAPI.convert(params);
        if (response.getData().isPresent()) {
            JsonObject data = response.getData().get();
            System.out.println("API Response OK: ");
            
            // The full name of the type being converted from
            System.out.printf("from-name: %s%n", data.get("from-name"));
            
            // The standard UTF-8 symbol used to represent the type being converted from
            System.out.printf("from-symbol: %s%n", data.get("from-symbol"));
            
            // The type of the value being converted from
            System.out.printf("from-type: %s%n", data.get("from-type"));
            
            // The value being converted from
            System.out.printf("from-value: %s%n", data.get("from-value"));
            
            // If a historical conversion was made using the 'historical-date' request option this will contain
            // the exact date used for the conversion in ISO format: YYYY-MM-DD
            System.out.printf("historical-date: %s%n", data.get("historical-date"));
            
            // The result of the conversion in string format
            System.out.printf("result: %s%n", data.get("result"));
            
            // The result of the conversion as a floating-point number
            System.out.printf("result-float: %s%n", data.get("result-float"));
            
            // The full name of the type being converted to
            System.out.printf("to-name: %s%n", data.get("to-name"));
            
            // The standard UTF-8 symbol used to represent the type being converted to
            System.out.printf("to-symbol: %s%n", data.get("to-symbol"));
            
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
