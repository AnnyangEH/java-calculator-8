package calculator;
import static java.lang.Integer.parseInt;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        int result = 0;
        String input = Console.readLine();
        System.out.println("덧셈할 문자열을 입력해 주세요.\n" + input);
        result = parseInt(input);
        System.out.println("결과 : " + result);
    }
}
