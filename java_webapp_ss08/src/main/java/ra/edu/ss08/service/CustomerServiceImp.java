package ra.edu.ss08.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.ss08.model.entity.Customer;
import ra.edu.ss08.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("khong ton tai khach hang"));
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Customer cus = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khach hang khong ton tai"));
        cus.setId(id);
        return customerRepository.save(cus);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer cus = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khach hang khong ton tai"));
        customerRepository.delete(cus);
    }
}
