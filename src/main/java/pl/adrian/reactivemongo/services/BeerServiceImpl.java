package pl.adrian.reactivemongo.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.adrian.reactivemongo.mappers.BeerMapper;
import pl.adrian.reactivemongo.model.BeerDTO;
import pl.adrian.reactivemongo.repositories.BeerRepository;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public Mono<BeerDTO> saveBeer(Mono<BeerDTO> beerDTO) {
        return beerDTO.map(beerMapper::beerDtoToBeer)
                .flatMap(beerRepository::save)
                .map(beerMapper::beerToBeerDto);
    }

    @Override
    public Mono<BeerDTO> getBeerById(String beerId) {
        return null;
    }
}
