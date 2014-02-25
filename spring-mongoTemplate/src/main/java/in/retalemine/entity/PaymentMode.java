package in.retalemine.entity;

public enum PaymentMode {
	CASH("cash"), CHEQUE("cheque");

	private String value;

	private PaymentMode(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
