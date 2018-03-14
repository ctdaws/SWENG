import javafx.scene.control.Button;

public class FLButton extends FLMedia<Button> {
    private Button button;

    FLButton(String id, Position position, String action) {
        this.id = id;
        this.button = new Button();
        this.button.setLayoutX(position.getX());
        this.button.setLayoutY(position.getY());
        this.setAction(action);
    }

    FLButton(String id, Position position, String action, String background) {
        this.id = id;
        this.button = new Button();
        this.button.setLayoutX(position.getX());
        this.button.setLayoutY(position.getY());
        this.setAction(action);
        this.button.setStyle("-fx-background-image: url('" + background + "');");
    }

    @Override
    public Button getMedia() { return this.button; }

    public void addText(String text) { this.button.setText(text); }

    public Button getButton() { return this.button; }

    private void setAction(String action) {
        switch(action) {
            case "test":
                this.button.setOnMouseClicked((clickEvent) -> System.out.println("Click!") );
                break;
            case "nextSlide":
                //this.button.setOnMouseClicked((clickEvent) -> LectureQuest.nextSlide() );
                break;
            default:
                break;
        }

    }
}
