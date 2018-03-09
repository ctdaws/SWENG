/*----------------------------------------------------------------------------------------------------------------------

                                  ___                                _____ _
                                 |_ _|_ __ ___   __ _  __ _  ___    |  ___| |
                                  | || '_ ` _ \ / _` |/ _` |/ _ \   | |_  | |
                                  | || | | | | | (_| | (_| |  __/   |  _| | |___
                                 |___|_| |_| |_|\__,_|\__, |\___|___|_|   |_____|
                                                      |___/    |_____|


------------------------------------------------------------------------------------------------------------------------

This is an Image_FL media object, created for SG3 Enterprise group as part of ELE00005H Software Engineering Project.

The Developer grants permission to the Buyer to use the provided code, without limitation, and the rights to use, copy,
modify and/or merge copies of the module code, royalty free from the date of final payment. The buyer is not permitted
to re-sell or re-license the module code at all, except as part of a derivative work. The Developer retains all
intellectual property ownership of the software.

The software is provided “as is”, without any form of warranty, express or implied, including but not limited to
warranties for merchantability, fitness for a particular purpose, and non-infringement. In no event shall the Developers
be liable for any claim, damages or other liability, whether in action of contract, tort or otherwise, arising from, out
of or in connection with the module code or the use of other dealings in the module code.

------------------------------------------------------------------------------------------------------------------------

Supported image formats are:
- Bitmap                                BMP     (.bmp)
- Graphics Interchange Format           GIF     (.gif)
- Joint Photographic Experts Group      JPEG    (.jpg)
- Portable Network Graphics             PNG     (.png)

Public Methods:
- Image_FL (constructor)
    Description:
        Creates and returns a new Image_FL object
    Example:
        // Create a new Image_FL at x=0, y=10, width=300, height=200
        Image_FL image_fl = new Image_FL("exampleImg.jpg", 0, 10, 300, 200);
    Arguments:
    - filename, String, file name of image, loaded as a resource
    - x, double, x-coordinate of top-left corner of image
    - y, double, y-coordinate of top-left corner of image
    - x2, double, x-coordinate of bottom-right corner of image
    - y2, double, y-coordinate of bottom-right corner of image

- Image_FL (constructor)
    Description:
        Creates and returns a new Image_FL object
    Example:
        // Create a new Image_FL at x=0, y=10, native size
        Image_FL image_fl = new Image_FL("exampleImg.jpg", 0, 10);
    Arguments:
    - filename, String, file name of image, loaded as a resource
    - x, double, x-coordinate of top-left corner of image
    - y, double, y-coordinate of top-left corner of image

- getImageView
    Description:
        Returns the ImageView object associated with the Image_FL. The returned ImageView can then be added to a pane
    Example:
        // Create a new pane and add image_fl to it
        Pane pane = new Pane();
        pane.getChildren.add(image_fl.getImageView());
    Returns:
    - ImageView, the ImageView associated with the Image_FL, to be added to a pane

- moveTo
    Description:
        Moves the Image_FL to a new position
    Example:
        // Move the image to 100, 200
        image_fl.moveTo(100, 200);
    Arguments:
    - newX, double, new top-left x-coordinate
    - newY, double, new top-left y-coordinate

- scaleByWidth
    Description:
        Will resize the Image_FL to the given width, preserving the aspect ratio
    Example:
        // Resize image_fl to a width of 150px
        image_fl.scaleByWidth(150);
    Arguments:
    - newWidth, double, new image width

- scaleByHeight
    Description:
        Will resize the Image_FL to the given height, preserving the aspect ratio
    Example:
        // Resize image_fl to a height of 100px
        image_fl.scaleByHeight(100);
    Arguments:
    - newHeight, double, new image height

- scale
    Description:
        Will resize the Image_FL to the given ratio
    Example:
        // Resize image_fl to half its previous size
        image_fl.scale(0.5);
    Arguments:
    - scaleFactor, double, new relative image size

- toggleGreyscale
    Description:
        When called will apply / remove the greyscale effect applied to the image
    Example:
        // Toggle the greyscale effect on image_fl
        image_fl.toggleGreyscale();

- isGreyscale
    Description:
        Returns true if the image has the greyscale effect applied; returns false if not
    Example:
        // Check if image_fl is greyscale, if true then remove the greyscale effect
        if(image_fl.isGreyscale) { image_fl.toggleGreyscale(); }
    Returns:
    - boolean, whether the greyscale effect is currently applied to the image

- toString ( @Override )
    Description:
        Allows the properties of the Image_FL object to be printed to the console from System.out.println()
    Example:
        // Print the properties of image_fl to the console
        System.out.println(image_fl)
    Returns:
    - String, properties of the Image_FL object

----------------------------------------------------------------------------------------------------------------------*/

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Image_FL {
    private String filename;
    private ImageView imageView;
    private boolean isGreyscale = false;

    // Takes all Image properties, sets width/height from x2/y2
    public Image_FL(String filename, double x, double y, double x2, double y2) {
        this.filename = filename;

        this.imageView = new ImageView(new Image(this.getClass().getResource(this.filename).toExternalForm()));
        this.imageView.setX(x);
        this.imageView.setY(y);
        this.imageView.setFitWidth(x2 - x);
        this.imageView.setFitHeight(y2 - y);

        System.out.println(this);
    }

    // Only takes path and position (x, y), uses native image size
    public Image_FL(String filename, double x, double y) {
        this.filename = filename;

        this.imageView = new ImageView(new Image(this.getClass().getResource(this.filename).toExternalForm()));
        this.imageView.setX(x);
        this.imageView.setY(y);
        this.imageView.setFitWidth(this.imageView.getImage().getWidth());
        this.imageView.setFitHeight(this.imageView.getImage().getHeight());

        //System.out.println(this);
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    public void moveTo(double newX, double newY) {
        this.imageView.setX(newX);
        this.imageView.setY(newY);

    }

    public void scaleByWidth(double newWidth) {
        double scaleFactor = newWidth / this.imageView.getFitWidth();
        this.imageView.setFitWidth(newWidth);
        this.imageView.setFitHeight(this.imageView.getFitHeight() * scaleFactor);
    }

    public void scaleByHeight(double newHeight) {
        double scaleFactor = newHeight / this.imageView.getFitHeight();
        this.imageView.setFitHeight(newHeight);
        this.imageView.setFitWidth(this.imageView.getFitWidth() * scaleFactor);
    }

    public void scale(double scaleFactor) {
        this.imageView.setFitWidth(this.imageView.getFitWidth() * scaleFactor);
        this.imageView.setFitHeight(this.imageView.getFitHeight() * scaleFactor);
    }

    public void toggleGreyscale() {
        if(isGreyscale) {
            this.isGreyscale = false;
            this.imageView.setEffect(new ColorAdjust(0.0, 0.0, 0.0, 0.0));
        } else {
            this.isGreyscale = true;
            this.imageView.setEffect(new ColorAdjust(0.0, -1.0, 0.0, 0.0));
        }
    }

    public boolean isGreyscale() {
        return this.isGreyscale;
    }

    @Override
    public String toString() {
        return ("Object:" + "\n" +
                "   Type: Image_FL\n" +
                "   Filename: " + this.filename + "\n" +
                "   Position: x=" + this.imageView.getX() + ", y=" + this.imageView.getY() + "\n" +
                "   Native Size (w/h): " + this.imageView.getImage().getWidth() + " x " + this.imageView.getImage().getHeight() + "\n" +
                "   Rendered Size (w/h): " + this.imageView.getFitWidth() + " x " + this.imageView.getFitHeight() + "\n" +
                "   Greyscale: " + this.isGreyscale
                );
    }
}
