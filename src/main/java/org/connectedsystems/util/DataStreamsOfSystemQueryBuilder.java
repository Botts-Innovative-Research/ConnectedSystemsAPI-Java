package org.connectedsystems.util;

import org.vast.util.TimeExtent;

import java.util.List;

/**
 * Query string parameters used to filter the results of a {@link org.connectedsystems.SystemsAPI#getSystems(DataStreamsOfSystemQueryBuilder)} request.
 */
public class DataStreamsOfSystemQueryBuilder extends QueryStringBuilder {
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
     * Limits the number of items that are presented in the response document.
     * <p>
     * Default: 100
     */
    public void limit(int limit) {
        addParameter("limit", limit);
    }
}
