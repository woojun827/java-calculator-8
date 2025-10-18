package calculator;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        try {
            int result = SpringCalculator.splitAndSum(input);
            System.out.println("결과: " + result);
        }catch (IllegalArgumentException e) {
            System.out.println("에러: " + e.getMessage());
        }
    }
}
