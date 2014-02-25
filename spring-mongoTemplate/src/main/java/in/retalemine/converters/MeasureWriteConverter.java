package in.retalemine.converters;

import javax.measure.Measure;
import org.springframework.core.convert.converter.Converter;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class MeasureWriteConverter implements
		Converter<Measure<?, ?>, DBObject> {

	@Override
	public DBObject convert(Measure<?, ?> source) {
		DBObject obj = new BasicDBObject();
		obj.put("value", source.getValue());
		obj.put("unit", source.getUnit().toString());
		return obj;
	}

}
