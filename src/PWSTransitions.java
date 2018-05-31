import javafx.util.Duration;

public class PWSTransitions {
    private boolean userTrigger;
    private int start;
    private int duration;

    public PWSTransitions(String start, int duration) {

        try {
            this.start = Integer.parseInt(start);
            this.userTrigger = false;
        }
        catch (NumberFormatException nfe) {
            this.start = 0;
            this.userTrigger = true;
        }
        this.duration = duration;
    }

    public boolean isTriggered() { return userTrigger; }

    @SuppressWarnings("unchecked")
    public <T> T getPwsStart() {
        if(userTrigger) {
            return (T) "trigger";
        }
        else {
            return (T) Integer.valueOf(start);
        }
    }

    public Duration getStart() {
        return Duration.millis(start);
    }

    public int getPwsDuration() {
        return this.duration;
    }

    public Duration getDuration() {
        if(duration < 0) { return Duration.INDEFINITE; }
        else { return Duration.millis(start + duration); }
    }

    @Override
    public String toString() {
        return "PWSTransitions: start = " + this.getStart() + ", duration = " + this.duration;
    }
}
