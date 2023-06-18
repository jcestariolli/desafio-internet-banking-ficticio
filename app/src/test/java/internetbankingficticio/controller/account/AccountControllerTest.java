package internetbankingficticio.controller.account;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.service.account.AccountServiceIF;
import internetbankingficticio.service.customeraccount.CustomerAccountServiceIF;
import internetbankingficticio.service.transaction.TransactionServiceIF;
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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static internetbankingficticio.test.utils.TestUtils.*;
import static internetbankingficticio.test.utils.account.AccountObjectsTestUtils.*;
import static internetbankingficticio.test.utils.account.AccountServiceMockTestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
public class AccountControllerTest extends AbstractTest {

    private static final String API_ENDPOINT = "/contas";
    private static ObjectMapper MAPPER;
    @Autowired
    private MockMvc mvc;
    @MockBean
    private AccountServiceIF accountService;
    @MockBean
    private CustomerAccountServiceIF customerAccountServiceIF;
    @MockBean
    private TransactionServiceIF transactionServiceIF;

    @BeforeAll
    public static void configureObjectMapperBeforeTests() {
        MAPPER = configureObjectMapper();
    }

    @Test
    @DisplayName("Should return Status Ok (200) And AccountDtoList when listAllAccounts API finds at least one Account")
    public void shouldReturnStatusOkAndAccountDtoList_whenListAllAccountsFindsAccount() throws Exception {
        List<AccountDto> expectedAccountDtoList = generateAccountDtoListObject();
        mockServiceListAllAccountsWithAccountList(accountService, expectedAccountDtoList);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(API_ENDPOINT).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).andReturn();

        List<AccountDto> returnedAccountDtoList = parseResponse(MAPPER, mvcResult, new TypeReference<List<AccountDto>>() {
        });
        assertThat(returnedAccountDtoList).isNotEmpty();
        assertThat(returnedAccountDtoList).size().isEqualTo(expectedAccountDtoList.size());
        assertEquals(returnedAccountDtoList, expectedAccountDtoList);
    }

    @Test
    @DisplayName("Should return Status NoContent (204) when listAllAccounts API does not find any Account")
    public void shouldReturnStatusNoContent_whenListAllAccountsApiDoesNotFindAnyAccount() throws Exception {
        mockServiceListAllAccountsWithAccountList(accountService, new ArrayList<>());
        mvc.perform(MockMvcRequestBuilders.get(API_ENDPOINT).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isNoContent()).andReturn();
    }

    @Test
    @DisplayName("Should return Status Ok (200) And AccountDto when findAccountById API finds a Account")
    public void shouldReturnStatusOkAndAccountDto_whenFindAccountByIdApiFindsAccount() throws Exception {
        String accountId = "12345678";
        AccountDto expectedAccountDto = generateAccountDtoObject(accountId, BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP), true);
        mockServiceFindAccountByIdWithAccount(accountService, accountId, expectedAccountDto);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(API_ENDPOINT + "/" + accountId).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).andReturn();

        AccountDto returnedAccountDto = parseResponse(MAPPER, mvcResult, AccountDto.class);
        assertThat(returnedAccountDto).isEqualTo(expectedAccountDto);
    }

    @Test
    @DisplayName("Should return Status NotFound (404) when findAccountById API does not find the Resource")
    public void shouldReturnStatusNotFound_whenFindAccountByIdApiDoesNotFindTheResource() throws Exception {
        String accountId = "12345678";
        mockServiceFindAccountByIdWithEmptyResult(accountService, accountId);
        mvc.perform(MockMvcRequestBuilders.get(API_ENDPOINT + "/" + accountId).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    @DisplayName("Should return Status Created (201) when createAccount API")
    public void shouldReturnStatusCreated_whenCreateAccountApi() throws Exception {
        AccountDto expectedAccountDto = generateAccountDtoObject("12345678", BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP), true);
        mockServiceCreateAccountWithAccount(accountService, expectedAccountDto);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(API_ENDPOINT).content(asJsonString(MAPPER, generateAccountCreateDtoObject("12345678", BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP), true))).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated()).andReturn();

        AccountDto returnedAccountDto = parseResponse(MAPPER, mvcResult, AccountDto.class);
        assertThat(returnedAccountDto).isEqualTo(expectedAccountDto);
    }

    @Test
    @DisplayName("Should return Status Ok (200) when updateAccount API")
    public void shouldReturnStatusOk_whenUpdateAccountApi() throws Exception {
        String accountId = "12345678";
        AccountDto expectedAccountDto = generateAccountDtoObject(accountId, BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP), true);
        mockServiceUpdateAccountWithAccount(accountService, expectedAccountDto);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(API_ENDPOINT + "/" + accountId).content(asJsonString(MAPPER, generateAccountUpdateDtoObject(true))).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).andReturn();

        AccountDto returnedAccountDto = parseResponse(MAPPER, mvcResult, AccountDto.class);
        assertThat(returnedAccountDto).isEqualTo(expectedAccountDto);
    }

    @Test
    @DisplayName("Should return Status NotFound (404) when updateAccount API does not find the Resource")
    public void shouldReturnStatusNotFound_whenUpdateAccountApiDoesNotFindTheResource() throws Exception {
        String accountId = "12345678";
        mockServiceUpdateAccountWithEmptyResult(accountService, accountId, generateAccountUpdateDtoObject(true));
        mvc.perform(MockMvcRequestBuilders.put(API_ENDPOINT + "/" + accountId).content(asJsonString(MAPPER, generateAccountUpdateDtoObject(true))).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isNotFound()).andReturn();

    }
}
