package com.neutrinoapi.examples;

import com.google.gson.JsonObject;
import com.neutrinoapi.client.APIResponse;
import com.neutrinoapi.client.NeutrinoAPIClient;
import java.util.HashMap;
import java.util.Map;

public class BINLookup {

    public static void main(String[] args) {

        NeutrinoAPIClient neutrinoAPI = new NeutrinoAPIClient("<your-user-id>", "<your-api-key>");
        Map<String, String> params = new HashMap<>();

        // The BIN or IIN number. This is the first 6, 8 or 10 digits of a card number, use 8 (or more)
        // digits for the highest level of accuracy
        params.put("bin-number", "47192100");

        // Pass in the customers IP address and we will return some extra information about them
        params.put("customer-ip", "");

        APIResponse response = neutrinoAPI.binLookup(params);
        if (response.getData().isPresent()) {
            JsonObject data = response.getData().get();
            System.out.println("API Response OK: ");
            
            // The BIN or IIN number
            System.out.printf("bin-number: %s%n", data.get("bin-number"));
            
            // The card brand (e.g. Visa or Mastercard)
            System.out.printf("card-brand: %s%n", data.get("card-brand"));
            
            // The card category. There are many different card categories the most common card categories are:
            // CLASSIC, BUSINESS, CORPORATE, PLATINUM, PREPAID
            System.out.printf("card-category: %s%n", data.get("card-category"));
            
            // The card type, will always be one of: DEBIT, CREDIT, CHARGE CARD
            System.out.printf("card-type: %s%n", data.get("card-type"));
            
            // The full country name of the issuer
            System.out.printf("country: %s%n", data.get("country"));
            
            // The ISO 2-letter country code of the issuer
            System.out.printf("country-code: %s%n", data.get("country-code"));
            
            // The ISO 3-letter country code of the issuer
            System.out.printf("country-code3: %s%n", data.get("country-code3"));
            
            // ISO 4217 currency code associated with the country of the issuer
            System.out.printf("currency-code: %s%n", data.get("currency-code"));
            
            // True if the customers IP is listed on one of our blocklists, see the IP Blocklist API
            System.out.printf("ip-blocklisted: %s%n", data.get("ip-blocklisted"));
            
            // An array of strings indicating which blocklists this IP is listed on
            System.out.printf("ip-blocklists: %s%n", data.get("ip-blocklists"));
            
            // The city of the customers IP (if detectable)
            System.out.printf("ip-city: %s%n", data.get("ip-city"));
            
            // The country of the customers IP
            System.out.printf("ip-country: %s%n", data.get("ip-country"));
            
            // The ISO 2-letter country code of the customers IP
            System.out.printf("ip-country-code: %s%n", data.get("ip-country-code"));
            
            // The ISO 3-letter country code of the customers IP
            System.out.printf("ip-country-code3: %s%n", data.get("ip-country-code3"));
            
            // True if the customers IP country matches the BIN country
            System.out.printf("ip-matches-bin: %s%n", data.get("ip-matches-bin"));
            
            // The region of the customers IP (if detectable)
            System.out.printf("ip-region: %s%n", data.get("ip-region"));
            
            // Is this a commercial/business use card
            System.out.printf("is-commercial: %s%n", data.get("is-commercial"));
            
            // Is this a prepaid or prepaid reloadable card
            System.out.printf("is-prepaid: %s%n", data.get("is-prepaid"));
            
            // The card issuer
            System.out.printf("issuer: %s%n", data.get("issuer"));
            
            // The card issuers phone number
            System.out.printf("issuer-phone: %s%n", data.get("issuer-phone"));
            
            // The card issuers website
            System.out.printf("issuer-website: %s%n", data.get("issuer-website"));
            
            // Is this a valid BIN or IIN number
            System.out.printf("valid: %s%n", data.get("valid"));
            
        } else {
            // API request failed, you should handle this gracefully!
            System.err.printf("API Error: %s, Error Code: %d, HTTP Status Code: %d%n", response.getErrorMessage(), response.getErrorCode(), response.getHttpStatusCode());
            response.getErrorCause().ifPresent(cause -> System.err.printf("Error Caused By: %s%n", cause));
        }
    }
}
