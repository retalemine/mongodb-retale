package in.retalemine.entity;

import in.retalemine.constants.MongoDBKeys;
import javax.measure.Measure;
import javax.measure.quantity.Quantity;
import org.jscience.economics.money.Money;
import org.jscience.physics.amount.Amount;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

public class BillItem<Q extends Quantity> extends Item<Q> {

	@Field(MongoDBKeys.BILL_ITEM_QTY)
	private Measure<Double, Q> quantity;
	@Transient
	private Amount<Money> amount;

	public BillItem(){
	}
	
	public BillItem(String productName, Measure<Double, Q> productUnit,
			Amount<Money> unitPrice, Measure<Double, Q> quantity,
			Amount<Money> amount) {
		super(productName, productUnit, unitPrice);
		this.quantity = quantity;
		//Assert.assertEquals(calculateAmount(productUnit, unitPrice, quantity), amount);
		this.amount = amount;
	}

	public Amount<Money> getAmount() {
		return amount;
	}

	private Object calculateAmount(Measure<Double, Q> productUnit,
			Amount<Money> unitPrice, Measure<Double, Q> quantity) {
		return unitPrice.times(quantity.to(productUnit.getUnit()).getValue()
				/ productUnit.getValue());
	}

}
