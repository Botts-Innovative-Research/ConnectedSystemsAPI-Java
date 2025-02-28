package org.connectedsystems.datamodels;

public class ObservedProperty {
    protected String definition;
    protected String label;
    protected String description;

    /**
     * Definition of the observed property.
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * Human-readable label of the observed property.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Human-readable description of the observed property.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        if (definition != null)
            sb.append("definition: ").append(definition).append(", ");
        if (label != null)
            sb.append("label: ").append(label).append(", ");
        if (description != null)
            sb.append("description: ").append(description).append(", ");

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
     * Builder for the ObservedProperty class.
     */
    public static class Builder {
        private final ObservedProperty observedProperty;

        public Builder() {
            observedProperty = new ObservedProperty();
        }

        /**
         * Definition of the observed property.
         */

        public Builder definition(String definition) {
            observedProperty.definition = definition;
            return this;
        }

        /**
         * Human-readable label of the observed property.
         */
        public Builder label(String label) {
            observedProperty.label = label;
            return this;
        }

        /**
         * Human-readable description of the observed property.
         */
        public Builder description(String description) {
            observedProperty.description = description;
            return this;
        }

        /**
         * Builds the ObservedProperty object.
         *
         * @return The built ObservedProperty object.
         */
        public ObservedProperty build() {
            return observedProperty;
        }
    }
}
