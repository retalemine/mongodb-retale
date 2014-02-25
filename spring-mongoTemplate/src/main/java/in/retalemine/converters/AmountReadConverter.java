package in.retalemine.converters;

import javax.measure.unit.Unit;
import org.jscience.physics.amount.Amount;
import org.springframework.core.convert.converter.Converter;
import com.mongodb.DBObject;

public class AmountReadConverter implements Converter<DBObject, Amount<?>> {

	@Override
	public Amount<?> convert(DBObject source) {
		return Amount.valueOf(Long.parseLong(source.get("value").toString()),
				Unit.valueOf((CharSequence) source.get("unit")));
	}

}
