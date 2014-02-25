package in.retalemine.repository;

import in.retalemine.entity.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Billrepository extends MongoRepository<Bill, String> {

}
