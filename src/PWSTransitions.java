import javafx.util.Duration;

/**
 * Class for containing information about the transition attributes described in xml
 *
 * @author Samuel Broughton
 * @version 1.3
 */
public class PWSTransitions {
//    Set if 'trigger' is used as start type
    private boolean userTrigger;
//    start time, milliseconds
    private int start;
//    duration, milliseconds
    private int duration;

    /**
     * Constructor for PWSTRansitions
     * @param start start type, either 'trigger' or a string-formatted int in seconds
     * @param duration duratino of animation in seconds
     */
    public PWSTransitions(String start, int duration) {
//        Try to convert start to an int. If no number can be made, assume 'trigger'
        try {
//            Set start(seconds) to milliseconds
            this.start = Integer.parseInt(start) * 1000;
//            Not manually triggered
            this.userTrigger = false;
        }
//        Start was not a valid number
        catch (NumberFormatException nfe) {
//            No delayed start when triggered
            this.start = 0;
//            Manually triggered
            this.userTrigger = true;
        }
//        Set duration to milliseconds
        this.duration = duration * 1000;
    }

    /**
     * Getter for manual trigger type
     * @return boolean true:manual, false:automatic
     */
    public boolean isTriggered() { return userTrigger; }

    /**
     * Method for returning the start type
     * @param <T> Generic type
     * @return will return either the string 'trigger', or the start time integer in milliseconds
     */
    @SuppressWarnings("unchecked")
    public <T> T getPwsStart() {
        if(userTrigger) {
            return (T) "trigger";
        }
        else {
            return (T) Integer.valueOf(start);
        }
    }

    /**
     * Getter for the start time
     * @return start time as a duration
     */
    public Duration getStart() { return Duration.millis(start + 1); }

    /**
     * Getter for the duration of the animation in milliseconds
     * @return the duration in milliseconds
     */
    public int getPwsDuration() { return this.duration; }

    /**
     * Getter for the duration. As end effect is triggered at the same time as start, duration is start+duration
     * @return duration as a duration
     */
    public Duration getDuration() {
//        -1 is used to signify there is no end effect
        if(duration < 0) { return Duration.INDEFINITE; }
        else { return Duration.millis(start + duration + 1); }
    }

    /**
     * Override of toString method
     * @return String describing this pwsTransitions object
     */
    @Override
    public String toString() {
        return "PWSTransitions: start = " + this.getStart() + ", duration = " + this.duration;
    }
}
