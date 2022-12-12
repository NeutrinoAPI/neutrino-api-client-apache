package com.neutrinoapi.examples;

import com.google.gson.JsonObject;
import com.neutrinoapi.client.APIResponse;
import com.neutrinoapi.client.NeutrinoAPIClient;
import java.util.HashMap;
import java.util.Map;

public class EmailValidate {

    public static void main(String[] args) {

        NeutrinoAPIClient neutrinoAPI = new NeutrinoAPIClient("<your-user-id>", "<your-api-key>");
        Map<String, String> params = new HashMap<>();

        // An email address
        params.put("email", "tech@neutrinoapi.com");

        // Automatically attempt to fix typos in the address
        params.put("fix-typos", "false");

        APIResponse response = neutrinoAPI.emailValidate(params);
        if (response.getData().isPresent()) {
            JsonObject data = response.getData().get();
            System.out.println("API Response OK: ");
            
            // The email domain
            System.out.printf("domain: %s%n", data.get("domain"));
            
            // True if this address has a domain error (e.g. no valid mail server records)
            System.out.printf("domain-error: %s%n", data.get("domain-error"));
            
            // The email address. If you have used the fix-typos option then this will be the fixed address
            System.out.printf("email: %s%n", data.get("email"));
            
            // True if this address is a disposable, temporary or darknet related email address
            System.out.printf("is-disposable: %s%n", data.get("is-disposable"));
            
            // True if this address is a free-mail address
            System.out.printf("is-freemail: %s%n", data.get("is-freemail"));
            
            // True if this address belongs to a person. False if this is a role based address, e.g. admin@,
            // help@, office@, etc.
            System.out.printf("is-personal: %s%n", data.get("is-personal"));
            
            // The email service provider domain
            System.out.printf("provider: %s%n", data.get("provider"));
            
            // True if this address has a syntax error
            System.out.printf("syntax-error: %s%n", data.get("syntax-error"));
            
            // True if typos have been fixed
            System.out.printf("typos-fixed: %s%n", data.get("typos-fixed"));
            
            // Is this a valid email
            System.out.printf("valid: %s%n", data.get("valid"));
            
        } else {
            // API request failed, you should handle this gracefully!
            System.err.printf("API Error: %s, Error Code: %d, HTTP Status Code: %d%n", response.getErrorMessage(), response.getErrorCode(), response.getHttpStatusCode());
            response.getErrorCause().ifPresent(cause -> System.err.printf("Error Caused By: %s%n", cause));
        }
    }
}
