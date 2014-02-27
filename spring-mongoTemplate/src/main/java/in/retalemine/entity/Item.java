package in.retalemine.entity;

import in.retalemine.constants.MongoDBKeys;
import javax.measure.Measure;
import javax.measure.quantity.Quantity;
import org.jscience.economics.money.Money;
import org.jscience.physics.amount.Amount;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

public class Item<Q extends Quantity> {

	@Field(MongoDBKeys.PRODUCT_NAME)
	protected String productName;
	@Field(MongoDBKeys.PRODUCT_UNIT)
	protected Measure<Double, Q> productUnit;
	@Field(MongoDBKeys.PRODUCT_PRICE)
	protected Amount<Money> unitPrice;
	@Transient
	protected String productDescription;

	public Item(){
		
	}
	
	public Item(String productName, Measure<Double, Q> productUnit,
			Amount<Money> unitPrice) {
		super();
		this.productName = productName;
		this.productUnit = productUnit;
		this.unitPrice = unitPrice;
		this.productDescription = productName + "-" + productUnit;
	}

}
