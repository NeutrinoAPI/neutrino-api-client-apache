
package com.neutrinoapi.client;

/**
 * Neutrino API error codes
 */
public class APIErrorCode
{
    public static final int INVALID_PARAMETER = 1;
    public static final int MAX_CALL_LIMIT = 2;
    public static final int BAD_URL = 3;
    public static final int ABUSE_DETECTED = 4;
    public static final int NOT_RESPONDING = 5;
    public static final int CONCURRENT = 6;
    public static final int NOT_VERIFIED = 7;
    public static final int TELEPHONY_LIMIT = 8;
    public static final int INVALID_JSON = 9;
    public static final int ACCESS_DENIED = 10;
    public static final int MAX_PHONE_CALLS = 11;
    public static final int BAD_AUDIO = 12;
    public static final int HLR_LIMIT_REACHED = 13;
    public static final int TELEPHONY_BLOCKED = 14;
    public static final int TELEPHONY_RATE_EXCEEDED = 15;
    public static final int FREE_LIMIT = 16;
    public static final int RENDERING_FAILED = 17;
    public static final int DEPRECATED_API = 18;
    public static final int CREDIT_LIMIT_REACHED = 19;
    public static final int NOT_MULTI_ENABLED = 21;
    public static final int NO_BATCH_MODE = 22;
    public static final int BATCH_LIMIT_EXCEEDED = 23;
    public static final int BATCH_INVALID = 24;
    public static final int USER_DEFINED_DAILY_LIMIT = 31;
    public static final int ACCESS_FORBIDDEN = 43;
    public static final int REQUEST_TOO_LARGE = 44;
    public static final int NO_ENDPOINT = 45;
    public static final int INTERNAL_SERVER_ERROR = 51;
    public static final int SERVER_OFFLINE = 52;
    public static final int CONNECT_TIMEOUT = 61;
    public static final int READ_TIMEOUT = 62;
    public static final int TIMEOUT = 63;
    public static final int DNS_LOOKUP_FAILED = 64;
    public static final int TLS_PROTOCOL_ERROR = 65;
    public static final int URL_PARSING_ERROR = 66;
    public static final int NETWORK_IO_ERROR = 67;
    public static final int FILE_IO_ERROR = 68;
    public static final int INVALID_JSON_RESPONSE = 69;
    public static final int NO_DATA = 70;
    public static final int API_GATEWAY_ERROR = 71;
    
    /**
     * Get description of error code
     */
    public static String getErrorMessage(int errorCode) {
        switch (errorCode) {
            case INVALID_PARAMETER: return "MISSING OR INVALID PARAMETER";
            case MAX_CALL_LIMIT: return "DAILY API LIMIT EXCEEDED";
            case BAD_URL: return "INVALID URL";
            case ABUSE_DETECTED: return "ACCOUNT OR IP BANNED";
            case NOT_RESPONDING: return "NOT RESPONDING. RETRY IN 5 SECONDS";
            case CONCURRENT: return "TOO MANY CONNECTIONS";
            case NOT_VERIFIED: return "ACCOUNT NOT VERIFIED";
            case TELEPHONY_LIMIT: return "TELEPHONY NOT ENABLED ON YOUR ACCOUNT. PLEASE CONTACT SUPPORT FOR HELP";
            case INVALID_JSON: return "INVALID JSON. JSON CONTENT TYPE SET BUT NON-PARSABLE JSON SUPPLIED";
            case ACCESS_DENIED: return "ACCESS DENIED. PLEASE CONTACT SUPPORT FOR ACCESS TO THIS API";
            case MAX_PHONE_CALLS: return "MAXIMUM SIMULTANEOUS PHONE CALLS";
            case BAD_AUDIO: return "COULD NOT LOAD AUDIO FROM URL";
            case HLR_LIMIT_REACHED: return "HLR LIMIT REACHED. CARD DECLINED";
            case TELEPHONY_BLOCKED: return "CALLS AND SMS TO THIS NUMBER ARE LIMITED";
            case TELEPHONY_RATE_EXCEEDED: return "CALL IN PROGRESS";
            case FREE_LIMIT: return "FREE PLAN LIMIT EXCEEDED";
            case RENDERING_FAILED: return "RENDERING FAILED. COULD NOT GENERATE OUTPUT FILE";
            case DEPRECATED_API: return "THIS API IS DEPRECATED. PLEASE USE THE LATEST VERSION";
            case CREDIT_LIMIT_REACHED: return "MAXIMUM ACCOUNT CREDIT LIMIT REACHED. PAYMENT METHOD DECLINED";
            case NOT_MULTI_ENABLED: return "BATCH PROCESSING NOT ENABLED FOR THIS ENDPOINT";
            case NO_BATCH_MODE: return "BATCH PROCESSING NOT AVAILABLE ON YOUR PLAN";
            case BATCH_LIMIT_EXCEEDED: return "BATCH PROCESSING REQUEST LIMIT EXCEEDED";
            case BATCH_INVALID: return "INVALID BATCH REQUEST. DOES NOT CONFORM TO SPEC";
            case USER_DEFINED_DAILY_LIMIT: return "DAILY API LIMIT EXCEEDED. SET BY ACCOUNT HOLDER";
            case ACCESS_FORBIDDEN: return "ACCESS DENIED. USER ID OR API KEY INVALID";
            case REQUEST_TOO_LARGE: return "REQUEST TOO LARGE. MAXIMUM SIZE IS 5MB FOR DATA AND 25MB FOR UPLOADS";
            case NO_ENDPOINT: return "ENDPOINT DOES NOT EXIST";
            case INTERNAL_SERVER_ERROR: return "FATAL EXCEPTION. REQUEST COULD NOT BE COMPLETED";
            case SERVER_OFFLINE: return "SERVER OFFLINE. MAINTENANCE IN PROGRESS";
            case CONNECT_TIMEOUT: return "TIMEOUT OCCURRED CONNECTING TO SERVER";
            case READ_TIMEOUT: return "TIMEOUT OCCURRED READING API RESPONSE";
            case TIMEOUT: return "TIMEOUT OCCURRED DURING API REQUEST";
            case DNS_LOOKUP_FAILED: return "ERROR RECEIVED FROM YOUR DNS RESOLVER";
            case TLS_PROTOCOL_ERROR: return "ERROR DURING TLS PROTOCOL HANDSHAKE";
            case URL_PARSING_ERROR: return "ERROR PARSING REQUEST URL";
            case NETWORK_IO_ERROR: return "IO ERROR DURING API REQUEST";
            case FILE_IO_ERROR: return "IO ERROR WRITING TO OUTPUT FILE";
            case INVALID_JSON_RESPONSE: return "INVALID JSON DATA RECEIVED";
            case NO_DATA: return "NO PAYLOAD DATA RECEIVED";
            case API_GATEWAY_ERROR: return "API GATEWAY ERROR";
            default: return String.format("API Error: %d", errorCode);
        }
    }
}
