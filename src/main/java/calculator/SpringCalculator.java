package calculator;

public class SpringCalculator {
    public static int splitAndSum(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }
        String[] tokens = input.split(",|:");

        int sum = 0;
        for (String token : tokens) {
            if (token == null || token.isEmpty()) {continue;}
            sum += Integer.parseInt(token);
        }
        return sum;
    }
}
