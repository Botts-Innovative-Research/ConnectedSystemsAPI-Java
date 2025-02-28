package org.connectedsystems.datamodels;

public enum ResultType {
    /**
     * When the result is a single scalar value with a unit of measure.
     */
    MEASURE("measure"),
    /**
     * When the result is a vector quantity (e.g., velocity vector, stress tensor).
     */
    VECTOR("vector"),
    /**
     * When the result is a record containing only scalar values and/or vectors.
     */
    RECORD("record"),
    /**
     * When the result is a coverage (any number of dimensions).
     */
    COVERAGE("coverage"),
    /**
     * When the result is a record with nested records and/or arrays.
     */
    COMPLEX("complex");

    private final String value;

    ResultType(String value) {
        this.value = value;
    }

    public static ResultType fromString(String value) {
        if (value == null || value.isEmpty()) return null;

        for (ResultType type : ResultType.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }

        throw new IllegalArgumentException("Unknown ResultType: " + value);
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }
}
