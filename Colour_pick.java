import java.util.*;
import java.io.File;
import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.shape.*;
import javafx.geometry.*;
import javafx.event.*;

public class Colour_pick extends Application {
		String string = new String("");
	@Override
	public void start(Stage stage){
		List<List<Color>> listOfLists = new ArrayList<List<Color>>(); 
		ImageView myImageView = new ImageView();

		Circle reporter = new Circle(50);

		Button gallery = new Button("Gallery");
		Button camera = new Button("Camera");
		// Button colour = new Button("Colour_pick");

		HBox button = new HBox(5);
		button.setPadding(new Insets(10, 10, 10, 10));
		button.getChildren().addAll(gallery,camera);

		VBox left = new VBox(5);
		left.setPadding(new Insets(10, 10, 10, 10));
		left.getChildren().addAll(button,myImageView);

		Text hex = new Text("hex :\t"+string);

		Text red = new Text("red :\t"+string);
		Text green = new Text("green :\t"+string);
		Text blue = new Text("blue :\t"+string);
		
		Text hue = new Text("hue :\t"+string);
		Text saturation = new Text("saturation :\t"+string);
		Text brightness = new Text("brightness :\t"+string);

		Text opacity = new Text("opacity :\t"+string);

		VBox right = new VBox(5);
		right.setPadding(new Insets(10, 10, 10, 10));
		right.getChildren().addAll(reporter,opacity,red,green,blue,hue,saturation,brightness,hex);

		HBox finalScreen = new HBox(5);
		finalScreen.setPadding(new Insets(10, 10, 10, 10));
		finalScreen.getChildren().addAll(left,right);

		gallery.setOnMouseClicked((new EventHandler<MouseEvent>() { 
			public void handle(MouseEvent event) {
				FileChooser fileChooser = new FileChooser();
				File file = fileChooser.showOpenDialog(null);

				if(file!=null)
				{
					Image image = new Image(file.toURI().toString());
					myImageView.setImage(image);
					listOfLists.clear();
					PixelReader pixelReader = image.getPixelReader();
					for(int pixelX = 0; pixelX < image.getWidth(); pixelX++)
					{
						listOfLists.add(new ArrayList<Color>());
						for(int pixelY = 0; pixelY <image.getHeight(); pixelY++)
							listOfLists.get(pixelX).add(pixelReader.getColor(pixelX,pixelY));
					}
				}
			}
		}));		

		myImageView.setOnMouseMoved((new EventHandler<MouseEvent>() { 
			public void handle(MouseEvent event) {
				
				reporter.setFill(listOfLists.get((int)event.getX()).get((int)event.getY()));
				
				string = (int)(((Color)reporter.getFill()).getOpacity())+"";
				opacity.setText("opacity :\t\t"+string);

				string = (int)(((Color)reporter.getFill()).getRed()*255)+"";
				red.setText("red :\t\t\t"+string);
				string = (int)(((Color)reporter.getFill()).getGreen()*255)+"";
				green.setText("green :\t\t"+string);
				string = (int)(((Color)reporter.getFill()).getBlue()*255)+"";
				blue.setText("blue :\t\t"+string);

				string = (int)((Color)reporter.getFill()).getHue()+"";
				hue.setText("hue :\t\t\t"+string);
				string = (int)(((Color)reporter.getFill()).getSaturation()*100)+"";
				saturation.setText("saturation :\t"+string);
				string = (int)(((Color)reporter.getFill()).getBrightness()*100)+"";
				brightness.setText("brightness :\t"+string);

				string = String.format("#%02x%02x%02x", (int)(((Color)reporter.getFill()).getRed()*255) , (int)(((Color)reporter.getFill()).getGreen()*255),
										(int)(((Color)reporter.getFill()).getBlue()*255));
				hex.setText("hex :\t\t\t"+string);
			}
		}));

		
		// Group root  = new Group(right);

		Scene scene = new Scene(finalScreen,1000,1000);
		stage.setScene(scene);
		stage.setTitle("ColorPicker");
		stage.show();
	}
	public static void main(String args[]){ 
		launch(args); 
	}
}