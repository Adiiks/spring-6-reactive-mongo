package pl.adrian.reactivemongo.mappers;

import org.mapstruct.Mapper;
import pl.adrian.reactivemongo.domain.Customer;
import pl.adrian.reactivemongo.model.CustomerDTO;

@Mapper
public interface CustomerMapper {

    CustomerDTO customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDTO customerDTO);
}
