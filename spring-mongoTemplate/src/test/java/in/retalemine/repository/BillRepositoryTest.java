package in.retalemine.repository;

import static javax.measure.unit.NonSI.LITER;
import static javax.measure.unit.SI.KILOGRAM;
import static javax.measure.unit.SI.MILLI;
import in.retalemine.entity.Bill;
import in.retalemine.entity.BillItem;
import in.retalemine.entity.Customer;
import in.retalemine.entity.Payment;
import in.retalemine.entity.PaymentMode;
import in.retalemine.entity.Tax;
import in.retalemine.entity.TaxType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;
import javax.measure.unit.UnitFormat;

import org.jscience.economics.money.Currency;
import org.jscience.economics.money.Money;
import org.jscience.physics.amount.Amount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations = { "classpath:spring-mongo-config.xml" })
public class BillRepositoryTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private BillRepository billrepository;

	@Autowired
	private MongoConverter mongoConverter;

	@Test(enabled = true)
	public void test_save() {

		billrepository.deleteAll();

		Currency INR = new Currency("INR");
		// UnitFormat.getInstance().label(INR, "₹");
		UnitFormat.getInstance().alias(INR, "₹");

		BillItem<?, ?> sugar = BillItem.valueOf("Sugar",
				Measure.valueOf(1.0, KILOGRAM), "Sugar - 1kg",
				Amount.valueOf(45.0, INR), Measure.valueOf(5.0, KILOGRAM),
				Amount.valueOf(225.0, INR));
		BillItem<?, ?> oil = BillItem
				.valueOf("Oil", Measure.valueOf(1.0, LITER), "Oil - 1L",
						Amount.valueOf(150.0, INR),
						Measure.valueOf(500.0, MILLI(LITER)),
						Amount.valueOf(75.0, INR));

		List<BillItem<? extends Quantity, ? extends Quantity>> bItems = new ArrayList<BillItem<? extends Quantity, ? extends Quantity>>();
		bItems.add(sugar);
		bItems.add(oil);

		Amount<Money> subTotal = Amount.valueOf(300.0, INR);
		Tax tax = new Tax(TaxType.VAT, 4.0);
		Amount<Money> totalAmount = Amount.valueOf(312.0, INR);
		Payment payment = new Payment(PaymentMode.CASH, false, true, new Date());
		Customer customer = new Customer("User", 9934, "first street");

		Bill bill = new Bill(1, new Date(), bItems, subTotal,
				Arrays.asList(tax), totalAmount, payment, customer, false);

		billrepository.save(bill);
		Bill fetchBill = billrepository.findOne(1);
		mongoConverter.convertToMongoType(fetchBill);
		billrepository.save(bill);
		System.out.println("sme ##");
	}
}
