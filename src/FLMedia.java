abstract class FLMedia<T> {
	public String id;
	public Position position;
	public boolean isRendered = true;

	public abstract T getMedia();

	public String getId() { return this.id; }

	public boolean isRendered() { return this.isRendered; }
}
