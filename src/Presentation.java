import javafx.scene.Group;

class Presentation extends Group{

	//private List<Slide> slides;
	//private int index;
	private Slide currentSlide;
	private Slide s1;
	private Slide s2;
	

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

		getChildren().add(currentSlide.text);

		

	}

	public void setSlide(Slide nextSlide) {
		getChildren().remove(currentSlide.text);

		currentSlide = nextSlide;

		getChildren().add(currentSlide.text);
	}
}