package in.retalemine.entity;

import org.springframework.data.mongodb.core.mapping.Field;

public class Custom {

	@Field("name")
	private String customerName;
	@Field("contactNo")
	private Integer contactNo;
	@Field("address")
	private String address;

	public Custom(String customerName, Integer contactNo, String address) {
		super();
		this.customerName = customerName;
		this.contactNo = contactNo;
		this.address = address;
	}
}
