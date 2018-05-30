public class PWSMeta {

    private String key;
    private String value;

    public PWSMeta(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() { return this.key; }

    public String getValue() { return this.value; }

    public String toString() {
        return "PWSMeta: key = '" + this.key + "'\n         value = '" + this.value + "'";
    }
}
