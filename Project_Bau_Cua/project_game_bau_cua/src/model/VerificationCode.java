package model;

public class VerificationCode {
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public VerificationCode(String code) {
		super();
		this.code = code;
	}
	
	public VerificationCode() {
		super();
	}
}
