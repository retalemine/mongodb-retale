package in.retalemine.repository;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomRepository<T> {

	public void insert(T entity);
}
