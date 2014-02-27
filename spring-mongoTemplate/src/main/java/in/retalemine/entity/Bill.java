package in.retalemine.entity;

import in.retalemine.constants.MongoDBKeys;
import java.util.Date;
import java.util.List;
import javax.measure.quantity.Quantity;
import javax.measure.unit.Unit;
import org.jscience.economics.money.Money;
import org.jscience.physics.amount.Amount;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Bill {

	@Id
	private Integer billNo;
	@Field(MongoDBKeys.BILL_DATE)
	private Date billDate;
	@Field(MongoDBKeys.BILL_ITEMS)
	private List<BillItem<? extends Quantity>> billItems;
	@Transient
	private Amount<Money> subTotal;
	@Field(MongoDBKeys.BILL_TAX)
	private List<Tax> taxes;
	@Field(MongoDBKeys.BILL_TOTAL)
	private Amount<Money> total;
	@Field(MongoDBKeys.BILL_PAYMENT)
	private Payment paymentDetails;
	@Field(MongoDBKeys.BILL_CUS_DETAIL)
	private Customer customerDetails;
	@Field(MongoDBKeys.BILL_DELIVERED)
	private Boolean isDoorDelivery;

	public Bill(){
		
	}
	
	public Bill(Integer billNo, Date billDate,
			List<BillItem<? extends Quantity>> billItems,
			Amount<Money> subTotal, List<Tax> taxes, Amount<Money> total,
			Payment paymentDetails, Customer customerDetails,
			Boolean isDoorDelivery) {
		super();
		this.billNo = billNo;
		this.billDate = billDate;
		this.billItems = billItems;
		//Assert.assertEquals(calculateSubTotal(billItems,subTotal.getUnit()), subTotal);
		this.subTotal = subTotal;
		this.taxes = taxes;
		//Assert.assertEquals(calculateTotal(this.subTotal, taxes), total);
		this.total = total;
		this.paymentDetails = paymentDetails;
		this.customerDetails = customerDetails;
		this.isDoorDelivery = isDoorDelivery;
	}

	private Amount<Money> calculateSubTotal(
			List<BillItem<? extends Quantity>> billItems, Unit<Money> currencyUnit) {
		Amount<Money> subTotal = Amount.valueOf(0.0,currencyUnit);
		for (BillItem<? extends Quantity> billItem : billItems) {
			subTotal = subTotal.plus(billItem.getAmount());
		}
		return subTotal;
	}

	private Amount<Money> calculateTotal(Amount<Money> subTotal, List<Tax> taxes) {
		Amount<Money> total = Amount.valueOf(0.0,subTotal.getUnit());
		Double percent = 0.0;
		for (Tax tax : taxes) {
			percent += tax.getTaxPercent();
		}
		total = total.plus(subTotal).plus(subTotal.times(percent/100));
		return total;
	}
}
