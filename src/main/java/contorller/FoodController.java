package contorller;

import entity.FoodDTO;
import food.FoodService;

import java.util.List;

public class FoodController {
    private FoodService foodService;


    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    public int foodJoin(FoodDTO foodDTO) {
        return foodService.join(foodDTO);
    }

    public List<FoodDTO> selectByStoreId(int storeId) {
        return foodService.selectByStoreId(storeId);
    }

    public FoodDTO selectByFoodId(int foodId) {
        return foodService.selectByFoodid(foodId);
    }

    public int updateFood(FoodDTO foodDTO) {
        return foodService.updateFood(foodDTO);
    }

    public int deleteFood(int foodId) {
        return foodService.deleteFood(foodId);
    }

//    public void deleteMember(String email) {
//        foodService.deleteMember(email);
//    }
}
