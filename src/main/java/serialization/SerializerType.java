package serialization;

public enum SerializerType {
    JSON("JSON files (*.json)", "*.json"),
    BINARY( "BIN files (*.bin)", "*.bin"),
    ARBITRARY( "TXT files (*.txt)", "*.txt");

    private final String descriptionFilter;
    private final String extensionFilter;

    SerializerType(String descriptionFilter, String extensionFilter) {
        this.descriptionFilter = descriptionFilter;
        this.extensionFilter = extensionFilter;
    }

    public String getDescriptionFilter() {
        return descriptionFilter;
    }

    public String getExtensionFilter() {
        return extensionFilter;
    }
}
