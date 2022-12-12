package com.neutrinoapi.client;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.nio.file.FileSystemException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Make a request to the Neutrino API
 */
public class NeutrinoAPIClient {

    public static final String MULTICLOUD_ENDPOINT = "https://neutrinoapi.net/";
    public static final String AWS_ENDPOINT = "https://aws.neutrinoapi.net/";
    public static final String GCP_ENDPOINT = "https://gcp.neutrinoapi.net/";
    public static final String MS_AZURE_ENDPOINT = "https://msa.neutrinoapi.net/";

    private static final long CONNECT_TIMEOUT_MS = TimeUnit.SECONDS.toMillis(10);

    private final String userID;
    private final String apiKey;
    private final String baseURL;

    /**
     * Initialize API client - Default
     *
     * @param userID from Neutrino API admin
     * @param APIKey from Neutrino API admin
     */
    public NeutrinoAPIClient(String userID, String APIKey) {
        this.userID = userID;
        this.apiKey = APIKey;
        this.baseURL = MULTICLOUD_ENDPOINT;
    }

    /**
     * Initialize API client, with base-URL
     *
     * @param userID from Neutrino API admin
     * @param APIKey from Neutrino API admin
     * @param baseURL overrides the API's baseURL default
     */
    public NeutrinoAPIClient(String userID, String APIKey, String baseURL) {
        this.userID = userID;
        this.apiKey = APIKey;
        this.baseURL = baseURL;
    }

    /**
     * Detect bad words, swear words and profanity in a given text
     * <p>The parameters this API accepts are:</p>
     * <ul>
     * <li>"censor-character" - The character to use to censor out the bad words found</li>
     * <li>"catalog" - Which catalog of bad words to use</li>
     * <li>"content" - The content to scan</li>
     * </ul>
     *
     * @param params The API request parameters
     * @return APIResponse
     * @see <a href="https://www.neutrinoapi.com/api/bad-word-filter">Documentation</a>
     */
    public APIResponse badWordFilter(Map<String, String> params) {
        return execRequest("POST", "bad-word-filter", params, null, 30L);
    }

    /**
     * Download our entire BIN database for direct use on your own systems
     * <p>The parameters this API accepts are:</p>
     * <ul>
     * <li>"include-iso3" - Include ISO 3-letter country codes and ISO 3-letter currency codes in the data</li>
     * <li>"include-8digit" - Include 8-digit and higher BIN codes</li>
     * </ul>
     *
     * @param params The API request parameters
     * @param outputFilePath to where the file is to be stored
     * @return APIResponse
     * @see <a href="https://www.neutrinoapi.com/api/bin-list-download">Documentation</a>
     */
    public APIResponse binListDownload(Map<String, String> params, Path outputFilePath) {
        return execRequest("POST", "bin-list-download", params, outputFilePath, 30L);
    }

    /**
     * Perform a BIN (Bank Identification Number) or IIN (Issuer Identification Number) lookup
     * <p>The parameters this API accepts are:</p>
     * <ul>
     * <li>"bin-number" - The BIN or IIN number</li>
     * <li>"customer-ip" - Pass in the customers IP address and we will return some extra information about them</li>
     * </ul>
     *
     * @param params The API request parameters
     * @return APIResponse
     * @see <a href="https://www.neutrinoapi.com/api/bin-lookup">Documentation</a>
     */
    public APIResponse binLookup(Map<String, String> params) {
        return execRequest("GET", "bin-lookup", params, null, 10L);
    }

    /**
     * Browser bot can extract content, interact with keyboard and mouse events, and execute JavaScript on a website
     * <p>The parameters this API accepts are:</p>
     * <ul>
     * <li>"delay" - Delay in seconds to wait before capturing any page data</li>
     * <li>"ignore-certificate-errors" - Ignore any TLS/SSL certificate errors and load the page anyway</li>
     * <li>"selector" - Extract content from the page DOM using this selector</li>
     * <li>"url" - The URL to load</li>
     * <li>"timeout" - Timeout in seconds</li>
     * <li>"exec" - Execute JavaScript on the website</li>
     * <li>"user-agent" - Override the browsers default user-agent string with this one</li>
     * </ul>
     *
     * @param params The API request parameters
     * @return APIResponse
     * @see <a href="https://www.neutrinoapi.com/api/browser-bot">Documentation</a>
     */
    public APIResponse browserBot(Map<String, String> params) {
        return execRequest("POST", "browser-bot", params, null, 300L);
    }

    /**
     * A currency and unit conversion tool
     * <p>The parameters this API accepts are:</p>
     * <ul>
     * <li>"from-value" - The value to convert from (e.g. 10.95)</li>
     * <li>"from-type" - The type of the value to convert from (e.g. USD)</li>
     * <li>"to-type" - The type to convert to (e.g. EUR)</li>
     * </ul>
     *
     * @param params The API request parameters
     * @return APIResponse
     * @see <a href="https://www.neutrinoapi.com/api/convert">Documentation</a>
     */
    public APIResponse convert(Map<String, String> params) {
        return execRequest("GET", "convert", params, null, 10L);
    }

    /**
     * Parse, validate and clean an email address
     * <p>The parameters this API accepts are:</p>
     * <ul>
     * <li>"email" - An email address</li>
     * <li>"fix-typos" - Automatically attempt to fix typos in the address</li>
     * </ul>
     *
     * @param params The API request parameters
     * @return APIResponse
     * @see <a href="https://www.neutrinoapi.com/api/email-validate">Documentation</a>
     */
    public APIResponse emailValidate(Map<String, String> params) {
        return execRequest("GET", "email-validate", params, null, 30L);
    }

    /**
     * SMTP based email address verification
     * <p>The parameters this API accepts are:</p>
     * <ul>
     * <li>"email" - An email address</li>
     * <li>"fix-typos" - Automatically attempt to fix typos in the address</li>
     * </ul>
     *
     * @param params The API request parameters
     * @return APIResponse
     * @see <a href="https://www.neutrinoapi.com/api/email-verify">Documentation</a>
     */
    public APIResponse emailVerify(Map<String, String> params) {
        return execRequest("GET", "email-verify", params, null, 120L);
    }

    /**
     * Geocode an address, partial address or just the name of a place
     * <p>The parameters this API accepts are:</p>
     * <ul>
     * <li>"address" - The full address</li>
     * <li>"house-number" - The house/building number to locate</li>
     * <li>"street" - The street/road name to locate</li>
     * <li>"city" - The city/town name to locate</li>
     * <li>"county" - The county/region name to locate</li>
     * <li>"state" - The state name to locate</li>
     * <li>"postal-code" - The postal code to locate</li>
     * <li>"country-code" - Limit result to this country (the default is no country bias)</li>
     * <li>"language-code" - The language to display results in</li>
     * <li>"fuzzy-search" - If no matches are found for the given address</li>
     * </ul>
     *
     * @param params The API request parameters
     * @return APIResponse
     * @see <a href="https://www.neutrinoapi.com/api/geocode-address">Documentation</a>
     */
    public APIResponse geocodeAddress(Map<String, String> params) {
        return execRequest("GET", "geocode-address", params, null, 30L);
    }

    /**
     * Convert a geographic coordinate (latitude and longitude) into a real world address
     * <p>The parameters this API accepts are:</p>
     * <ul>
     * <li>"latitude" - The location latitude in decimal degrees format</li>
     * <li>"longitude" - The location longitude in decimal degrees format</li>
     * <li>"language-code" - The language to display results in</li>
     * <li>"zoom" - The zoom level to respond with: address - the most precise address available street - the street level city - the city level state - the state level country - the country level </li>
     * </ul>
     *
     * @param params The API request parameters
     * @return APIResponse
     * @see <a href="https://www.neutrinoapi.com/api/geocode-reverse">Documentation</a>
     */
    public APIResponse geocodeReverse(Map<String, String> params) {
        return execRequest("GET", "geocode-reverse", params, null, 30L);
    }

    /**
     * Connect to the global mobile cellular network and retrieve the status of a mobile device
     * <p>The parameters this API accepts are:</p>
     * <ul>
     * <li>"number" - A phone number</li>
     * <li>"country-code" - ISO 2-letter country code</li>
     * </ul>
     *
     * @param params The API request parameters
     * @return APIResponse
     * @see <a href="https://www.neutrinoapi.com/api/hlr-lookup">Documentation</a>
     */
    public APIResponse hlrLookup(Map<String, String> params) {
        return execRequest("GET", "hlr-lookup", params, null, 30L);
    }

    /**
     * Check the reputation of an IP address, domain name or URL against a comprehensive list of blacklists and blocklists
     * <p>The parameters this API accepts are:</p>
     * <ul>
     * <li>"host" - An IP address</li>
     * <li>"list-rating" - Only check lists with this rating or better</li>
     * <li>"zones" - Only check these DNSBL zones/hosts</li>
     * </ul>
     *
     * @param params The API request parameters
     * @return APIResponse
     * @see <a href="https://www.neutrinoapi.com/api/host-reputation">Documentation</a>
     */
    public APIResponse hostReputation(Map<String, String> params) {
        return execRequest("GET", "host-reputation", params, null, 120L);
    }

    /**
     * Clean and sanitize untrusted HTML
     * <p>The parameters this API accepts are:</p>
     * <ul>
     * <li>"output-type" - The level of sanitization</li>
     * <li>"content" - The HTML content</li>
     * </ul>
     *
     * @param params The API request parameters
     * @param outputFilePath to where the file is to be stored
     * @return APIResponse
     * @see <a href="https://www.neutrinoapi.com/api/html-clean">Documentation</a>
     */
    public APIResponse htmlClean(Map<String, String> params, Path outputFilePath) {
        return execRequest("POST", "html-clean", params, outputFilePath, 30L);
    }

    /**
     * Render HTML content to PDF, JPG or PNG
     * <p>The parameters this API accepts are:</p>
     * <ul>
     * <li>"margin" - The document margin (in mm)</li>
     * <li>"css" - Inject custom CSS into the HTML</li>
     * <li>"image-width" - If rendering to an image format (PNG or JPG) use this image width (in pixels)</li>
     * <li>"footer" - The footer HTML to insert into each page</li>
     * <li>"format" - Which format to output</li>
     * <li>"zoom" - Set the zoom factor when rendering the page (2.0 for double size</li>
     * <li>"title" - The document title</li>
     * <li>"content" - The HTML content</li>
     * <li>"page-width" - Set the PDF page width explicitly (in mm)</li>
     * <li>"timeout" - Timeout in seconds</li>
     * <li>"margin-right" - The document right margin (in mm)</li>
     * <li>"grayscale" - Render the final document in grayscale</li>
     * <li>"margin-left" - The document left margin (in mm)</li>
     * <li>"page-size" - Set the document page size</li>
     * <li>"delay" - Number of seconds to wait before rendering the page (can be useful for pages with animations etc)</li>
     * <li>"ignore-certificate-errors" - Ignore any TLS/SSL certificate errors</li>
     * <li>"page-height" - Set the PDF page height explicitly (in mm)</li>
     * <li>"image-height" - If rendering to an image format (PNG or JPG) use this image height (in pixels)</li>
     * <li>"header" - The header HTML to insert into each page</li>
     * <li>"margin-top" - The document top margin (in mm)</li>
     * <li>"margin-bottom" - The document bottom margin (in mm)</li>
     * <li>"landscape" - Set the document to landscape orientation</li>
     * </ul>
     *
     * @param params The API request parameters
     * @param outputFilePath to where the file is to be stored
     * @return APIResponse
     * @see <a href="https://www.neutrinoapi.com/api/html-render">Documentation</a>
     */
    public APIResponse htmlRender(Map<String, String> params, Path outputFilePath) {
        return execRequest("POST", "html-render", params, outputFilePath, 300L);
    }

    /**
     * Resize an image and output as either JPEG or PNG
     * <p>The parameters this API accepts are:</p>
     * <ul>
     * <li>"width" - The width to resize to (in px) while preserving aspect ratio</li>
     * <li>"format" - The output image format</li>
     * <li>"image-url" - The URL or Base64 encoded Data URL for the source image (you can also upload an image file directly in which case this field is ignored)</li>
     * <li>"height" - The height to resize to (in px) while preserving aspect ratio</li>
     * </ul>
     *
     * @param params The API request parameters
     * @param outputFilePath to where the file is to be stored
     * @return APIResponse
     * @see <a href="https://www.neutrinoapi.com/api/image-resize">Documentation</a>
     */
    public APIResponse imageResize(Map<String, String> params, Path outputFilePath) {
        return execRequest("POST", "image-resize", params, outputFilePath, 20L);
    }

    /**
     * Watermark one image with another image
     * <p>The parameters this API accepts are:</p>
     * <ul>
     * <li>"format" - The output image format</li>
     * <li>"width" - If set resize the resulting image to this width (in px) while preserving aspect ratio</li>
     * <li>"image-url" - The URL or Base64 encoded Data URL for the source image (you can also upload an image file directly in which case this field is ignored)</li>
     * <li>"position" - The position of the watermark image</li>
     * <li>"watermark-url" - The URL or Base64 encoded Data URL for the watermark image (you can also upload an image file directly in which case this field is ignored)</li>
     * <li>"opacity" - The opacity of the watermark (0 to 100)</li>
     * <li>"height" - If set resize the resulting image to this height (in px) while preserving aspect ratio</li>
     * </ul>
     *
     * @param params The API request parameters
     * @param outputFilePath to where the file is to be stored
     * @return APIResponse
     * @see <a href="https://www.neutrinoapi.com/api/image-watermark">Documentation</a>
     */
    public APIResponse imageWatermark(Map<String, String> params, Path outputFilePath) {
        return execRequest("POST", "image-watermark", params, outputFilePath, 20L);
    }

    /**
     * The IP Blocklist API will detect potentially malicious or dangerous IP addresses
     * <p>The parameters this API accepts are:</p>
     * <ul>
     * <li>"ip" - An IPv4 or IPv6 address</li>
     * <li>"vpn-lookup" - Include public VPN provider IP addresses</li>
     * </ul>
     *
     * @param params The API request parameters
     * @return APIResponse
     * @see <a href="https://www.neutrinoapi.com/api/ip-blocklist">Documentation</a>
     */
    public APIResponse ipBlocklist(Map<String, String> params) {
        return execRequest("GET", "ip-blocklist", params, null, 10L);
    }

    /**
     * This API is a direct feed to our IP blocklist data
     * <p>The parameters this API accepts are:</p>
     * <ul>
     * <li>"format" - The data format</li>
     * <li>"include-vpn" - Include public VPN provider IP addresses</li>
     * <li>"cidr" - Output IPs using CIDR notation</li>
     * <li>"ip6" - Output the IPv6 version of the blocklist</li>
     * </ul>
     *
     * @param params The API request parameters
     * @param outputFilePath to where the file is to be stored
     * @return APIResponse
     * @see <a href="https://www.neutrinoapi.com/api/ip-blocklist-download">Documentation</a>
     */
    public APIResponse ipBlocklistDownload(Map<String, String> params, Path outputFilePath) {
        return execRequest("POST", "ip-blocklist-download", params, outputFilePath, 30L);
    }

    /**
     * Get location information about an IP address and do reverse DNS (PTR) lookups
     * <p>The parameters this API accepts are:</p>
     * <ul>
     * <li>"ip" - IPv4 or IPv6 address</li>
     * <li>"reverse-lookup" - Do a reverse DNS (PTR) lookup</li>
     * </ul>
     *
     * @param params The API request parameters
     * @return APIResponse
     * @see <a href="https://www.neutrinoapi.com/api/ip-info">Documentation</a>
     */
    public APIResponse ipInfo(Map<String, String> params) {
        return execRequest("GET", "ip-info", params, null, 10L);
    }

    /**
     * Analyze and extract provider information for an IP address
     * <p>The parameters this API accepts are:</p>
     * <ul>
     * <li>"ip" - IPv4 or IPv6 address</li>
     * </ul>
     *
     * @param params The API request parameters
     * @return APIResponse
     * @see <a href="https://www.neutrinoapi.com/api/ip-probe">Documentation</a>
     */
    public APIResponse ipProbe(Map<String, String> params) {
        return execRequest("GET", "ip-probe", params, null, 120L);
    }

    /**
     * Make an automated call to any valid phone number and playback an audio message
     * <p>The parameters this API accepts are:</p>
     * <ul>
     * <li>"number" - The phone number to call</li>
     * <li>"limit" - Limit the total number of calls allowed to the supplied phone number</li>
     * <li>"audio-url" - A URL to a valid audio file</li>
     * <li>"limit-ttl" - Set the TTL in number of days that the 'limit' option will remember a phone number (the default is 1 day and the maximum is 365 days)</li>
     * </ul>
     *
     * @param params The API request parameters
     * @return APIResponse
     * @see <a href="https://www.neutrinoapi.com/api/phone-playback">Documentation</a>
     */
    public APIResponse phonePlayback(Map<String, String> params) {
        return execRequest("POST", "phone-playback", params, null, 30L);
    }

    /**
     * Parse, validate and get location information about a phone number
     * <p>The parameters this API accepts are:</p>
     * <ul>
     * <li>"number" - A phone number</li>
     * <li>"country-code" - ISO 2-letter country code</li>
     * <li>"ip" - Pass in a users IP address and we will assume numbers are based in the country of the IP address</li>
     * </ul>
     *
     * @param params The API request parameters
     * @return APIResponse
     * @see <a href="https://www.neutrinoapi.com/api/phone-validate">Documentation</a>
     */
    public APIResponse phoneValidate(Map<String, String> params) {
        return execRequest("GET", "phone-validate", params, null, 10L);
    }

    /**
     * Make an automated call to any valid phone number and playback a unique security code
     * <p>The parameters this API accepts are:</p>
     * <ul>
     * <li>"number" - The phone number to send the verification code to</li>
     * <li>"country-code" - ISO 2-letter country code</li>
     * <li>"security-code" - Pass in your own security code</li>
     * <li>"language-code" - The language to playback the verification code in</li>
     * <li>"code-length" - The number of digits to use in the security code (between 4 and 12)</li>
     * <li>"limit" - Limit the total number of calls allowed to the supplied phone number</li>
     * <li>"playback-delay" - The delay in milliseconds between the playback of each security code</li>
     * <li>"limit-ttl" - Set the TTL in number of days that the 'limit' option will remember a phone number (the default is 1 day and the maximum is 365 days)</li>
     * </ul>
     *
     * @param params The API request parameters
     * @return APIResponse
     * @see <a href="https://www.neutrinoapi.com/api/phone-verify">Documentation</a>
     */
    public APIResponse phoneVerify(Map<String, String> params) {
        return execRequest("POST", "phone-verify", params, null, 30L);
    }

    /**
     * Generate a QR code as a PNG image
     * <p>The parameters this API accepts are:</p>
     * <ul>
     * <li>"width" - The width of the QR code (in px)</li>
     * <li>"fg-color" - The QR code foreground color</li>
     * <li>"bg-color" - The QR code background color</li>
     * <li>"content" - The content to encode into the QR code (e.g. a URL or a phone number)</li>
     * <li>"height" - The height of the QR code (in px)</li>
     * </ul>
     *
     * @param params The API request parameters
     * @param outputFilePath to where the file is to be stored
     * @return APIResponse
     * @see <a href="https://www.neutrinoapi.com/api/qr-code">Documentation</a>
     */
    public APIResponse qrCode(Map<String, String> params, Path outputFilePath) {
        return execRequest("POST", "qr-code", params, outputFilePath, 20L);
    }

    /**
     * Send a free-form message to any mobile device via SMS
     * <p>The parameters this API accepts are:</p>
     * <ul>
     * <li>"number" - The phone number to send a message to</li>
     * <li>"country-code" - ISO 2-letter country code</li>
     * <li>"limit" - Limit the total number of SMS allowed to the supplied phone number</li>
     * <li>"message" - The SMS message to send</li>
     * <li>"limit-ttl" - Set the TTL in number of days that the 'limit' option will remember a phone number (the default is 1 day and the maximum is 365 days)</li>
     * </ul>
     *
     * @param params The API request parameters
     * @return APIResponse
     * @see <a href="https://www.neutrinoapi.com/api/sms-message">Documentation</a>
     */
    public APIResponse smsMessage(Map<String, String> params) {
        return execRequest("POST", "sms-message", params, null, 30L);
    }

    /**
     * Send a unique security code to any mobile device via SMS
     * <p>The parameters this API accepts are:</p>
     * <ul>
     * <li>"number" - The phone number to send a verification code to</li>
     * <li>"country-code" - ISO 2-letter country code</li>
     * <li>"security-code" - Pass in your own security code</li>
     * <li>"language-code" - The language to send the verification code in</li>
     * <li>"code-length" - The number of digits to use in the security code (must be between 4 and 12)</li>
     * <li>"limit" - Limit the total number of SMS allowed to the supplied phone number</li>
     * <li>"limit-ttl" - Set the TTL in number of days that the 'limit' option will remember a phone number (the default is 1 day and the maximum is 365 days)</li>
     * </ul>
     *
     * @param params The API request parameters
     * @return APIResponse
     * @see <a href="https://www.neutrinoapi.com/api/sms-verify">Documentation</a>
     */
    public APIResponse smsVerify(Map<String, String> params) {
        return execRequest("POST", "sms-verify", params, null, 30L);
    }

    /**
     * Parse, validate and get detailed user-agent information from a user agent string or from client hints
     * <p>The parameters this API accepts are:</p>
     * <ul>
     * <li>"ua" - The user-agent string to lookup</li>
     * <li>"ua-version" - For client hints this corresponds to the 'UA-Full-Version' header or 'uaFullVersion' from NavigatorUAData</li>
     * <li>"ua-platform" - For client hints this corresponds to the 'UA-Platform' header or 'platform' from NavigatorUAData</li>
     * <li>"ua-platform-version" - For client hints this corresponds to the 'UA-Platform-Version' header or 'platformVersion' from NavigatorUAData</li>
     * <li>"ua-mobile" - For client hints this corresponds to the 'UA-Mobile' header or 'mobile' from NavigatorUAData</li>
     * <li>"device-model" - For client hints this corresponds to the 'UA-Model' header or 'model' from NavigatorUAData</li>
     * <li>"device-brand" - This parameter is only used in combination with 'device-model' when doing direct device lookups without any user-agent data</li>
     * </ul>
     *
     * @param params The API request parameters
     * @return APIResponse
     * @see <a href="https://www.neutrinoapi.com/api/ua-lookup">Documentation</a>
     */
    public APIResponse uaLookup(Map<String, String> params) {
        return execRequest("GET", "ua-lookup", params, null, 10L);
    }

    /**
     * Parse, analyze and retrieve content from the supplied URL
     * <p>The parameters this API accepts are:</p>
     * <ul>
     * <li>"url" - The URL to probe</li>
     * <li>"fetch-content" - If this URL responds with html</li>
     * <li>"ignore-certificate-errors" - Ignore any TLS/SSL certificate errors and load the URL anyway</li>
     * <li>"timeout" - Timeout in seconds</li>
     * <li>"retry" - If the request fails for any reason try again this many times</li>
     * </ul>
     *
     * @param params The API request parameters
     * @return APIResponse
     * @see <a href="https://www.neutrinoapi.com/api/url-info">Documentation</a>
     */
    public APIResponse urlInfo(Map<String, String> params) {
        return execRequest("GET", "url-info", params, null, 30L);
    }

    /**
     * Check if a security code sent via SMS Verify or Phone Verify is valid
     * <p>The parameters this API accepts are:</p>
     * <ul>
     * <li>"security-code" - The security code to verify</li>
     * <li>"limit-by" - If set then enable additional brute-force protection by limiting the number of attempts by the supplied value</li>
     * </ul>
     *
     * @param params The API request parameters
     * @return APIResponse
     * @see <a href="https://www.neutrinoapi.com/api/verify-security-code">Documentation</a>
     */
    public APIResponse verifySecurityCode(Map<String, String> params) {
        return execRequest("GET", "verify-security-code", params, null, 30L);
    }

    /**
     * Make a request to the Neutrino API
     */
    private APIResponse execRequest(String httpMethod, String endpoint, Map<String, String> params, Path outputFilePath, long timeoutInSeconds) {
        APIResponse response = APIResponse.of(APIResponse.NO_STATUS, APIResponse.NO_CONTENT_TYPE, APIErrorCode.NO_DATA);
        HttpRequestBase httpReq;
        try ( CloseableHttpClient client = HttpClients.createDefault()) {
            // Get Ready
            URIBuilder uriBuilder = new URIBuilder(String.format("%s%s", this.baseURL, endpoint));
            if (httpMethod.equals("GET")) {
                // Request using HTTP GET
                params.forEach(uriBuilder::setParameter);
                httpReq = new HttpGet(uriBuilder.build());
            } else {
                // Using using HTTP POST
                List<NameValuePair> postData = new ArrayList<>();
                params.forEach((key, value) -> postData.add(new BasicNameValuePair(key, value)));
                httpReq = new HttpPost(uriBuilder.build());
                ((HttpEntityEnclosingRequestBase) httpReq).setEntity(new UrlEncodedFormEntity(postData, "UTF-8"));
            }
            httpReq.setHeader("User-ID", this.userID);
            httpReq.setHeader("API-Key", this.apiKey);
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(Math.toIntExact(CONNECT_TIMEOUT_MS))
                    .setSocketTimeout(Math.toIntExact(TimeUnit.SECONDS.toMillis(timeoutInSeconds)))
                    .build();
            httpReq.setConfig(config);

            // Execute
            try ( CloseableHttpResponse httpResponse = client.execute(httpReq)) {
                String contentType = "";
                if (httpResponse.containsHeader("Content-Type")) {
                    contentType = httpResponse.getFirstHeader("Content-Type").getValue();
                }
                HttpEntity entity = httpResponse.getEntity();
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                if (statusCode == 200 && entity != null) {
                    if (contentType.contains("application/json")) {
                        String rawResponse = EntityUtils.toString(entity, "UTF-8");
                        JsonObject json = JsonParser.parseString(rawResponse).getAsJsonObject();
                        response = APIResponse.of(statusCode, contentType, json);
                    } else if (outputFilePath != null) {
                        try ( FileOutputStream os = new FileOutputStream(outputFilePath.toFile())) {
                            entity.writeTo(os);
                        }
                        if (outputFilePath.toFile().length() > 0) {
                            response = APIResponse.of(statusCode, contentType, outputFilePath);
                        }
                    } else {
                        String rawResponse = EntityUtils.toString(entity, "UTF-8");
                        response = APIResponse.of(statusCode, contentType, APIErrorCode.API_GATEWAY_ERROR, rawResponse);
                    }
                } else {
                    String rawResponse = (entity != null) ? EntityUtils.toString(entity, "UTF-8") : "";
                    if (contentType.contains("application/json")) {
                        JsonObject json = JsonParser.parseString(rawResponse).getAsJsonObject();
                        if (json.has("api-error") && json.has("api-error-msg")) {
                            int errorCode = json.get("api-error").getAsInt();
                            String errorMessage = json.get("api-error-msg").getAsString();
                            if (errorCode == 1) {
                                errorMessage = String.format("%s, Name: %s, Type: %s", errorMessage,
                                        json.get("api-parameter-name").getAsString(),
                                        json.get("api-parameter-type").getAsString());
                            }
                            response = APIResponse.of(statusCode, contentType, errorCode, errorMessage);
                        }
                    } else {
                        response = APIResponse.of(statusCode, contentType, APIErrorCode.API_GATEWAY_ERROR, rawResponse);
                    }
                }
            }
        } catch (ClientProtocolException | SSLException e) {
            response = APIResponse.of(APIErrorCode.TLS_PROTOCOL_ERROR, e);
        } catch (URISyntaxException e) {
            response = APIResponse.of(APIErrorCode.URL_PARSING_ERROR, e);
        } catch (JsonSyntaxException e) {
            response = APIResponse.of(APIErrorCode.INVALID_JSON, e);
        } catch (ConnectTimeoutException | HttpHostConnectException e) {
            response = APIResponse.of(APIErrorCode.CONNECT_TIMEOUT, e);
        } catch (SocketTimeoutException e) {
            response = APIResponse.of(APIErrorCode.READ_TIMEOUT, e);
        } catch (UnknownHostException e) {
            response = APIResponse.of(APIErrorCode.DNS_LOOKUP_FAILED, e);
        } catch (FileNotFoundException | FileSystemException e) {
            response = APIResponse.of(APIErrorCode.FILE_IO_ERROR, e);
        } catch (IOException e) {
            response = APIResponse.of(APIErrorCode.NETWORK_IO_ERROR, e);
        }
        return response;
    }
}
