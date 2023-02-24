package pl.adrian.reactivemongo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.adrian.reactivemongo.domain.Beer;
import pl.adrian.reactivemongo.mappers.BeerMapper;
import pl.adrian.reactivemongo.model.BeerDTO;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@SpringBootTest
class BeerServiceImplTest {

    @Autowired
    BeerService beerService;

    @Autowired
    BeerMapper beerMapper;

    BeerDTO beerDTO;

    @BeforeEach
    void setUp() {
        beerDTO = beerMapper.beerToBeerDto(getTestBeer());
    }

    @Test
    void saveBeer() throws InterruptedException {
        Mono<BeerDTO> savedBeer = beerService.saveBeer(Mono.just(beerDTO));

        savedBeer.subscribe(beer -> System.out.println(beer.getId()));

        Thread.sleep(1000L);
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