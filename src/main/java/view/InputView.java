package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_MAIN_TEXT = "## 메인 화면 입니다 [1] 주문, [2] 결제, [3] 종료";
    private static final String INPUT_TABLE_NUMBER_TEXT = "## 주문할 테이블을 선택하세요.";
    private static final String INPUT_MENU_NUMBER_TEXT = "## 메뉴을 선택하세요.";
    private static final String INPUT_MENU_AMOUNT_TEXT = "## 선택한 메뉴의 주문 수량을 선택하세요.";
    private static final String INPUT_ADD_BASKET_TEXT = "## 장바구니에 음식을 더 추가하시겠습니가? [1] 추가 [2] 취소";

    private static final String INPUT_TABLE_NUMBER_PAYMENT_TEXT = "## 결제할 테이블을 선택해주세요";
    private static final String INPUT_PAYMENT_TEXT = "## 결제 수단을 선택해주세요";


    public static int inputMain(){
        System.out.println(INPUT_MAIN_TEXT);
        return inputInt();
    }
    public static int inputTableNumber() {
        System.out.println(INPUT_TABLE_NUMBER_TEXT);
        return inputInt();
    }

    public static int inputTableNumberToPayment() {
        System.out.println(INPUT_TABLE_NUMBER_PAYMENT_TEXT);
        return inputInt();
    }

    public static int inputMenuNumber() {
        System.out.println(INPUT_MENU_NUMBER_TEXT);
        return inputInt();
    }

    public static int inputMenuAmount() {
        System.out.println(INPUT_MENU_AMOUNT_TEXT);
        return inputInt();
    }

    public static int inputAddBasket() {
        System.out.println(INPUT_ADD_BASKET_TEXT);
        return inputInt();
    }

    public static int inputPayment() {
        System.out.println(INPUT_PAYMENT_TEXT);
        return inputInt();
    }

    private static int inputInt(){
        try {
            return scanner.nextInt();
        }catch (InputMismatchException e){
            return inputInt();
        }
    }
}
