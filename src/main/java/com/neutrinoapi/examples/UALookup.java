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

        // The user-agent string to lookup. For client hints use the 'UA' header or the JSON data directly
        // from 'navigator.userAgentData.brands' or 'navigator.userAgentData.getHighEntropyValues()'
        params.put("ua", "Mozilla/5.0 (Linux; Android 11; SM-G9980U1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.101 Mobile Safari/537.36");

        // For client hints this corresponds to the 'UA-Full-Version' header or 'uaFullVersion' from
        // NavigatorUAData
        params.put("ua-version", "");

        // For client hints this corresponds to the 'UA-Platform' header or 'platform' from NavigatorUAData
        params.put("ua-platform", "");

        // For client hints this corresponds to the 'UA-Platform-Version' header or 'platformVersion' from
        // NavigatorUAData
        params.put("ua-platform-version", "");

        // For client hints this corresponds to the 'UA-Mobile' header or 'mobile' from NavigatorUAData
        params.put("ua-mobile", "");

        // For client hints this corresponds to the 'UA-Model' header or 'model' from NavigatorUAData. You
        // can also use this parameter to lookup a device directly by its model name, model code or hardware
        // code, on android you can get the model name from:
        // https://developer.android.com/reference/android/os/Build.html#MODEL
        params.put("device-model", "");

        // This parameter is only used in combination with 'device-model' when doing direct device lookups
        // without any user-agent data. Set this to the brand or manufacturer name, this is required for
        // accurate device detection with ambiguous model names. On android you can get the device brand
        // from: https://developer.android.com/reference/android/os/Build#MANUFACTURER
        params.put("device-brand", "");

        APIResponse response = neutrinoAPI.uaLookup(params);
        if (response.getData().isPresent()) {
            JsonObject data = response.getData().get();
            System.out.println("API Response OK: ");
            
            // If the client is a web browser which underlying browser engine does it use
            System.out.printf("browser-engine: %s%n", data.get("browser-engine"));
            
            // If the client is a web browser which year was this browser version released
            System.out.printf("browser-release: %s%n", data.get("browser-release"));
            
            // The device brand / manufacturer
            System.out.printf("device-brand: %s%n", data.get("device-brand"));
            
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
            
            // The device display width in CSS 'px'
            System.out.printf("device-width-px: %s%n", data.get("device-width-px"));
            
            // Is this a mobile device (e.g. a phone or tablet)
            System.out.printf("is-mobile: %s%n", data.get("is-mobile"));
            
            // Is this a WebView / embedded software client
            System.out.printf("is-webview: %s%n", data.get("is-webview"));
            
            // The client software name
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
            
            // The client software full version
            System.out.printf("version: %s%n", data.get("version"));
            
            // The client software major version
            System.out.printf("version-major: %s%n", data.get("version-major"));
            
        } else {
            // API request failed, you should handle this gracefully!
            System.err.printf("API Error: %s, Error Code: %d, HTTP Status Code: %d%n", response.getErrorMessage(), response.getErrorCode(), response.getHttpStatusCode());
            response.getErrorCause().ifPresent(cause -> System.err.printf("Error Caused By: %s%n", cause));
        }
    }
}
