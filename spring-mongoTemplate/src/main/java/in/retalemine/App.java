package in.retalemine;

import java.util.List;

import in.retalemine.config.SpringMongoConfig;
import in.retalemine.config.SpringMongoConfigYA;
import in.retalemine.model.Employee;

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
	public static void main(String[] args) {
		ApplicationContext ctx;

		// ctx = new ClassPathXmlApplicationContext("spring-mongo-config.xml");
		// ctx = new GenericXmlApplicationContext("spring-mongo-config.xml");
		// ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		ctx = new AnnotationConfigApplicationContext(SpringMongoConfigYA.class);

		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

		App app = new App();
		app.handleEmployeeCollection(mongoOperation);
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
