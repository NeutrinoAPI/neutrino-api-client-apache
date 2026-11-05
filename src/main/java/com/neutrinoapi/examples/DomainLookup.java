package com.neutrinoapi.examples;

import com.google.gson.JsonObject;
import com.neutrinoapi.client.APIResponse;
import com.neutrinoapi.client.NeutrinoAPIClient;
import java.util.HashMap;
import java.util.Map;

public class DomainLookup {

    public static void main(String[] args) {

        NeutrinoAPIClient neutrinoAPI = new NeutrinoAPIClient("<your-user-id>", "<your-api-key>");
        Map<String, String> params = new HashMap<>();

        // A domain name, hostname, FQDN, URL, HTML link or email address to lookup. For internationalized
        // domain names (IDNs) this field will accept either punycode or the raw unicode domain name, the
        // API will always automatically return IDNs encoded into punycode
        params.put("host", "neutrinoapi.com");

        // For domains that we have never seen before then perform various live checks and realtime
        // reconnaissance. NOTE: this option may add additional non-deterministic delay to the request, if
        // you require consistently fast API response times or just want to check our domain blocklists then
        // you can disable this option
        params.put("live", "true");

        APIResponse response = neutrinoAPI.domainLookup(params);
        if (response.getData().isPresent()) {
            JsonObject data = response.getData().get();
            System.out.println("API Response OK: ");
            
            // The number of days since the domain was registered. A domain age of under 90 days is generally
            // considered to be potentially risky. A value of 0 indicates no registration date was found for
            // this domain
            System.out.printf("age: %s%n", data.get("age"));
            
            // An array of strings indicating which blocklist categories this domain is listed on. Current
            // possible values are:
            // • phishing - Domain has recently been hosting phishing links or involved in the sending of
            //   phishing messages
            // • malware - Domain has recently been hosting malware or involved in the distribution of malware
            // • spam - Domain has recently been sending spam either directly or indirectly
            // • anonymizer - Domain is involved in anonymizer activity such as disposable email, hosting
            //   proxies or tor services
            // • nefarious - Domain is involved in nefarious or malicious activity such as hacking, fraud or
            //   other abusive behavior
            System.out.printf("blocklists: %s%n", data.get("blocklists"));
            
            // The primary domain of the DNS provider for this domain
            System.out.printf("dns-provider: %s%n", data.get("dns-provider"));
            
            // The primary domain name excluding any subdomains. This is also commonly referred to as the
            // second-level domain (SLD), the registrable domain, the root domain or the eTLD+1
            System.out.printf("domain: %s%n", data.get("domain"));
            
            // The effective top-level domain (eTLD). Usually the same as the TLD but will differ when a domain
            // is on the public suffix list (PSL) and acts as a TLD for registrable domains (for example
            // github.io is an eTLD)
            System.out.printf("etld: %s%n", data.get("etld"));
            
            // The ISO date this domain is due to expire. An empty value indicates we could not reliably
            // determine the date
            System.out.printf("expiry-date: %s%n", data.get("expiry-date"));
            
            // The fully qualified domain name (FQDN)
            System.out.printf("fqdn: %s%n", data.get("fqdn"));
            
            // This domain is hosting adult content such as porn, webcams, escorts, etc
            System.out.printf("is-adult: %s%n", data.get("is-adult"));
            
            // Is this domain under a government or military TLD
            System.out.printf("is-gov: %s%n", data.get("is-gov"));
            
            // Consider this domain malicious as it is currently listed on at least 1 blocklist
            System.out.printf("is-malicious: %s%n", data.get("is-malicious"));
            
            // Is this domain under an OpenNIC TLD
            System.out.printf("is-opennic: %s%n", data.get("is-opennic"));
            
            // True if this domain is unseen and is currently being processed in the background. This field only
            // matters when the 'live' lookup setting has been explicitly disabled and indicates that not all
            // domain data my be present yet
            System.out.printf("is-pending: %s%n", data.get("is-pending"));
            
            // Is the FQDN a subdomain of the primary domain
            System.out.printf("is-subdomain: %s%n", data.get("is-subdomain"));
            
            // The primary domain of the email provider for this domain. An empty value indicates the domain has
            // no valid MX records
            System.out.printf("mail-provider: %s%n", data.get("mail-provider"));
            
            // The status of the domains mail configuration, possible values are:
            // • ok - the domain is in working order and can receive email
            // • invalid - the domain is not a conformant hostname. May contain invalid syntax or characters
            // • no-service - the domain owner has indicated there is no mail service on the domain (also
            //   known as the 'Null MX')
            // • no-mail - the domain has no valid MX records so cannot receive email
            // • mx-invalid - MX records contain invalid or non-conformant hostname values
            // • mx-bogon - MX records point to bogon IP addresses
            // • resolv-error - MX records do not resolve to any valid IP addresses
            // • unknown - mail status is currently unknown (the domain may still be pending processing)
            System.out.printf("mail-status: %s%n", data.get("mail-status"));
            
            // The domains estimated global traffic rank with the highest rank being 1. A value of 0 indicates
            // the domain is currently ranked outside of the top 1M of domains
            System.out.printf("rank: %s%n", data.get("rank"));
            
            // The ISO date this domain was registered or first seen on the internet. An empty value indicates
            // we could not reliably determine the date
            System.out.printf("registered-date: %s%n", data.get("registered-date"));
            
            // True if the registrar is an officially IANA accredited registrar
            System.out.printf("registrar-accredited: %s%n", data.get("registrar-accredited"));
            
            // The ISO 2-letter country code where the registrar is located
            System.out.printf("registrar-country-code: %s%n", data.get("registrar-country-code"));
            
            // The IANA registrar ID (0 if no official IANA registrar ID exists)
            System.out.printf("registrar-id: %s%n", data.get("registrar-id"));
            
            // The name of the domain registrar owning this domain
            System.out.printf("registrar-name: %s%n", data.get("registrar-name"));
            
            // The main website URL for the registrar
            System.out.printf("registrar-website: %s%n", data.get("registrar-website"));
            
            // An array of objects containing details on which specific blocklist sensors have detected this
            // domain
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
            
            // The top-level domain (TLD)
            System.out.printf("tld: %s%n", data.get("tld"));
            
            // For a country code top-level domain (ccTLD) this will contain the associated ISO 2-letter country
            // code. For non-ccTLD domains this field will always be empty
            System.out.printf("tld-cc: %s%n", data.get("tld-cc"));
            
            // True if a valid domain was found. For a domain to be considered valid it must be registered and
            // have valid DNS NS records
            System.out.printf("valid: %s%n", data.get("valid"));
            
            // The primary domain of the website hosting provider for this domain. An empty value indicates the
            // domain has no active website provider
            System.out.printf("website-provider: %s%n", data.get("website-provider"));
            
            // The status of the domains website configuration, possible values are:
            // • ok - the domain has a valid and working website
            // • no-http - the domain has website records but the HTTP servers are unresponsive or dead
            // • no-ip - the domain has no valid A or AAAA website records
            // • bogon-ip - the domains website records point to bogon or invalid IP addresses
            // • parked - the domain has a working website but indicates it is currently parked or for sale
            // • unknown - website status is currently unknown (the domain may still be pending processing)
            System.out.printf("website-status: %s%n", data.get("website-status"));
            
        } else {
            // API request failed, you should handle this gracefully!
            System.err.printf("API Error: %s, Error Code: %d, HTTP Status Code: %d%n", response.getErrorMessage(), response.getErrorCode(), response.getHttpStatusCode());
            response.getErrorCause().ifPresent(cause -> System.err.printf("Error Caused By: %s%n", cause));
        }
    }
}
