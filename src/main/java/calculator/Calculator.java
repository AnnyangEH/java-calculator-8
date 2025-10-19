package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.math.BigInteger;

public class Calculator {
    private static final Pattern CUSTOM_PATTERN = Pattern.compile("^//(.)\\n(.*)$");

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
        if (input.startsWith("//") && !input.contains("\n")) {
            throw new IllegalArgumentException("커스텀 구분자 형식 오류");
        }
    }

    private void validateTokens(String[] tokens) {
        for (String token : tokens) {
            if (token.startsWith("-"))
                throw new IllegalArgumentException("음수는 허용되지 않습니다.");
            if (!token.matches("\\d+"))
                throw new IllegalArgumentException("숫자 외 문자가 포함되었습니다.");
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
        Matcher matcher = CUSTOM_PATTERN.matcher(input);
        if (matcher.matches())
            delimiter = Pattern.quote(matcher.group(1));
        return delimiter;
    }

    private String parseNumber(String input) {
        Matcher matcher = CUSTOM_PATTERN.matcher(input);
        if (matcher.matches()) {
            String numbers = matcher.group(2);
            if (numbers.isEmpty())
                numbers = "0";
            return numbers;
        }
        return input;
    }

    private BigInteger calculate(String[] numbers) {
        BigInteger sum = BigInteger.ZERO;
        for (String number : numbers) {
            sum = sum.add(new BigInteger(number));
        }
        return sum;
    }
}
