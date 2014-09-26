package in.retalemine.yarepository;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MyCustomRepository<T, ID extends Serializable> extends
		MongoRepository<T, ID> {

	public void insert(T entity);
}