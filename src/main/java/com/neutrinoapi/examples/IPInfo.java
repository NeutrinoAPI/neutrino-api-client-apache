package com.neutrinoapi.examples;

import com.google.gson.JsonObject;
import com.neutrinoapi.client.APIResponse;
import com.neutrinoapi.client.NeutrinoAPIClient;
import java.util.HashMap;
import java.util.Map;

public class IPInfo {

    public static void main(String[] args) {

        NeutrinoAPIClient neutrinoAPI = new NeutrinoAPIClient("<your-user-id>", "<your-api-key>");
        Map<String, String> params = new HashMap<>();

        // IPv4 or IPv6 address
        params.put("ip", "1.1.1.1");

        // Do a reverse DNS (PTR) lookup. This option can add extra delay to the request so only use it if
        // you need it
        params.put("reverse-lookup", "false");

        APIResponse response = neutrinoAPI.ipInfo(params);
        if (response.getData().isPresent()) {
            JsonObject data = response.getData().get();
            System.out.println("API Response OK: ");
            
            // Name of the city (if detectable)
            System.out.printf("city: %s%n", data.get("city"));
            
            // ISO 2-letter continent code
            System.out.printf("continent-code: %s%n", data.get("continent-code"));
            
            // Full country name
            System.out.printf("country: %s%n", data.get("country"));
            
            // ISO 2-letter country code
            System.out.printf("country-code: %s%n", data.get("country-code"));
            
            // ISO 3-letter country code
            System.out.printf("country-code3: %s%n", data.get("country-code3"));
            
            // ISO 4217 currency code associated with the country
            System.out.printf("currency-code: %s%n", data.get("currency-code"));
            
            // The IPs host domain (only set if reverse-lookup has been used)
            System.out.printf("host-domain: %s%n", data.get("host-domain"));
            
            // The IPs full hostname (only set if reverse-lookup has been used)
            System.out.printf("hostname: %s%n", data.get("hostname"));
            
            // The IP address
            System.out.printf("ip: %s%n", data.get("ip"));
            
            // True if this is a bogon IP address such as a private network, local network or reserved address
            System.out.printf("is-bogon: %s%n", data.get("is-bogon"));
            
            // True if this is a IPv4 mapped IPv6 address
            System.out.printf("is-v4-mapped: %s%n", data.get("is-v4-mapped"));
            
            // True if this is a IPv6 address. False if IPv4
            System.out.printf("is-v6: %s%n", data.get("is-v6"));
            
            // Location latitude
            System.out.printf("latitude: %s%n", data.get("latitude"));
            
            // Location longitude
            System.out.printf("longitude: %s%n", data.get("longitude"));
            
            // Name of the region (if detectable)
            System.out.printf("region: %s%n", data.get("region"));
            
            // ISO 3166-2 region code (if detectable)
            System.out.printf("region-code: %s%n", data.get("region-code"));
            
            // Map containing timezone details for the location
            System.out.printf("timezone: %s%n", data.get("timezone"));
            
            // True if this is a valid IPv4 or IPv6 address
            System.out.printf("valid: %s%n", data.get("valid"));
            
        } else {
            // API request failed, you should handle this gracefully!
            System.err.printf("API Error: %s, Error Code: %d, HTTP Status Code: %d%n", response.getErrorMessage(), response.getErrorCode(), response.getHttpStatusCode());
            response.getErrorCause().ifPresent(cause -> System.err.printf("Error Caused By: %s%n", cause));
        }
    }
}
