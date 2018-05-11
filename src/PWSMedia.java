abstract class PWSMedia<T> {
    private String id;
    private PWSPosition pwsPosition;

    public abstract T getPwsMedia();

    public void setId(String id) { this.id = id; }

    public String getId() { return this.id; }

    public void setPwsPosition(PWSPosition pwsPosition) { this.pwsPosition = pwsPosition; }

    public PWSPosition getPwsPosition() { return this.pwsPosition; }
}
