package in.retalemine.entity;

import java.util.Date;
import java.util.List;
import org.jscience.economics.money.Money;
import org.jscience.physics.amount.Amount;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Bill {

	@Id
	private Integer billNo;
	@Field("bDate")
	private Date billDate;
	@Field("bItems")
	private List<BillItem> billItems;
	@Field("tax")
	private Tax tax;
	@Field("bTotal")
	private Amount<Money> totalAmount;
	@Field("payment")
	private Payment payment;
	@Field("cDetails")
	private Custom customerDetails;
	@Field("dDelivered")
	private Boolean isDoorDelivery;

	public Bill(Integer billNo, Date billDate, List<BillItem> billItems,
			Amount<Money> totalAmount, Tax tax, Payment payment,
			Custom customerDetails, Boolean isDoorDelivery) {
		super();
		this.billNo = billNo;
		this.billDate = billDate;
		this.billItems = billItems;
		this.totalAmount = totalAmount;
		this.tax = tax;
		this.payment = payment;
		this.customerDetails = customerDetails;
		this.isDoorDelivery = isDoorDelivery;
	}
}
