package store;

import entity.FoodDTO;
import entity.StoreDTO;

import java.util.List;

public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public int join(StoreDTO storeDTO) {
        return storeRepository.createStore(storeDTO);
    }

    public List<StoreDTO> selectByEmail(String email) {
        return storeRepository.selectByEmail(email);
    }

    public List<StoreDTO> selectByCategory(int catogory) {
        return storeRepository.selectByCategory(catogory);
    }

    public StoreDTO selectByStoreId(int storeId) {
        return storeRepository.selectByStoreId(storeId);
    }

    public int deleteStore(int storeId) {
        return storeRepository.deleteStore(storeId);
    }

    public int updateStore(StoreDTO storeDTO) {
        return storeRepository.updateStore(storeDTO);
    }
}