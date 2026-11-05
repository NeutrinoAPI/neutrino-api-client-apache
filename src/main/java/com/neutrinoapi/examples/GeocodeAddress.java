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

        // The house/building number to locate (for structured address searches)
        params.put("house-number", "");

        // The street/road name to locate (for structured address searches)
        params.put("street", "");

        // The city/town name to locate (for structured address searches)
        params.put("city", "");

        // The county/region name to locate (for structured address searches)
        params.put("county", "");

        // The state name to locate (for structured address searches)
        params.put("state", "");

        // The postal code to locate (for structured address searches)
        params.put("postal-code", "");

        // Prefer results from this country code (the default is no country bias)
        params.put("country-code", "");

        // Prefer results from this list of country codes (space or comma separated values). Preference is
        // in the order supplied, you can use the 'country-code' parameter to also set a dynamic or user
        // supplied country code which takes precedence
        params.put("country-codes", "");

        // The language to display results in, available languages are:
        // • ar, de, en, es, fr, it, ja, nl, pt, ru, zh
        params.put("language-code", "en");

        // Enable auto-complete for search-as-you-type and search suggestions. This option can find
        // addresses based on partial search terms, combine with 'country-code' for country specific
        // searches. Auto-complete works best with 'fuzzy-search' disabled for the fastest response times
        // but can be combined if required
        params.put("auto-complete", "false");

        // If no matches are found for the given address, start performing a recursive fuzzy search until a
        // geolocation is found. This option is useful for processing user supplied input that may be
        // incomplete or have typos and where you do not mind some potential loss in precision
        params.put("fuzzy-search", "false");

        // Limit search results to this many locations (between 1 and a maximum of 32)
        params.put("limit", "8");

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
                // The full address string using comma-separated values, use 'postal-address' for the address string
                // formatted using country/region specific rules
                System.out.printf("    address: %s%n", item.get("address"));
                // The components which make up the address such as road, city, state, etc. These are country/region
                // specific values, use 'address-structured' for a strict address structure which is the same for
                // all locations globally
                System.out.printf("    address-components: %s%n", item.get("address-components"));
                // Structure of address-structured
                System.out.printf("    address-structured: %s%n", item.get("address-structured"));
                // The detected building type for the address, possible values are:
                // • none - no building type was detected for this location
                // • house - a residential house/property
                // • apartment - a residential apartment complex
                // • office - a commercial/business office
                // • accommodation - a hotel/motel or other type of accommodation
                // • education - a school or university
                // • food - a restaurant or place where food can be purchased
                // • shop - a shop selling any type of goods
                // • attraction - an attraction such as theme parks or tourist activities
                // • medical - a hospital or other medical facility
                // • transport - a transportation building such as a bus or train depot
                // • commercial - a commercial/industrial building
                // • government - a government or military facility
                // • public - a public building such as a townhall or community centre
                System.out.printf("    building-type: %s%n", item.get("building-type"));
                // The city of the location
                System.out.printf("    city: %s%n", item.get("city"));
                // ISO 2-letter continent code
                System.out.printf("    continent-code: %s%n", item.get("continent-code"));
                // The country of the location
                System.out.printf("    country: %s%n", item.get("country"));
                // The ISO 2-letter country code of the location
                System.out.printf("    country-code: %s%n", item.get("country-code"));
                // The ISO 3-letter country code of the location
                System.out.printf("    country-code3: %s%n", item.get("country-code3"));
                // ISO 4217 currency code associated with the country
                System.out.printf("    currency-code: %s%n", item.get("currency-code"));
                // The location geohash
                System.out.printf("    geohash: %s%n", item.get("geohash"));
                // The ISO 2-letter language code for the official language spoken in the country
                System.out.printf("    language-code: %s%n", item.get("language-code"));
                // The location latitude
                System.out.printf("    latitude: %s%n", item.get("latitude"));
                // Map containing any external metadata linked to the location. Currently supports the following
                // values which are only set if known: website-url, phone-number, org-name, wikipedia-title,
                // wikidata-entity
                System.out.printf("    location-profile: %s%n", item.get("location-profile"));
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
                // The formatted address using local standards suitable for printing on an envelope
                System.out.printf("    postal-address: %s%n", item.get("postal-address"));
                // The postal code for the location
                System.out.printf("    postal-code: %s%n", item.get("postal-code"));
                // The ISO 3166-2 region code for the location
                System.out.printf("    region-code: %s%n", item.get("region-code"));
                // The state of the location
                System.out.printf("    state: %s%n", item.get("state"));
                // Structure of timezone
                System.out.printf("    timezone: %s%n", item.get("timezone"));
            });
            
        } else {
            // API request failed, you should handle this gracefully!
            System.err.printf("API Error: %s, Error Code: %d, HTTP Status Code: %d%n", response.getErrorMessage(), response.getErrorCode(), response.getHttpStatusCode());
            response.getErrorCause().ifPresent(cause -> System.err.printf("Error Caused By: %s%n", cause));
        }
    }
}
