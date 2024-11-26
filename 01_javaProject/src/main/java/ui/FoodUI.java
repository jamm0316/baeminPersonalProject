package ui;

import entity.FoodDTO;
import entity.OwnerDTO;
import entity.StoreDTO;
import food.FoodController;
import food.FoodRepository;
import food.FoodService;
import owner.OwnerController;
import owner.OwnerRepository;
import owner.OwnerService;
import store.StoreController;
import store.StoreRepository;
import store.StoreService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoodUI {
    static Scanner input = new Scanner(System.in);
    static OwnerController o_controller = new OwnerController(new OwnerService(new OwnerRepository()));
    static StoreController s_controller = new StoreController(new StoreService(new StoreRepository()));
    static FoodController food_controller = new FoodController(new FoodService(new FoodRepository()));

    public static void f_foodMenu(OwnerDTO ownerDTO) {
        o_controller = new OwnerController(new OwnerService(new OwnerRepository()));
        s_controller = new StoreController(new StoreService(new StoreRepository()));
        food_controller = new FoodController(new FoodService(new FoodRepository()));

        while (true) {
            try {
                menu();
                int select = Integer.parseInt(input.nextLine());
                switch (select) {
                    case 1 -> {
                        f_createFood(ownerDTO);
                    }
                    case 2 -> {
                        f_updateFood(ownerDTO);
                    }
                    case 3 -> {
                        selectFood(ownerDTO);
                    }
                    case 4 -> {
                        f_deleteFood(ownerDTO);
                    }
                    case 9 -> {
                        return;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("\n*****😅잘못된 입력입니다. 숫자를 입력해주세요!*****\n");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("\n*****😅오류가 발생했습니다.*****\n");
            }
        }
    }

    private static void menu() {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("1.음식 등록 | 2.음식 수정 | 3.음식 조회 | 4.음식 삭제 | 9.이전메뉴");
        System.out.println("-------------------------------------------------------");
        System.out.print("👀무엇을 하시겠어요?>> ");
    }

    //음식등록
    public static int f_createFood(OwnerDTO ownerDTO) {
        FoodDTO foodDTO = StoreUI.f_searchStore(ownerDTO);
        System.out.print("🍎음식 명이 무엇인가요? >> ");
        String name = input.nextLine();

        System.out.print("💵가격을 얼마인가요? >> ");
        int price = Integer.parseInt(input.nextLine());

        System.out.print("⏱️최대 조리시간은 얼마나 되나요? >> ");
        int maxCookingTime = Integer.parseInt(input.nextLine());

        foodDTO.setName(name);
        foodDTO.setPrice(price);
        foodDTO.setMaxCookingTime(maxCookingTime);

        int result = food_controller.foodJoin(foodDTO);
        return result;
    }

    public static void f_deleteFood(OwnerDTO ownerDTO) {
        FoodDTO foodDTO = selectStoreFood(ownerDTO);
        food_controller.deleteFood(foodDTO.getFoodId());
    }

    public static int f_updateFood(OwnerDTO ownerDTO) {
        FoodDTO foodDTO = updateFood(ownerDTO);
        int result = food_controller.updateFood(foodDTO);
        System.out.println(result + "건이 업데이트 되었습니다.");
        return result;
    }

    //foodDTO 수정
    public static FoodDTO updateFood(OwnerDTO ownerDTO) {
        FoodDTO foodDTO = selectStoreFood(ownerDTO);

        System.out.println("\n----------------------------------------");
        System.out.print("🍰‍변경된 음식명 >> ");
        foodDTO.setName(input.nextLine());

        System.out.println("\n----------------------------------------");
        System.out.print("💵‍변경된 가격 >> ");
        foodDTO.setPrice(Integer.parseInt(input.nextLine()));

        System.out.println("\n----------------------------------------");
        System.out.print("⏱️변경된 조리시간 >> ");
        foodDTO.setMaxCookingTime(Integer.parseInt(input.nextLine()));

        return foodDTO;
    }

    public static List<FoodDTO> selectFood(OwnerDTO ownerDTO) {
        System.out.println("----------------------------------------");
        List<StoreDTO> storeList = StoreUI.displayStores(ownerDTO);
        System.out.print("\n💁‍어느 매장을 선택해주세요(번호를 입력해주세요) >> ");
        int selectStore = Integer.parseInt(input.nextLine());
        List<FoodDTO> foodList = displayFoods(storeList, selectStore);
        return foodList;
    }

    public static FoodDTO selectStoreFood(OwnerDTO ownerDTO) {
        System.out.println("----------------------------------------");
        List<StoreDTO> storeList = StoreUI.displayStores(ownerDTO);
        System.out.print("\n💁‍매장을 선택해주세요(번호를 입력해주세요) >> ");
        int selectStore = Integer.parseInt(input.nextLine());
        List<FoodDTO> foodList = displayFoods(storeList, selectStore);

        System.out.println("----------------------------------------");
        System.out.print("\n💁‍음식을 선택해주세요(번호를 입력해주세요) >> ");
        int selectFood = Integer.parseInt(input.nextLine());
        FoodDTO foodDTO = new FoodDTO();
        for (FoodDTO food : foodList) {
            if (food.getStoreId() == selectFood) {
                foodDTO = food;
            }
        }
        foodDTO.setFoodId(selectFood);
        foodDTO.setStoreId(selectStore);
        return foodDTO;
    }

    //매장의 음식 나열하기
    public static List<FoodDTO> displayFoods(List<StoreDTO> storeList, int selectStore) {
        List<FoodDTO> foodList = new ArrayList<>();
        for (StoreDTO storeDTO : storeList) {
            if (storeDTO.getId() == selectStore) {
                foodList = food_controller.selectByStoreId(storeDTO.getId());
                System.out.println(String.format("%-10s %-20s %-20s %-20s", "음식번호", "음식이름", "가격", "최대조리시간"));
                for (FoodDTO food : foodList) {
                    System.out.println(String.format("%-10d %-20s %-20s %-20s",
                            food.getFoodId(), food.getName(), food.getPrice(), food.getMaxCookingTime()));
                }
            }
        }
        return foodList;
    }
}
