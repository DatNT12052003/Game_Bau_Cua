package model;

public class DiceHistory {
	private String timeDice;
	private String faceDice1;
	private String faceDice2;
	private String faceDice3;
	private String userBet;
	private int receiveMoney;
	
	public String getTimeDice() {
		return timeDice;
	}
	public void setTimeDice(String timeDice) {
		this.timeDice = timeDice;
	}
	public String getFaceDice1() {
		return faceDice1;
	}
	public void setFaceDice1(String faceDice1) {
		this.faceDice1 = faceDice1;
	}
	public String getFaceDice2() {
		return faceDice2;
	}
	public void setFaceDice2(String faceDice2) {
		this.faceDice2 = faceDice2;
	}
	public String getFaceDice3() {
		return faceDice3;
	}
	public void setFaceDice3(String faceDice3) {
		this.faceDice3 = faceDice3;
	}
	public String getUserBet() {
		return userBet;
	}
	public void setUserBet(String userBet) {
		this.userBet = userBet;
	}
	public int getReceiveMoney() {
		return receiveMoney;
	}
	public void setReceiveMoney(int receiveMoney) {
		this.receiveMoney = receiveMoney;
	}
	public DiceHistory(String timeDice, String faceDice1, String faceDice2, String faceDice3, String userBet, int receiveMoney) {
		super();
		this.timeDice = timeDice;
		this.faceDice1 = faceDice1;
		this.faceDice2 = faceDice2;
		this.faceDice3 = faceDice3;
		this.userBet = userBet;
		this.receiveMoney = receiveMoney;
	}
	
	public DiceHistory() {
		super();
	}
	
	
}
