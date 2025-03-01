package org.connectedsystems.datamodels;

import com.google.gson.annotations.SerializedName;
import org.vast.util.TimeExtent;

import java.util.List;

public class DataStreamResource {
    protected String id;
    protected String name;
    protected String description;
    protected TimeExtent validTime;
    protected List<String> formats;
    @SerializedName("system@link")
    protected Link systemLink;
    protected String outputName;
    @SerializedName("procedure@link")
    protected Link procedureLink;
    @SerializedName("deployment@link")
    protected Link deploymentLink;
    @SerializedName("featureOfInterest@link")
    protected Link featureOfInterestLink;
    @SerializedName("samplingFeature@link")
    protected Link samplingFeatureLink;
    protected List<ObservedProperty> observedProperties;
    protected TimeExtent phenomenonTime;
    protected String phenomenonTimeInterval;
    protected TimeExtent resultTime;
    protected String resultTimeInterval;
    @SerializedName("type")
    protected DataStreamType dataStreamType;
    protected ResultType resultType;
    protected Boolean live;
    protected List<Link> links;
    protected ObservationSchema schema;

    /**
     * Local resource ID. If set on creation, the server may ignore it.
     */
    public String getId() {
        return id;
    }

    /**
     * Human-readable name of the resource.
     */
    public String getName() {
        return name;
    }

    /**
     * Human-readable description of the resource.
     */
    public String getDescription() {
        return description;
    }

    /**
     * The validity period of the data stream’s description.
     */
    public TimeExtent getValidTime() {
        return validTime;
    }

    /**
     * The list of formats that the observations in the datastream can be encoded to.
     */
    public List<String> getFormats() {
        return formats;
    }

    /**
     * Link to the system producing the observations.
     */
    public Link getSystemLink() {
        return systemLink;
    }

    /**
     * Name of the system output feeding this datastream.
     */
    public String getOutputName() {
        return outputName;
    }

    /**
     * Link to the procedure used to acquire observations
     * (only provided if all observations in the datastream share the same procedure).
     */
    public Link getProcedureLink() {
        return procedureLink;
    }

    /**
     * Link to the deployment during which the observations are/were collected
     * (only provided if all observations in the datastream share the same deployment).
     */
    public Link getDeploymentLink() {
        return deploymentLink;
    }

    /**
     * Link to the ultimate feature of interest
     * (only provided if all observations in the datastream share the same feature of interest).
     */
    public Link getFeatureOfInterestLink() {
        return featureOfInterestLink;
    }

    /**
     * Link to the sampling feature
     * (only provided if all observations in the datastream share the same sampling feature).
     */
    public Link getSamplingFeatureLink() {
        return samplingFeatureLink;
    }

    /**
     * Properties for which the observations in the datastream provide measurements.
     */
    public List<ObservedProperty> getObservedProperties() {
        return observedProperties;
    }

    /**
     * The time period spanned by the phenomenon times of all observations in the datastream.
     */
    public TimeExtent getPhenomenonTime() {
        return phenomenonTime;
    }

    /**
     * An indication of how often feature of interest properties are observed.
     */
    public String getPhenomenonTimeInterval() {
        return phenomenonTimeInterval;
    }

    /**
     * The time period spanned by the result times of all observations in the datastream.
     */
    public TimeExtent getResultTime() {
        return resultTime;
    }

    /**
     * An indication of how often observation results are produced.
     */
    public String getResultTimeInterval() {
        return resultTimeInterval;
    }

    /**
     * Type of the data stream.
     */
    public DataStreamType getDataStreamType() {
        return dataStreamType;
    }

    /**
     * The type of result for observations in the datastream.
     */
    public ResultType getResultType() {
        return resultType;
    }

    /**
     * Indicates whether live data is available from the datastream.
     */
    public Boolean isLive() {
        return live;
    }

    /**
     * List of links associated with the data stream.
     */
    public List<Link> getLinks() {
        return links;
    }

    /**
     * The schema of the data stream.
     */
    public ObservationSchema getSchema() {
        return schema;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        if (id != null)
            sb.append("id: ").append(id).append(", ");
        if (name != null)
            sb.append("name: ").append(name).append(", ");
        if (description != null)
            sb.append("description: ").append(description).append(", ");
        if (validTime != null)
            sb.append("validTime: ").append(validTime).append(", ");
        if (formats != null)
            sb.append("formats: ").append(formats).append(", ");
        if (systemLink != null)
            sb.append("systemLink: ").append(systemLink).append(", ");
        if (outputName != null)
            sb.append("outputName: ").append(outputName).append(", ");
        if (procedureLink != null)
            sb.append("procedureLink: ").append(procedureLink).append(", ");
        if (deploymentLink != null)
            sb.append("deploymentLink: ").append(deploymentLink).append(", ");
        if (featureOfInterestLink != null)
            sb.append("featureOfInterestLink: ").append(featureOfInterestLink).append(", ");
        if (samplingFeatureLink != null)
            sb.append("samplingFeatureLink: ").append(samplingFeatureLink).append(", ");
        if (observedProperties != null)
            sb.append("observedProperties: ").append(observedProperties).append(", ");
        if (phenomenonTime != null)
            sb.append("phenomenonTime: ").append(phenomenonTime).append(", ");
        if (phenomenonTimeInterval != null)
            sb.append("phenomenonTimeInterval: ").append(phenomenonTimeInterval).append(", ");
        if (resultTime != null)
            sb.append("resultTime: ").append(resultTime).append(", ");
        if (resultTimeInterval != null)
            sb.append("resultTimeInterval: ").append(resultTimeInterval).append(", ");
        if (dataStreamType != null)
            sb.append("dataStreamType: ").append(dataStreamType).append(", ");
        if (resultType != null)
            sb.append("resultType: ").append(resultType).append(", ");
        if (live != null)
            sb.append("live: ").append(live).append(", ");

        // Remove the last comma and space
        if (sb.length() > 1 && sb.charAt(sb.length() - 2) == ',') {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("}");

        // If the string is just "{}" (i.e., no properties were set), return an empty string
        if (sb.length() == 2)
            return "";
        return sb.toString();
    }

    /**
     * Builder for the DataStreamResource class.
     */
    public static class Builder {
        private final DataStreamResource dataStreamResource;

        public Builder() {
            dataStreamResource = new DataStreamResource();
        }

        public Builder(DataStreamResource dataStreamResource) {
            this.dataStreamResource = dataStreamResource;
        }

        /**
         * Local resource ID. If set on creation, the server may ignore it.
         */
        public Builder setId(String id) {
            dataStreamResource.id = id;
            return this;
        }

        /**
         * Human-readable name of the resource.
         */
        public Builder setName(String name) {
            dataStreamResource.name = name;
            return this;
        }

        /**
         * Human-readable description of the resource.
         */
        public Builder setDescription(String description) {
            dataStreamResource.description = description;
            return this;
        }

        /**
         * The validity period of the data stream’s description.
         */
        public Builder setValidTime(TimeExtent validTime) {
            dataStreamResource.validTime = validTime;
            return this;
        }

        /**
         * The list of formats that the observations in the datastream can be encoded to.
         */
        public Builder setFormats(List<String> formats) {
            dataStreamResource.formats = formats;
            return this;
        }

        /**
         * Link to the system producing the observations.
         */
        public Builder setSystemLink(Link systemLink) {
            dataStreamResource.systemLink = systemLink;
            return this;
        }

        /**
         * Name of the system output feeding this datastream.
         */
        public Builder setOutputName(String outputName) {
            dataStreamResource.outputName = outputName;
            return this;
        }

        /**
         * Link to the procedure used to acquire observations
         * (only provided if all observations in the datastream share the same procedure).
         */
        public Builder setProcedureLink(Link procedureLink) {
            dataStreamResource.procedureLink = procedureLink;
            return this;
        }

        /**
         * Link to the deployment during which the observations are/were collected
         * (only provided if all observations in the datastream share the same deployment).
         */
        public Builder setDeploymentLink(Link deploymentLink) {
            dataStreamResource.deploymentLink = deploymentLink;
            return this;
        }

        /**
         * Link to the ultimate feature of interest
         * (only provided if all observations in the datastream share the same feature of interest).
         */
        public Builder setFeatureOfInterestLink(Link featureOfInterestLink) {
            dataStreamResource.featureOfInterestLink = featureOfInterestLink;
            return this;
        }

        /**
         * Link to the sampling feature
         * (only provided if all observations in the datastream share the same sampling feature).
         */
        public Builder setSamplingFeatureLink(Link samplingFeatureLink) {
            dataStreamResource.samplingFeatureLink = samplingFeatureLink;
            return this;
        }

        /**
         * Properties for which the observations in the datastream provide measurements.
         */
        public Builder setObservedProperties(List<ObservedProperty> observedProperties) {
            dataStreamResource.observedProperties = observedProperties;
            return this;
        }

        /**
         * The time period spanned by the phenomenon times of all observations in the datastream.
         */
        public Builder setPhenomenonTime(TimeExtent phenomenonTime) {
            dataStreamResource.phenomenonTime = phenomenonTime;
            return this;
        }

        /**
         * An indication of how often feature of interest properties are observed.
         */
        public Builder setPhenomenonTimeInterval(String phenomenonTimeInterval) {
            dataStreamResource.phenomenonTimeInterval = phenomenonTimeInterval;
            return this;
        }

        /**
         * The time period spanned by the result times of all observations in the datastream.
         */
        public Builder setResultTime(TimeExtent resultTime) {
            dataStreamResource.resultTime = resultTime;
            return this;
        }

        /**
         * An indication of how often observation results are produced.
         */
        public Builder setResultTimeInterval(String resultTimeInterval) {
            dataStreamResource.resultTimeInterval = resultTimeInterval;
            return this;
        }

        /**
         * Type of the data stream.
         */
        public Builder setDataStreamType(DataStreamType dataStreamType) {
            dataStreamResource.dataStreamType = dataStreamType;
            return this;
        }

        /**
         * The type of result for observations in the datastream.
         */
        public Builder setResultType(ResultType resultType) {
            dataStreamResource.resultType = resultType;
            return this;
        }

        /**
         * Indicates whether live data is available from the datastream.
         */
        public Builder setLive(Boolean live) {
            dataStreamResource.live = live;
            return this;
        }

        /**
         * List of links associated with the data stream.
         */
        public Builder setLinks(List<Link> links) {
            dataStreamResource.links = links;
            return this;
        }

        /**
         * The schema of the data stream.
         */
        public Builder setSchema(ObservationSchema schema) {
            dataStreamResource.schema = schema;
            return this;
        }

        /**
         * Builds the DataStreamResource object.
         *
         * @return The built DataStreamResource object.
         * @throws IllegalStateException if any required fields are not set.
         */
        public DataStreamResource build() {
            if (dataStreamResource.name == null || dataStreamResource.name.isEmpty())
                throw new IllegalStateException("name must be set.");
            if (dataStreamResource.outputName == null || dataStreamResource.outputName.isEmpty())
                throw new IllegalStateException("outputName must be set.");
            return dataStreamResource;
        }
    }
}
