package in.retalemine.converters;

import in.retalemine.constants.MongoDBKeys;
import in.retalemine.entity.Payment;
import in.retalemine.entity.PaymentMode;
import org.springframework.core.convert.converter.Converter;
import com.mongodb.DBObject;

public class PaymentReadConverter implements Converter<DBObject, Payment> {

	@Override
	public Payment convert(DBObject source) {
		return new Payment(PaymentMode.valueOf((String) source
				.get(MongoDBKeys.BILL_PAYMENT_MODE)), null, null, null);
	}

}