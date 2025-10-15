package calculator;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        int result = 0;
        int sum = 0;
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();
        String[] parts = input.split("[,:]");
        for (String part : parts) {
            if (!part.isEmpty())
                sum += Integer.parseInt(part);
        }
        System.out.println("결과 : " + sum);
    //TODO: 
    /*
    1. 빈 문자열 예외 처리
    2. 커스텀 구분자 처리
    3. 음수 문자 처리
    */
    }
}
