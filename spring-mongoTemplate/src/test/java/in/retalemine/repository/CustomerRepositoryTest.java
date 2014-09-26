package in.retalemine.repository;

import in.retalemine.entity.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations = { "classpath:spring-mongo-config.xml" })
public class CustomerRepositoryTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private MongoConverter mongoConverter;

	public CustomerRepositoryTest() {
		// TODO Auto-generated constructor stub
	}

	@Test(enabled = true)
	public void test_save() {
		customerRepository.deleteAll();

		// save a couple of customers
		customerRepository.save(new Customer("Alice Smith", 101, "address"));
		customerRepository.save(new Customer("Alice Gills", 201, "address 2"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : customerRepository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("--------------------------------");
		System.out
				.println(customerRepository.findByCustomerName("Alice Smith"));

		System.out.println("--------------------------------");
		for (Customer customer : customerRepository.findByContactNo(101)) {
			System.out.println(customer);
		}

		customerRepository.insert(new Customer("Alex", 301, "address 3"));
	}

}
