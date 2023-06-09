package app;
	
import game.Game;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class to launch the JavaFX application.
 */
public class Main extends Application
{
	/*----------------------------------------*/
	
	private Game game;
	
	/*----------------------------------------*/
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception
	{
		game = new Game(stage);
		game.run();
	}
	
	/*----------------------------------------*/
}
