package gui;

import java.io.IOException;

import constants.Settings;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.MainLogic;


public class GUI extends Application {
	
	private static final GUI guiController = new GUI();
	private Stage primaryStage;
	private AnchorPane ap;
	private boolean[] options = new boolean[Settings.NUM_OPTIONS];
			
	@Override
	public void start (Stage primaryStage) throws IOException {
		
		setPrimaryStage(primaryStage);
		FXMLLoader loader = new FXMLLoader();	
		loader.setLocation(getClass().getResource("GUI.fxml"));
		ap = loader.load();
		primaryStage.setTitle("TCG Randomizer");
		primaryStage.setScene(new Scene(ap));
		primaryStage.show();
	}

	public static void main (String[] args) {		
		launch();
	}
	
	public static GUI getGuiController() {
		return guiController;
	}

	public Stage getPrimaryStage (Event e) {
		return primaryStage;
	}

	public void setPrimaryStage (Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public AnchorPane getAnchorPane() {
		return ap;
	}

	public void setAnchorPane(AnchorPane ap) {
		this.ap = ap;
	}

	public boolean getOption (int which) {
		return options[which];
	}

	public void setOption (int which) {
		getGuiController().options[which] ^= true;
	}
	
	@FXML
	private void handleHPOptionClick() {
		setOption (Settings.Options.HP.ordinal());
	}
	
	@FXML
	private void handleWROptionClick() {
		setOption (Settings.Options.WR.ordinal());
	}
	
	@FXML
	private void handleRCOptionClick() {	
		setOption (Settings.Options.RC.ordinal());
	}
	
	@FXML
	private void handleMovesOptionClick() {	
		setOption (Settings.Options.MOVES.ordinal());
	}

	@FXML
	private void begin() {
		MainLogic.main();
	}
	
}
