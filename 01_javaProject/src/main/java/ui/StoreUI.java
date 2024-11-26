package ui;

import com.shinhan.project.deliverService.ui.UIutil;
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

import java.util.List;
import java.util.Scanner;

public class StoreUI {
    static Scanner input = new Scanner(System.in);
    static OwnerController o_controller = new OwnerController(new OwnerService(new OwnerRepository()));
    static StoreController s_controller = new StoreController(new StoreService(new StoreRepository()));
    static FoodController food_controller = new FoodController(new FoodService(new FoodRepository()));

    public static void f_storeMenu(OwnerDTO ownerDTO) {

        while (true) {
            try {
                menu();
                int select = Integer.parseInt(input.nextLine());
                switch (select) {
                    case 1 -> {
                        f_createStore(ownerDTO);
                    }
                    case 2 -> {
                        f_updateStore(ownerDTO);
                    }
                    case 3 -> {
                        displayStores(ownerDTO);
                    }
                    case 4 -> {
                        f_deleteStore(ownerDTO);
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
        System.out.println("1.매장 등록 | 2.매장 수정 | 3.매장 조회 | 4.매장 삭제 | 9.이전메뉴");
        System.out.println("-------------------------------------------------------");
        System.out.print("👀무엇을 하시겠어요?>> ");
    }

    public static int f_updateStore(OwnerDTO ownerDTO) {
        int storeId = selectStore(ownerDTO);
        StoreDTO storeDTO = s_controller.selectByStoreId(storeId);
        System.out.println("\n------------------------------");
        System.out.print("💁‍수정할 가게명을 알려주세요>> ");
        String name = input.nextLine();
        storeDTO.setName(name);

        System.out.println("\n🗺️어디에 위치한 가게인가요? ");
        System.out.println("\n1.강남구 | 2,강동구 | 3.강서구 | 4.관악구 | 5.구로구");
        System.out.print("6.금천구 | 7,동작구 | 8.서초구 | 9.송파구 | 10.양천구 | 11.영등포구 >> ");
        int location = Integer.parseInt(input.nextLine());
        storeDTO.setArea_id(location);
        System.out.println();

        System.out.println("\n🍔어떤 음식을 파시나요?>> ");
        System.out.println("\n1.🍗치킨 | 2,🍜중식 | 3.🍽️돈까스 | 4.🍕피자 | 5.🍣회");
        System.out.print("6.🥘찜탕 | 7,🐖족발 | 8.🍰디저트 | 9.🫕분식 | 10.☕️카페 >> ");
        int category = Integer.parseInt(input.nextLine());
        storeDTO.setCategory(category);

        return s_controller.updateStore(storeDTO);
    }

    public static void f_deleteStore(OwnerDTO ownerDTO) {
        int storeId = selectStore(ownerDTO);
        System.out.print("⚠️정말 삭제하시겠어요?(y/n)>> ");
        String isDel = input.nextLine();
        if (isDel.equals("y")) {
            s_controller.deleteStore(storeId);
        }
    }

    public static int selectStore(OwnerDTO ownerDTO) {
        System.out.println("\n----------------------------------------");
        displayStores(ownerDTO);
        System.out.print("💁‍어느 매장을 선택해주세요. (번호를 입력해주세요) >> ");
        int selectStore = Integer.parseInt(input.nextLine());
        return selectStore;
    }

    //가게등록
    public static int f_createStore(OwnerDTO ownerDTO) {
        System.out.println("------------------------------");
        System.out.print("💁‍가게명을 알려주세요>> ");
        String name = input.nextLine();

        System.out.println("🗺️어디에 위치한 가게인가요?>> ");
        System.out.println("1.강남구 | 2,강동구 | 3.강서구 | 4.관악구 | 5.구로구");
        System.out.print("6.금천구 | 7,동작구 | 8.서초구 | 9.송파구 | 10.양천구 | 11.영등포구 >> ");
        int location = Integer.parseInt(input.nextLine());
        System.out.println();

        System.out.println("🍔어떤 음식을 파시나요?>> ");
        System.out.println("1.🍗치킨 | 2,🍜중식 | 3.🍽️돈까스 | 4.🍕피자 | 5.🍣회");
        System.out.print("6.🥘찜탕 | 7,🐖족발 | 8.🍰디저트 | 9.🫕분식 | 10.☕️카페 >> ");
        int category = Integer.parseInt(input.nextLine());

        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setName(name);
        storeDTO.setOwnerEmail(ownerDTO.getEmail());
        storeDTO.setArea_id(location);
        storeDTO.setCategory(category);
        int result = s_controller.storeJoin(storeDTO);


        String area = UIutil.parseArea(storeDTO.getArea_id());
        System.out.println("🎉🎉축하해요~!!🎉🎉");
        System.out.printf("%s님 환영합니다! " + area + "민을 먹여살려주세요!\n", storeDTO.getName());
        return result;
    }

    //가게찾기
    public static FoodDTO f_searchStore(OwnerDTO ownerDTO) {
        System.out.println("------------------------------");
        System.out.println(ownerDTO.getName() + "님의 매장리스트 ");
        List<StoreDTO> storeList = displayStores(ownerDTO);

        System.out.print("💁‍어느 매장을 선택해주세요. (번호를 입력해주세요) >> ");
        int selectStore = Integer.parseInt(input.nextLine());
        FoodDTO foodDTO = new FoodDTO();
        for (StoreDTO storeDTO : storeList) {
            if (selectStore == storeDTO.getId()) {
                foodDTO.setStoreId(storeDTO.getId());
            }
        }
        return foodDTO;
    }

    //가게리스트 출력
    public static List<StoreDTO> displayStores(OwnerDTO ownerDTO) {
        String email = ownerDTO.getEmail();
        List<StoreDTO> storeList = s_controller.selectByEmail(email);
        System.out.println(String.format("%-10s %-20s %-20s %-20s", "가게번호", "가게명", "음식종류", "가게위치"));
        for (StoreDTO store : storeList) {
            String category = UIutil.parseCategory(store.getCategory());
            String area = UIutil.parseArea(store.getArea_id());

            System.out.println(String.format("%-10d %-20s %-20s %-20s",
                    store.getId(), store.getName(), category, area));
        }
        return storeList;
    }
}
