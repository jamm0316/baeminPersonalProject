package com.shinhan.project.deliverService.ui;

import app.ApplicationMain;
import customer.CustoemrController;
import customer.CustomerRepository;
import customer.CustomerService;
import entity.*;
import food.FoodController;
import food.FoodRepository;
import food.FoodService;
import orderitem.OrderItemController;
import orderitem.OrderItemRepository;
import orderitem.OrderItemService;
import store.StoreController;
import store.StoreRepository;
import store.StoreService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerUI {
    static Scanner input = new Scanner(System.in);
    static StoreController s_controller = new StoreController(new StoreService(new StoreRepository()));
    static CustoemrController c_controller = new CustoemrController(new CustomerService(new CustomerRepository()));
    static OrderItemController orderItem_controller = new OrderItemController(new OrderItemService(new OrderItemRepository()));
    static FoodController food_controller = new FoodController(new FoodService(new FoodRepository()));


    //고객 로그인 뷰
    public static void loginView(CustomerDTO customerDTO) {

        boolean isStop = false;
        List<OrderItemDTO> orderItemList = new ArrayList<>();

        while (!isStop) {
            try {
                menu();
                int select = Integer.parseInt(input.nextLine());
                switch (select) {
                    case 1 -> {
                        f_findCategory(customerDTO, orderItemList);
                    }
                    case 2 -> {
                        f_orderFoods(customerDTO, orderItemList);
                    }
                    case 3 -> {
                        f_infoOrder(customerDTO);
                    }
                    case 4 -> {
                        f_infoMember(customerDTO);
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

    private static void f_infoOrder(CustomerDTO customerDTO) {
        List<OrdersDTO> ordersList = orderItem_controller.selectOrders(customerDTO.getEmail());
        if (ordersList.size() != 0) {
            System.out.println(String.format("%-20s %-20s %-10s %-20s", "음식점", "음식이름", "주문현황", "주문날짜"));
            for (OrdersDTO ordersDTO : ordersList) {
                //음식점, 음식이름, 주문현황, 주문날짜
                if (ordersDTO.getStatus() != null) {
                    System.out.println(String.format("%-10s %-20s %-10s %-20s",
                            ordersDTO.getStoreName(), ordersDTO.getFoodName(), ordersDTO.getStatus(), ordersDTO.getOrderDate()));
                }
            }
        } else {
            System.out.println("주문 내역이 없습니다.");
        }
    }

    //메뉴ui
    private static void menu() {
        System.out.println("\n--------------------------------------------------------");
        System.out.println("1.카테고리로 찾기 | 2.주문하기 | 3.주문정보 | 4.회원정보 | 9.로그아웃");
        System.out.println("--------------------------------------------------------");
        System.out.print("👀무엇을 하시겠어요?>> ");
    }

    //1. 카테고리 조회
    private static void f_findCategory(CustomerDTO customerDTO, List<OrderItemDTO> orderItemList) {

        System.out.println("\n🍔카테고리를 골라주세요 ");
        System.out.println("1.🍗치킨 | 2,🍜중식 | 3.🍽️돈까스 | 4.🍕피자 | 5.🍣회");
        System.out.print("6.🥘찜탕 | 7,🐖족발 | 8.🍰디저트 | 9.🫕분식 | 10.☕️카페 >> ");
        int category = Integer.parseInt(input.nextLine());

        //가게 선택
        StoreDTO storeDTO = f_selectStore(customerDTO, category);
        if (storeDTO == null) {
            return;
        }

        //음식 선택
        FoodDTO foodDTO = f_selelctFood(storeDTO);

        //주문서 작성
        OrderItemDTO orderItemDTO = f_makeOrderItems(customerDTO, foodDTO, storeDTO);

        //주문서 저장
        saveOrderItem(orderItemDTO, orderItemList);
    }

    //1-1. 가게조회
    private static StoreDTO f_selectStore(CustomerDTO customerDTO, int categoryId) {
        List<StoreDTO> storeList = s_controller.selectByCategory(categoryId);
        String category = com.shinhan.project.deliverService.ui.UIutil.parseCategory(categoryId);
        String area = com.shinhan.project.deliverService.ui.UIutil.parseArea(customerDTO.getLocation());

        System.out.println("------------------------------");
        System.out.println(area + "의 " + category + " 맛집 리스트 ");
        if (storeList.size() != 0) {
            for (StoreDTO store : storeList) {
                if (store.getArea_id() == customerDTO.getLocation()) {
                }
                System.out.println(String.format("%-10s %-20s %-10s", "음식점 코드", "음식점", "위치"));
                System.out.println(String.format("%-10d %-20s %-10s",
                        store.getId(), store.getName(), com.shinhan.project.deliverService.ui.UIutil.parseArea(store.getArea_id())));
            }

            //가게 선택
            System.out.print("👀어떤가게에서 주문하시겠어요?(코드번호 입력)>> ");
            int storeId = Integer.parseInt(input.nextLine());

            StoreDTO storeDTO = new StoreDTO();
            for (StoreDTO store : storeList) {
                if (store.getId() == storeId) {
                    storeDTO = store;
                } else {
                    System.out.println("코드명을 다시 입력해주세요");
                }
            }
            return storeDTO;
        } else {
            System.out.println("🥺고객님 위치에는 아직 개설한 가게가 없네요. 카테고리를 다시 선택해주세요.");
        }
        return null;
    }

    //1-2. 가게별 음식조회
    private static FoodDTO f_selelctFood(StoreDTO storeDTO) {
        List<FoodDTO> foodList = food_controller.selectByStoreId(storeDTO.getId());
        System.out.println("------------------------------");
        System.out.println(storeDTO.getName() + "의 판매 음식");
        System.out.println(String.format("%-10s %-20s %-10s", "음식코드", "음식명", "최대조리시간"));
        for (FoodDTO foodDTO : foodList) {
            System.out.println(String.format("%-10d %-20s %-10s", foodDTO.getFoodId(), foodDTO.getName(), foodDTO.getMaxCookingTime() + "분"));
        }

        System.out.print("👀어떤음식을 주문하시겠어요?(코드번호 입력)>> ");
        int foodId = Integer.parseInt(input.nextLine());
        FoodDTO foodDTO = new FoodDTO();

        for (FoodDTO food : foodList) {
            if (foodId == food.getFoodId()) {
                foodDTO = food;
            }
        }
        return foodDTO;
    }

    //1-3. 단건 주문서 작성
    private static OrderItemDTO f_makeOrderItems(CustomerDTO customerDTO, FoodDTO foodDTO, StoreDTO storeDTO) {
        OrderItemDTO orderItemDTO = getOrderItemDTO(customerDTO, foodDTO);

        System.out.println(String.format("%-20s %-20s %-10s %-10s %-20s", "음식점", "음식명", "갯수", "최대소요시간", "금액"));
        int arrivalTime = orderItemDTO.getQuantity() * foodDTO.getMaxCookingTime();
        int price = orderItemDTO.getQuantity() * foodDTO.getPrice();
        System.out.println(String.format("%-20s %-20s %-10d %-10s %-20s", storeDTO.getName(), foodDTO.getName(),
                orderItemDTO.getQuantity(), arrivalTime + "분", price + "원"));
        return orderItemDTO;
    }

    //1-4. 단건 주문서 저장
    private static OrderItemDTO getOrderItemDTO(CustomerDTO customerDTO, FoodDTO foodDTO) {
        System.out.print("👀몇개를 주문하시겠어요?>> ");
        int quantity = Integer.parseInt(input.nextLine());

        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setFoodId(foodDTO.getFoodId());
        orderItemDTO.setEmail(customerDTO.getEmail());
        orderItemDTO.setQuantity(quantity);
        return orderItemDTO;
    }

    //1-5. 주문서 저장
    private static List<OrderItemDTO> saveOrderItem(OrderItemDTO orderItemDTO, List<OrderItemDTO> orderItemList) {
        System.out.print("👀주문서에 저장할까요?(y/n)>> ");
        String isSave = input.nextLine();

        int result = 0;
        if (isSave.equalsIgnoreCase("y")) {
            if (orderItemList == null) {
                orderItemList = new ArrayList<>();
            }
            Date currentDate = new Date(System.currentTimeMillis());
            orderItemDTO.setStatus("주문확인 중");
            //주문항목 생성
            result = orderItem_controller.createOrderItem(orderItemDTO, currentDate);
            orderItemList.add(orderItemDTO);
            System.out.println(result + "건이 저장되었습니다.");
            return orderItemList;
        } else if (isSave.equals("n")) {
            System.out.println("주문이 취소되었습니다.");
        } else {
            System.out.println("다시 입력해주세요.");
        }
        return orderItemList;
    }

    //2. 최종 주문
    private static void f_orderFoods(CustomerDTO customerDTO, List<OrderItemDTO> orderItemList) {
        System.out.println("------------------------------");
        System.out.println(customerDTO.getName() + "님의 주문내역");
        System.out.println(String.format("%-20s %-20s %-10s", "음식점", "음식명", "갯수"));
        int arrivalTime = 0;
        int totalPrice = 0;
        int totalCount = 0;
        for (OrderItemDTO orderItemDTO : orderItemList) {
            int foodId = orderItemDTO.getFoodId();
            FoodDTO foodDTO = food_controller.selectByFoodId(foodId);

            int storeId = foodDTO.getStoreId();
            StoreDTO storeDTO = s_controller.selectByStoreId(storeId);

            arrivalTime += (orderItemDTO.getQuantity() * foodDTO.getMaxCookingTime());
            totalPrice += (orderItemDTO.getQuantity() * foodDTO.getPrice());
            totalCount += orderItemDTO.getQuantity();

            System.out.println(String.format("%-20s %-20s %-10s",
                    storeDTO.getName(), foodDTO.getName(), orderItemDTO.getQuantity()));
        }

        System.out.println("=============================================");
        System.out.println(String.format("%-20s %-20s %-20s", "최대소요시간", "총 금액", "총 개수"));
        System.out.println(String.format("%-20s %-20s %-20s", arrivalTime + "분", totalPrice + "원", totalCount + "개"));

        System.out.print("👀주문할까요?(y/n)>> ");
        String isOrder = input.nextLine();
        if (isOrder.equalsIgnoreCase("y")) {
//                Date currentDate = new Date(System.currentTimeMillis());
//                for (OrderItemDTO orderItemDTO : orderItemList) {
//
//                    orderItemDTO.setOrderDate(currentDate);
//                    orderItemDTO.setStatus("주문확인 중");
//                    orderItem_controller.updateDate(orderItemDTO);
//                }
            System.out.println("🎉🎉🎉주문에 성공하였습니다!🎉🎉🎉");
            System.out.println("사장님이 주문을 확인하고 있어요.🛵");
            orderItemList.clear();
        } else if (isOrder.equals("n")) {
            System.out.println("주문이 취소되었습니다.");
        } else {
            System.out.println("다시 입력해주세요.");
        }
    }


    //4. 회원조회메뉴
    private static void f_infoMember(CustomerDTO customerDTO) {
        System.out.println("\n---------------------------------------");
        System.out.println("1.회원 정보 확인 | 2.회원 탈퇴 | 9.이전메뉴 ");
        System.out.println("---------------------------------------");
        System.out.print("👀무엇을 하시겠어요?>> ");
        int select = Integer.parseInt(input.nextLine());
        switch (select) {
            case 1 -> {
                displayCustomerInfo(customerDTO);
            }
            case 2 -> {
                deleteCustomer(customerDTO);
            }
            case 9 -> {
                return;
            }
        }
    }

    //4-1. 회원조회
    private static void displayCustomerInfo(CustomerDTO customerDTO) {
        System.out.println();
        System.out.println("회원ID: " + customerDTO.getEmail());
        System.out.println("닉네임: " + customerDTO.getNickName());
        System.out.println("회원명: " + customerDTO.getName());
        System.out.println("암호 : " + customerDTO.getNickName());
        System.out.println("지역 : " + com.shinhan.project.deliverService.ui.UIutil.parseArea(customerDTO.getLocation()));
    }

    //4-2. 회원탈퇴
    private static void deleteCustomer(CustomerDTO customerDTO) {
        System.out.print("⚠️정말 탈퇴하실 건가요?(y/n)>> ");
        String isDelete = input.nextLine();

        if (isDelete.equals("y")) {
            System.out.print("🥺이메일을 입력해주세요>> ");
            String email = input.nextLine();
            if (email.equals(customerDTO.getEmail())) {
                System.out.println("\n🥰또 찾아주세요~");
                c_controller.deleteMember(email);
                ApplicationMain.runApp();
            } else {
                System.out.println("🥺이메일을 정확히 입력해주세요!");
            }
        } else if (isDelete.equals("n")) {
            System.out.println("🥰다시 보니 반가워요!!");
        } else {
            System.out.println("👀앗! 다시 선택해주세요");
        }
    }

}
