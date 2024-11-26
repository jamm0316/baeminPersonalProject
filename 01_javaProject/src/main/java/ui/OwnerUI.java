package ui;

import app.ApplicationMain;
import entity.FoodDTO;
import entity.OrderItemDTO;
import entity.OwnerDTO;
import food.FoodController;
import food.FoodRepository;
import food.FoodService;
import orderitem.OrderItemController;
import orderitem.OrderItemRepository;
import orderitem.OrderItemService;
import owner.OwnerController;
import owner.OwnerRepository;
import owner.OwnerService;
import store.StoreController;
import store.StoreRepository;
import store.StoreService;

import java.util.List;
import java.util.Scanner;

public class OwnerUI {
    static Scanner input = new Scanner(System.in);
    static OwnerController o_controller = new OwnerController(new OwnerService(new OwnerRepository()));
    static StoreController s_controller = new StoreController(new StoreService(new StoreRepository()));
    static FoodController food_controller = new FoodController(new FoodService(new FoodRepository()));
    static OrderItemController orderItem_controller = new OrderItemController(new OrderItemService(new OrderItemRepository()));

    public static void loginView(OwnerDTO ownerDTO) {
        o_controller = new OwnerController(new OwnerService(new OwnerRepository()));
        s_controller = new StoreController(new StoreService(new StoreRepository()));
        food_controller = new FoodController(new FoodService(new FoodRepository()));
        boolean isStop = false;

        while (!isStop) {
            try {
                menu(ownerDTO);
                int select = Integer.parseInt(input.nextLine());

                switch (select) {
                    case 1 -> {
                        StoreUI.f_storeMenu(ownerDTO);
                    }
                    case 2 -> {
                        FoodUI.f_foodMenu(ownerDTO);
                    }
                    case 3 -> {
                        f_orderSummary(ownerDTO);
                    }
                    case 4 -> {
                        f_ownerInfo(ownerDTO);
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
        input.close();
    }

    public static void f_orderSummary(OwnerDTO ownerDTO) {
        int storeId = StoreUI.selectStore(ownerDTO);
        List<OrderItemDTO> orderItemDTOS = orderItem_controller.selectOrderSummary(storeId);

        if (orderItemDTOS.size() == 0) {
            System.out.println("🥺아직 주문내역이 없네요");
            return;
        }

        System.out.println(String.format("%-20s %-20s %-20s %-20s", "주문번호", "배달상태", "음식명", "갯수"));
        for (OrderItemDTO orderItemDTO : orderItemDTOS) {
            FoodDTO foodDTO = food_controller.selectByFoodId(orderItemDTO.getFoodId());
            System.out.println(String.format("%-20s %-20s %-20s %-20s",
                    orderItemDTO.getId(), orderItemDTO.getStatus(), foodDTO.getName(), orderItemDTO.getQuantity()));
        }

        System.out.print("👀배달상태를 변경할 주문번호>> ");
        int orderId = Integer.parseInt(input.nextLine());
        System.out.println("\n--------------------");
        System.out.println("1.주문확인 | 2.배달중 | 3.배달완료 ");
        System.out.println("--------------------");
        System.out.print("👀변경할 상태>> ");
        int status = Integer.parseInt(input.nextLine());
        if (status == 3) {
            orderItem_controller.deleteOrderItem(orderId);
        } else if (status == 1) {
            orderItem_controller.updateStatus(orderId, "주문확인");
        } else if (status == 2) {
            orderItem_controller.updateStatus(orderId, "배달중");
        }
    }

    public static void f_ownerInfo(OwnerDTO ownerDTO) {
        System.out.println("\n---------------------------------------");
        System.out.println("1.회원 정보 확인 | 2.회원 탈퇴 | 9.이전메뉴 ");
        System.out.println("---------------------------------------");
        System.out.print("👀무엇을 하시겠어요?>> ");
        int select = Integer.parseInt(input.nextLine());
        switch (select) {
            case 1 -> {
                displayOwnerInfo(ownerDTO);
            }
            case 2 -> {
                deleteOwner(ownerDTO);
            }
            case 9 -> {
                return;
            }
        }
    }

    private static void deleteOwner(OwnerDTO ownerDTO) {
        System.out.print("⚠️정말 탈퇴하실 건가요?(y/n)>> ");
        String isDelete = input.nextLine();

        if (isDelete.equals("y")) {
            System.out.print("🥺이메일을 입력해주세요>> ");
            String email = input.nextLine();
            if (email.equals(ownerDTO.getEmail())) {
                System.out.println("🥰또 찾아주세요~");
                o_controller.deleteMember(email);
                ApplicationMain.runApp();
            } else {
                System.out.println("🥺이메일을 정확히 입력해주세요!");
            }

        } else if (isDelete.equals("n")) {
            System.out.println("\n🥰다시 보니 반가워요!!");
        } else {
            System.out.println("👀앗! 다시 선택해주세요");
        }
    }

    private static void displayOwnerInfo(OwnerDTO ownerDTO) {
        System.out.println("\n회원ID: " + ownerDTO.getEmail());
        System.out.println("회원명: " + ownerDTO.getName());
        System.out.println("암호 : " + ownerDTO.getPassword());
        System.out.println();
        System.out.println("가게정보");
        System.out.println("---------------------------------------");
        StoreUI.displayStores(ownerDTO);
    }

    //메인ui
    public static void menu(OwnerDTO ownerDTO) {
        System.out.println();
        System.out.println(ownerDTO.getName() + "님 안녕하세요?");
        System.out.println("-------------------------------------------------------");
        System.out.println("1.매장 메뉴 | 2.음식 메뉴 | 3.주문 내역 | 4.회원 정보 | 9.로그아웃");
        System.out.println("-------------------------------------------------------");
        System.out.print("👀무엇을 하시겠어요?: ");
    }
}
