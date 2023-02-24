package pl.adrian.reactivemongo.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pl.adrian.reactivemongo.domain.Customer;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
}
