package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
	private List<Integer> HPList = new ArrayList<Integer>();
	private List<Integer> RCList = new ArrayList<Integer>();
	
	/* Options to randomize HP, weaknesses/resistances, retreat cost, and shuffle moves */
	@FXML private CheckBox optionHP, optionWR, optionRC, optionMoves;
	@FXML private CheckBox optionFillEmpty;
	
	/* Minimum and maximum HP and retreat cost values for each of the 6 evolution types */
	@FXML private ChoiceBox<Integer> minHP1, minHP2, minHP3, minHP4, minHP5, minHP6;
	@FXML private ChoiceBox<Integer> maxHP1, maxHP2, maxHP3, maxHP4, maxHP5, maxHP6;
	@FXML private ChoiceBox<Integer> minRC1, minRC2, minRC3, minRC4, minRC5, minRC6;
	@FXML private ChoiceBox<Integer> maxRC1, maxRC2, maxRC3, maxRC4, maxRC5, maxRC6;
	
	/* Minimum and maximum number of weaknesses and resistances */
	@FXML private ChoiceBox<Integer> minW, maxW, minR, maxR;

	public static GUIController getGuiController() {
		return guiController;
	}
	
	@Override
	public void initialize (URL location, ResourceBundle resources) {
		
		setAllMainOptions();
		initChoiceBoxes();
		choiceBoxesListener();
	}
	
	public boolean getOption (int which) {
		return options[which];
	}

	private void setOption (int which) {
		getGuiController().options[which] ^= true;
	}
	
	/** Used during initialization to mark all main options as true */
	public void setAllMainOptions() {
		handleHPOption();
		handleWROption();
		handleRCOption();
		handleMovesOption();
	}

	private void handleHPOption() {
		setOption (Settings.Options.HP.ordinal());
	}

	private void handleWROption() {
		setOption (Settings.Options.WR.ordinal());
	}

	private void handleRCOption() {
		setOption (Settings.Options.RC.ordinal());
	}

	private void handleMovesOption() {
		setOption (Settings.Options.MOVES.ordinal());
	}
	
	@FXML
	private void beginProgram() {
		MainLogic.main();
	}
	
	@FXML
	private void handleHPOptionClick() {
		handleHPOption();
		minHP1.setDisable(minHP1.isDisable()^true);
		maxHP1.setDisable(maxHP1.isDisable()^true);
		minHP2.setDisable(minHP2.isDisable()^true);
		maxHP2.setDisable(maxHP2.isDisable()^true);
		minHP3.setDisable(minHP3.isDisable()^true);
		maxHP3.setDisable(maxHP3.isDisable()^true);
		minHP4.setDisable(minHP4.isDisable()^true);
		maxHP4.setDisable(maxHP4.isDisable()^true);
		minHP5.setDisable(minHP5.isDisable()^true);
		maxHP5.setDisable(maxHP5.isDisable()^true);
		minHP6.setDisable(minHP6.isDisable()^true);
		maxHP6.setDisable(maxHP6.isDisable()^true);
	}
	
	@FXML
	private void handleWROptionClick() {
		handleWROption();
		minW.setDisable(minW.isDisable()^true);
		maxW.setDisable(maxW.isDisable()^true);
		minR.setDisable(minR.isDisable()^true);
		maxR.setDisable(maxR.isDisable()^true);
	}
	
	@FXML
	private void handleRCOptionClick() {	
		handleRCOption();
		minRC1.setDisable(minRC1.isDisable()^true);
		maxRC1.setDisable(maxRC1.isDisable()^true);
		minRC2.setDisable(minRC2.isDisable()^true);
		maxRC2.setDisable(maxRC2.isDisable()^true);
		minRC3.setDisable(minRC3.isDisable()^true);
		maxRC3.setDisable(maxRC3.isDisable()^true);
		minRC4.setDisable(minRC4.isDisable()^true);
		maxRC4.setDisable(maxRC4.isDisable()^true);
		minRC5.setDisable(minRC5.isDisable()^true);
		maxRC5.setDisable(maxRC5.isDisable()^true);
		minRC6.setDisable(minRC6.isDisable()^true);
		maxRC6.setDisable(maxRC6.isDisable()^true);
	}
	
	@FXML
	private void handleMovesOptionClick() {
		handleMovesOption();
		optionFillEmpty.setDisable(optionFillEmpty.isDisable()^true);
	}
	
	@FXML
	private void handleFillEmptyOptionClick() {	
		setOption (Settings.Options.FILL.ordinal());
		Settings.settings.setFillEmptySelected(Settings.settings.isFillEmptySelected()^true);
	}	
	
	/** Initializes all choice boxes to their default values */
	private void initChoiceBoxes() {
		
		HPList.addAll(Arrays.asList(30, 40, 50, 60, 70, 80, 90, 100, 110, 120));
		RCList.addAll(Arrays.asList(0, 1, 2, 3));
		
		minHP1.setValue(EvoTypes.EVO1OF1.getMinHP());
		maxHP1.setValue(EvoTypes.EVO1OF1.getMaxHP());
		minRC1.setValue(EvoTypes.EVO1OF1.getMinRC());
		maxRC1.setValue(EvoTypes.EVO1OF1.getMaxRC());
		minHP1.getItems().addAll(HPList);
		maxHP1.getItems().addAll(HPList);
		minRC1.getItems().addAll(RCList);
		maxRC1.getItems().addAll(RCList);
		
		minHP2.setValue(EvoTypes.EVO1OF2.getMinHP());
		maxHP2.setValue(EvoTypes.EVO1OF2.getMaxHP());
		minRC2.setValue(EvoTypes.EVO1OF2.getMinRC());
		maxRC2.setValue(EvoTypes.EVO1OF2.getMaxRC());
		minHP2.getItems().addAll(HPList);
		maxHP2.getItems().addAll(HPList);
		minRC2.getItems().addAll(RCList);
		maxRC2.getItems().addAll(RCList);
		
		minHP3.setValue(EvoTypes.EVO2OF2.getMinHP());
		maxHP3.setValue(EvoTypes.EVO2OF2.getMaxHP());
		minRC3.setValue(EvoTypes.EVO2OF2.getMinRC());
		maxRC3.setValue(EvoTypes.EVO2OF2.getMaxRC());
		minHP3.getItems().addAll(HPList);
		maxHP3.getItems().addAll(HPList);
		minRC3.getItems().addAll(RCList);
		maxRC3.getItems().addAll(RCList);
		
		minHP4.setValue(EvoTypes.EVO1OF3.getMinHP());
		maxHP4.setValue(EvoTypes.EVO1OF3.getMaxHP());
		minRC4.setValue(EvoTypes.EVO1OF3.getMinRC());
		maxRC4.setValue(EvoTypes.EVO1OF3.getMaxRC());
		minHP4.getItems().addAll(HPList);
		maxHP4.getItems().addAll(HPList);
		minRC4.getItems().addAll(RCList);
		maxRC4.getItems().addAll(RCList);
		
		minHP5.setValue(EvoTypes.EVO2OF3.getMinHP());
		maxHP5.setValue(EvoTypes.EVO2OF3.getMaxHP());
		minRC5.setValue(EvoTypes.EVO2OF3.getMinRC());
		maxRC5.setValue(EvoTypes.EVO2OF3.getMaxRC());
		minHP5.getItems().addAll(HPList);
		maxHP5.getItems().addAll(HPList);
		minRC5.getItems().addAll(RCList);
		maxRC5.getItems().addAll(RCList);
		
		minHP6.setValue(EvoTypes.EVO3OF3.getMinHP());
		maxHP6.setValue(EvoTypes.EVO3OF3.getMaxHP());
		minRC6.setValue(EvoTypes.EVO3OF3.getMinRC());
		maxRC6.setValue(EvoTypes.EVO3OF3.getMaxRC());
		minHP6.getItems().addAll(HPList);
		maxHP6.getItems().addAll(HPList);
		minRC6.getItems().addAll(RCList);
		maxRC6.getItems().addAll(RCList);
		
		/* RCList doubles as WRList */
		minW.setValue(Settings.settings.getMinWeaknesses());
		maxW.setValue(Settings.settings.getMaxWeaknesses());
		minR.setValue(Settings.settings.getMinResistances());
		maxR.setValue(Settings.settings.getMaxResistances());
		minW.getItems().addAll(RCList);
		maxW.getItems().addAll(RCList);
		minR.getItems().addAll(RCList);
		maxR.getItems().addAll(RCList);
	}
	
	/** Listens to value of choice box changing */
	private void choiceBoxesListener() {
		
		minHP1.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				EvoTypes.EVO1OF1.setMinHP(HPList.get(newValue.intValue()));
			}
		});
		
		maxHP1.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				EvoTypes.EVO1OF1.setMaxHP(HPList.get(newValue.intValue()));
			}
		});
		
		minRC1.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				EvoTypes.EVO1OF1.setMinRC(RCList.get(newValue.intValue()));
			}
		});
		
		maxRC1.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				EvoTypes.EVO1OF1.setMaxRC(RCList.get(newValue.intValue()));
			}
		});
		
		minHP2.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				EvoTypes.EVO1OF2.setMinHP(HPList.get(newValue.intValue()));
			}
		});
		
		maxHP2.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				EvoTypes.EVO1OF2.setMaxHP(HPList.get(newValue.intValue()));
			}
		});
		
		minRC2.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				EvoTypes.EVO1OF2.setMinRC(RCList.get(newValue.intValue()));
			}
		});
		
		maxRC2.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				EvoTypes.EVO1OF2.setMaxRC(RCList.get(newValue.intValue()));
			}
		});
		
		minHP3.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				EvoTypes.EVO2OF2.setMinHP(HPList.get(newValue.intValue()));
			}
		});
		
		maxHP3.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				EvoTypes.EVO2OF2.setMaxHP(HPList.get(newValue.intValue()));
			}
		});
		
		minRC3.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				EvoTypes.EVO2OF2.setMinRC(RCList.get(newValue.intValue()));
			}
		});
		
		maxRC3.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				EvoTypes.EVO2OF2.setMaxRC(RCList.get(newValue.intValue()));
			}
		});
		
		minHP4.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				EvoTypes.EVO1OF3.setMinHP(HPList.get(newValue.intValue()));
			}
		});
		
		maxHP4.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				EvoTypes.EVO1OF3.setMaxHP(HPList.get(newValue.intValue()));
			}
		});
		
		minRC4.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				EvoTypes.EVO1OF3.setMinRC(RCList.get(newValue.intValue()));
			}
		});
		
		maxRC4.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				EvoTypes.EVO1OF3.setMaxRC(RCList.get(newValue.intValue()));
			}
		});
		
		minHP5.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				EvoTypes.EVO2OF3.setMinHP(HPList.get(newValue.intValue()));
			}
		});
		
		maxHP5.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				EvoTypes.EVO2OF3.setMaxHP(HPList.get(newValue.intValue()));
			}
		});
		
		minRC5.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				EvoTypes.EVO2OF3.setMinRC(RCList.get(newValue.intValue()));
			}
		});
		
		maxRC5.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				EvoTypes.EVO2OF3.setMaxRC(RCList.get(newValue.intValue()));
			}
		});
		
		minHP6.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				EvoTypes.EVO3OF3.setMinHP(HPList.get(newValue.intValue()));
			}
		});
		
		maxHP6.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				EvoTypes.EVO3OF3.setMaxHP(HPList.get(newValue.intValue()));
			}
		});
		
		minRC6.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				EvoTypes.EVO3OF3.setMinRC(RCList.get(newValue.intValue()));
			}
		});
		
		maxRC6.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				EvoTypes.EVO3OF3.setMaxRC(RCList.get(newValue.intValue()));
			}
		});
		
		minW.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				Settings.settings.setMinWeaknesses(RCList.get(newValue.intValue()));
			}
		});
		
		maxW.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				Settings.settings.setMaxWeaknesses(RCList.get(newValue.intValue()));
			}
		});
		
		minR.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				Settings.settings.setMinResistances(RCList.get(newValue.intValue()));
			}
		});
		
		maxR.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				Settings.settings.setMaxResistances(RCList.get(newValue.intValue()));
			}
		});
	}

}
