package in.retalemine.converters;

import in.retalemine.constants.MongoDBKeys;
import in.retalemine.entity.Payment;
import in.retalemine.entity.PaymentMode;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;
import com.mongodb.DBObject;

public class PaymentReadConverter implements Converter<DBObject, Payment> {

	@Override
	public Payment convert(DBObject source) {
		PaymentMode pMode = null;
		if (null != source.get(MongoDBKeys.BILL_PAYMENT_MODE)) {
			for (PaymentMode pm : PaymentMode.values()) {
				if (pm.getValue().equals(
						source.get(MongoDBKeys.BILL_PAYMENT_MODE).toString())) {
					pMode = pm;
				}

			}
		}
		return new Payment(pMode,
				(Boolean) source.get(MongoDBKeys.BILL_PAYMENT_DELAYED),
				(Boolean) source.get(MongoDBKeys.BILL_PAYMENT_PAID),
				(Date) source.get(MongoDBKeys.BILL_PAYMENT_DATE));
	}
}
