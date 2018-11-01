import java.io.*;
import javafx.application.Application;
import javafx.event.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.stage.*;
import java.awt.image.BufferedImage;
import java.util.logging.*;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;

public class Colour_pick extends Application {
    ImageView myImageView;
    @Override
    public void start(Stage stage) throws FileNotFoundException{
        stage.setTitle("ColorPicker");
        stage.setMaximized(true);

	 //    BorderPane border = new BorderPane();
		// border.setPadding(new Insets(20, 0, 20, 20));

		Button gallery = new Button("Gallery");
		Button camera = new Button("Camera");


		gallery.setMaxWidth(Double.MAX_VALUE);
		camera.setMaxWidth(Double.MAX_VALUE);

		gallery.setOnAction(btnLoadEventListener);
		myImageView = new ImageView();

		VBox buttons = new VBox();
		buttons.setSpacing(10);
		buttons.setPadding(new Insets(10, 20, 10, 20)); 
		buttons.getChildren().addAll(gallery,camera);

		HBox photo = new HBox();
		photo.getChildren().addAll(buttons,myImageView);
        Group root = new Group(photo);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String args[]){ 
      launch(args); 
   }

       EventHandler<ActionEvent> btnLoadEventListener
    = new EventHandler<ActionEvent>(){
  
        @Override
        public void handle(ActionEvent t) {
            FileChooser fileChooser = new FileChooser();
              
            //Set extension filter
            FileChooser.ExtensionFilter extFilterJPG = 
                    new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
            FileChooser.ExtensionFilter extFilterjpg = 
                    new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
            FileChooser.ExtensionFilter extFilterPNG = 
                    new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
            FileChooser.ExtensionFilter extFilterpng = 
                    new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
            fileChooser.getExtensionFilters()
                    .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
 
            //Show open file dialog
            File file = fileChooser.showOpenDialog(null);
             
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                myImageView.setImage(image);
            } catch (IOException ex) {
                Logger.getLogger(Colour_pick.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };
}