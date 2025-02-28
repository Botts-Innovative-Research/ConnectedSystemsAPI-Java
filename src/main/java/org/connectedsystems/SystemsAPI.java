package org.connectedsystems;

import org.connectedsystems.datamodels.Properties;
import org.connectedsystems.datamodels.SystemResource;
import org.connectedsystems.net.APIRequest;
import org.connectedsystems.net.APIResponse;
import org.connectedsystems.net.Endpoint;
import org.connectedsystems.net.HttpRequestMethod;
import org.connectedsystems.util.SystemsQueryBuilder;

import java.io.IOException;
import java.util.Map;

import static org.connectedsystems.GsonFactory.gson;

/**
 * API for interacting with the Connected Systems API.
 * This class provides methods for listing, creating, updating, and deleting systems and subsystems.
 */
public class SystemsAPI {
    private final ConnectedSystemsAPI connectedSystemsAPI;

    protected SystemsAPI(ConnectedSystemsAPI connectedSystemsAPI) {
        this.connectedSystemsAPI = connectedSystemsAPI;
    }

    /**
     * List all {@link SystemResource} available from this server endpoint.
     * By default, only top level systems are included.
     *
     * @return {@link APIResponse} containing a list of {@link SystemResource} objects.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<SystemResource> getSystems() throws IOException {
        return getSystems(new SystemsQueryBuilder());
    }

    /**
     * List all {@link SystemResource} available from this server endpoint.
     *
     * @param queryParams The query parameters to filter the results.
     * @return {@link APIResponse} containing a list of {@link SystemResource} objects.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<SystemResource> getSystems(SystemsQueryBuilder queryParams) throws IOException {
        APIRequest apiRequest = new APIRequest.APIRequestBuilder()
                .setApiRoot(connectedSystemsAPI.apiRoot)
                .setResourcePath(Endpoint.SYSTEMS_COLLECTION)
                .setAuthorizationToken(connectedSystemsAPI.authorizationToken)
                .setRequestMethod(HttpRequestMethod.GET)
                .setQueryParams(queryParams.getParameters())
                .build();

        return apiRequest.execute(SystemResource.class);
    }

    /**
     * List all {@link SystemResource} that are subsystems (i.e., components) of a specific parent system.
     *
     * @param systemId The ID of the system to get subsystems for.
     * @return {@link APIResponse} containing a list of {@link SystemResource} objects.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<SystemResource> getSubsystems(String systemId) throws IOException {
        return getSubsystems(systemId, new SystemsQueryBuilder());
    }

    /**
     * List all {@link SystemResource} that are subsystems (i.e., components) of a specific parent system.
     *
     * @param systemId    The ID of the system to get subsystems for.
     * @param queryParams The query parameters to filter the results.
     * @return {@link APIResponse} containing a list of {@link SystemResource} objects.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<SystemResource> getSubsystems(String systemId, SystemsQueryBuilder queryParams) throws IOException {
        APIRequest apiRequest = new APIRequest.APIRequestBuilder()
                .setApiRoot(connectedSystemsAPI.apiRoot)
                .setResourcePath(Endpoint.SYSTEMS_COLLECTION)
                .setResourceId(systemId)
                .setSubResourcePath(Endpoint.SUBSYSTEMS_COLLECTION)
                .setAuthorizationToken(connectedSystemsAPI.authorizationToken)
                .setRequestMethod(HttpRequestMethod.GET)
                .setQueryParams(queryParams.getParameters())
                .build();

        return apiRequest.execute(SystemResource.class);
    }

    /**
     * Get the latest {@link SystemResource} valid before or at the current time, by default.
     *
     * @param systemId The ID of the system to get the latest version of.
     * @return {@link APIResponse} containing the latest {@link SystemResource} object.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<SystemResource> getSystem(String systemId) throws IOException {
        APIRequest apiRequest = new APIRequest.APIRequestBuilder()
                .setApiRoot(connectedSystemsAPI.apiRoot)
                .setResourcePath(Endpoint.SYSTEMS_COLLECTION)
                .setResourceId(systemId)
                .setAuthorizationToken(connectedSystemsAPI.authorizationToken)
                .setRequestMethod(HttpRequestMethod.GET)
                .build();

        return apiRequest.execute(SystemResource.class);
    }

    /**
     * Get the latest {@link SystemResource} valid before or at the current time, by default.
     *
     * @param systemUid The UID of the system to get the latest version of.
     * @return {@link APIResponse} containing the latest {@link SystemResource} object.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<SystemResource> getSystemByUid(String systemUid) throws IOException {
        APIRequest apiRequest = new APIRequest.APIRequestBuilder()
                .setApiRoot(connectedSystemsAPI.apiRoot)
                .setResourcePath(Endpoint.SYSTEMS_COLLECTION)
                .setAuthorizationToken(connectedSystemsAPI.authorizationToken)
                .setRequestMethod(HttpRequestMethod.GET)
                .setQueryParams(Map.of("uid", systemUid))
                .build();

        return apiRequest.execute(SystemResource.class);
    }

    /**
     * Add a new top-level {@link SystemResource} to the server (i.e., the system will have no parent).
     *
     * @param systemResource The {@link SystemResource} to add.
     * @return {@link APIResponse} with the response code of the create operation.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<Void> createSystem(SystemResource systemResource) throws IOException {
        String systemResourceJson = gson.toJson(systemResource);
        APIRequest apiRequest = new APIRequest.APIRequestBuilder()
                .setApiRoot(connectedSystemsAPI.apiRoot)
                .setResourcePath(Endpoint.SYSTEMS_COLLECTION)
                .setAuthorizationToken(connectedSystemsAPI.authorizationToken)
                .setBody(systemResourceJson)
                .setRequestMethod(HttpRequestMethod.POST)
                .build();

        return apiRequest.execute(Void.class);
    }

    /**
     * Add a new {@link SystemResource} as a subsystem of an existing system.
     *
     * @param systemId       The ID of the system to add the subsystem to.
     * @param systemResource The {@link SystemResource} to add as a subsystem.
     * @return {@link APIResponse} with the response code of the create operation.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<Void> createSubsystem(String systemId, SystemResource systemResource) throws IOException {
        String systemResourceJson = gson.toJson(systemResource);
        APIRequest apiRequest = new APIRequest.APIRequestBuilder()
                .setApiRoot(connectedSystemsAPI.apiRoot)
                .setResourcePath(Endpoint.SYSTEMS_COLLECTION)
                .setResourceId(systemId)
                .setSubResourcePath(Endpoint.SUBSYSTEMS_COLLECTION)
                .setAuthorizationToken(connectedSystemsAPI.authorizationToken)
                .setBody(systemResourceJson)
                .setRequestMethod(HttpRequestMethod.POST)
                .build();

        return apiRequest.execute(Void.class);
    }

    /**
     * This will completely replace the existing description of the system with the provided {@link SystemResource}.
     * <p>
     * If system history is supported
     * and the validTime {@link Properties#getValidTime()} property starts after the time of the previous description,
     * the provided description becomes the current one,
     * and all previous descriptions are made available via the history subcollection.
     *
     * @param systemId       The ID of the system to update.
     * @param systemResource The {@link SystemResource} to update the system with.
     * @return {@link APIResponse} with the response code of the update operation.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<Void> updateSystem(String systemId, SystemResource systemResource) throws IOException {
        String systemResourceJson = gson.toJson(systemResource);
        APIRequest apiRequest = new APIRequest.APIRequestBuilder()
                .setApiRoot(connectedSystemsAPI.apiRoot)
                .setResourcePath(Endpoint.SYSTEMS_COLLECTION)
                .setResourceId(systemId)
                .setAuthorizationToken(connectedSystemsAPI.authorizationToken)
                .setBody(systemResourceJson)
                .setRequestMethod(HttpRequestMethod.PUT)
                .build();

        return apiRequest.execute(Void.class);
    }

    /**
     * Delete the given {@link SystemResource} from the server and remove it from all collections it is associated to.
     *
     * @param systemId The ID of the {@link SystemResource} to delete.
     * @return {@link APIResponse} with the response code of the delete operation.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<Void> deleteSystem(String systemId) throws IOException {
        return deleteSystem(systemId, false);
    }

    /**
     * Delete the given {@link SystemResource} from the server and remove it from all collections it is associated to.
     *
     * @param systemId The ID of the {@link SystemResource} to delete.
     * @param cascade  If true, all associated sub-resources hosted by the same server
     *                 (sampling features, data streams, command streams, observations, and commands)
     *                 are also deleted.
     * @return {@link APIResponse} with the response code of the delete operation.
     * @throws IOException if an error occurs while making the API request.
     */
    public APIResponse<Void> deleteSystem(String systemId, boolean cascade) throws IOException {
        APIRequest apiRequest = new APIRequest.APIRequestBuilder()
                .setApiRoot(connectedSystemsAPI.apiRoot)
                .setResourcePath(Endpoint.SYSTEMS_COLLECTION)
                .setResourceId(systemId)
                .setAuthorizationToken(connectedSystemsAPI.authorizationToken)
                .setRequestMethod(HttpRequestMethod.DELETE)
                .setQueryParams(Map.of("cascade", String.valueOf(cascade)))
                .build();

        return apiRequest.execute(Void.class);
    }
}
