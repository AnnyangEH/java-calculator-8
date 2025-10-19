package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    private static final Pattern CUSTOM_PATTERN = Pattern.compile("//(.)\\r?\\n(.*)");

    public void run() {
        String input = getUserInput();
        validateInput(input);

        String delimiter = parseDelimiter(input);
        String numbers = parseNumbers(input);

        String[] tokens = numbers.split(Pattern.quote(delimiter));
        validateTokens(tokens);

        BigInteger result = calculate(tokens);
        printResult(result);
    }

    private String getUserInput() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        if (input == null || input.isEmpty())
            input = "0";
        return input;
    }

    private void validateInput(String input) {
        if (input.startsWith("//")) {
            if (input.length() < 4)
                throw new IllegalArgumentException("커스텀 구분자 형식 오류");
            if (!input.contains("\n") && !input.contains("\\n"))
                throw new IllegalArgumentException("커스텀 구분자 형식 오류");
        }
    }

    private void validateTokens(String[] tokens) {
        for (String token : tokens) {
            if (token.isEmpty()) {
                throw new IllegalArgumentException("잘못된 입력입니다. 구분자 사이에 값이 없습니다.");
            }
            if (token.startsWith("-")) {
                throw new IllegalArgumentException("음수는 허용되지 않습니다.");
            }
            if (!token.matches("\\d+")) {
                throw new IllegalArgumentException("숫자 외 문자가 포함되었습니다.");
            }
        }
    }

    private String parseDelimiter(String input) {
        if (input.startsWith("//")) {
            return String.valueOf(input.charAt(2));
        }
        return "[,:]";
    }

    private String parseNumbers(String input) {
        Matcher matcher = CUSTOM_PATTERN.matcher(input);

        if (matcher.matches())
            return matcher.group(2);

        // 정규식으로 매칭되지 않은 경우
        if (input.startsWith("//")) {
            int suffixIndex = input.indexOf('\n');
            if (suffixIndex == -1)
                suffixIndex = input.indexOf("\\n"); // 리터럴 개행 처리

            if (suffixIndex != -1) {
                int offset;
                if (input.charAt(suffixIndex) == '\\') {
                    offset = 2;
                } else {
                    offset = 1;
                }
                return input.substring(suffixIndex + offset);
            }
        }

        return input;
    }

    // 오버플로우 방지 BigInteger
    private BigInteger calculate(String[] numbers) {
        BigInteger sum = BigInteger.ZERO;
        for (String number : numbers) {
            sum = sum.add(new BigInteger(number));
        }
        return sum;
    }

    private void printResult(BigInteger result) {
        System.out.print("결과 : " + result);
    }
}
