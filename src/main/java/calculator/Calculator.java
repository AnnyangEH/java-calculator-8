package calculator;
import camp.nextstep.edu.missionutils.Console;

public class Calculator {
    public void run() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = parseString();
        String[] numbers = splitString(input);
        int result = calculate(numbers);
        System.out.println(result);
    }

    private int calculate(String[] numbers) {
        int sum = 0;

        for (String number : numbers) {
            if (!number.isEmpty())
                sum += Integer.parseInt(number);
        }
        return sum;
    }

    private String parseString() {
        String input = Console.readLine();
        if (input.isEmpty())
            return "0";
        return input;
    }

    private String[] splitString(String input) {
        return input.split("[,:]");
    }
}
