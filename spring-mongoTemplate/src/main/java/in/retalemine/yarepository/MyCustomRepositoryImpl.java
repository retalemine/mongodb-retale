package in.retalemine.yarepository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MyCustomRepositoryImpl<T, ID extends Serializable> extends
		SimpleMongoRepository<T, ID> implements MyCustomRepository<T, ID> {

	public MyCustomRepositoryImpl(MongoEntityInformation<T, ID> metadata,
			MongoOperations mongoOperations) {
		super(metadata, mongoOperations);
	}

	@Override
	public void insert(T entity) {
		this.getMongoOperations().insert(entity);

	}

	public Page<T> search(Query query, Pageable pageable) {
		long total = this.getMongoOperations().count(query,
				this.getEntityInformation().getJavaType());
		return new PageImpl<T>(this.getMongoOperations()
				.find(query.with(pageable),
						this.getEntityInformation().getJavaType()), pageable,
				total);
	}

}