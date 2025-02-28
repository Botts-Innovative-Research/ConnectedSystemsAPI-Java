package org.connectedsystems.datamodels;

public enum DataStreamType {
    /**
     * For datastreams providing status observations of the parent system itself or one of its subsystems.
     */
    STATUS("status"),
    /**
     * For datastreams providing observations of other features of interest (not the system itself).
     */
    OBSERVATION("observation");

    private final String value;

    DataStreamType(String value) {
        this.value = value;
    }

    /**
     * Converts a string value to the corresponding DataStreamType.
     *
     * @param value The string value to convert.
     * @return The corresponding DataStreamType if the value matches one of the enum constants, or null if the value is null or empty.
     * @throws IllegalArgumentException if the value does not match any DataStreamType.
     */
    public static DataStreamType fromString(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        for (DataStreamType type : DataStreamType.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown DataStreamType: " + value);
    }

    @Override
    public String toString() {
        return getValue();
    }

    public String getValue() {
        return value;
    }
}
