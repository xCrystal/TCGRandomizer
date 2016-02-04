package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import logic.MainLogic;
import settings.EvoTypes;
import settings.Settings;

public class GUIController implements Initializable {
	
	private static final GUIController guiController = new GUIController();
	private boolean[] options = new boolean[Settings.NUM_OPTIONS];
	@FXML private CheckBox optionHP, optionWR, optionRC, optionMoves;
	@FXML private ChoiceBox<Integer> minHP1, minHP2, minHP3, minHP4, minHP5, minHP6;
	@FXML private ChoiceBox<Integer> maxHP1, maxHP2, maxHP3, maxHP4, maxHP5, maxHP6;
	@FXML private ChoiceBox<Integer> minRC1, minRC2, minRC3, minRC4, minRC5, minRC6;
	@FXML private ChoiceBox<Integer> maxRC1, maxRC2, maxRC3, maxRC4, maxRC5, maxRC6;

	public static GUIController getGuiController() {
		return guiController;
	}
	
	@Override
	public void initialize (URL location, ResourceBundle resources) {
		
		initCheckBoxes();
		initChoiceBoxes();
	}
	
	@FXML
	private void beginProgram() {
		MainLogic.main();
	}
	
	public boolean getOption (int which) {
		return options[which];
	}

	public void setOption (int which) {
		getGuiController().options[which] ^= true;
	}
	
	/** Used during initialization to mark all options as true */
	public void setAllOptions() {
		handleHPOptionClick();
		handleWROptionClick();
		handleRCOptionClick();
		handleMovesOptionClick();
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
	
	/** Initializes all options to selected */
	private void initCheckBoxes() {
		
		optionHP.setSelected(true);
		optionWR.setSelected(true);
		optionRC.setSelected(true);
		optionMoves.setSelected(true);
		setAllOptions();
	}
	
	/** Initializes all choice boxes to their default values */
	private void initChoiceBoxes() {
		
		minHP1.setValue(EvoTypes.EVO1OF1.getMinHP());
		maxHP1.setValue(EvoTypes.EVO1OF1.getMaxHP());
		minRC1.setValue(EvoTypes.EVO1OF1.getMinRC());
		maxRC1.setValue(EvoTypes.EVO1OF1.getMaxRC());
		minHP1.getItems().addAll(30, 40, 50, 60, 70, 80, 90, 100, 110, 120);
		maxHP1.getItems().addAll(30, 40, 50, 60, 70, 80, 90, 100, 110, 120);
		minRC1.getItems().addAll(0, 1, 2, 3);
		maxRC1.getItems().addAll(0, 1, 2, 3);
		
		minHP2.setValue(EvoTypes.EVO1OF2.getMinHP());
		maxHP2.setValue(EvoTypes.EVO1OF2.getMaxHP());
		minRC2.setValue(EvoTypes.EVO1OF2.getMinRC());
		maxRC2.setValue(EvoTypes.EVO1OF2.getMaxRC());
		minHP2.getItems().addAll(30, 40, 50, 60, 70, 80, 90, 100, 110, 120);
		maxHP2.getItems().addAll(30, 40, 50, 60, 70, 80, 90, 100, 110, 120);
		minRC2.getItems().addAll(0, 1, 2, 3);
		maxRC2.getItems().addAll(0, 1, 2, 3);
		
		minHP3.setValue(EvoTypes.EVO2OF2.getMinHP());
		maxHP3.setValue(EvoTypes.EVO2OF2.getMaxHP());
		minRC3.setValue(EvoTypes.EVO2OF2.getMinRC());
		maxRC3.setValue(EvoTypes.EVO2OF2.getMaxRC());
		minHP3.getItems().addAll(30, 40, 50, 60, 70, 80, 90, 100, 110, 120);
		maxHP3.getItems().addAll(30, 40, 50, 60, 70, 80, 90, 100, 110, 120);
		minRC3.getItems().addAll(0, 1, 2, 3);
		maxRC3.getItems().addAll(0, 1, 2, 3);
		
		minHP4.setValue(EvoTypes.EVO1OF3.getMinHP());
		maxHP4.setValue(EvoTypes.EVO1OF3.getMaxHP());
		minRC4.setValue(EvoTypes.EVO1OF3.getMinRC());
		maxRC4.setValue(EvoTypes.EVO1OF3.getMaxRC());
		minHP4.getItems().addAll(30, 40, 50, 60, 70, 80, 90, 100, 110, 120);
		maxHP4.getItems().addAll(30, 40, 50, 60, 70, 80, 90, 100, 110, 120);
		minRC4.getItems().addAll(0, 1, 2, 3);
		maxRC4.getItems().addAll(0, 1, 2, 3);
		
		minHP5.setValue(EvoTypes.EVO2OF3.getMinHP());
		maxHP5.setValue(EvoTypes.EVO2OF3.getMaxHP());
		minRC5.setValue(EvoTypes.EVO2OF3.getMinRC());
		maxRC5.setValue(EvoTypes.EVO2OF3.getMaxRC());
		minHP5.getItems().addAll(30, 40, 50, 60, 70, 80, 90, 100, 110, 120);
		maxHP5.getItems().addAll(30, 40, 50, 60, 70, 80, 90, 100, 110, 120);
		minRC5.getItems().addAll(0, 1, 2, 3);
		maxRC5.getItems().addAll(0, 1, 2, 3);
		
		minHP6.setValue(EvoTypes.EVO3OF3.getMinHP());
		maxHP6.setValue(EvoTypes.EVO3OF3.getMaxHP());
		minRC6.setValue(EvoTypes.EVO3OF3.getMinRC());
		maxRC6.setValue(EvoTypes.EVO3OF3.getMaxRC());
		minHP6.getItems().addAll(30, 40, 50, 60, 70, 80, 90, 100, 110, 120);
		maxHP6.getItems().addAll(30, 40, 50, 60, 70, 80, 90, 100, 110, 120);
		minRC6.getItems().addAll(0, 1, 2, 3);
		maxRC6.getItems().addAll(0, 1, 2, 3);
	}

}
