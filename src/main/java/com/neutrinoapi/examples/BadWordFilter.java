package com.neutrinoapi.examples;

import com.google.gson.JsonObject;
import com.neutrinoapi.client.APIResponse;
import com.neutrinoapi.client.NeutrinoAPIClient;
import java.util.HashMap;
import java.util.Map;

public class BadWordFilter {

    public static void main(String[] args) {

        NeutrinoAPIClient neutrinoAPI = new NeutrinoAPIClient("<your-user-id>", "<your-api-key>");
        Map<String, String> params = new HashMap<>();

        // The character to use to censor out the bad words found
        params.put("censor-character", "");

        // Which catalog of bad words to use, we currently maintain two bad word catalogs:
        // • strict - the largest database of bad words which includes profanity, obscenity, sexual, rude,
        //   cuss, dirty, swear and objectionable words and phrases. This catalog is suitable for
        //   environments of all ages including educational or children's content
        // • obscene - like the strict catalog but does not include any mild profanities, idiomatic
        //   phrases or words which are considered formal terminology. This catalog is suitable for adult
        //   environments where certain types of bad words are considered OK
        params.put("catalog", "strict");

        // The content to scan. This can be either a URL to load from, a file upload or an HTML content
        // string
        params.put("content", "https://en.wikipedia.org/wiki/Profanity");

        APIResponse response = neutrinoAPI.badWordFilter(params);
        if (response.getData().isPresent()) {
            JsonObject data = response.getData().get();
            System.out.println("API Response OK: ");
            
            // An array of the bad words found
            System.out.printf("bad-words-list: %s%n", data.get("bad-words-list"));
            
            // Total number of bad words detected
            System.out.printf("bad-words-total: %s%n", data.get("bad-words-total"));
            
            // The censored content (only set if censor-character has been set)
            System.out.printf("censored-content: %s%n", data.get("censored-content"));
            
            // Does the text contain bad words
            System.out.printf("is-bad: %s%n", data.get("is-bad"));
            
        } else {
            // API request failed, you should handle this gracefully!
            System.err.printf("API Error: %s, Error Code: %d, HTTP Status Code: %d%n", response.getErrorMessage(), response.getErrorCode(), response.getHttpStatusCode());
            response.getErrorCause().ifPresent(cause -> System.err.printf("Error Caused By: %s%n", cause));
        }
    }
}
