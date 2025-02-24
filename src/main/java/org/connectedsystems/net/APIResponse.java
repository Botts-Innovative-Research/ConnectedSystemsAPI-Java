package org.connectedsystems.net;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class APIResponse<T> {
    private final int responseCode;
    private final String responseMessage;
    private final String responseBody;
    private final List<T> items;

    public APIResponse(APIRequest apiRequest, Class<T> clazz, boolean isSingleItem) {
        this(apiRequest.getResponseCode(), apiRequest.getResponseMessage(), apiRequest.getResponseBody(), clazz, isSingleItem);
    }

    public APIResponse(int responseCode, String responseMessage, String responseBody, Class<T> clazz, boolean isSingleItem) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.responseBody = responseBody;
        if (isSingleItem) {
            items = new ArrayList<>();
            items.add(deserializeItem(clazz));
        } else {
            this.items = deserializeItems(clazz);
        }
    }

    private T deserializeItem(Class<T> clazz) {
        if (responseBody == null || responseBody.isEmpty()) return null;
        if (responseBody.charAt(0) != '{') return null;

        return new Gson().fromJson(responseBody, clazz);
    }

    private List<T> deserializeItems(Class<T> clazz) {
        if (responseBody == null || responseBody.isEmpty()) return Collections.emptyList();
        if (responseBody.charAt(0) != '{') return Collections.emptyList();

        APIResponseItems<T> apiResponse = APIResponseItems.fromJson(responseBody, clazz);
        return apiResponse.getItems();
    }

    public boolean isSuccessful() {
        return responseCode >= 200 && responseCode < 300;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public List<T> getItems() {
        return items;
    }
}
