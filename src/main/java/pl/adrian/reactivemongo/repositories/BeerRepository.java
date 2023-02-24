package pl.adrian.reactivemongo.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pl.adrian.reactivemongo.domain.Beer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BeerRepository extends ReactiveMongoRepository<Beer, String> {

    Mono<Beer> findFirstByBeerName(String beerName);

    Flux<Beer> findAllByBeerStyle(String beerStyle);
}
