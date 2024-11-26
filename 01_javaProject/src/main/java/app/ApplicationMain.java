package app;

import customer.CustomerRepository;
import entity.OwnerDTO;
import customer.CustomerService;
import customer.CustoemrController;
import entity.CustomerDTO;
import owner.OwnerController;
import owner.OwnerRepository;
import owner.OwnerService;
import ui.OwnerUI;

import java.util.Scanner;

public class ApplicationMain {
    static Scanner input;
    static CustoemrController c_controller;
    static OwnerController o_controller;

    public static void main(String[] args) {
        runApp();
    }

    public static void runApp() {
        input = new Scanner(System.in);
        c_controller = new CustoemrController(new CustomerService(new CustomerRepository()));
        o_controller = new OwnerController(new OwnerService(new OwnerRepository()));
        boolean isStop = false;

        while (!isStop) {
            try {
                menu();
                int select = Integer.parseInt(input.nextLine());
                switch (select) {
                    case 1 -> {
                        //회원가입
                        f_selectMemberUI();
                        int selectCreate = Integer.parseInt(input.nextLine());
                        f_createMember(selectCreate);
                    }
                    case 2 -> {
                        // 로그인
                        f_selectMemberUI();
                        int selectLogin = Integer.parseInt(input.nextLine());
                        f_login(selectLogin);
                    }
                    case 3 -> {
                        // 비밀번호 찾기
                        f_selectMemberUI();
                        int select_findPassword = Integer.parseInt(input.nextLine());
                        f_findMember(select_findPassword);
                    }
                    case 9 -> {
                        //프로그램 종료
                        System.out.println("프로그램을 종료합니다.");
                        isStop = true;
                    }
                    default -> System.out.println("\n*****😅잘못된 입력입니다. 메뉴를 다시 선택해주세요*****");
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

    private static void f_login(int selectLogin) {
        if (selectLogin == 1) {
            f_loginOwner();
        } else if (selectLogin == 2) {
            f_loginCustomer();
        } else {
            System.out.println("\n*****😅잘못된 입력입니다. 메뉴를 다시 선택해주세요*****");
        }
    }

    private static void f_loginOwner() {
        System.out.print("📧이메일을 입력해주세요>> ");
        String email = input.nextLine();

        System.out.print("🔑비밀번호를 입력해주세요>> ");
        String password = input.nextLine();
        OwnerDTO ownerDTO = o_controller.login(email, password);
        if (ownerDTO != null) {
            OwnerUI.loginView(ownerDTO);
        }
    }

    private static void f_loginCustomer() {
        System.out.print("📧이메일을 입력해주세요>> ");
        String email = input.nextLine();

        System.out.print("🔑비밀번호를 입력해주세요>> ");
        String password = input.nextLine();
        CustomerDTO customerDTO = c_controller.login(email, password);
        if (customerDTO != null) {
            com.shinhan.project.deliverService.ui.CustomerUI.loginView(customerDTO);
        } else {
            System.out.println("🥲앗! 이메일과 비밀번호를 확인해주세요");
        }
    }

    private static void f_findMember(int selectFindPassword) {
        if (selectFindPassword == 1) {
            f_findOwner();
        } else if (selectFindPassword == 2) {
            f_findCustomer();
        } else {
            System.out.println("\n*****😅잘못된 입력입니다. 메뉴를 다시 선택해주세요*****");
        }
    }

    private static void f_findCustomer() {
        System.out.print("📧이메일을 입력해주세요>> ");
        String email = input.nextLine();
        CustomerDTO customerDTO = c_controller.selectByEmail(email);
        com.shinhan.project.deliverService.ui.UIutil.display(customerDTO);

    }

    private static void f_findOwner() {
        System.out.print("📧이메일을 입력해주세요>> ");
        String email = input.nextLine();
        OwnerDTO ownerDTO = o_controller.selectByEmail(email);
        com.shinhan.project.deliverService.ui.UIutil.display(ownerDTO);
    }

    private static void f_createMember(int select_create) {
        if (select_create == 1) {
            f_createOwner();
        } else if (select_create == 2) {
            f_createCustomer();
        } else {
            System.out.println("\n*****😅잘못된 입력입니다. 메뉴를 다시 선택해주세요*****");
        }
    }

    private static void f_createOwner() {
        o_controller.ownerJoin(f_makeOwner());
    }

    private static void f_selectMemberUI() {
        System.out.println("\n------------------------");
        System.out.println("1.판매자 회원 | 2.구매자 회원");
        System.out.println("------------------------");
        System.out.print("👀원하시는 항목을 입력해주세요>> ");
    }

    private static void f_createCustomer() {
        c_controller.customerJoin(f_makeMember());
    }

    private static OwnerDTO f_makeOwner() {
        System.out.println("------------------------------");
        System.out.print("📧이메일을 알려주세요>> ");
        String email = input.nextLine();

        System.out.print("💁‍이름을 알려주세요>> ");
        String name = input.nextLine();

        System.out.print("🥷당신만 아는 비밀번호를 입력해주세요>> ");
        String password = input.nextLine();

        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setEmail(email);
        ownerDTO.setName(name);
        ownerDTO.setPassword(password);

        System.out.println("🎉🎉축하해요~!!🎉🎉");
        System.out.println("회원가입이 완료되었습니다.");
        System.out.printf("%s님 환영합니다!\n", ownerDTO.getName());

        return ownerDTO;
    }

    private static CustomerDTO f_makeMember() {
        System.out.println("------------------------------");
        System.out.print("💁‍이름을 알려주세요>> ");
        String name = input.nextLine();

        System.out.print("📧이메일을 알려주세요>> ");
        String email = input.nextLine();

        System.out.print("🐶어떻게 부르면 될까요?>> ");
        String nickName = input.nextLine();

        System.out.print("🥷당신만 아는 비밀번호를 입력해주세요>> ");
        String password = input.nextLine();

        System.out.println("🛵배달 받을 위치를 알려주세요: ");
        System.out.println("1.강남구 | 2,강동구 | 3.강서구 | 4.관악구 | 5.구로구");
        System.out.print("6.금천구 | 7,동작구 | 8.서초구 | 9.송파구 | 10.양천구 | 11.영등포구 >> ");
        int location = Integer.parseInt(input.nextLine());

        CustomerDTO memberDTO = new CustomerDTO();
        memberDTO.setName(name);
        memberDTO.setEmail(email);
        memberDTO.setNickName(nickName);
        memberDTO.setPassword(password);
        memberDTO.setLocation(location);

        System.out.println("🎉🎉축하해요~!!🎉🎉");
        System.out.println("회원가입이 완료되었습니다.");
        System.out.printf("%s님 환영합니다!\n", memberDTO.getNickName());
        return memberDTO;
    }

    private static void menu() {
        System.out.println("-----------------------------------------------");
        System.out.println("1.회원가입 | 2.로그인 | 3.비밀번호 찾기 | 9. 프로그램 종료");
        System.out.println("-----------------------------------------------");
        System.out.print("👀무엇을 하시겠어요>> ");
    }

}
