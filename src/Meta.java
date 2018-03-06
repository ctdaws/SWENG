public class Meta {
	private String keyword;
	private String value;

	public Meta(String key, String value) {
		this.keyword = key;
		this.value = value;

		printProperties(this);
	}

	public void setMeta(String key, String value) {
		this.keyword = key;
		this.value = value;
	}

	public String getKeyword() { return this.keyword; }

	public String getValue() { return this.value; }

	public void printProperties(Meta meta) {
		System.out.println("");
		System.out.println("New Meta Created. Listing properties:");
		System.out.println("	Keyword: " + meta.getKeyword());
		System.out.println("	Value: " + meta.getValue());
	}
}
