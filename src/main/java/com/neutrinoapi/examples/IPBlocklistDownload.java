package com.neutrinoapi.examples;

import com.neutrinoapi.client.APIResponse;
import com.neutrinoapi.client.NeutrinoAPIClient;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class IPBlocklistDownload {

    public static void main(String[] args) {

        Path tmpFile;
        try {
            tmpFile = Files.createTempFile("ip-blocklist-download-", ".csv");
        } catch (IOException ex) {
            System.err.printf("Failed to create temp file: %s%n", ex);
            return;
        }

        NeutrinoAPIClient neutrinoAPI = new NeutrinoAPIClient("<your-user-id>", "<your-api-key>");
        Map<String, String> params = new HashMap<>();

        // The data format. Can be either CSV or TXT
        params.put("format", "csv");

        // Output IPs using CIDR notation. This option should be preferred but is off by default for
        // backwards compatibility
        params.put("cidr", "false");

        // Output the IPv6 version of the blocklist, the default is to output IPv4 only. Note that this
        // option enables CIDR notation too as this is the only notation currently supported for IPv6
        params.put("ip6", "false");

        // The category of IP addresses to include in the download file, possible values are:
        // • all - all IPs available on your current plan (excludes VPN providers for any plans lower than
        //   Tier 3)
        // • bot - all IPs hosting a malicious bot or part of a botnet. This is a broad category which
        //   includes brute-force crackers
        // • exploit-bot - all IPs hosting an exploit finding bot or running exploit scanning software
        // • hijacked - all IPs that are part of a hijacked netblock or a netblock controlled by a
        //   criminal organization
        // • malware - all IPs involved in distributing or running malware
        // • proxy - all IPs detected as an anonymous web proxy or anonymous HTTP proxy
        // • spam-bot - all IPs hosting a spam bot, comment spamming or any other spamming type software
        // • spider - all IPs running a hostile web spider / web crawler
        // • spyware - all IPs involved in distributing or running spyware
        // • tor - all IPs that are Tor nodes or running a Tor related service
        // • vpn - all IPs belonging to public VPN providers (only available for Tier 3 or higher
        //   accounts)
        params.put("category", "all");

        // Set this option to 'gzip' to have the output file compressed using gzip
        params.put("output-encoding", "");

        // Do not download the file but just return the current files MurmurHash3 checksum. You can use this
        // feature to check if the file has changed since a previous check
        params.put("checksum", "false");

        APIResponse response = neutrinoAPI.ipBlocklistDownload(params, tmpFile);
        if (response.getFile().isPresent()) {
            Path outputFile = response.getFile().get();
            // API request successful, print out the file path
            System.out.printf("API Response OK, output saved to: %s, content type: %s%n", outputFile, response.getContentType());
        } else {
            // API request failed, you should handle this gracefully!
            System.err.printf("API Error: %s, Error Code: %d, HTTP Status Code: %d%n", response.getErrorMessage(), response.getErrorCode(), response.getHttpStatusCode());
            response.getErrorCause().ifPresent(cause -> System.err.printf("Error Caused By: %s%n", cause));
        }
    }
}
