package com.neutrinoapi.examples;

import com.google.gson.JsonObject;
import com.neutrinoapi.client.APIResponse;
import com.neutrinoapi.client.NeutrinoAPIClient;
import java.util.HashMap;
import java.util.Map;

public class EmailVerify {

    public static void main(String[] args) {

        NeutrinoAPIClient neutrinoAPI = new NeutrinoAPIClient("<your-user-id>", "<your-api-key>");
        Map<String, String> params = new HashMap<>();

        // An email address
        params.put("email", "tech@neutrinoapi.com");

        // Automatically attempt to fix typos in the address
        params.put("fix-typos", "false");

        APIResponse response = neutrinoAPI.emailVerify(params);
        if (response.getData().isPresent()) {
            JsonObject data = response.getData().get();
            System.out.println("API Response OK: ");
            
            // The domain name of this email address
            System.out.printf("domain: %s%n", data.get("domain"));
            
            // True if this address has any domain name or DNS related errors. Check the 'domain-status' field
            // for the detailed error reason
            System.out.printf("domain-error: %s%n", data.get("domain-error"));
            
            // The email domain status, possible values are:
            // • ok - the domain is in working order and can receive email
            // • invalid - the domain is not a conformant hostname. May contain invalid syntax or characters
            // • no-service - the domain owner has indicated there is no mail service on the domain (also
            //   known as the 'Null MX')
            // • no-mail - the domain has no valid MX records so cannot receive email
            // • mx-invalid - MX records contain invalid or non-conformant hostname values
            // • mx-bogon - MX records point to bogon IP addresses
            // • resolv-error - MX records do not resolve to any valid IP addresses
            System.out.printf("domain-status: %s%n", data.get("domain-status"));
            
            // The complete email address. If you enabled the 'fix-typos' option then this will be the corrected
            // address
            System.out.printf("email: %s%n", data.get("email"));
            
            // True if this email domain has a catch-all policy. A catch-all domain will accept mail for any
            // username so therefor the 'smtp-status' will always be 'ok'
            System.out.printf("is-catch-all: %s%n", data.get("is-catch-all"));
            
            // True if the mail server responded with a temporary failure (either a 4xx response code or
            // unresponsive server). You can retry this address later, we recommend waiting at least 15 minutes
            // before retrying
            System.out.printf("is-deferred: %s%n", data.get("is-deferred"));
            
            // True if this address is a disposable, temporary or darknet related email address
            System.out.printf("is-disposable: %s%n", data.get("is-disposable"));
            
            // True if this address is from a free email provider
            System.out.printf("is-freemail: %s%n", data.get("is-freemail"));
            
            // True if this address likely belongs to a person. False if this is a role based address, e.g.
            // admin@, help@, office@, etc.
            System.out.printf("is-personal: %s%n", data.get("is-personal"));
            
            // The first resolved IP address of the primary MX server, may be empty if there are domain errors
            // present
            System.out.printf("mx-ip: %s%n", data.get("mx-ip"));
            
            // The domain name of the email hosting provider
            System.out.printf("provider: %s%n", data.get("provider"));
            
            // The raw SMTP response message received during verification
            System.out.printf("smtp-response: %s%n", data.get("smtp-response"));
            
            // The SMTP username verification status for this address:
            // • ok - verification was successful, this is a real username that can receive mail
            // • absent - this username or domain is not registered with the email service provider
            // • invalid - not a valid email address, check the 'domain-status' field for specific details
            // • unresponsive - the mail servers for this domain have repeatedly timed-out or refused multiple
            //   connection attempts
            // • unknown - sorry, we could not reliably determine the status of this username
            System.out.printf("smtp-status: %s%n", data.get("smtp-status"));
            
            // True if this address has any syntax errors or is not in RFC compliant formatting
            System.out.printf("syntax-error: %s%n", data.get("syntax-error"));
            
            // True if any typos have been fixed. The 'fix-typos' option must be enabled for this to work
            System.out.printf("typos-fixed: %s%n", data.get("typos-fixed"));
            
            // Is this a valid email address. To be valid an email must have: correct syntax, a registered and
            // active domain name, correct DNS records and operational MX servers
            System.out.printf("valid: %s%n", data.get("valid"));
            
            // True if this email address has passed SMTP username verification. Check the 'smtp-status' and
            // 'domain-status' fields for specific verification details
            System.out.printf("verified: %s%n", data.get("verified"));
            
        } else {
            // API request failed, you should handle this gracefully!
            System.err.printf("API Error: %s, Error Code: %d, HTTP Status Code: %d%n", response.getErrorMessage(), response.getErrorCode(), response.getHttpStatusCode());
            response.getErrorCause().ifPresent(cause -> System.err.printf("Error Caused By: %s%n", cause));
        }
    }
}
