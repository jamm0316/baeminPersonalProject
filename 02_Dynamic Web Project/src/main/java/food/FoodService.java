package food;

import entity.FoodDTO;
import entity.StoreDTO;

import java.util.List;

public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository memberRepository) {
        this.foodRepository = memberRepository;
    }

    public int join(FoodDTO foodDTO) {
        return foodRepository.createFood(foodDTO);
    }

    public List<FoodDTO> selectByStoreId(int storeId) {
        return foodRepository.selectByStoreId(storeId);
    }

    public FoodDTO selectByFoodid(int foodId) {
        return foodRepository.selectByFoodId(foodId);
    }

    public int updateFood(FoodDTO foodDTO) {
        return foodRepository.updateFood(foodDTO);
    }

    public int deleteFood(int foodId) {
        return foodRepository.deleteFood(foodId);
    }
}