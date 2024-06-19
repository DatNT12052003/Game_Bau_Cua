package model;

public class DiceInGame {
	private String name;
	private int quantity;
	
	public DiceInGame(String name, int quantity) {
		super();
		this.name = name;
		this.quantity = quantity;
	}

	public DiceInGame() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
