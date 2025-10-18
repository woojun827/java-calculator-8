package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpringCalculator {

    private static final Pattern CUSTOM_PATTERN = Pattern.compile("//(.)\n(.*)", Pattern.DOTALL);

    public static int splitAndSum(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String numbers = input;
        String delimiter = ",|:";   // 기본 구문자 미리 설정

        if (input.startsWith("//")){
            Matcher m = CUSTOM_PATTERN.matcher(input);
            if (m.matches()){
                delimiter = Pattern.quote(m.group(1));  // 특수문자 대용!
                numbers = m.group(2);
            }
        }

        String[] tokens = numbers.split(delimiter);

        int sum = 0;
        for (String token : tokens) {
            if (token == null || token.isEmpty()) {continue;}
            sum += Integer.parseInt(token);
        }
        return sum;
    }
}
