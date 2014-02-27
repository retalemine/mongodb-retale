package in.retalemine.entity;

import in.retalemine.constants.MongoDBKeys;
import java.util.Date;
import java.util.List;
import javax.measure.Measure;
import javax.measure.quantity.Quantity;
import org.jscience.economics.money.Money;
import org.jscience.physics.amount.Amount;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Product<Q extends Quantity> extends Item<Q> {

	@Field(MongoDBKeys.PRODUCT_PRICES)
	private List<Amount<Money>> unitPrices;
	@Field(MongoDBKeys.PRODUCT_CREATION_OR_MODIFIED_DATE)
	private Date createdOrModifiedDate;

	public Product(String productName, Measure<Double, Q> productUnit,
			Amount<Money> unitPrice, List<Amount<Money>> unitPrices,
			Date createdOrModifiedDate) {
		super(productName, productUnit, unitPrice);
		this.unitPrices = unitPrices;
		this.createdOrModifiedDate = createdOrModifiedDate;
	}

}
