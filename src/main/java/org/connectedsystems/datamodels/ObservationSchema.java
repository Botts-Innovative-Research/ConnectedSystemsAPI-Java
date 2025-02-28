package org.connectedsystems.datamodels;

import net.opengis.swe.v20.DataComponent;
import net.opengis.swe.v20.DataRecord;

public class ObservationSchema {
    protected String obsFormat;
    protected DataRecord parametersSchema;
    protected DataComponent resultSchema;
    protected Link resultLink;

    /**
     * Format of the observation.
     */
    public String getObsFormat() {
        return obsFormat;
    }

    /**
     * Record schema for the observation parameters property.
     * If omitted, parameters are not included in the datastream.
     */
    public DataRecord getParametersSchema() {
        return parametersSchema;
    }

    /**
     * Schema for the observation result property.
     * This describes the observed properties included in the result
     * and how they are structured if the result is a record, a vector quantity or a coverage.
     */
    public DataComponent getResultSchema() {
        return resultSchema;
    }

    /**
     * Encoding information in case the result is provided out-of-band via the result@link property.
     */
    public Link getResultLink() {
        return resultLink;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        sb.append("obsFormat: ").append(obsFormat).append(", ");
        if (parametersSchema != null)
            sb.append("parametersSchema: ").append(parametersSchema).append(", ");
        sb.append("resultSchema: ").append(resultSchema).append(", ");
        if (resultLink != null)
            sb.append("resultLink: ").append(resultLink).append(", ");
        // Remove the last comma and space
        if (sb.length() > 1 && sb.charAt(sb.length() - 2) == ',') {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("}");

        return sb.toString();
    }

    /**
     * Builder for the ObservationSchema class.
     */
    public static class Builder {
        private final ObservationSchema observationSchema;

        public Builder() {
            observationSchema = new ObservationSchema();
        }

        /**
         * Format of the observation.
         */
        public Builder setObsFormat(String obsFormat) {
            observationSchema.obsFormat = obsFormat;
            return this;
        }

        /**
         * Record schema for the observation parameters property.
         * If omitted, parameters are not included in the datastream.
         */
        public Builder setParametersSchema(DataRecord parametersSchema) {
            observationSchema.parametersSchema = parametersSchema;
            return this;
        }

        /**
         * Schema for the observation result property.
         * This describes the observed properties included in the result
         * and how they are structured if the result is a record, a vector quantity or a coverage.
         */
        public Builder setResultSchema(DataComponent resultSchema) {
            observationSchema.resultSchema = resultSchema;

            return this;
        }

        /**
         * Encoding information in case the result is provided out-of-band via the result@link property.
         */
        public Builder setResultLink(Link resultLink) {
            observationSchema.resultLink = resultLink;
            return this;
        }

        /**
         * Build the ObservationSchema object.
         *
         * @return the ObservationSchema object
         * @throws IllegalArgumentException if any required properties are not set
         */
        public ObservationSchema build() {
            if (observationSchema.obsFormat == null || observationSchema.obsFormat.isEmpty())
                throw new IllegalArgumentException("obsFormat must be set.");
            if (observationSchema.resultSchema == null)
                throw new IllegalArgumentException("resultSchema must be set.");
            if (observationSchema.resultLink != null && observationSchema.resultLink.mediaType == null)
                throw new IllegalArgumentException("resultLink mediaType must be set if resultLink is provided.");
            return observationSchema;
        }
    }
}
