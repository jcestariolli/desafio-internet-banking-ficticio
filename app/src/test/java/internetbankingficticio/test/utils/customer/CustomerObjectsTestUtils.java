package internetbankingficticio.test.utils.customer;

import internetbankingficticio.dao.customer.CustomerDao;
import internetbankingficticio.dto.customer.CustomerCreateDto;
import internetbankingficticio.dto.customer.CustomerDto;
import internetbankingficticio.dto.customer.CustomerUpdateDto;

import java.util.List;

import static internetbankingficticio.utils.DateUtils.getDateNow;

public class CustomerObjectsTestUtils {


    public static List<CustomerDao> generateCustomerDaoListObject() {
        return List.of(generateCustomerDaoObject(1L, "Customer Test 1"), generateCustomerDaoObject(2L, "Customer Test 2"), generateCustomerDaoObject(3L, "Customer Test 3"));
    }

    public static CustomerDao generateCustomerDaoObject(Long id, String name) {
        return CustomerDao.builder().id(id).name(name).birthday(getDateNow()).build();
    }

    public static CustomerDao generateCustomerDaoObject(String name) {
        return CustomerDao.builder().name(name).birthday(getDateNow()).build();
    }

    public static List<CustomerDto> generateCustomerDtoListObject() {
        return List.of(generateCustomerDtoObject(1L, "Customer Test 1"), generateCustomerDtoObject(2L, "Customer Test 2"), generateCustomerDtoObject(3L, "Customer Test 3"));
    }

    public static CustomerDto generateCustomerDtoObject(Long id, String name) {
        return CustomerDto.builder().id(id).name(name).birthday(getDateNow()).build();
    }

    public static CustomerCreateDto generateCustomerCreateDtoObject(String name) {
        return CustomerCreateDto.builder().name(name).birthday(getDateNow()).build();
    }

    public static CustomerUpdateDto generateCustomerUpdateDtoObject(String name) {
        return CustomerUpdateDto.builder().name(name).birthday(getDateNow()).build();
    }
}
