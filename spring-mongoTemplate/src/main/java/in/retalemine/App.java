package in.retalemine;

import in.retalemine.entity.Customer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

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
		}
	}

	private void springMongoTemplate(MongoOperations mongoOperation) {

		Customer customer = new Customer("firstName", 23, "address");
		Query searchCustomerQuery = new Query(Criteria.where("customerName")
				.is("firstName"));

		// save
		mongoOperation.save(customer);

		// find
		Customer savedCustomer = mongoOperation.findOne(searchCustomerQuery,
				Customer.class);

		System.out.println("Saved Customer: " + savedCustomer);

		// update
		mongoOperation.updateFirst(searchCustomerQuery,
				Update.update("customerName", "new Name"), Customer.class);

		// find
		Customer updatedCustomer = mongoOperation.findOne(searchCustomerQuery,
				Customer.class);

		System.out.println("Updated Customer: " + updatedCustomer);

		// delete
		mongoOperation.remove(searchCustomerQuery, Customer.class);

		// List
		List<Customer> listCustomer = mongoOperation.findAll(Customer.class);
		System.out.println("size of customers = " + listCustomer.size());

	}
}
