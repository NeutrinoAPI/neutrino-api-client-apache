package com.neutrinoapi.examples;

import com.google.gson.JsonObject;
import com.neutrinoapi.client.APIResponse;
import com.neutrinoapi.client.NeutrinoAPIClient;
import java.util.HashMap;
import java.util.Map;

public class IPProbe {

    public static void main(String[] args) {

        NeutrinoAPIClient neutrinoAPI = new NeutrinoAPIClient("<your-user-id>", "<your-api-key>");
        Map<String, String> params = new HashMap<>();

        // IPv4 or IPv6 address
        params.put("ip", "194.233.98.38");

        APIResponse response = neutrinoAPI.ipProbe(params);
        if (response.getData().isPresent()) {
            JsonObject data = response.getData().get();
            System.out.println("API Response OK: ");
            
            // The age of the autonomous system (AS) in number of years since registration
            System.out.printf("as-age: %s%n", data.get("as-age"));
            
            // The autonomous system (AS) CIDR range
            System.out.printf("as-cidr: %s%n", data.get("as-cidr"));
            
            // The autonomous system (AS) ISO 2-letter country code
            System.out.printf("as-country-code: %s%n", data.get("as-country-code"));
            
            // The autonomous system (AS) ISO 3-letter country code
            System.out.printf("as-country-code3: %s%n", data.get("as-country-code3"));
            
            // The autonomous system (AS) description / company name
            System.out.printf("as-description: %s%n", data.get("as-description"));
            
            // Array of all the domains associated with the autonomous system (AS)
            System.out.printf("as-domains: %s%n", data.get("as-domains"));
            
            // The autonomous system (AS) number
            System.out.printf("asn: %s%n", data.get("asn"));
            
            // Full city name (if detectable)
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
            
            // The IPs host domain
            System.out.printf("host-domain: %s%n", data.get("host-domain"));
            
            // The IPs full hostname (PTR)
            System.out.printf("hostname: %s%n", data.get("hostname"));
            
            // The IP address
            System.out.printf("ip: %s%n", data.get("ip"));
            
            // True if this is a bogon IP address such as a private network, local network or reserved address
            System.out.printf("is-bogon: %s%n", data.get("is-bogon"));
            
            // True if this IP belongs to a hosting company. Note that this can still be true even if the
            // provider type is VPN/proxy, this occurs in the case that the IP is detected as both types
            System.out.printf("is-hosting: %s%n", data.get("is-hosting"));
            
            // True if this IP belongs to an internet service provider. Note that this can still be true even if
            // the provider type is VPN/proxy, this occurs in the case that the IP is detected as both types
            System.out.printf("is-isp: %s%n", data.get("is-isp"));
            
            // True if this IP ia a proxy
            System.out.printf("is-proxy: %s%n", data.get("is-proxy"));
            
            // True if this is a IPv4 mapped IPv6 address
            System.out.printf("is-v4-mapped: %s%n", data.get("is-v4-mapped"));
            
            // True if this is a IPv6 address. False if IPv4
            System.out.printf("is-v6: %s%n", data.get("is-v6"));
            
            // True if this IP ia a VPN
            System.out.printf("is-vpn: %s%n", data.get("is-vpn"));
            
            // A description of the provider (usually extracted from the providers website)
            System.out.printf("provider-description: %s%n", data.get("provider-description"));
            
            // The domain name of the provider
            System.out.printf("provider-domain: %s%n", data.get("provider-domain"));
            
            // The detected provider type, possible values are:
            // • isp - IP belongs to an internet service provider. This includes both mobile, home and
            //   business internet providers
            // • hosting - IP belongs to a hosting company. This includes website hosting, cloud computing
            //   platforms and colocation facilities
            // • vpn - IP belongs to a VPN provider
            // • proxy - IP belongs to a proxy service. This includes HTTP/SOCKS proxies and browser based
            //   proxies
            // • university - IP belongs to a university/college/campus
            // • government - IP belongs to a government department. This includes military facilities
            // • commercial - IP belongs to a commercial entity such as a corporate headquarters or company
            //   office
            // • unknown - could not identify the provider type
            System.out.printf("provider-type: %s%n", data.get("provider-type"));
            
            // The website URL for the provider
            System.out.printf("provider-website: %s%n", data.get("provider-website"));
            
            // Full region name (if detectable)
            System.out.printf("region: %s%n", data.get("region"));
            
            // ISO 3166-2 region code (if detectable)
            System.out.printf("region-code: %s%n", data.get("region-code"));
            
            // True if this is a valid IPv4 or IPv6 address
            System.out.printf("valid: %s%n", data.get("valid"));
            
            // The domain of the VPN provider (may be empty if the VPN domain is not detectable)
            System.out.printf("vpn-domain: %s%n", data.get("vpn-domain"));
            
        } else {
            // API request failed, you should handle this gracefully!
            System.err.printf("API Error: %s, Error Code: %d, HTTP Status Code: %d%n", response.getErrorMessage(), response.getErrorCode(), response.getHttpStatusCode());
            response.getErrorCause().ifPresent(cause -> System.err.printf("Error Caused By: %s%n", cause));
        }
    }
}
