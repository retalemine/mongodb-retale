package in.retalemine.converters;

import org.jscience.physics.amount.Amount;
import org.springframework.core.convert.converter.Converter;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class AmountWriteConverter implements Converter<Amount<?>, DBObject> {

	@Override
	public DBObject convert(Amount<?> source) {
		DBObject obj = new BasicDBObject();
		obj.put("value", source.getEstimatedValue());
		obj.put("unit", source.getUnit().toString());
		return obj;
	}

}
