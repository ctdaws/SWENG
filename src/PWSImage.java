import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PWSImage extends PWSMedia<ImageView>{

    private ImageView imageView;

    private String fileName;

    @Override
    public ImageView getPwsMedia() { return this.imageView; }

    public PWSImage(String id, PWSPosition pwsPosition, PWSTransitions pwsTransitions, String path) {
        this.setId(id);
        this.setPwsPosition(pwsPosition);
        this.setPwsTransitions(pwsTransitions);
        this.fileName = path;
        this.imageView = new ImageView(new Image(this.getClass().getResource(path).toExternalForm()));
        this.imageView.setX(this.getPwsPosition().getX());
        this.imageView.setY(this.getPwsPosition().getY());
        this.imageView.setFitWidth(this.getPwsPosition().getWidth());
        this.imageView.setFitHeight(this.getPwsPosition().getHeight());
    }

    @Override
    public String toString() {
        return "PWSImage:\nid = " + this.getId() + "\nfileName = " + this.fileName + "\n" + this.getPwsPosition();
    }
}
