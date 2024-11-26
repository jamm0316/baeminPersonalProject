package contorller;

import customer.CustomerService;
import entity.CustomerDTO;

public class CustoemrController {
    private CustomerService customerService;


    public CustoemrController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public int customerJoin(CustomerDTO customerDTO) {
        return customerService.join(customerDTO);
    }

    public CustomerDTO selectByEmail(String email) {
        return customerService.selectByEmail(email);
    }

    public void deleteMember(String email) {
        customerService.deleteMember(email);
    }

    public CustomerDTO login(String email, String password) {
        return customerService.login(email, password);
    }
}
