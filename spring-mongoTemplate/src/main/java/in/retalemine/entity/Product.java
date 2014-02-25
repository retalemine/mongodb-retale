package in.retalemine.entity;

import java.util.Date;
import javax.measure.Measure;
import org.jscience.economics.money.Money;
import org.jscience.physics.amount.Amount;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

public class Product {

	@Field("pName")
	protected String productName;
	@Field("pUnit")
	protected Measure<?, ?> unit;
	@Field("pPrice")
	protected Amount<Money> unitPrice;
	// Amount<?> oilPrice = Amount.valueOf(120, INR.divide(LITER)); //120 INR/L
	@Transient
	private Date CreatedOrModifiedDate;

	public Product() {
		super();
	}
	
	public Product(String productName, Measure<?, ?> unit,
			Amount<Money> unitPrice) {
		super();
		this.productName = productName;
		this.unit = unit;
		this.unitPrice = unitPrice;
	}

}
