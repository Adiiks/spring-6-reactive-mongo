package pl.adrian.reactivemongo.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pl.adrian.reactivemongo.domain.Beer;

public interface BeerRepository extends ReactiveMongoRepository<Beer, String> {
}
