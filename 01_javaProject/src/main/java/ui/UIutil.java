package com.shinhan.project.deliverService.ui;

import entity.CustomerDTO;
import entity.OwnerDTO;

public class UIutil {

    //비밀번호 찾기 출력(판매자)
    public static void display(OwnerDTO ownerDTO) {
        if (ownerDTO == null) {
            System.out.println("아이디와 이름을 다시한번 확인해주세요~👀");
        } else {
            System.out.println("🔑비밀번호를 찾았어요!!");
            System.out.printf("아이디: %s\n", ownerDTO.getEmail());
            System.out.printf("비밀번호: %s\n", ownerDTO.getPassword());
        }
    }

    //비밀번호 찾기 출력(구매자)
    public static void display(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            System.out.println("아이디와 이름을 다시한번 확인해주세요~👀");
        } else {
            System.out.println("🔑비밀번호를 찾았어요!!");
            System.out.printf("아이디: %s\n", customerDTO.getEmail());
            System.out.printf("비밀번호: %s\n", customerDTO.getPassword());
        }
    }

    public static String parseArea(int areaId) {
        String location;
        switch (areaId) {
            case 1 -> {
                location = "강남구";
                return location;
            }
            case 2 -> {
                location = "강동구";
                return location;
            }
            case 3 -> {
                location = "강서구";
                return location;
            }
            case 4 -> {
                location = "관악구";
                return location;
            }
            case 5 -> {
                location = "구로구";
                return location;
            }
            case 6 -> {
                location = "금천구";
                return location;
            }
            case 7 -> {
                location = "동작구";
                return location;
            }
            case 8 -> {
                location = "서초구";
                return location;
            }
            case 9 -> {
                location = "송파구";
                return location;
            }
            case 10 -> {
                location = "양천구";
                return location;
            }
            case 11 -> {
                location = "영등포구";
                return location;
            }
        }
        return null;
    }
    public static String parseCategory(int categoryId) {
        String category;
        switch (categoryId) {
            case 1 -> {
                category = "치킨";
                return category;
            }
            case 2 -> {
                category = "중식";
                return category;
            }
            case 3 -> {
                category = "돈까스";
                return category;
            }
            case 4 -> {
                category = "피자";
                return category;
            }
            case 5 -> {
                category = "회";
                return category;
            }
            case 6 -> {
                category = "찜탕";
                return category;
            }
            case 7 -> {
                category = "족발";
                return category;
            }
            case 8 -> {
                category = "디저트";
                return category;
            }
            case 9 -> {
                category = "분식";
                return category;
            }
            case 10 -> {
                category = "️카페";
                return category;
            }
        }
        return null;
    }
}
