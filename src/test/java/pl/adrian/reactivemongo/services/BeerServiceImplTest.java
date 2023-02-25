package pl.adrian.reactivemongo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.adrian.reactivemongo.domain.Beer;
import pl.adrian.reactivemongo.mappers.BeerMapper;
import pl.adrian.reactivemongo.model.BeerDTO;
import pl.adrian.reactivemongo.repositories.BeerRepository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public
class BeerServiceImplTest {

    @Autowired
    BeerService beerService;

    @Autowired
    BeerMapper beerMapper;

    @Autowired
    BeerRepository beerRepository;

    BeerDTO beerDTO;

    @BeforeEach
    void setUp() {
        beerDTO = beerMapper.beerToBeerDto(getTestBeer());
    }

    @Test
    void saveBeer() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        Mono<BeerDTO> savedBeer = beerService.saveBeer(Mono.just(beerDTO));

        savedBeer.subscribe(beer -> {
            System.out.println(beer.getId());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void findFirstByBeerName() {
        Beer beer = getTestBeer();
        beerRepository.save(beer);

        Mono<BeerDTO> foundBeer = beerService.findFirstByBeerName(beer.getBeerName());

        assertNotNull(foundBeer.block());
    }

    @Test
    void findAllByBeerStyle() {
        Beer beer = getTestBeer();
        beerRepository.save(beer);

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerService.findAllByBeerStyle(beer.getBeerStyle())
                .subscribe(dto -> {
                    System.out.println(dto.toString());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    public static Beer getTestBeer() {
        return Beer.builder()
                .beerName("Space Dust")
                .beerStyle("IPA")
                .price(BigDecimal.TEN)
                .quantityOnHand(12)
                .upc("13213123")
                .build();
    }
}