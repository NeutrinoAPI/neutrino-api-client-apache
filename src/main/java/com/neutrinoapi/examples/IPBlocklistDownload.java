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

        // Include public VPN provider IP addresses, this option is only available for Tier 3 or higher
        // accounts. WARNING: This option will add at least an additional 8 million IP addresses to the
        // download if not using CIDR notation
        params.put("include-vpn", "false");

        // Output IPs using CIDR notation. This option should be preferred but is off by default for
        // backwards compatibility
        params.put("cidr", "false");

        // Output the IPv6 version of the blocklist, the default is to output IPv4 only. Note that this
        // option enables CIDR notation too as this is the only notation currently supported for IPv6
        params.put("ip6", "false");

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
