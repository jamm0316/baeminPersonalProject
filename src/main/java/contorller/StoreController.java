package contorller;

import entity.StoreDTO;
import store.StoreService;

import java.util.List;

public class StoreController {
    private StoreService storeService;


    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    public int storeJoin(StoreDTO storeDTO) {
        return storeService.join(storeDTO);
    }

    public List<StoreDTO> selectByEmail(String email) {
        return storeService.selectByEmail(email);
    }

    public List<StoreDTO> selectByCategory(int category) {
        return storeService.selectByCategory(category);
    }

    public StoreDTO selectByStoreId(int storeId) {
        return storeService.selectByStoreId(storeId);
    }

    public int deleteStore(int storeId) {
        return storeService.deleteStore(storeId);
    }

    public int updateStore(StoreDTO storeDTO) {
        return storeService.updateStore(storeDTO);
    }
}
