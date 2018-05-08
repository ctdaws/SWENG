
import java.util.ArrayList;

class Question {
    ArrayList<Slide> slideArray;
    private String id;

    public Question(String id) {
        slideArray = new ArrayList<Slide>();
        this.id = id;
    }

    public void add(Slide newSlide) { this.slideArray.add(newSlide); }

}