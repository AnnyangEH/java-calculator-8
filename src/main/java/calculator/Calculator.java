package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Calculator {
    public void run() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();
        String delimiter = "[,:]";
        String parseString = input;
        int suffixIndex;
        if (input.startsWith("//")) {
            delimiter = String.valueOf(input.charAt(2));
            suffixIndex = input.indexOf("\\n");
            if (suffixIndex != -1) {
                parseString = input.substring(suffixIndex + 2);
            }
        }

        String[] numbers = parseString.split(delimiter);
        int sum = 0;
        for (String number : numbers) {
            sum += Integer.parseInt(number);
        }
        System.out.println("결과 : " + sum);
    }
}
