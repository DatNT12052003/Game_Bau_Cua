package model;

public class Dice {
	final private String[] diceFace = {"Nai", "Bầu", "Gà", "Cá", "Tôm", "Cua"};

	public String getDiceFace(int i) {
		return diceFace[i];
	}
}