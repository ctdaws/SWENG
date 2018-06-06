/**
 * Class for containing a key/value pair for presentation nmetadata
 */
public class PWSMeta {

//    String key/value pair
    private String key;
    private String value;

    /**
     * Constructor for new metadata
     * @param key String keyname
     * @param value String data
     */
    PWSMeta(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Getter for metadata key
     * @return metadata key
     */
    public String getKey() { return this.key; }

    /**
     * Getter for metadata value
     * @return metadata value
     */
    public String getValue() { return this.value; }

    /**
     * Override of toString method
     * @return String listing the key and value of the metadata
     */
    @Override
    public String toString() {
        return "PWSMeta: key = '" + this.key + "'\n         value = '" + this.value + "'";
    }
}
