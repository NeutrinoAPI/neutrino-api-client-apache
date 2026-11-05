package com.neutrinoapi.examples;

import com.google.gson.JsonObject;
import com.neutrinoapi.client.APIResponse;
import com.neutrinoapi.client.NeutrinoAPIClient;
import java.util.HashMap;
import java.util.Map;

public class UALookup {

    public static void main(String[] args) {

        NeutrinoAPIClient neutrinoAPI = new NeutrinoAPIClient("<your-user-id>", "<your-api-key>");
        Map<String, String> params = new HashMap<>();

        // The user-agent string to lookup. For client hints this corresponds to the 'sec-ch-ua' header or
        // you can also pass the JSON data directly from the
        // 'navigator.userAgentData.getHighEntropyValues()' JavaScript API
        params.put("ua", "Mozilla/5.0 (Linux; Android 11; SM-G9980U1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.101 Mobile Safari/537.36");

        // For client hints this corresponds to the 'sec-ch-ua-full-version' header
        params.put("ua-version", "");

        // For client hints this corresponds to the 'sec-ch-ua-platform' header
        params.put("ua-platform", "");

        // For client hints this corresponds to the 'sec-ch-ua-platform-version' header
        params.put("ua-platform-version", "");

        // For client hints this corresponds to the 'sec-ch-ua-mobile' header
        params.put("ua-mobile", "");

        // For client hints this corresponds to the 'sec-ch-ua-model' header. You can also use this
        // parameter to lookup a device directly by its model name, model code or hardware code, on android
        // you can get the model name from:
        // https://developer.android.com/reference/android/os/Build.html#MODEL
        params.put("device-model", "");

        // This parameter is only used in combination with 'device-model' for direct device lookups without
        // any user-agent data. Set this to the brand or manufacturer name, this is required for accurate
        // device detection with ambiguous model names. On android you can get the device brand from:
        // https://developer.android.com/reference/android/os/Build#MANUFACTURER
        params.put("device-brand", "");

        // For client hints this corresponds to the 'sec-ch-ua-arch' header
        params.put("ua-arch", "");

        // For client hints this corresponds to the 'sec-ch-ua-bitness' header
        params.put("ua-bitness", "");

        // For client hints this corresponds to the 'sec-ch-ua-form-factors' header
        params.put("ua-form-factors", "");

        APIResponse response = neutrinoAPI.uaLookup(params);
        if (response.getData().isPresent()) {
            JsonObject data = response.getData().get();
            System.out.println("API Response OK: ");
            
            // For web browsers which underlying browser engine does it use
            System.out.printf("browser-engine: %s%n", data.get("browser-engine"));
            
            // The web browser name. For Apps/WebViews this will be the name of the underlying browser used,
            // otherwise this is identical to the 'name' field
            System.out.printf("browser-name: %s%n", data.get("browser-name"));
            
            // For web browsers which year was this browser version released
            System.out.printf("browser-release: %s%n", data.get("browser-release"));
            
            // For web browsers the ISO date this browser version was released
            System.out.printf("browser-release-date: %s%n", data.get("browser-release-date"));
            
            // The browsers full version. For Apps/WebViews this will be the full version of the underlying app
            // browser, otherwise this is identical to the 'version' field
            System.out.printf("browser-version: %s%n", data.get("browser-version"));
            
            // The browsers major version. For Apps/WebViews this will be the major version of the underlying
            // app browser, otherwise this is identical to the 'version-major' field
            System.out.printf("browser-version-major: %s%n", data.get("browser-version-major"));
            
            // The device brand / manufacturer
            System.out.printf("device-brand: %s%n", data.get("device-brand"));
            
            // The device display height in millimeters
            System.out.printf("device-height-mm: %s%n", data.get("device-height-mm"));
            
            // The device display height in CSS 'px'
            System.out.printf("device-height-px: %s%n", data.get("device-height-px"));
            
            // The device model
            System.out.printf("device-model: %s%n", data.get("device-model"));
            
            // The device model code
            System.out.printf("device-model-code: %s%n", data.get("device-model-code"));
            
            // The device display pixel ratio (the ratio of the resolution in physical pixels to the resolution
            // in CSS pixels)
            System.out.printf("device-pixel-ratio: %s%n", data.get("device-pixel-ratio"));
            
            // The device display PPI (pixels per inch)
            System.out.printf("device-ppi: %s%n", data.get("device-ppi"));
            
            // The average device price on release in USD
            System.out.printf("device-price: %s%n", data.get("device-price"));
            
            // The year when this device model was released
            System.out.printf("device-release: %s%n", data.get("device-release"));
            
            // The device display resolution in physical pixels (e.g. 720x1280)
            System.out.printf("device-resolution: %s%n", data.get("device-resolution"));
            
            // The device display width in millimeters
            System.out.printf("device-width-mm: %s%n", data.get("device-width-mm"));
            
            // The device display width in CSS 'px'
            System.out.printf("device-width-px: %s%n", data.get("device-width-px"));
            
            // True if this user agent is from a mobile or desktop app. You can distinguish the app name and
            // version from the 'name' and 'version-*' fields and the underlying browser/WebView from the
            // 'browser-*' fields
            System.out.printf("is-app: %s%n", data.get("is-app"));
            
            // For web browsers is the version up-to-date with the current stable release (+/- one version)
            System.out.printf("is-current: %s%n", data.get("is-current"));
            
            // Is this a mobile device (e.g. a phone or tablet)
            System.out.printf("is-mobile: %s%n", data.get("is-mobile"));
            
            // For chrome/chromium based browsers is this user agent string frozen and requires client hints for
            // more accurate details. For more information see user-agent reduction and to test a browser for
            // client hints go to the client hints analyzer test page
            System.out.printf("is-ua-frozen: %s%n", data.get("is-ua-frozen"));
            
            // Is this a WebView or embedded web browser. This includes mobile and desktop apps using WebViews
            System.out.printf("is-webview: %s%n", data.get("is-webview"));
            
            // The client software name / user agent name / web browser name
            System.out.printf("name: %s%n", data.get("name"));
            
            // The full operating system name
            System.out.printf("os: %s%n", data.get("os"));
            
            // The operating system family. The major OS families are: Android, Windows, macOS, iOS, Linux
            System.out.printf("os-family: %s%n", data.get("os-family"));
            
            // The operating system full version
            System.out.printf("os-version: %s%n", data.get("os-version"));
            
            // The operating system major version
            System.out.printf("os-version-major: %s%n", data.get("os-version-major"));
            
            // The user agent type, possible values are:
            // • desktop
            // • phone
            // • tablet
            // • wearable
            // • tv
            // • console
            // • email
            // • library
            // • robot
            // • unknown
            System.out.printf("type: %s%n", data.get("type"));
            
            // The user agent string
            System.out.printf("ua: %s%n", data.get("ua"));
            
            // The user agent software full version
            System.out.printf("version: %s%n", data.get("version"));
            
            // The user agent software major version
            System.out.printf("version-major: %s%n", data.get("version-major"));
            
        } else {
            // API request failed, you should handle this gracefully!
            System.err.printf("API Error: %s, Error Code: %d, HTTP Status Code: %d%n", response.getErrorMessage(), response.getErrorCode(), response.getHttpStatusCode());
            response.getErrorCause().ifPresent(cause -> System.err.printf("Error Caused By: %s%n", cause));
        }
    }
}
