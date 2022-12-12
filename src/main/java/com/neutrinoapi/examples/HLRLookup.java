package com.neutrinoapi.examples;

import com.google.gson.JsonObject;
import com.neutrinoapi.client.APIResponse;
import com.neutrinoapi.client.NeutrinoAPIClient;
import java.util.HashMap;
import java.util.Map;

public class HLRLookup {

    public static void main(String[] args) {

        NeutrinoAPIClient neutrinoAPI = new NeutrinoAPIClient("<your-user-id>", "<your-api-key>");
        Map<String, String> params = new HashMap<>();

        // A phone number
        params.put("number", "+12106100045");

        // ISO 2-letter country code, assume numbers are based in this country. If not set numbers are
        // assumed to be in international format (with or without the leading + sign)
        params.put("country-code", "");

        APIResponse response = neutrinoAPI.hlrLookup(params);
        if (response.getData().isPresent()) {
            JsonObject data = response.getData().get();
            System.out.println("API Response OK: ");
            
            // The phone number country
            System.out.printf("country: %s%n", data.get("country"));
            
            // The number location as an ISO 2-letter country code
            System.out.printf("country-code: %s%n", data.get("country-code"));
            
            // The number location as an ISO 3-letter country code
            System.out.printf("country-code3: %s%n", data.get("country-code3"));
            
            // ISO 4217 currency code associated with the country
            System.out.printf("currency-code: %s%n", data.get("currency-code"));
            
            // The currently used network/carrier name
            System.out.printf("current-network: %s%n", data.get("current-network"));
            
            // The HLR lookup status, possible values are:
            // • ok - the HLR lookup was successful and the device is connected
            // • absent - the number was once registered but the device has been switched off or out of
            //   network range for some time
            // • unknown - the number is not known by the mobile network
            // • invalid - the number is not a valid mobile MSISDN number
            // • fixed-line - the number is a registered fixed-line not mobile
            // • voip - the number has been detected as a VOIP line
            // • failed - the HLR lookup has failed, we could not determine the real status of this number
            System.out.printf("hlr-status: %s%n", data.get("hlr-status"));
            
            // Was the HLR lookup successful. If true then this is a working and registered cell-phone or mobile
            // device (SMS and phone calls will be delivered)
            System.out.printf("hlr-valid: %s%n", data.get("hlr-valid"));
            
            // The mobile IMSI number (International Mobile Subscriber Identity)
            System.out.printf("imsi: %s%n", data.get("imsi"));
            
            // The international calling code
            System.out.printf("international-calling-code: %s%n", data.get("international-calling-code"));
            
            // The number represented in full international format
            System.out.printf("international-number: %s%n", data.get("international-number"));
            
            // True if this is a mobile number (only true with 100% certainty, if the number type is unknown
            // this value will be false)
            System.out.printf("is-mobile: %s%n", data.get("is-mobile"));
            
            // Has this number been ported to another network
            System.out.printf("is-ported: %s%n", data.get("is-ported"));
            
            // Is this number currently roaming from its origin country
            System.out.printf("is-roaming: %s%n", data.get("is-roaming"));
            
            // The number represented in local dialing format
            System.out.printf("local-number: %s%n", data.get("local-number"));
            
            // The number location. Could be a city, region or country depending on the type of number
            System.out.printf("location: %s%n", data.get("location"));
            
            // The mobile MCC number (Mobile Country Code)
            System.out.printf("mcc: %s%n", data.get("mcc"));
            
            // The mobile MNC number (Mobile Network Code)
            System.out.printf("mnc: %s%n", data.get("mnc"));
            
            // The mobile MSC number (Mobile Switching Center)
            System.out.printf("msc: %s%n", data.get("msc"));
            
            // The mobile MSIN number (Mobile Subscription Identification Number)
            System.out.printf("msin: %s%n", data.get("msin"));
            
            // The number type, possible values are:
            // • mobile
            // • fixed-line
            // • premium-rate
            // • toll-free
            // • voip
            // • unknown
            System.out.printf("number-type: %s%n", data.get("number-type"));
            
            // True if this a valid phone number
            System.out.printf("number-valid: %s%n", data.get("number-valid"));
            
            // The origin network/carrier name
            System.out.printf("origin-network: %s%n", data.get("origin-network"));
            
            // The ported to network/carrier name (only set if the number has been ported)
            System.out.printf("ported-network: %s%n", data.get("ported-network"));
            
            // If the number is currently roaming, the ISO 2-letter country code of the roaming in country
            System.out.printf("roaming-country-code: %s%n", data.get("roaming-country-code"));
            
        } else {
            // API request failed, you should handle this gracefully!
            System.err.printf("API Error: %s, Error Code: %d, HTTP Status Code: %d%n", response.getErrorMessage(), response.getErrorCode(), response.getHttpStatusCode());
            response.getErrorCause().ifPresent(cause -> System.err.printf("Error Caused By: %s%n", cause));
        }
    }
}
