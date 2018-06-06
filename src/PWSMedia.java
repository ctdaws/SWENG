import javafx.animation.Timeline;

/**
 * This class provides an abstract type for PWS media objects to extend from. Gives variables that all media objects are
 * required to have; such as an id, position, transition and animation timeline.
 *
 * @author Samuel Broughton, Chris Dawson
 * @version 2.4
 *
 * @param <T> Generic type that allows for all media objects to be stored in a collection
 */
abstract class PWSMedia<T> {

//    ID for identifying the object
    private String id;
//    Position information
    private PWSPosition pwsPosition;
//    Information about the animation timings
    private PWSTransitions pwsTransitions;
//    Animation timeline with animation frames
    private Timeline timeline;

    /**
     * Required method for retrieving the JavaFX media held by the PWSMedia wrapper
     * @return Generic type set later by each object that extends from this abstract class
     */
    public abstract T getPwsMedia();

    /**
     * Setter for private ID
     * @param id String for new ID
     */
    public void setId(String id) { this.id = id; }

    /**
     * Getter for private string
     * @return ID string
     */
    public String getId() { return id; }

    /**
     * Setter for private pwsPosition
     * @param pwsPosition pwsPosition containing object layout descriptions
     */
    public void setPwsPosition(PWSPosition pwsPosition) { this.pwsPosition = pwsPosition; }

    /**
     * Getter for private pwsPosition
     * @return position information in PWSPosition object
     */
    public PWSPosition getPwsPosition() { return pwsPosition; }

    /**
     * Setter for private pwsTransitions
     * @param pwsTransitions animation timings contained in a pwsTransitions object
     */
    public void setPwsTransitions(PWSTransitions pwsTransitions) { this.pwsTransitions = pwsTransitions; }

    /**
     * Getter for private pwsTransitions
     * @return animation information stored in a pwsTransitions object
     */
    public PWSTransitions getPwsTransitions() { return pwsTransitions; }

    /**
     * Setter for private timeline
     * @param timeline JavaFX timeline containing animation keyframes
     */
    public void setTimeline(Timeline timeline) { this.timeline = timeline; }

    /**
     * Getter for private timeline
     * @return animation timeline of media object
     */
    public Timeline getTimeline() { return timeline; }

    /**
     * Abstract method to be defined by the implemented object, used for creating keyframes and then a complete timeline
     * @param pwsTransitions timing information used when generating triggerable actions
     */
    public abstract void setTransition(PWSTransitions pwsTransitions);

    /**
     * Method used to play timeline from 'trigger' cuepoint for media with triggerable actions
     */
    public void trigger() { this.timeline.playFrom("trigger"); }
}
