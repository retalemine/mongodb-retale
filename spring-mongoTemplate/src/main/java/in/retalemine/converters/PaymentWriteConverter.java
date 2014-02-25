package in.retalemine.converters;

import in.retalemine.constants.MongoDBKeys;
import in.retalemine.entity.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class PaymentWriteConverter implements Converter<Payment, DBObject> {

	static final Logger logger = LoggerFactory
			.getLogger(PaymentWriteConverter.class);

	@Override
	public DBObject convert(Payment source) {
		// DBObject db1 = new BasicDBObject();
		// for (Field field : source.getClass().getDeclaredFields()) {
		// try {
		// if (null != field
		// .getAnnotation(org.springframework.data.mongodb.core.mapping.Field.class))
		// {
		// logger.info(field.getType().toString());
		// logger.info(field.getType().getName());
		// logger.info(field.getType().getSimpleName());
		// logger.info(field.getGenericType().toString());
		// if (false) {
		//
		// } else {
		//
		// logger.info(
		// "@fielded {}",
		// field.getAnnotation(
		// org.springframework.data.mongodb.core.mapping.Field.class)
		// .value());
		//
		// field.setAccessible(true);
		// logger.info(field.get(source).toString());
		// db1.put(field
		// .getAnnotation(
		// org.springframework.data.mongodb.core.mapping.Field.class)
		// .value(), field.get(source));
		// }
		//
		// } else {
		// field.setAccessible(true);
		// logger.info("nun {}", field.getName());
		// db1.put(field.getName(), field.get(source));
		// }
		// } catch (IllegalArgumentException e) {
		// e.printStackTrace();
		// } catch (IllegalAccessException e) {
		// e.printStackTrace();
		// }
		//
		// }
		DBObject db = new BasicDBObject();
		db.put(MongoDBKeys.BILL_PAYMENT_MODE, source.getPayMode().getValue());
		db.put("delayed", source.getIsDelayedPay());
		db.put("paid", source.getIsPaid());
		db.put("date", source.getPaidDate());
		return db;
	}
}
