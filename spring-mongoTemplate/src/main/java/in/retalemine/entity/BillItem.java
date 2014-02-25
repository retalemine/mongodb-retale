package in.retalemine.entity;

import javax.measure.Measure;
import org.jscience.economics.money.Money;
import org.jscience.physics.amount.Amount;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

public class BillItem extends Product {
	@Field("qty")
	private Measure<?, ?> quantity;
	@Transient
	private Amount<Money> amount;

	public BillItem(Product product, Measure<?, ?> quantity,
			Amount<Money> amount) {
		this.productName = product.productName;
		this.unit = product.unit;
		this.unitPrice = product.unitPrice;
		this.quantity = quantity;
		this.amount = amount;
	}
}
