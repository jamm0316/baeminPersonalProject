package owner;

import entity.CustomerDTO;
import entity.OwnerDTO;

public class OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public int join(OwnerDTO ownerDTO) {
        return ownerRepository.createOwner(ownerDTO);
    }

    public OwnerDTO selectByEmail(String email) {
        OwnerDTO ownerDTO = ownerRepository.selectByEmail(email);
        return ownerDTO;
    }

    public OwnerDTO login(String email, String password) {
        OwnerDTO ownerDTO = ownerRepository.selectByEmail(email);
        if (ownerDTO == null || ownerDTO.getPassword() == null) {
            ownerDTO.setEmail("hasNotEmailUser");
            ownerDTO.setPassword("hasNotEmailUser");
            return ownerDTO;
        } else if (!ownerDTO.getPassword().equals(password)) {
            ownerDTO.setPassword("WrongPasswordUser");
            return ownerDTO;
        } else {
            return ownerDTO;
        }
    }

    public void deleteMember(String email) {
        ownerRepository.deleteMember(email);
    }

//    public String findPasswordByEmailAndName(String email, String name) {
//        Member byEmailAndName = memberRepository.findByEmailAndName(email, name);
//        if (byEmailAndName != null) {
//            return byEmailAndName.getPassword();
//        }
//        return null;
//    }

    //todo: login만들기
//    public Member login
//        }
//    }
}
