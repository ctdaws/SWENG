package Old;

import javafx.scene.control.Button;

public class FLButton extends FLMedia<Button> {
    private Button button;

    FLButton(String id, Position position) {
        this.id = id;
        this.button = new Button();
        this.button.setLayoutX(position.getX());
        this.button.setLayoutY(position.getY());
    }

    FLButton(String id, Position position, double width, double height) {
        this.id = id;
        this.button = new Button();
        this.button.setLayoutX(position.getX());
        this.button.setLayoutY(position.getY());
        this.button.setStyle("-fx-min-width: " + width + "px; " +
                             "-fx-min-height: " + height + "px; " +
                             "-fx-border: none; " +
                             "-fx-background-color: transparent;");
    }

    FLButton(String id, Position position, double width, double height, String background) {
        this.id = id;
        this.button = new Button();
        this.button.setLayoutX(position.getX());
        this.button.setLayoutY(position.getY());
        this.button.setStyle("-fx-background-image: url('" + background + "'); " +
                             "-fx-background-size: " + width + "px " + height + "px; " +
                             "-fx-min-width: " + width + "px; " +
                             "-fx-min-height: " + height + "px; " +
                             "-fx-border: none; " +
                             "-fx-background-color: transparent;");
    }

    @Override
    public Button getMedia() { return this.button; }

    public void addText(String text) { this.button.setText(text); }

    public Button getButton() { return this.button; }
}
