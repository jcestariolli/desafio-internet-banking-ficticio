package internetbankingficticio.controller.customer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import internetbankingficticio.dto.customer.CustomerDto;
import internetbankingficticio.dto.customer.CustomerUpdateDto;
import internetbankingficticio.service.customer.CustomerServiceIF;
import internetbankingficticio.service.customeraccount.CustomerAccountServiceIF;
import internetbankingficticio.test.AbstractTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static internetbankingficticio.test.utils.TestUtils.*;
import static internetbankingficticio.test.utils.customer.CustomerObjectsTestUtils.*;
import static internetbankingficticio.test.utils.customer.CustomerServiceMockTestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest extends AbstractTest {

    private static final String API_ENDPOINT = "/clientes";
    private static ObjectMapper MAPPER;
    @Autowired
    private MockMvc mvc;
    @MockBean
    private CustomerServiceIF customerService;
    @MockBean
    private CustomerAccountServiceIF customerAccountServiceIF;

    @BeforeAll
    public static void configureObjectMapperBeforeTests() {
        MAPPER = configureObjectMapper();
    }

    @Test
    @DisplayName("Should return Status Ok (200) And CustomerDtoList when listAllCustomers API")
    public void shouldReturnStatusOkAndCustomerDtoList_whenListAllCustomers() throws Exception {
        List<CustomerDto> expectedCustomerDtoList = generateCustomerDtoListObject();
        mockServiceListAllCustomersWithCustomerList(customerService, expectedCustomerDtoList);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(API_ENDPOINT).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).andReturn();
        List<CustomerDto> returnedCustomerDtoList = parseResponse(MAPPER, mvcResult, new TypeReference<List<CustomerDto>>() {
        });
        assertThat(returnedCustomerDtoList).isNotEmpty();
        assertThat(returnedCustomerDtoList).size().isEqualTo(expectedCustomerDtoList.size());
        assertEquals(returnedCustomerDtoList, expectedCustomerDtoList);
    }


    @Test
    @DisplayName("Should return Status Ok (200) And CustomerDto when findCustomerById API finds a Customer")
    public void shouldReturnStatusOkAndCustomerDto_whenFindCustomerByIdApiFindsCustomer() throws Exception {
        Long customerId = 1L;
        CustomerDto expectedCustomerDto = generateCustomerDtoObject(customerId, "Customer Test 1");
        mockServiceFindCustomerByIdWithCustomer(customerService, customerId, expectedCustomerDto);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(API_ENDPOINT + "/" + customerId).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).andReturn();

        CustomerDto returnedCustomerDto = parseResponse(MAPPER, mvcResult, CustomerDto.class);
        assertThat(returnedCustomerDto).isEqualTo(expectedCustomerDto);
    }

    @Test
    @DisplayName("Should return Status NotFound (404) when findCustomerById API does not find the Resource")
    public void shouldReturnStatusNotFound_whenFindCustomerByIdApiDoesNotFindTheResource() throws Exception {
        Long customerId = 1L;
        mockServiceFindCustomerByIdThrowCustomerNotFoundExcept(customerService, customerId);
        mvc.perform(MockMvcRequestBuilders.get(API_ENDPOINT + "/" + customerId).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    @DisplayName("Should return Status Created (201) when createCustomer API")
    public void shouldReturnStatusCreated_whenCreateCustomerApi() throws Exception {
        CustomerDto expectedCustomerDto = generateCustomerDtoObject(1L, "Customer Test");
        mockServiceCreateCustomerWithCustomer(customerService, expectedCustomerDto);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(API_ENDPOINT).content(asJsonString(MAPPER, generateCustomerCreateDtoObject("Customer Test"))).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated()).andReturn();

        CustomerDto returnedCustomerDto = parseResponse(MAPPER, mvcResult, CustomerDto.class);
        assertThat(returnedCustomerDto).isEqualTo(expectedCustomerDto);
    }

    @Test
    @DisplayName("Should return Status Ok (200) when updateCustomer API")
    public void shouldReturnStatusOk_whenUpdateCustomerApi() throws Exception {
        Long customerId = 1L;
        String customerName = "Customer Test 1";
        CustomerDto expectedCustomerDto = generateCustomerDtoObject(customerId, customerName);
        mockServiceUpdateCustomerWithCustomer(customerService, expectedCustomerDto);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(API_ENDPOINT + "/" + customerId).content(asJsonString(MAPPER, generateCustomerUpdateDtoObject(customerName))).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).andReturn();

        CustomerDto returnedCustomerDto = parseResponse(MAPPER, mvcResult, CustomerDto.class);
        assertThat(returnedCustomerDto).isEqualTo(expectedCustomerDto);
    }

    @Test
    @DisplayName("Should return Status NotFound (404) when updateCustomer API does not find the Resource")
    public void shouldReturnStatusNotFound_whenUpdateCustomerApiDoesNotFindTheResource() throws Exception {
        Long customerId = 1L;
        mockServiceUpdateCustomerCustomerNotFoundExcept(customerService, customerId);
        mvc.perform(MockMvcRequestBuilders.put(API_ENDPOINT + "/" + customerId).content(asJsonString(MAPPER, CustomerUpdateDto.builder().name("Teste Update").build())).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isNotFound()).andReturn();
    }
}
