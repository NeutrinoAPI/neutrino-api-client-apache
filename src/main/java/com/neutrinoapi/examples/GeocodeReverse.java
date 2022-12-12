package com.neutrinoapi.examples;

import com.google.gson.JsonObject;
import com.neutrinoapi.client.APIResponse;
import com.neutrinoapi.client.NeutrinoAPIClient;
import java.util.HashMap;
import java.util.Map;

public class GeocodeReverse {

    public static void main(String[] args) {

        NeutrinoAPIClient neutrinoAPI = new NeutrinoAPIClient("<your-user-id>", "<your-api-key>");
        Map<String, String> params = new HashMap<>();

        // The location latitude in decimal degrees format
        params.put("latitude", "-41.2775847");

        // The location longitude in decimal degrees format
        params.put("longitude", "174.7775229");

        // The language to display results in, available languages are:
        // • de, en, es, fr, it, pt, ru
        params.put("language-code", "en");

        // The zoom level to respond with:
        // • address - the most precise address available
        // • street - the street level
        // • city - the city level
        // • state - the state level
        // • country - the country level
        params.put("zoom", "address");

        APIResponse response = neutrinoAPI.geocodeReverse(params);
        if (response.getData().isPresent()) {
            JsonObject data = response.getData().get();
            System.out.println("API Response OK: ");
            
            // The fully formatted address
            System.out.printf("address: %s%n", data.get("address"));
            
            // The components which make up the address such as road, city, state, etc
            System.out.printf("address-components: %s%n", data.get("address-components"));
            
            // The city of the location
            System.out.printf("city: %s%n", data.get("city"));
            
            // The country of the location
            System.out.printf("country: %s%n", data.get("country"));
            
            // The ISO 2-letter country code of the location
            System.out.printf("country-code: %s%n", data.get("country-code"));
            
            // The ISO 3-letter country code of the location
            System.out.printf("country-code3: %s%n", data.get("country-code3"));
            
            // ISO 4217 currency code associated with the country
            System.out.printf("currency-code: %s%n", data.get("currency-code"));
            
            // True if these coordinates map to a real location
            System.out.printf("found: %s%n", data.get("found"));
            
            // The location latitude
            System.out.printf("latitude: %s%n", data.get("latitude"));
            
            // Array of strings containing any location tags associated with the address. Tags are additional
            // pieces of metadata about a specific location, there are thousands of different tags. Some
            // examples of tags: shop, office, cafe, bank, pub
            System.out.printf("location-tags: %s%n", data.get("location-tags"));
            
            // The detected location type ordered roughly from most to least precise, possible values are:
            // • address - indicates a precise street address
            // • street - accurate to the street level but may not point to the exact location of the
            //   house/building number
            // • city - accurate to the city level, this includes villages, towns, suburbs, etc
            // • postal-code - indicates a postal code area (no house or street information present)
            // • railway - location is part of a rail network such as a station or railway track
            // • natural - indicates a natural feature, for example a mountain peak or a waterway
            // • island - location is an island or archipelago
            // • administrative - indicates an administrative boundary such as a country, state or province
            System.out.printf("location-type: %s%n", data.get("location-type"));
            
            // The location longitude
            System.out.printf("longitude: %s%n", data.get("longitude"));
            
            // The postal code for the location
            System.out.printf("postal-code: %s%n", data.get("postal-code"));
            
            // The state of the location
            System.out.printf("state: %s%n", data.get("state"));
            
            // Map containing timezone details for the location
            System.out.printf("timezone: %s%n", data.get("timezone"));
            
        } else {
            // API request failed, you should handle this gracefully!
            System.err.printf("API Error: %s, Error Code: %d, HTTP Status Code: %d%n", response.getErrorMessage(), response.getErrorCode(), response.getHttpStatusCode());
            response.getErrorCause().ifPresent(cause -> System.err.printf("Error Caused By: %s%n", cause));
        }
    }
}
