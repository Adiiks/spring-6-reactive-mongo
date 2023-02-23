package pl.adrian.reactivemongo.mappers;

import org.mapstruct.Mapper;
import pl.adrian.reactivemongo.domain.Beer;
import pl.adrian.reactivemongo.model.BeerDTO;

@Mapper
public interface BeerMapper {

    BeerDTO beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDTO beerDTO);
}
