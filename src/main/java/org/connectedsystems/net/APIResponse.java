package org.connectedsystems.net;

import com.google.common.net.HttpHeaders;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.connectedsystems.GsonFactory.gson;

/**
 * Represents the response from an API request.
 *
 * @param <T> The type of the items in the response,
 *            or {@link Void} if the response is not expected to contain any items,
 *            e.g., for POST, PUT, or DELETE requests.
 */
public class APIResponse<T> {
    private static final String JSON_ARRAY_ITEMS = "items";

    private final int responseCode;
    private final String responseMessage;
    private final String responseBody;
    private final List<T> items;
    private final boolean isSingleItem;
    private final Map<String, List<String>> headers;

    /**
     * Constructs an APIResponse object from the given parameters.
     *
     * @param clazz           The class type of the items in the response,
     *                        or {@link Void} if the response is not expected to contain any items,
     *                        e.g., for POST, PUT, or DELETE requests.
     * @param responseCode    The HTTP response code from the API request.
     * @param responseMessage The HTTP response message from the API request.
     * @param responseBody    The raw JSON response body from the API request,
     *                        or a string representation of the response if not in JSON format.
     * @param headers         The headers from the API response.
     */
    public APIResponse(Class<T> clazz, int responseCode, String responseMessage, String responseBody, Map<String, List<String>> headers) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.responseBody = responseBody;
        this.headers = headers;
        items = new ArrayList<>();

        if (clazz == Void.class || responseBody == null || responseBody.isEmpty() || responseBody.charAt(0) != '{') {
            this.isSingleItem = false;
            return;
        }

        var jsonObj = JsonParser.parseString(responseBody).getAsJsonObject();
        if (jsonObj.has(JSON_ARRAY_ITEMS)) {
            isSingleItem = false;
            jsonObj.get(JSON_ARRAY_ITEMS).getAsJsonArray().forEach(itemElement -> items.add(deserializeItem(clazz, itemElement)));
        } else {
            isSingleItem = true;
            items.add(deserializeItem(clazz, jsonObj));
        }
    }

    /**
     * Constructs an APIResponse object from the given APIRequest.
     *
     * @param clazz      The class type of the items in the response,
     *                   or {@link Void} if the response is not expected to contain any items,
     *                   e.g., for POST, PUT, or DELETE requests.
     * @param apiRequest The APIRequest object to get the response from.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse(Class<T> clazz, APIRequest apiRequest) throws IOException {
        this(clazz, apiRequest.getConnection().getResponseCode(), apiRequest.getConnection().getResponseMessage(), apiRequest.getResponseBody(), apiRequest.getConnection().getHeaderFields());
    }

    /**
     * Deserializes a single item from the JSON response.
     *
     * @param clazz       The class type of the item to deserialize.
     * @param jsonElement The JSON element representing the item.
     * @return The deserialized item of type T.
     */
    private T deserializeItem(Class<T> clazz, JsonElement jsonElement) {
        return gson.fromJson(jsonElement, clazz);
    }

    /**
     * @return True if the response code is in the 200-399 range, indicating a successful request.
     */
    public boolean isSuccessful() {
        return responseCode >= 200 && responseCode < 400;
    }

    /**
     * @return The HTTP response code from the API request.
     */
    public int getResponseCode() {
        return responseCode;
    }

    /**
     * @return The HTTP response message from the API request.
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * @return The raw JSON response body from the API request.
     * Use {@link #getItem()} or {@link #getItems()} to get the deserialized response.
     */
    public String getResponseBody() {
        return responseBody;
    }

    /**
     * @return True if the request was for a single item,
     * i.e., getting a single system by ID.
     * <p>
     * False if the request was for a list of items,
     * i.e., getting all systems, even if only one item is returned.
     */
    public boolean isSingleItem() {
        return isSingleItem;
    }

    /**
     * @return The headers from the API response.
     * For POST requests, this may include the {@link HttpHeaders#LOCATION} header with the URL of the created resource.
     */
    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    /**
     * @return The single item from the response, or null if the response is a list of items or no items are returned.
     */
    public T getItem() {
        return isSingleItem && !items.isEmpty() ? items.get(0) : null;
    }

    /**
     * @return The list of items from the response or an empty list if no items are returned.
     * If the response is a single item, the list will contain one item, and {@link #getItem()} will return the same item.
     */
    public List<T> getItems() {
        return items;
    }
}
