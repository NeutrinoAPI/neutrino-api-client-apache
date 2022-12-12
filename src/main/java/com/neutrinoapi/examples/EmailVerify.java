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
            
            // The email domain
            System.out.printf("domain: %s%n", data.get("domain"));
            
            // True if this address has a domain error (e.g. no valid mail server records)
            System.out.printf("domain-error: %s%n", data.get("domain-error"));
            
            // The email address. If you have used the fix-typos option then this will be the fixed address
            System.out.printf("email: %s%n", data.get("email"));
            
            // True if this email domain has a catch-all policy (it will accept mail for any username)
            System.out.printf("is-catch-all: %s%n", data.get("is-catch-all"));
            
            // True if the mail server responded with a temporary failure (either a 4xx response code or
            // unresponsive server). You can retry this address later, we recommend waiting at least 15 minutes
            // before retrying
            System.out.printf("is-deferred: %s%n", data.get("is-deferred"));
            
            // True if this address is a disposable, temporary or darknet related email address
            System.out.printf("is-disposable: %s%n", data.get("is-disposable"));
            
            // True if this address is a free-mail address
            System.out.printf("is-freemail: %s%n", data.get("is-freemail"));
            
            // True if this address is for a person. False if this is a role based address, e.g. admin@, help@,
            // office@, etc.
            System.out.printf("is-personal: %s%n", data.get("is-personal"));
            
            // The email service provider domain
            System.out.printf("provider: %s%n", data.get("provider"));
            
            // The raw SMTP response message received during verification
            System.out.printf("smtp-response: %s%n", data.get("smtp-response"));
            
            // The SMTP verification status for the address:
            // • ok - SMTP verification was successful, this is a real address that can receive mail
            // • invalid - this is not a valid email address (has either a domain or syntax error)
            // • absent - this address is not registered with the email service provider
            // • unresponsive - the mail server(s) for this address timed-out or refused to open an SMTP
            //   connection
            // • unknown - sorry, we could not reliably determine the real status of this address (this
            //   address may or may not exist)
            System.out.printf("smtp-status: %s%n", data.get("smtp-status"));
            
            // True if this address has a syntax error
            System.out.printf("syntax-error: %s%n", data.get("syntax-error"));
            
            // True if typos have been fixed
            System.out.printf("typos-fixed: %s%n", data.get("typos-fixed"));
            
            // Is this a valid email address (syntax and domain is valid)
            System.out.printf("valid: %s%n", data.get("valid"));
            
            // True if this address has passed SMTP verification. Check the smtp-status and smtp-response fields
            // for specific verification details
            System.out.printf("verified: %s%n", data.get("verified"));
            
        } else {
            // API request failed, you should handle this gracefully!
            System.err.printf("API Error: %s, Error Code: %d, HTTP Status Code: %d%n", response.getErrorMessage(), response.getErrorCode(), response.getHttpStatusCode());
            response.getErrorCause().ifPresent(cause -> System.err.printf("Error Caused By: %s%n", cause));
        }
    }
}
