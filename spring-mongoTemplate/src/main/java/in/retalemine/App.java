package in.retalemine;

import static javax.measure.unit.NonSI.*;
import static javax.measure.unit.SI.*;
import in.retalemine.entity.Bill;
import in.retalemine.entity.BillItem;
import in.retalemine.entity.Customer;
import in.retalemine.entity.Payment;
import in.retalemine.entity.PaymentMode;
import in.retalemine.entity.Product;
import in.retalemine.entity.Tax;
import in.retalemine.entity.TaxType;
import in.retalemine.model.Employee;
import in.retalemine.repository.BillRepository;
import in.retalemine.repository.CustomerRepository;
import in.retalemine.repository.ProductRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.measure.Measure;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Quantity;
import javax.measure.quantity.Volume;
import javax.measure.unit.UnitFormat;

import org.jscience.economics.money.Currency;
import org.jscience.economics.money.Money;
import org.jscience.physics.amount.Amount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * Hello world!
 * 
 */
public class App {

	private static final Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		App app = new App();
		ApplicationContext ctx;

		// ctx = new ClassPathXmlApplicationContext("spring-mongo-config.xml");
		ctx = new GenericXmlApplicationContext("spring-mongo-config.xml");
		// ctx = new
		// AnnotationConfigApplicationContext(SpringMongoConfig.class);
		// ctx = new
		// AnnotationConfigApplicationContext(SpringMongoConfigYA.class);

		System.out.println("Beans available in application contex:");
		logger.info("Beans available in application context");
		for (String beanname : ctx.getBeanDefinitionNames()) {
			System.out.println(beanname);
			logger.info("Bean Name - {}", beanname);
		}

		if (args.length > 0) {
			MongoOperations mongoOperation = (MongoOperations) ctx
					.getBean("mongoTemplate");
			app.springMongoTemplate(mongoOperation);
		} else {
			CustomerRepository customerRepository = (CustomerRepository) ctx
					.getBean(CustomerRepository.class);
			app.springMongoRepository(customerRepository);

			BillRepository billrepository = (BillRepository) ctx
					.getBean(BillRepository.class);
			MongoConverter mm = new MappingMongoConverter(
					(MongoDbFactory) ctx.getBean("mongoDbFactory"),
					(MappingContext) ctx
							.getBean("mappingConverter.mappingContext"));
			app.springMongoBillRepository(billrepository, mm);

			ProductRepository prodrepository = (ProductRepository) ctx
					.getBean(ProductRepository.class);
			app.springMongoProdRepository(prodrepository, mm);

		}
	}

	private void springMongoProdRepository(ProductRepository prodrepository,
			MongoConverter mm) {
		Currency INR = new Currency("INR");

		prodrepository.deleteAll();

		Product<Mass> prod = new Product<Mass>("Sugar", Measure.valueOf(1.0,
				KILOGRAM), null, Arrays.asList(Amount.valueOf(45.0, INR)),
				new Date());
		prodrepository.save(prod);

		Product<Volume> prod2 = new Product<Volume>("Sun oil", Measure.valueOf(
				1.0, LITER), null, Arrays.asList(Amount.valueOf(145.0, INR)),
				new Date());
		prodrepository.save(prod2);

		Product<?> prodfind = prodrepository.findByProductName("Sugar");
		logger.info("product details {}", prodfind);
		logger.info("product details {}", mm.convertToMongoType(prodfind));
	}

	private void springMongoBillRepository(BillRepository billrepository,
			MongoConverter mm) {
		Currency INR = new Currency("INR");
		// UnitFormat.getInstance().label(INR, "₹");
		UnitFormat.getInstance().alias(INR, "₹");

		billrepository.deleteAll();

		BillItem<Mass> sugar = new BillItem<Mass>("Sugar", Measure.valueOf(1.0,
				KILOGRAM), Amount.valueOf(45.0, INR), Measure.valueOf(5.0,
				KILOGRAM), Amount.valueOf(225.0, INR));
		BillItem<Volume> oil = new BillItem<Volume>("Oil", Measure.valueOf(1.0,
				LITER), Amount.valueOf(150.0, INR), Measure.valueOf(500.0,
				MILLI(LITER)), Amount.valueOf(75.0, INR));
		List<BillItem<? extends Quantity>> bItems = new ArrayList<BillItem<? extends Quantity>>();
		bItems.add(sugar);
		bItems.add(oil);

		Amount<Money> subTotal = Amount.valueOf(300.0, INR);
		Tax tax = new Tax(TaxType.VAT, 4.0);
		Amount<Money> totalAmount = Amount.valueOf(312.0, INR);
		Payment payment = new Payment(PaymentMode.CASH, false, true, new Date());
		Customer customer = new Customer("Subramani", 9934, "first street");

		Bill bill = new Bill(1, new Date(), bItems, subTotal,
				Arrays.asList(tax), totalAmount, payment, customer, false);

		billrepository.save(bill);

		Bill fetchBill = billrepository.findOne(1);
		logger.info("bill details {}", fetchBill);
		logger.info("bill details {}", mm.convertToMongoType(fetchBill));
	}

	private void loging(Amount<Money> totalAmount, Currency INR) {
		logger.info("TotalAmount Obj {}", totalAmount);
		logger.info("TotalAmount tostring {}", totalAmount.toString());
		logger.info("TotalAmount abs {}", totalAmount.abs());
		logger.info("TotalAmount approx {}",
				totalAmount.approximates(Amount.valueOf(60.5, INR)));
		logger.info("TotalAmount estimated {}", totalAmount.getEstimatedValue());
		logger.info("TotalAmount max {}", totalAmount.getMaximumValue());
		logger.info("TotalAmount min {}", totalAmount.getMinimumValue());
		logger.info("TotalAmount unit {}", totalAmount.getUnit());
		logger.info("TotalAmount text {}", totalAmount.toText());
	}

	private void springMongoRepository(CustomerRepository customerRepository) {

		customerRepository.deleteAll();

		// save a couple of customers
		customerRepository.save(new in.retalemine.model.Customer("Alice",
				"Smith"));
		customerRepository
				.save(new in.retalemine.model.Customer("Bob", "Smith"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (in.retalemine.model.Customer customer : customerRepository
				.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(customerRepository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (in.retalemine.model.Customer customer : customerRepository
				.findByLastName("Smith")) {
			System.out.println(customer);
		}
	}

	private void springMongoTemplate(MongoOperations mongoOperation) {

		handleEmployeeCollection(mongoOperation);
	}

	private void handleEmployeeCollection(MongoOperations mongoOperation) {

		Employee employee = new Employee("100", "firstName", "lastName", 23);
		Query searchEmployeeQuery = new Query(Criteria.where("id").is("100"));

		// save
		mongoOperation.save(employee);

		// find
		Employee savedEmployee = mongoOperation.findOne(searchEmployeeQuery,
				Employee.class);

		System.out.println("Saved Employee: " + savedEmployee);

		// update
		mongoOperation.updateFirst(searchEmployeeQuery,
				Update.update("lastname", "new lastName"), Employee.class);

		// find
		Employee updatedEmployee = mongoOperation.findOne(searchEmployeeQuery,
				Employee.class);

		System.out.println("Updated Employee: " + updatedEmployee);

		// delete
		mongoOperation.remove(searchEmployeeQuery, Employee.class);

		// List
		List<Employee> listEmployee = mongoOperation.findAll(Employee.class);
		System.out.println("size of employees = " + listEmployee.size());

	}
}
