package com.neutrinoapi.examples;

import com.google.gson.JsonObject;
import com.neutrinoapi.client.APIResponse;
import com.neutrinoapi.client.NeutrinoAPIClient;
import java.util.HashMap;
import java.util.Map;

public class IPBlocklist {

    public static void main(String[] args) {

        NeutrinoAPIClient neutrinoAPI = new NeutrinoAPIClient("<your-user-id>", "<your-api-key>");
        Map<String, String> params = new HashMap<>();

        // An IPv4 or IPv6 address. Accepts standard IP notation (with or without port number), CIDR
        // notation and IPv6 compressed notation. If multiple IPs are passed using comma-separated values
        // the first non-bogon address on the list will be checked
        params.put("ip", "104.244.72.115");

        // Include public VPN provider IP addresses. NOTE: For more advanced VPN detection including the
        // ability to identify private and stealth VPNs use the IP Probe API
        params.put("vpn-lookup", "false");

        APIResponse response = neutrinoAPI.ipBlocklist(params);
        if (response.getData().isPresent()) {
            JsonObject data = response.getData().get();
            System.out.println("API Response OK: ");
            
            // An array of strings indicating which blocklist categories this IP is listed on
            System.out.printf("blocklists: %s%n", data.get("blocklists"));
            
            // The CIDR address for this listing (only set if the IP is listed)
            System.out.printf("cidr: %s%n", data.get("cidr"));
            
            // The IP address
            System.out.printf("ip: %s%n", data.get("ip"));
            
            // IP is hosting a malicious bot or is part of a botnet. This is a broad category which includes
            // brute-force crackers
            System.out.printf("is-bot: %s%n", data.get("is-bot"));
            
            // IP has been flagged as a significant attack source by DShield (dshield.org)
            System.out.printf("is-dshield: %s%n", data.get("is-dshield"));
            
            // IP is hosting an exploit finding bot or is running exploit scanning software
            System.out.printf("is-exploit-bot: %s%n", data.get("is-exploit-bot"));
            
            // IP is part of a hijacked netblock or a netblock controlled by a criminal organization
            System.out.printf("is-hijacked: %s%n", data.get("is-hijacked"));
            
            // Is this IP on a blocklist
            System.out.printf("is-listed: %s%n", data.get("is-listed"));
            
            // IP is involved in distributing or is running malware
            System.out.printf("is-malware: %s%n", data.get("is-malware"));
            
            // IP has been detected as an anonymous web proxy or anonymous HTTP proxy
            System.out.printf("is-proxy: %s%n", data.get("is-proxy"));
            
            // IP address is hosting a spam bot, comment spamming or any other spamming type software
            System.out.printf("is-spam-bot: %s%n", data.get("is-spam-bot"));
            
            // IP is running a hostile web spider / web crawler
            System.out.printf("is-spider: %s%n", data.get("is-spider"));
            
            // IP is involved in distributing or is running spyware
            System.out.printf("is-spyware: %s%n", data.get("is-spyware"));
            
            // IP is a Tor node or running a Tor related service
            System.out.printf("is-tor: %s%n", data.get("is-tor"));
            
            // IP belongs to a public VPN provider (only set if the 'vpn-lookup' option is enabled)
            System.out.printf("is-vpn: %s%n", data.get("is-vpn"));
            
            // The unix time when this IP was last seen on any blocklist. IPs are automatically removed after 7
            // days therefor this value will never be older than 7 days
            System.out.printf("last-seen: %s%n", data.get("last-seen"));
            
            // The number of blocklists the IP is listed on
            System.out.printf("list-count: %s%n", data.get("list-count"));
            
            // An array of objects containing details on which specific sensors detected the IP
            System.out.printf("sensors:%n");
            data.getAsJsonArray("sensors").forEach(jsonElement -> {
                JsonObject item = jsonElement.getAsJsonObject();
                System.out.printf("%n");
                // The primary blocklist category this sensor belongs to
                System.out.printf("    blocklist: %s%n", item.get("blocklist"));
                // Contains details about the sensor source and what type of malicious activity was detected
                System.out.printf("    description: %s%n", item.get("description"));
                // The sensor ID. This is a permanent and unique ID for each sensor
                System.out.printf("    id: %s%n", item.get("id"));
            });
            
        } else {
            // API request failed, you should handle this gracefully!
            System.err.printf("API Error: %s, Error Code: %d, HTTP Status Code: %d%n", response.getErrorMessage(), response.getErrorCode(), response.getHttpStatusCode());
            response.getErrorCause().ifPresent(cause -> System.err.printf("Error Caused By: %s%n", cause));
        }
    }
}
