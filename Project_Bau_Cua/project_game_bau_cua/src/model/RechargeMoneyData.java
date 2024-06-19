package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RechargeMoneyData {
	private static ObservableList<RechargeMoney> rechargeMoneyList = FXCollections.observableArrayList();

	public static ObservableList<RechargeMoney> getRechargeMoneyList() {
		return rechargeMoneyList;
	}

	public static void setRechargeMoneyList(ObservableList<RechargeMoney> rechargeMoneyList) {
		RechargeMoneyData.rechargeMoneyList = rechargeMoneyList;
	}
	
	public static void addRechargeMoney(RechargeMoney rechargeMoney) {
	     rechargeMoneyList.add(rechargeMoney);
	 }
	
	public static int getRechargeMoney() {
		return rechargeMoneyList.getLast().getQuantity();
	}
}