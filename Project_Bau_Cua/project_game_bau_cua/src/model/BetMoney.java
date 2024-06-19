package model;

public class BetMoney {
	private String name;
	private int betMoney;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBetMoney() {
		return betMoney;
	}
	public void setBetMoney(int betMoney) {
		this.betMoney = betMoney;
	}
	
	public BetMoney(String name, int betMoney) {
		super();
		this.name = name;
		this.betMoney = betMoney;
	}
	
	public BetMoney() {
		super();
	}
}
