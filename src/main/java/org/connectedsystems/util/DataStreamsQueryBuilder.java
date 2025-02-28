package org.connectedsystems.util;

import org.vast.util.TimeExtent;

import java.util.List;

/**
 * Query string parameters used to filter the results of a {@link org.connectedsystems.SystemsAPI#getSystems(DataStreamsQueryBuilder)} request.
 */
public class DataStreamsQueryBuilder extends QueryStringBuilder {
    /**
     * List of resource local IDs or unique IDs (URI).
     * Only resources that have one of the provided identifiers are selected.
     */
    public void id(List<String> id) {
        addParameter("id", id);
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
     * Either a date-time or an interval.
     * Date and time expressions adhere to RFC 3339.
     * Intervals may be bounded or half-bounded (double-dots at start or end).
     */
    public void phenomenonTime(TimeExtent phenomenonTime) {
        addParameter("phenomenonTime", phenomenonTime);
    }

    /**
     * Either a date-time or an interval.
     * Date and time expressions adhere to RFC 3339.
     * Intervals may be bounded or half-bounded (double-dots at start or end).
     */
    public void resultTime(TimeExtent resultTime) {
        addParameter("resultTime", resultTime);
    }

    /**
     * List of system local IDs or unique IDs (URI).
     * Only resources that are associated with a System that has one of the provided identifiers are selected.
     */
    public void system(List<String> system) {
        addParameter("system", system);
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
     * Limits the number of items that are presented in the response document.
     * <p>
     * Default: 100
     */
    public void limit(int limit) {
        addParameter("limit", limit);
    }
}
