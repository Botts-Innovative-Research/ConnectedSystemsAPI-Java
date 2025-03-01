package org.connectedsystems.datamodels;

import com.google.gson.annotations.SerializedName;
import net.opengis.swe.v20.DataBlock;

import java.time.Instant;
import java.util.List;

public class ObservationResource {
    protected String id;
    @SerializedName("datastream@id")
    protected String dataStreamId;
    @SerializedName("samplingFeature@id")
    protected String samplingFeatureId;
    @SerializedName("procedure@link")
    protected Link procedureLink;
    protected Instant phenomenonTime;
    protected DataBlock result;
    protected Instant resultTime;
    @SerializedName("result@link")
    protected Link resultLink;
    protected List<Link> links;

    /**
     * Local ID of the observation (ignored on create or update).
     */
    public String getId() {
        return id;
    }

    /**
     * Local ID of the datastream that the observation is part of.
     */
    public String getDataStreamId() {
        return dataStreamId;
    }

    /**
     * Local ID of the sampling feature that is the target of the observation.
     */
    public String getSamplingFeatureId() {
        return samplingFeatureId;
    }

    /**
     * Link to the procedure/method used to make the observation.
     */
    public Link getProcedureLink() {
        return procedureLink;
    }

    /**
     * The time at which the observation result is a valid estimate of the sampling feature property(ies).
     * Defaults to the same value as resultTime.
     */
    public Instant getPhenomenonTime() {
        return phenomenonTime;
    }

    /**
     * The time at which the observation result was generated.
     */
    public Instant getResultTime() {
        return resultTime;
    }

    /**
     * Result of the observation.
     * Must be valid, according to the result schema provided in the datastream metadata.
     */
    public DataBlock getResult() {
        return result;
    }

    /**
     * Link to external result data (e.g. large raster dataset served by a tiling service).
     */
    public Link getResultLink() {
        return resultLink;
    }

    /**
     * Links to related resources.
     */
    public List<Link> getLinks() {
        return links;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        if (id != null)
            sb.append("id: ").append(id).append(", ");
        sb.append("datastreamId: ").append(dataStreamId).append(", ");
        if (samplingFeatureId != null)
            sb.append("samplingFeatureId: ").append(samplingFeatureId).append(", ");
        if (procedureLink != null)
            sb.append("procedureLink: ").append(procedureLink).append(", ");
        if (phenomenonTime != null)
            sb.append("phenomenonTime: ").append(phenomenonTime).append(", ");
        if (resultTime != null)
            sb.append("resultTime: ").append(resultTime).append(", ");
        if (result != null)
            sb.append("result: ").append(result).append(", ");
        if (resultLink != null)
            sb.append("resultLink: ").append(resultLink).append(", ");
        if (links != null)
            sb.append("links: ").append(links).append(", ");
        // Remove the last comma and space
        if (sb.length() > 1 && sb.charAt(sb.length() - 2) == ',') {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("}");

        return sb.toString();
    }

    /**
     * Builder for {@link ObservationResource}.
     */
    public static class Builder {
        private final ObservationResource observationResource;

        public Builder() {
            observationResource = new ObservationResource();
        }

        /**
         * Local ID of the observation (ignored on create or update).
         */
        public Builder setId(String id) {
            observationResource.id = id;
            return this;
        }

        /**
         * Local ID of the datastream that the observation is part of.
         */
        public Builder setDataStreamId(String dataStreamId) {
            observationResource.dataStreamId = dataStreamId;
            return this;
        }

        /**
         * Local ID of the sampling feature that is the target of the observation.
         */
        public Builder setSamplingFeatureId(String samplingFeatureId) {
            observationResource.samplingFeatureId = samplingFeatureId;
            return this;
        }

        /**
         * Link to the procedure/method used to make the observation.
         */
        public Builder setProcedureLink(Link procedureLink) {
            observationResource.procedureLink = procedureLink;
            return this;
        }

        /**
         * The time at which the observation result is a valid estimate of the sampling feature property(ies).
         * Defaults to the same value as resultTime.
         */
        public Builder setPhenomenonTime(Instant phenomenonTime) {
            observationResource.phenomenonTime = phenomenonTime;
            return this;
        }

        /**
         * The time at which the observation result was generated.
         */
        public Builder setResultTime(Instant resultTime) {
            observationResource.resultTime = resultTime;
            return this;
        }

        /**
         * Result of the observation.
         * Must be valid, according to the result schema provided in the datastream metadata.
         */
        public Builder setResult(DataBlock result) {
            observationResource.result = result;
            return this;
        }

        /**
         * Link to external result data (e.g. large raster dataset served by a tiling service).
         */
        public Builder setResultLink(Link resultLink) {
            observationResource.resultLink = resultLink;
            return this;
        }

        /**
         * Links to related resources.
         */
        public Builder setLinks(List<Link> links) {
            observationResource.links = links;
            return this;
        }

        public ObservationResource build() {
            return observationResource;
        }
    }
}
