package org.connectedsystems;

import org.connectedsystems.datamodels.DataStreamResource;
import org.connectedsystems.datamodels.ObservationSchema;
import org.connectedsystems.net.APIRequest;
import org.connectedsystems.net.APIResponse;
import org.connectedsystems.net.Endpoint;
import org.connectedsystems.net.HttpRequestMethod;
import org.connectedsystems.util.DataStreamsQueryBuilder;

import java.io.IOException;
import java.util.Map;

import static org.connectedsystems.GsonFactory.gson;

public class DataStreamsAPI {
    private final ConnectedSystemsAPI connectedSystemsAPI;

    public DataStreamsAPI(ConnectedSystemsAPI connectedSystemsAPI) {
        this.connectedSystemsAPI = connectedSystemsAPI;
    }

    /**
     * List all {@link DataStreamResource} available from this server endpoint.
     *
     * @return {@link APIResponse} containing a list of {@link DataStreamResource}.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<DataStreamResource> getDataStreams() throws IOException {
        return getDataStreams(new DataStreamsQueryBuilder());
    }

    /**
     * List all {@link DataStreamResource} available from this server endpoint.
     *
     * @param queryParams The query parameters to filter the results.
     * @return {@link APIResponse} containing a list of {@link DataStreamResource}.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<DataStreamResource> getDataStreams(DataStreamsQueryBuilder queryParams) throws IOException {
        APIRequest apiRequest = new APIRequest.APIRequestBuilder()
                .setApiRoot(connectedSystemsAPI.apiRoot)
                .setResourcePath(Endpoint.DATA_STREAMS_COLLECTION)
                .setAuthorizationToken(connectedSystemsAPI.authorizationToken)
                .setRequestMethod(HttpRequestMethod.GET)
                .setQueryParams(queryParams.getParameters())
                .build();

        return apiRequest.execute(DataStreamResource.class);
    }

    /**
     * List all {@link DataStreamResource} available from the parent system.
     *
     * @param systemId The ID of the system to filter the results.
     * @return {@link APIResponse} containing a list of {@link DataStreamResource}.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<DataStreamResource> getDataStreamsOfSystem(String systemId) throws IOException {
        return getDataStreamsOfSystem(systemId, new DataStreamsQueryBuilder());
    }

    /**
     * List all {@link DataStreamResource} available from the parent system.
     *
     * @param systemId    The ID of the system to filter the results.
     * @param queryParams The query parameters to filter the results.
     * @return {@link APIResponse} containing a list of {@link DataStreamResource}.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<DataStreamResource> getDataStreamsOfSystem(String systemId, DataStreamsQueryBuilder queryParams) throws IOException {
        APIRequest apiRequest = new APIRequest.APIRequestBuilder()
                .setApiRoot(connectedSystemsAPI.apiRoot)
                .setResourcePath(Endpoint.SYSTEMS_COLLECTION)
                .setResourceId(systemId)
                .setSubResourcePath(Endpoint.DATA_STREAMS_COLLECTION)
                .setAuthorizationToken(connectedSystemsAPI.authorizationToken)
                .setRequestMethod(HttpRequestMethod.GET)
                .setQueryParams(queryParams.getParameters())
                .build();

        return apiRequest.execute(DataStreamResource.class);
    }

    /**
     * Get a specific {@link DataStreamResource} by its ID.
     *
     * @param dataStreamId The ID of the data stream to retrieve.
     * @return {@link APIResponse} containing the {@link DataStreamResource}.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<DataStreamResource> getDataStream(String dataStreamId) throws IOException {
        APIRequest apiRequest = new APIRequest.APIRequestBuilder()
                .setApiRoot(connectedSystemsAPI.apiRoot)
                .setResourcePath(Endpoint.DATA_STREAMS_COLLECTION)
                .setResourceId(dataStreamId)
                .setAuthorizationToken(connectedSystemsAPI.authorizationToken)
                .setRequestMethod(HttpRequestMethod.GET)
                .build();

        return apiRequest.execute(DataStreamResource.class);
    }

    /**
     * Create a new {@link DataStreamResource} for an existing system.
     *
     * @param systemId           The ID of the system to create the data stream for.
     * @param dataStreamResource The {@link DataStreamResource} to create.
     * @return {@link APIResponse} with the response code of the create operation.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<Void> createDataStream(String systemId, DataStreamResource dataStreamResource) throws IOException {
        APIRequest apiRequest = new APIRequest.APIRequestBuilder()
                .setApiRoot(connectedSystemsAPI.apiRoot)
                .setResourcePath(Endpoint.SYSTEMS_COLLECTION)
                .setResourceId(systemId)
                .setSubResourcePath(Endpoint.DATA_STREAMS_COLLECTION)
                .setAuthorizationToken(connectedSystemsAPI.authorizationToken)
                .setRequestMethod(HttpRequestMethod.POST)
                .setBody(gson.toJson(dataStreamResource))
                .build();

        return apiRequest.execute(Void.class);
    }

    /**
     * Update an existing data stream.
     * Note: This method will likely fail if the data stream has any associated observations.
     *
     * @param dataStreamId       The ID of the data stream to update.
     * @param dataStreamResource The {@link DataStreamResource} to update the data stream with.
     * @return {@link APIResponse} with the response code of the update operation.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<Void> updateDataStream(String dataStreamId, DataStreamResource dataStreamResource) throws IOException {
        APIRequest apiRequest = new APIRequest.APIRequestBuilder()
                .setApiRoot(connectedSystemsAPI.apiRoot)
                .setResourcePath(Endpoint.DATA_STREAMS_COLLECTION)
                .setResourceId(dataStreamId)
                .setAuthorizationToken(connectedSystemsAPI.authorizationToken)
                .setRequestMethod(HttpRequestMethod.PUT)
                .setBody(gson.toJson(dataStreamResource))
                .build();

        return apiRequest.execute(Void.class);
    }

    /**
     * Delete an existing data stream.
     *
     * @param dataStreamId The ID of the data stream to delete.
     * @return {@link APIResponse} with the response code of the delete operation.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<Void> deleteDataStream(String dataStreamId) throws IOException {
        return deleteDataStream(dataStreamId, false);
    }

    /**
     * Delete an existing data stream.
     *
     * @param dataStreamId The ID of the data stream to delete.
     * @param cascade      If true, all associated observations are also deleted.
     * @return {@link APIResponse} with the response code of the delete operation.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<Void> deleteDataStream(String dataStreamId, boolean cascade) throws IOException {
        APIRequest apiRequest = new APIRequest.APIRequestBuilder()
                .setApiRoot(connectedSystemsAPI.apiRoot)
                .setResourcePath(Endpoint.DATA_STREAMS_COLLECTION)
                .setResourceId(dataStreamId)
                .setAuthorizationToken(connectedSystemsAPI.authorizationToken)
                .setRequestMethod(HttpRequestMethod.DELETE)
                .setQueryParams(Map.of("cascade", String.valueOf(cascade)))
                .build();

        return apiRequest.execute(Void.class);
    }

    /**
     * Get the schema of a data stream.
     *
     * @param dataStreamId The ID of the data stream to retrieve the schema for.
     * @return {@link APIResponse} containing the {@link ObservationSchema}.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<ObservationSchema> getObservationSchema(String dataStreamId) throws IOException {
        APIRequest apiRequest = new APIRequest.APIRequestBuilder()
                .setApiRoot(connectedSystemsAPI.apiRoot)
                .setResourcePath(Endpoint.DATA_STREAMS_COLLECTION)
                .setResourceId(dataStreamId)
                .setSubResourcePath(Endpoint.DATA_STREAM_SCHEMA)
                .setAuthorizationToken(connectedSystemsAPI.authorizationToken)
                .setRequestMethod(HttpRequestMethod.GET)
                .build();

        return apiRequest.execute(ObservationSchema.class);
    }
}
