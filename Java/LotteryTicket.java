
import java.util.LinkedHashMap;
import java.util.Map;

public class LotteryTicket {

    public String countDigits(String digits) {
        Map<Character, Integer> digitCount = new LinkedHashMap<>();

        for (char digit : digits.toCharArray()) {
            digitCount.put(digit, digitCount.getOrDefault(digit, 0) + 1);
        }

        StringBuilder result = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : digitCount.entrySet()) {
            if (!result.isEmpty()) {
                result.append(",");
            }
            result.append(entry.getKey()).append(":").append(entry.getValue() + 1);
        }

        return result.toString();
    }
}