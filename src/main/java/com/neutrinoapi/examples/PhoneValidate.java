package com.neutrinoapi.examples;

import com.google.gson.JsonObject;
import com.neutrinoapi.client.APIResponse;
import com.neutrinoapi.client.NeutrinoAPIClient;
import java.util.HashMap;
import java.util.Map;

public class PhoneValidate {

    public static void main(String[] args) {

        NeutrinoAPIClient neutrinoAPI = new NeutrinoAPIClient("<your-user-id>", "<your-api-key>");
        Map<String, String> params = new HashMap<>();

        // A phone number. This can be in international format (E.164) or local format. If passing local
        // format you must also set either the 'country-code' OR 'ip' options as well
        params.put("number", "+6495552000");

        // ISO 2-letter country code, assume numbers are based in this country. If not set numbers are
        // assumed to be in international format (with or without the leading + sign)
        params.put("country-code", "");

        // Pass in a users IP address and we will assume numbers are based in the country of the IP address
        params.put("ip", "");

        APIResponse response = neutrinoAPI.phoneValidate(params);
        if (response.getData().isPresent()) {
            JsonObject data = response.getData().get();
            System.out.println("API Response OK: ");
            
            // The phone number country
            System.out.printf("country: %s%n", data.get("country"));
            
            // The phone number country as an ISO 2-letter country code
            System.out.printf("country-code: %s%n", data.get("country-code"));
            
            // The phone number country as an ISO 3-letter country code
            System.out.printf("country-code3: %s%n", data.get("country-code3"));
            
            // ISO 4217 currency code associated with the country
            System.out.printf("currency-code: %s%n", data.get("currency-code"));
            
            // The international calling code
            System.out.printf("international-calling-code: %s%n", data.get("international-calling-code"));
            
            // The number represented in full international format (E.164)
            System.out.printf("international-number: %s%n", data.get("international-number"));
            
            // True if this is a mobile number. If the number type is unknown this value will be false
            System.out.printf("is-mobile: %s%n", data.get("is-mobile"));
            
            // The number represented in local dialing format
            System.out.printf("local-number: %s%n", data.get("local-number"));
            
            // The phone number location. Could be the city, region or country depending on the type of number
            System.out.printf("location: %s%n", data.get("location"));
            
            // The network/carrier who owns the prefix (this only works for some countries, use HLR lookup for
            // global network detection)
            System.out.printf("prefix-network: %s%n", data.get("prefix-network"));
            
            // The number type based on the number prefix. Possible values are:
            // • mobile
            // • fixed-line
            // • premium-rate
            // • toll-free
            // • voip
            // • unknown (use HLR lookup)
            System.out.printf("type: %s%n", data.get("type"));
            
            // Is this a valid phone number
            System.out.printf("valid: %s%n", data.get("valid"));
            
        } else {
            // API request failed, you should handle this gracefully!
            System.err.printf("API Error: %s, Error Code: %d, HTTP Status Code: %d%n", response.getErrorMessage(), response.getErrorCode(), response.getHttpStatusCode());
            response.getErrorCause().ifPresent(cause -> System.err.printf("Error Caused By: %s%n", cause));
        }
    }
}
