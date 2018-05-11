public class PWSTransitions {
    private boolean userTrigger;
    private int startI;
    private int duration;

    public PWSTransitions(String start, int duration) {

        try {
            startI = Integer.parseInt(start);
            userTrigger = false;
        }
        catch (NumberFormatException nfe) {
            startI = 0;
            userTrigger = true;
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getStart() {
        if(userTrigger) {
            return (T) "trigger";
        }
        else {
            return (T) Integer.valueOf(startI);
        }
    }

    public int getDuration() {
        return this.duration;
    }

    @Override
    public String toString() {
        return "PWSTransitions: start = " + this.getStart() + ", duration = " + this.duration;
    }
}
