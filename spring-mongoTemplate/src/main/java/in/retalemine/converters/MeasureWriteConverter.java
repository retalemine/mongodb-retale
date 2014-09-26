package in.retalemine.converters;

import in.retalemine.constants.MongoDBKeys;

import javax.measure.Measure;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
@WritingConverter
public class MeasureWriteConverter implements
		Converter<Measure<?, ?>, DBObject> {

	@Override
	public DBObject convert(Measure<?, ?> source) {
		DBObject dbO = new BasicDBObject();
		dbO.put(MongoDBKeys.VALUE, source.getValue());
		dbO.put(MongoDBKeys.UNIT, source.getUnit().toString());
		return dbO;
	}
}
