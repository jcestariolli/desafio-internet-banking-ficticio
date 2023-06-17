package internetbankingficticio.customer.repository;

import internetbankingficticio.AbstractTest;
import internetbankingficticio.customer.dao.CustomerDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static internetbankingficticio.utils.TestUtils.getDateNow;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CustomerRepositoryIntegrationTest extends AbstractTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Should not find any Customer when Repository is empty")
    public void shouldNotFindAnyCustomer_whenRepositoryIsEmpty() {
        List<CustomerDao> customerList = customerRepository.findAll();

        assertThat(customerList).isEmpty();
    }

    @Test
    @DisplayName("Should find all Customers when Repository has data")
    public void shouldFindAllCustomers_whenRepositoryHasData() {
        CustomerDao customer1 = testEntityManager.persist(CustomerDao.builder().name("Customer Test 1").birthday(getDateNow()).build());
        CustomerDao customer2 = testEntityManager.persist(CustomerDao.builder().name("Customer Test 2").birthday(getDateNow()).build());
        CustomerDao customer3 = testEntityManager.persist(CustomerDao.builder().name("Customer Test 3").birthday(getDateNow()).build());
        CustomerDao customer4 = testEntityManager.persist(CustomerDao.builder().name("Customer Test 4").birthday(getDateNow()).build());

        List<CustomerDao> customerList = customerRepository.findAll();

        assertThat(customerList).hasSize(4).contains(customer1, customer2, customer3, customer4);
    }

    @Test
    @DisplayName("Should find Customer by CustomerId")
    public void shouldFindCustomerById() {
        CustomerDao customerXpto = testEntityManager.persist(CustomerDao.builder().name("Customer Test XPTO").birthday(getDateNow()).build());

        Optional<CustomerDao> foundCustomer = customerRepository.findById(customerXpto.getId());

        assertThat(foundCustomer).isPresent();
        assertThat(foundCustomer.get()).isEqualTo(customerXpto);
    }

    @Test
    @DisplayName("Should store the Customer")
    public void shouldStoreCustomer() {
        CustomerDao customer = CustomerDao.builder().name("Customer Test Single").birthday(getDateNow()).build();

        CustomerDao storedCustomer = customerRepository.save(customer);

        assertThat(storedCustomer).hasFieldOrProperty("id");
        assertThat(storedCustomer).hasFieldOrPropertyWithValue("name", customer.getName());
        assertThat(storedCustomer).hasFieldOrPropertyWithValue("birthday", new Timestamp(customer.getBirthday().getTime()));
    }

    @Test
    @DisplayName("Should update the Customer by CustomerId")
    public void shouldUpdateCustomerById() {
        CustomerDao customer5 = testEntityManager.persist(CustomerDao.builder().name("Customer Test 5").birthday(getDateNow()).build());

        CustomerDao foundCustomerToUpdate = customerRepository.findById(customer5.getId()).get();
        CustomerDao updatedCustomer5 = CustomerDao.builder().name("Updated Customer Test 5").birthday(getDateNow()).build();
        foundCustomerToUpdate.setName(updatedCustomer5.getName());
        foundCustomerToUpdate.setBirthday(updatedCustomer5.getBirthday());

        customerRepository.save(foundCustomerToUpdate);

        CustomerDao storedCustomerAfterUpdate = customerRepository.findById(customer5.getId()).get();
        assertThat(storedCustomerAfterUpdate.getId()).isEqualTo(customer5.getId());
        assertThat(storedCustomerAfterUpdate.getName()).isEqualTo(updatedCustomer5.getName());
        assertThat(storedCustomerAfterUpdate.getBirthday()).isEqualTo(updatedCustomer5.getBirthday());
    }

    @Test
    @DisplayName("Should delete the Customer by CustomerId")
    public void shouldDeleteCustomerById() {
        CustomerDao customer9 = testEntityManager.persist(CustomerDao.builder().name("Customer Test 9").birthday(getDateNow()).build());
        CustomerDao customer10 = testEntityManager.persist(CustomerDao.builder().name("Customer Test 10").birthday(getDateNow()).build());

        customerRepository.deleteById(customer10.getId());

        List<CustomerDao> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(1).contains(customer9);
    }

    @Test
    @DisplayName("Should delete all Customers")
    public void shouldDeleteAllCustomers() {
        testEntityManager.persist(CustomerDao.builder().name("Customer Test A").birthday(getDateNow()).build());
        testEntityManager.persist(CustomerDao.builder().name("Customer Test B").birthday(getDateNow()).build());
        testEntityManager.persist(CustomerDao.builder().name("Customer Test C").birthday(getDateNow()).build());
        testEntityManager.persist(CustomerDao.builder().name("Customer Test D").birthday(getDateNow()).build());

        customerRepository.deleteAll();

        assertThat(customerRepository.findAll()).isEmpty();
    }

}
