package com.neutrinoapi.examples;

import com.google.gson.JsonObject;
import com.neutrinoapi.client.APIResponse;
import com.neutrinoapi.client.NeutrinoAPIClient;
import java.util.HashMap;
import java.util.Map;

public class GeocodeAddress {

    public static void main(String[] args) {

        NeutrinoAPIClient neutrinoAPI = new NeutrinoAPIClient("<your-user-id>", "<your-api-key>");
        Map<String, String> params = new HashMap<>();

        // The full address, partial address or name of a place to try and locate. Comma separated address
        // components are preferred.
        params.put("address", "1 Molesworth Street, Thorndon, Wellington 6011");

        // The house/building number to locate
        params.put("house-number", "");

        // The street/road name to locate
        params.put("street", "");

        // The city/town name to locate
        params.put("city", "");

        // The county/region name to locate
        params.put("county", "");

        // The state name to locate
        params.put("state", "");

        // The postal code to locate
        params.put("postal-code", "");

        // Limit result to this country (the default is no country bias)
        params.put("country-code", "");

        // The language to display results in, available languages are:
        // • de, en, es, fr, it, pt, ru, zh
        params.put("language-code", "en");

        // If no matches are found for the given address, start performing a recursive fuzzy search until a
        // geolocation is found. This option is recommended for processing user input or implementing
        // auto-complete. We use a combination of approximate string matching and data cleansing to find
        // possible location matches
        params.put("fuzzy-search", "false");

        APIResponse response = neutrinoAPI.geocodeAddress(params);
        if (response.getData().isPresent()) {
            JsonObject data = response.getData().get();
            System.out.println("API Response OK: ");
            
            // The number of possible matching locations found
            System.out.printf("found: %s%n", data.get("found"));
            
            // Array of matching location objects
            System.out.printf("locations:%n");
            data.getAsJsonArray("locations").forEach(jsonElement -> {
                JsonObject item = jsonElement.getAsJsonObject();
                System.out.printf("%n");
                // The fully formatted address
                System.out.printf("    address: %s%n", item.get("address"));
                // The components which make up the address such as road, city, state, etc
                System.out.printf("    address-components: %s%n", item.get("address-components"));
                // The city of the location
                System.out.printf("    city: %s%n", item.get("city"));
                // The country of the location
                System.out.printf("    country: %s%n", item.get("country"));
                // The ISO 2-letter country code of the location
                System.out.printf("    country-code: %s%n", item.get("country-code"));
                // The ISO 3-letter country code of the location
                System.out.printf("    country-code3: %s%n", item.get("country-code3"));
                // ISO 4217 currency code associated with the country
                System.out.printf("    currency-code: %s%n", item.get("currency-code"));
                // The location latitude
                System.out.printf("    latitude: %s%n", item.get("latitude"));
                // Array of strings containing any location tags associated with the address. Tags are additional
                // pieces of metadata about a specific location, there are thousands of different tags. Some
                // examples of tags: shop, office, cafe, bank, pub
                System.out.printf("    location-tags: %s%n", item.get("location-tags"));
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
                System.out.printf("    location-type: %s%n", item.get("location-type"));
                // The location longitude
                System.out.printf("    longitude: %s%n", item.get("longitude"));
                // The postal code for the location
                System.out.printf("    postal-code: %s%n", item.get("postal-code"));
                // The state of the location
                System.out.printf("    state: %s%n", item.get("state"));
                // Map containing timezone details for the location
                System.out.printf("    timezone: %s%n", item.get("timezone"));
            });
            
        } else {
            // API request failed, you should handle this gracefully!
            System.err.printf("API Error: %s, Error Code: %d, HTTP Status Code: %d%n", response.getErrorMessage(), response.getErrorCode(), response.getHttpStatusCode());
            response.getErrorCause().ifPresent(cause -> System.err.printf("Error Caused By: %s%n", cause));
        }
    }
}
