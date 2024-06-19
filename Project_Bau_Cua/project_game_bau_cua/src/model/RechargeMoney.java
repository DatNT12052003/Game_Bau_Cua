package model;

public class RechargeMoney {
	private String timeRecharge;
	private int quantity;
	
	public String getTimeRecharge() {
		return timeRecharge;
	}
	public void setTimeRecharge(String timeRecharge) {
		this.timeRecharge = timeRecharge;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public RechargeMoney(String timeRecharge, int quantity) {
		super();
		this.timeRecharge = timeRecharge;
		this.quantity = quantity;
	}
	
	public RechargeMoney() {
		super();
	}
}
