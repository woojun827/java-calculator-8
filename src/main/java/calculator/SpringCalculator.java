package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpringCalculator {
    // 구분자 패턴을 잡는 정규식을 미리 컴파일해 상수로 보관
    private static final Pattern CUSTOM_PATTERN = Pattern.compile("//(.)\n(.*)", Pattern.DOTALL);

    public static int splitAndSum(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String numbers = input;
        String delimiter = ",|:";   // 기본 구문자 미리 설정

        if (input.startsWith("//")){    // //로 시작하면 커스텀 구분자 모드로 판정
            Matcher m = CUSTOM_PATTERN.matcher(input);
            if (m.matches()){   // 정규식이 입력 전체를 매칭했는지 검사
                delimiter = Pattern.quote(m.group(1));  // 그룹 1에서 커스텀 구분자 추출하여 기본 구문자 대체
                // Pattern.quote() 로 감싸 정규식 특수문자여도 안전하게 리터럴 취급하도록 처리
                numbers = m.group(2);   // 숫자들이 들어있는 본문을 추출해 그룹 2에 저장
            }else{  // 정규식에 맞지 않을시 예외 처리
                throw new IllegalArgumentException("잘못된 구분자 형식입니다: " + input);
            }
        }

        String[] tokens = numbers.split(delimiter);

        int sum = 0;
        for (String token : tokens) {   // split한 token을 sum에 더하는 과
            if (token == null) continue;
            token = token.trim();   // 토근 앞뒤 공백 제거
            if (token.isEmpty()) continue;

            final int number;
            try{
                number = Integer.parseInt(token);
            }catch (NumberFormatException e){
                throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다: "+ token);
            }

            if (number<0){
                throw new IllegalArgumentException("음수는 입력할 수 없습니다: "+token);
            }

            sum += number;
        }
        return sum;
    }
}
