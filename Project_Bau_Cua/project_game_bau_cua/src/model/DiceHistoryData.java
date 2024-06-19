package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DiceHistoryData {
	private static ObservableList<DiceHistory> diceHistoryList = FXCollections.observableArrayList();

	public static ObservableList<DiceHistory> getDiceHistoryList() {
		return diceHistoryList;
	}

	public static void setDiceHistoryList(ObservableList<DiceHistory> diceHistoryList) {
		DiceHistoryData.diceHistoryList = diceHistoryList;
	}
	
	public static void addDiceHistory(DiceHistory diceHistory) {
	     diceHistoryList.add(diceHistory);
	 }
}
