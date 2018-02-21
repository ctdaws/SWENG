import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

import javafx.scene.Group;


class Presentation extends Group{

	//private List<Slide> slides;
	//private int index;
	private Slide currentSlide;
	private Slide s1;
	private Slide s2;
	public EventHandler<KeyEvent> keyEventHandler;

	public Presentation() {

		/*
		this.slides = new ArrayList();
		slides.add(new Slide("1"));
		slides.add(new Slide("2"));

		index = 0;
		*/
		s1 = new Slide("1");
		s2 = new Slide("2");

		currentSlide = s1;
		
		keyEventHandler = new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                switch(ke.getCode()) {
                    case ESCAPE:
                        System.out.println("Esc pressed");
                       //stop();
                    break;
                    case RIGHT:
                        System.out.println("Right pressed");
                        // Display slide 2                    
                        //s2.text.setVisible(true);
                        //s1.text.setVisible(false);
                   		setSlide(s2);
                    break; 
                    case LEFT:
                        System.out.println("Left pressed");
                        // Display slide 1
                        //s1.text.setVisible(true);
                        //s2.text.setVisible(false);
                        setSlide(s1);
                    break;
                }
            }
        };

	}

	public void setSlide(Slide nextSlide) {
		getChildren().remove(currentSlide.text);
		currentSlide.removeEventHandler(KeyEvent.KEY_PRESSED, keyEventHandler);

		currentSlide = nextSlide;
		currentSlide.addEventHandler(KeyEvent.KEY_PRESSED, keyEventHandler);

		getChildren().add(currentSlide.text);
	}
}