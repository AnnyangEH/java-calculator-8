package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Calculator {
    public void run() {
        try {
        String input = getUserInput();
        validateInput(input);

        String delimiter = parseDelimiter(input);
        String numbers = parseNumber(input);
        String[] tokens = numbers.split(delimiter);
        validateTokens(tokens);

        System.out.print("결과 : " + calculate(tokens));
        } catch (IllegalArgumentException e) {
            System.out.print("에러: " + e.getMessage());
        }
    }

    private void validateInput(String input) {
        if (input.startsWith("//")) {
            if (input.length() < 5)
                throw new IllegalArgumentException("커스텀 구분자 길이 오류");
            int suffixIndex = input.indexOf("\\n");
            if (suffixIndex != 3 || suffixIndex == -1)
                throw new IllegalArgumentException("커스텀 구분자 형식 오류");
        }
    }

    private void validateTokens(String[] tokens) {
        long sum = 0;
        long value = 0;
        for (String token : tokens) {
            if (token.startsWith("-"))
                throw new IllegalArgumentException("음수는 허용되지 않습니다.");
            if (!token.matches("\\d+"))
                throw new IllegalArgumentException("숫자 외 문자가 포함되었습니다.");
            value = Long.parseLong(token);
            sum += value;
            // 오버플로우 방지
            if (sum > Integer.MAX_VALUE)
                throw new IllegalArgumentException("너무 큰 수 입니다.");
        }
    }


    private String getUserInput() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();
        if (input.isEmpty())
            input = "0";
        return input;
    }

    private String parseDelimiter(String input) {
        String delimiter = "[,:]";
        if (input.startsWith("//"))
            delimiter = String.valueOf(input.charAt(2));
        return delimiter;
    }

    private String parseNumber(String input) {
        if (!input.startsWith("//"))
            return input;
        int suffixIndex = input.indexOf("\\n");
        input = input.substring(suffixIndex + 2);
        if (input.isEmpty())
            input = "0";
        return input;
    }

    private int calculate(String[] numbers) {
        int sum = 0;
        for (String number : numbers) {
            sum += Integer.parseInt(number);
        }
        return sum;
    }

}
