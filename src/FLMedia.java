abstract class FLMedia<T> {
	public Position position;
	public boolean isRendered = true;

	public abstract T getMedia();

	public boolean isRendered() {
		return this.isRendered;
	}
}
