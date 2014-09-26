package in.retalemine.yarepository;

import in.retalemine.entity.Customer;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyCustomerRepository extends MongoRepository<Customer, String>,
		MyCustomRepository<Customer, String> {

	public Customer findByCustomerName(String customerName);

	public List<Customer> findByContactNo(Integer contactNo);

}