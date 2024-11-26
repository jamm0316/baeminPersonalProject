package customer;

import entity.CustomerDTO;

public class CustomerService {

    private final CustomerRepository customerRepository;

    //todo: 다형성을 이용해 인터페이스를 주입하는 것으로 바꾸어 OCP원칙을 지키며, Config 객체에서 이를 관리하도록 한다.
    public CustomerService(CustomerRepository memberRepository) {
        this.customerRepository = memberRepository;
    }

    public int join(CustomerDTO memberDTO) {
        return customerRepository.createMember(memberDTO);
    }

    public CustomerDTO selectByEmail(String email) {
        CustomerDTO memberDTO = customerRepository.selectByEmail(email);
        return memberDTO;
    }

    public void deleteMember(String email) {
        customerRepository.deleteMember(email);
    }

    public CustomerDTO login(String email, String password) {
        CustomerDTO customerDTO = customerRepository.selectByEmail(email);
        if (customerDTO.getPassword().equals(password)) {
            return customerDTO;
        }
        return null;
    }
}
