package in.retalemine.entity;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

public class Tax {

	@Field("type")
	private TaxType taxType;
	@Field("pct")
	private Double taxPercent;
	@Transient
	private Double taxValue;

	public Tax(TaxType taxType, Double taxPercent, Double taxValue) {
		super();
		this.taxType = taxType;
		this.taxPercent = taxPercent;
		this.taxValue = taxValue;
	}

}
