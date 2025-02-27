package org.connectedsystems.util;

import org.vast.util.TimeExtent;

import java.util.List;

/**
 * Query string parameters used to filter the results of a {@link org.connectedsystems.SystemsAPI#getSystems(SystemsQueryBuilder)} request.
 */
public class SystemsQueryBuilder extends QueryStringBuilder {
    /**
     * List of resource local IDs or unique IDs (URI).
     * Only resources that have one of the provided identifiers are selected.
     */
    public void id(List<String> id) {
        addParameter("id", id);
    }

    /**
     * The bounding box is provided as four or six numbers,
     * depending on whether the coordinate reference system includes a vertical axis (height or depth).
     */
    public void bbox(List<Float> bbox) {
        addParameter("bbox", bbox);
    }

    /**
     * Only features that have a validTime property that intersects the value of datetime are selected.
     * If history is supported for a feature type, the following also applies:
     * - If datetime is a time instant or now, only the description valid at the specified time is selected.
     * - If datetime is a time period, only the latest description valid during the period is selected.
     * - The response can never include more than one description of the same feature.
     */
    public void datetime(TimeExtent datetime) {
        addParameter("datetime", datetime);
    }

    /**
     * WKT geometry and operator to filter resources on their location or geometry.
     * Only features that have a geometry that intersects the value of geom are selected.
     * <p>
     * Examples:
     * - geom=LINESTRING((-86.53 12.45), (-86.54 12.46), (-86.55 12.47))
     * - geom=POLYGON((0 0,4 0,4 4,0 4,0 0))
     */
    public void geom(String geom) {
        addParameter("geom", geom);
    }

    /**
     * List of keywords used for full-text search.
     * Only resources that have textual fields that contain one of the specified keywords are selected.
     * The resource name and description properties are always searched.
     * It is up to the server to decide which other textual fields are searched.
     * <p>
     * Examples:
     * - q=temp
     * - q=gps,imu
     */
    public void q(List<String> q) {
        addParameter("q", q);
    }

    /**
     * List of resource local IDs or unique IDs (URI).
     * Only resources that have a parent that has one of the provided identifiers are selected.
     */
    public void parent(List<String> parent) {
        addParameter("parent", parent);
    }

    /**
     * List of procedure local IDs or unique IDs (URI).
     * Only systems that implement a procedure that has one of the provided identifiers are selected.
     */
    public void procedure(List<String> procedure) {
        addParameter("procedure", procedure);
    }

    /**
     * List of feature local IDs or unique IDs (URI).
     * Only resources that are associated with a feature of interest that has one of the provided identifiers are selected.
     */
    public void foi(List<String> foi) {
        addParameter("foi", foi);
    }

    /**
     * List of property local IDs or unique IDs (URI).
     * Only resources that are associated with an observable property that has one of the provided identifiers are selected.
     */
    public void observedProperty(List<String> observedProperty) {
        addParameter("observedProperty", observedProperty);
    }

    /**
     * List of property local IDs or unique IDs (URI).
     * Only resources that are associated with a controllable property that has one of the provided identifiers are selected.
     */
    public void controlledProperty(List<String> controlledProperty) {
        addParameter("controlledProperty", controlledProperty);
    }

    /**
     * If true, instructs the server to include subsystems in the search results.
     * <p>
     * Default: false
     */
    public void recursive(boolean recursive) {
        addParameter("recursive", recursive);
    }

    /**
     * Limits the number of items that are presented in the response document.
     * <p>
     * Default: 100
     */
    public void limit(int limit) {
        addParameter("limit", limit);
    }
}
