package com.neutrinoapi.examples;

import com.google.gson.JsonObject;
import com.neutrinoapi.client.APIResponse;
import com.neutrinoapi.client.NeutrinoAPIClient;
import java.util.HashMap;
import java.util.Map;

public class VerifySecurityCode {

    public static void main(String[] args) {

        NeutrinoAPIClient neutrinoAPI = new NeutrinoAPIClient("<your-user-id>", "<your-api-key>");
        Map<String, String> params = new HashMap<>();

        // The security code to verify
        params.put("security-code", "123456");

        // If set then enable additional brute-force protection by limiting the number of attempts by the
        // supplied value. This can be set to any unique identifier you would like to limit by, for example
        // a hash of the users email, phone number or IP address. Requests to this API will be ignored after
        // approximately 10 failed verification attempts
        params.put("limit-by", "");

        APIResponse response = neutrinoAPI.verifySecurityCode(params);
        if (response.getData().isPresent()) {
            JsonObject data = response.getData().get();
            System.out.println("API Response OK: ");
            
            // True if the code is valid
            System.out.printf("verified: %s%n", data.get("verified"));
            
        } else {
            // API request failed, you should handle this gracefully!
            System.err.printf("API Error: %s, Error Code: %d, HTTP Status Code: %d%n", response.getErrorMessage(), response.getErrorCode(), response.getHttpStatusCode());
            response.getErrorCause().ifPresent(cause -> System.err.printf("Error Caused By: %s%n", cause));
        }
    }
}
