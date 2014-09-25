package in.retalemine.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class CustomerRepositoryImpl<T> implements CustomRepository<T> {

	@Autowired
	MongoTemplate mongoTemplate;

	public CustomerRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(T entity) {
		System.out.println("I am called .. cool");
		mongoTemplate.insert(entity);
	}

}
