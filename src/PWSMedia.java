abstract class PWSMedia<T> {
    private String id;
    private PWSPosition pwsPosition;
    private PWSTransitions pwsTransitions;

    public abstract T getPwsMedia();

    public void setId(String id) { this.id = id; }

    public String getId() { return this.id; }

    public void setPwsPosition(PWSPosition pwsPosition) { this.pwsPosition = pwsPosition; }

    public PWSPosition getPwsPosition() { return this.pwsPosition; }

    public void setPwsTransitions(PWSTransitions pwsTransitions) { this.pwsTransitions = pwsTransitions; }

    public PWSTransitions getPwsTransitions() { return this.pwsTransitions; }
}
