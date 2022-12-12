package com.neutrinoapi.examples;

import com.google.gson.JsonObject;
import com.neutrinoapi.client.APIResponse;
import com.neutrinoapi.client.NeutrinoAPIClient;
import java.util.HashMap;
import java.util.Map;

public class HostReputation {

    public static void main(String[] args) {

        NeutrinoAPIClient neutrinoAPI = new NeutrinoAPIClient("<your-user-id>", "<your-api-key>");
        Map<String, String> params = new HashMap<>();

        // An IP address, domain name, FQDN or URL. If you supply a domain/URL it will be checked against
        // the URI DNSBL lists
        params.put("host", "neutrinoapi.com");

        // Only check lists with this rating or better
        params.put("list-rating", "3");

        // Only check these DNSBL zones/hosts. Multiple zones can be supplied as comma-separated values
        params.put("zones", "");

        APIResponse response = neutrinoAPI.hostReputation(params);
        if (response.getData().isPresent()) {
            JsonObject data = response.getData().get();
            System.out.println("API Response OK: ");
            
            // The IP address or host name
            System.out.printf("host: %s%n", data.get("host"));
            
            // Is this host blacklisted
            System.out.printf("is-listed: %s%n", data.get("is-listed"));
            
            // The number of DNSBLs the host is listed on
            System.out.printf("list-count: %s%n", data.get("list-count"));
            
            // Array of objects for each DNSBL checked
            System.out.printf("lists:%n");
            data.getAsJsonArray("lists").forEach(jsonElement -> {
                JsonObject item = jsonElement.getAsJsonObject();
                System.out.printf("%n");
                // True if the host is currently black-listed
                System.out.printf("    is-listed: %s%n", item.get("is-listed"));
                // The hostname of the DNSBL
                System.out.printf("    list-host: %s%n", item.get("list-host"));
                // The name of the DNSBL
                System.out.printf("    list-name: %s%n", item.get("list-name"));
                // The list rating [1-3] with 1 being the best rating and 3 the lowest rating
                System.out.printf("    list-rating: %s%n", item.get("list-rating"));
                // The DNSBL server response time in milliseconds
                System.out.printf("    response-time: %s%n", item.get("response-time"));
                // The specific return code for this listing (only set if listed)
                System.out.printf("    return-code: %s%n", item.get("return-code"));
                // The TXT record returned for this listing (only set if listed)
                System.out.printf("    txt-record: %s%n", item.get("txt-record"));
            });
            
        } else {
            // API request failed, you should handle this gracefully!
            System.err.printf("API Error: %s, Error Code: %d, HTTP Status Code: %d%n", response.getErrorMessage(), response.getErrorCode(), response.getHttpStatusCode());
            response.getErrorCause().ifPresent(cause -> System.err.printf("Error Caused By: %s%n", cause));
        }
    }
}
