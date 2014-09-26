package in.retalemine.yarepository;

import in.retalemine.entity.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations = { "classpath:spring-mongo-config.xml" })
public class MyCustomerRepositoryTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private MyCustomerRepository myCustomRepository;

	public MyCustomerRepositoryTest() {
		// TODO Auto-generated constructor stub
	}

	@Test(enabled = true)
	public void test_insert() {
		myCustomRepository.insert(new Customer("Lion", 401, "address 4"));
	}
}
