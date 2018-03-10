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

----------------------------------------------------------------------------------------------------------------------*/

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Image_FL {
    private String filename;
    private ImageView imageView;
    private boolean isGreyscale = false;
    private Transition transition;

    // Takes all Image properties, sets width/height from x2/y2
    public Image_FL(String filename, double x, double y, double x2, double y2) {
        this.filename = filename;
        this.imageView = new ImageView(new Image(this.getClass().getResource(this.filename).toExternalForm()));
        this.moveTo(x, y);
        this.setSize(x2 - x, y2 - y);
    }

    // Only takes path and position (x, y), uses native image size
    public Image_FL(String filename, double x, double y) {
        this.filename = filename;
        this.imageView = new ImageView(new Image(this.getClass().getResource(this.filename).toExternalForm()));
        this.moveTo(x, y);
        this.setSize(this.imageView.getImage().getWidth(), this.imageView.getImage().getHeight());
    }

    public ImageView getImageView() { return this.imageView; }

    public void moveTo(double newX, double newY) {
        this.imageView.setX(newX);
        this.imageView.setY(newY);
    }

    private void setSize(double width, double height) {
        this.imageView.setFitWidth(width);
        this.imageView.setFitHeight(height);
    }

    public void setTransition(String startType, int duration) {
        this.transition = new Transition(startType, duration);
    }

    public void scaleByWidth(double newWidth) {
        double scaleFactor = newWidth / this.imageView.getFitWidth();
        this.setSize(newWidth, this.imageView.getFitHeight() * scaleFactor);
    }

    public void scaleByHeight(double newHeight) {
        double scaleFactor = newHeight / this.imageView.getFitHeight();
        this.setSize(this.imageView.getFitWidth() * scaleFactor, newHeight);
    }

    public void scale(double scaleFactor) {
        this.setSize(this.imageView.getFitWidth() * scaleFactor, this.imageView.getFitHeight() * scaleFactor);
    }

    public void toggleGreyscale() {
        if(this.isGreyscale) {
            this.imageView.setEffect(new ColorAdjust(0.0, -1.0, 0.0, 0.0));
        } else {
            this.imageView.setEffect(new ColorAdjust(0.0, -1.0, 0.0, 0.0));
        }
        this.isGreyscale = !this.isGreyscale;
    }

    public boolean isGreyscale() { return this.isGreyscale; }

    public Transition getTransition() {
        return this.transition;
    }

    @Override
    public String toString() {
        return ("\nObject:" +
                "\n   Type: Image_FL" +
                "\n   Filename: " + this.filename +
                "\n   Position: x=" + this.imageView.getX() + ", y=" + this.imageView.getY() +
                "\n   Native Size (w/h): " + this.imageView.getImage().getWidth() + " x " + this.imageView.getImage().getHeight() +
                "\n   Rendered Size (w/h): " + this.imageView.getFitWidth() + " x " + this.imageView.getFitHeight() +
                "\n   Greyscale: " + this.isGreyscale +
                "\n   Transition: " + this.getTransition()
                );
    }

    public class Transition {
        private boolean userTriggered;
        private int startTime;
        private int duration;

        public Transition() {
            this.userTriggered = false;
            this.startTime = 0;
            this.duration = 0;
        }

        public Transition(String startType, int duration) {
            this.setStart(startType);
            this.duration = duration;
        }

        public void setStart(String startType) {
            if(startType.equals("trigger")) {
                this.userTriggered = true;
                this.startTime = 0;
            } else {
                this.userTriggered = false;
                this.startTime = Integer.parseInt(startType);
            }
        }

        public boolean isUserTriggered() { return this.userTriggered; }

        public int getStartTime() { return this.startTime; }

        public int getDuration() { return this.duration; }

        @Override
        public String toString() {
            return ("\nObject:" +
            "\n   Type: Image_FL.Transition" +
            "\n   Trigger onClick: " + this.userTriggered +
            "\n   Start Time: " + this.startTime +
            "\n   Duration " + this.duration
            );
        }
    }
}
