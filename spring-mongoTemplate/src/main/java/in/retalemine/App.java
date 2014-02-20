package in.retalemine;

import java.util.List;
import in.retalemine.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		App app = new App();
		app.handleEmployeeCollection();
	}

	private void handleEmployeeCollection() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"spring-mongo-config.xml");

		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

		Employee employee = new Employee("100", "firstName", "lastName", 23);

		// save
		mongoOperation.save(employee);

		// find
		Employee savedEmployee = mongoOperation.findOne(new Query(Criteria
				.where("id").is("100")), Employee.class);

		System.out.println("Saved Employee: " + savedEmployee);

		// update
		mongoOperation.updateFirst(
				new Query(Criteria.where("firstname").is("firstName")),
				Update.update("lastname", "new lastName"), Employee.class);

		// find
		Employee updatedEmployee = mongoOperation.findOne(new Query(Criteria
				.where("id").is("100")), Employee.class);

		System.out.println("Updated Employee: " + updatedEmployee);

		// delete
		mongoOperation.remove(new Query(Criteria.where("id").is("100")),
				Employee.class);

		// List
		List<Employee> listEmployee = mongoOperation.findAll(Employee.class);
		System.out.println("size of employees = " + listEmployee.size());

	}
}
