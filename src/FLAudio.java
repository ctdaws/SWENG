public class FLAudio {

	private FLPosition position;
	private String path;

	public FLAudio(FLPosition position, String path) {
		this.position = position;
		this.path = path;
	}

	public void setPosition(double x, double y, double x2, double y2) {
		this.position = new FLPosition(x, y, x2, y2);
	}

	public void setPath(String path) {
		this.path = path;
	}

	public FLPosition getPositition() {
		return this.position;
	}

	public String getPath() {
		return this.path;
	}

}
