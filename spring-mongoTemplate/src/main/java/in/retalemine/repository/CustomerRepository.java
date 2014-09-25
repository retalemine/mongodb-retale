package in.retalemine.repository;

import in.retalemine.entity.Customer;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String>,
		CustomRepository<Customer> {

	public Customer findByCustomerName(String customerName);

	public List<Customer> findByContactNo(Integer contactNo);

}