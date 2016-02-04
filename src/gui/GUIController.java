package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import logic.MainLogic;
import settings.EvoTypes;
import settings.Settings;

public class GUIController implements Initializable {
	
	private static final GUIController guiController = new GUIController();
	private boolean[] options = new boolean[Settings.NUM_OPTIONS];
	@FXML private ChoiceBox<Integer> minHP1, minHP2, minHP3, minHP4, minHP5, minHP6;
	@FXML private ChoiceBox<Integer> maxHP1, maxHP2, maxHP3, maxHP4, maxHP5, maxHP6;
	@FXML private ChoiceBox<Integer> minRC1, minRC2, minRC3, minRC4, minRC5, minRC6;
	@FXML private ChoiceBox<Integer> maxRC1, maxRC2, maxRC3, maxRC4, maxRC5, maxRC6;

	public static GUIController getGuiController() {
		return guiController;
	}
	
	@Override
	public void initialize (URL location, ResourceBundle resources) {
		
		initChoiceBoxValues();
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
	
	private void initChoiceBoxValues() {
		minHP1.setValue(EvoTypes.EVO1OF1.getMinHP());
		minHP1.getItems().add(50);
	}

	@FXML
	private void beginProgram() {
		MainLogic.main();
	}

}
