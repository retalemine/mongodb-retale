package in.retalemine;

import static javax.measure.unit.SI.KILOGRAM;
import in.retalemine.config.SpringMongoConfig;
import in.retalemine.config.SpringMongoConfigYA;
import in.retalemine.entity.Bill;
import in.retalemine.entity.BillItem;
import in.retalemine.entity.Custom;
import in.retalemine.entity.Payment;
import in.retalemine.entity.PaymentMode;
import in.retalemine.entity.Product;
import in.retalemine.entity.Tax;
import in.retalemine.entity.TaxType;
import in.retalemine.model.Customer;
import in.retalemine.model.Employee;
import in.retalemine.repository.Billrepository;
import in.retalemine.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.measure.Measure;
import javax.measure.unit.UnitFormat;
import org.jscience.economics.money.Currency;
import org.jscience.economics.money.Money;
import org.jscience.physics.amount.Amount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * Hello world!
 * 
 */
public class App {

	static final Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		App app = new App();
		ApplicationContext ctx;

//		ctx = new ClassPathXmlApplicationContext("spring-mongo-config.xml");
		ctx = new GenericXmlApplicationContext("spring-mongo-config.xml");
//		ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
//		ctx = new AnnotationConfigApplicationContext(SpringMongoConfigYA.class);

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
			Billrepository billrepository = (Billrepository) ctx
					.getBean(Billrepository.class);
			app.springMongoBillRepository(billrepository);
		}
	}

	private void springMongoBillRepository(Billrepository billrepository) {
		billrepository.deleteAll();

		Custom customer = new Custom("Subramani", 9934, "first street");
		Payment payment = new Payment(PaymentMode.CASH, false, true, new Date());
		Tax tax = new Tax(TaxType.VAT, 4.0, 0.0);
		Currency INR = new Currency("INR");
		// UnitFormat.getInstance().label(INR, "₹");
		UnitFormat.getInstance().alias(INR, "₹");
		Product prod = new Product("Sugar", Measure.valueOf(1.0, KILOGRAM),
				Amount.valueOf(45.0, INR));
		BillItem bItem = new BillItem(prod, Measure.valueOf(5.0, KILOGRAM),
				Amount.valueOf(145.0, INR));
		List<BillItem> bItems = new ArrayList<BillItem>();
		bItems.add(bItem);
		Amount<Money> totalAmount = Amount.valueOf(60.5, INR);
		Bill bill = new Bill(1, new Date(), bItems, totalAmount, tax, payment,
				customer, false);
		billrepository.save(bill);
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
		customerRepository.save(new Customer("Alice", "Smith"));
		customerRepository.save(new Customer("Bob", "Smith"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : customerRepository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(customerRepository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Customer customer : customerRepository.findByLastName("Smith")) {
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
