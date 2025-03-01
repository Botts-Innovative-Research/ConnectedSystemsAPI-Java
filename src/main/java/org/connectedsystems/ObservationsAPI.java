package org.connectedsystems;

import net.opengis.swe.v20.DataComponent;
import org.connectedsystems.datamodels.ObservationResource;
import org.connectedsystems.datamodels.ObservationSchema;
import org.connectedsystems.net.APIRequest;
import org.connectedsystems.net.APIResponse;
import org.connectedsystems.net.Endpoint;
import org.connectedsystems.net.HttpRequestMethod;
import org.connectedsystems.util.ObservationsOfDataStreamQueryBuilder;
import org.connectedsystems.util.ObservationsQueryBuilder;

import java.io.IOException;

public class ObservationsAPI {
    private final ConnectedSystemsAPI connectedSystemsAPI;

    public ObservationsAPI(ConnectedSystemsAPI connectedSystemsAPI) {
        this.connectedSystemsAPI = connectedSystemsAPI;
    }

    /**
     * List all observations available from this server endpoint.
     * <p>
     * Note: This method does not deserialize the observations; it only returns the raw JSON response.
     * Use {@link #getObservationsOfDataStream(String, DataComponent)} to deserialize the observations.
     *
     * @return {@link APIResponse} containing the list of observations.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<Void> getObservations() throws IOException {
        return getObservations(new ObservationsQueryBuilder());
    }

    /**
     * List all observations available from this server endpoint.
     * <p>
     * Note: This method does not deserialize the observations; it only returns the raw JSON response.
     * Use {@link #getObservationsOfDataStream(String, DataComponent, ObservationsOfDataStreamQueryBuilder)} to deserialize the observations.
     *
     * @param queryParams {@link ObservationsQueryBuilder} containing the query parameters.
     * @return {@link APIResponse} containing the list of observations.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<Void> getObservations(ObservationsQueryBuilder queryParams) throws IOException {
        APIRequest apiRequest = new APIRequest.APIRequestBuilder()
                .setApiRoot(connectedSystemsAPI.apiRoot)
                .setResourcePath(Endpoint.OBSERVATIONS_COLLECTION)
                .setAuthorizationToken(connectedSystemsAPI.authorizationToken)
                .setRequestMethod(HttpRequestMethod.GET)
                .setQueryParams(queryParams.getParameters())
                .build();

        return apiRequest.execute(Void.class);
    }

    /**
     * List all {@link ObservationResource} available from a specific datastream.
     *
     * @param dataStreamId the ID of the datastream.
     * @param resultSchema the schema of the observation result.
     *                     This is used to deserialize the result field of the observation.
     *                     Typically, this comes from {@link ObservationSchema#getResultSchema()} of the datastream.
     * @return {@link APIResponse} containing the list of {@link ObservationResource}.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<ObservationResource> getObservationsOfDataStream(String dataStreamId, DataComponent resultSchema) throws IOException {
        return getObservationsOfDataStream(dataStreamId, resultSchema, new ObservationsOfDataStreamQueryBuilder());
    }

    /**
     * List all {@link ObservationResource} available from a specific datastream.
     *
     * @param dataStreamId the ID of the datastream.
     * @param resultSchema the schema of the observation result.
     *                     This is used to deserialize the result field of the observation.
     *                     Typically, this comes from {@link ObservationSchema#getResultSchema()} of the datastream.
     * @param queryParams  {@link ObservationsOfDataStreamQueryBuilder} containing the query parameters.
     * @return {@link APIResponse} containing the list of {@link ObservationResource}.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<ObservationResource> getObservationsOfDataStream(String dataStreamId, DataComponent resultSchema, ObservationsOfDataStreamQueryBuilder queryParams) throws IOException {
        var gson = GsonFactory.createGson(resultSchema);
        APIRequest apiRequest = new APIRequest.APIRequestBuilder()
                .setApiRoot(connectedSystemsAPI.apiRoot)
                .setResourcePath(Endpoint.DATA_STREAMS_COLLECTION)
                .setResourceId(dataStreamId)
                .setSubResourcePath(Endpoint.OBSERVATIONS_COLLECTION)
                .setAuthorizationToken(connectedSystemsAPI.authorizationToken)
                .setRequestMethod(HttpRequestMethod.GET)
                .setQueryParams(queryParams.getParameters())
                .build();

        return apiRequest.execute(ObservationResource.class, gson);
    }

    /**
     * Get a specific observation by its ID.
     *
     * @param observationId the ID of the observation.
     * @param resultSchema  the schema of the observation result.
     *                      This is used to deserialize the result field of the observation.
     *                      Typically, this comes from {@link ObservationSchema#getResultSchema()} of the datastream.
     * @return {@link APIResponse} containing the {@link ObservationResource}.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<ObservationResource> getObservation(String observationId, DataComponent resultSchema) throws IOException {
        var gson = GsonFactory.createGson(resultSchema);
        APIRequest apiRequest = new APIRequest.APIRequestBuilder()
                .setApiRoot(connectedSystemsAPI.apiRoot)
                .setResourcePath(Endpoint.OBSERVATIONS_COLLECTION)
                .setResourceId(observationId)
                .setAuthorizationToken(connectedSystemsAPI.authorizationToken)
                .setRequestMethod(HttpRequestMethod.GET)
                .build();

        return apiRequest.execute(ObservationResource.class, gson);
    }

    /**
     * Add a new observation to an existing datastream.
     *
     * @param dataStreamId        the ID of the datastream.
     * @param observationResource the observation to add.
     * @return {@link APIResponse} containing the response from the server.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<Void> createObservation(String dataStreamId, ObservationResource observationResource, DataComponent resultSchema) throws IOException {
        var gson = GsonFactory.createGson(resultSchema);
        APIRequest apiRequest = new APIRequest.APIRequestBuilder()
                .setApiRoot(connectedSystemsAPI.apiRoot)
                .setResourcePath(Endpoint.DATA_STREAMS_COLLECTION)
                .setResourceId(dataStreamId)
                .setSubResourcePath(Endpoint.OBSERVATIONS_COLLECTION)
                .setAuthorizationToken(connectedSystemsAPI.authorizationToken)
                .setRequestMethod(HttpRequestMethod.POST)
                .setBody(gson.toJson(observationResource))
                .build();

        return apiRequest.execute(Void.class);
    }

    /**
     * Update an existing observation.
     *
     * @param observationId       the ID of the observation.
     * @param observationResource the updated observation.
     * @return {@link APIResponse} containing the response from the server.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<Void> updateObservation(String observationId, ObservationResource observationResource, DataComponent resultSchema) throws IOException {
        var gson = GsonFactory.createGson(resultSchema);
        APIRequest apiRequest = new APIRequest.APIRequestBuilder()
                .setApiRoot(connectedSystemsAPI.apiRoot)
                .setResourcePath(Endpoint.OBSERVATIONS_COLLECTION)
                .setResourceId(observationId)
                .setAuthorizationToken(connectedSystemsAPI.authorizationToken)
                .setRequestMethod(HttpRequestMethod.PUT)
                .setBody(gson.toJson(observationResource))
                .build();

        return apiRequest.execute(Void.class);
    }

    /**
     * Delete an existing observation.
     *
     * @param observationId the ID of the observation.
     * @return {@link APIResponse} containing the response from the server.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<Void> deleteObservation(String observationId) throws IOException {
        APIRequest apiRequest = new APIRequest.APIRequestBuilder()
                .setApiRoot(connectedSystemsAPI.apiRoot)
                .setResourcePath(Endpoint.OBSERVATIONS_COLLECTION)
                .setResourceId(observationId)
                .setAuthorizationToken(connectedSystemsAPI.authorizationToken)
                .setRequestMethod(HttpRequestMethod.DELETE)
                .build();

        return apiRequest.execute(Void.class);
    }
}
