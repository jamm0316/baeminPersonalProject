package owner;

import entity.OwnerDTO;

public class OwnerController {
    private OwnerService ownerService;


    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    public int ownerJoin(OwnerDTO ownerDTO) {
        return ownerService.join(ownerDTO);
    }

    public OwnerDTO selectByEmail(String email) {
        return ownerService.selectByEmail(email);
    }

    public OwnerDTO login(String email, String password) {
        return ownerService.login(email, password);
    }
    public void deleteMember(String email) {
        ownerService.deleteMember(email);
    }
}
