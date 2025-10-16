package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Calculator {
    private final String DEFAULT_DELIMITER = "[,:]";

    public void run() {
        String input = parseInput();
        String delimiter = DEFAULT_DELIMITER;
        String numbers = input;
        int result = 0;

        if (input.startsWith("//")) {
            delimiter = String.valueOf(input.charAt(2));

            String[] inputWithoutDelimiter = input.split("\\n", 2);
            numbers = inputWithoutDelimiter[1];
        }
        String[] tokens = splitString(numbers, delimiter);
        result = calculate(tokens);
        System.out.println(result);
    }

    private String[] splitString(String numbers, String delimiter) {
        return numbers.split(delimiter);
    }

    private String parseInput() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();
        if (input.isEmpty())
            return "0";
        return input;
    }

    private int calculate(String[] tokens) {
        int sum = 0;
        for (String token : tokens) {
            sum += Integer.parseInt(token);
        }
        return sum;
    }
}
