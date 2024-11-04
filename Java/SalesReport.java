
import java.time.LocalDate;
import java.util.*;

public class SalesReport {

    public String generateReport(String salesData) {
        Map<String, Map<String, Integer>> quarterlySales = new TreeMap<>();

        String[] salesEntries = salesData.split(";");

        for (String entry : salesEntries) {
            String[] parts = entry.split(":");
            LocalDate date = LocalDate.parse(parts[0]);
            String product = parts[1];
            int quantity = Integer.parseInt(parts[2]);

            int month = date.getMonthValue();
            String quarter = "Q" + ((month - 1) / 3 + 1);

            quarterlySales.putIfAbsent(quarter, new TreeMap<>());
            Map<String, Integer> productSales = quarterlySales.get(quarter);

            productSales.put(product, productSales.getOrDefault(product, 0) + quantity);
        }

        StringBuilder report = new StringBuilder();
        for (Map.Entry<String, Map<String, Integer>> quarterEntry : quarterlySales.entrySet()) {
            if (!report.isEmpty()) {
                report.append("\n");
            }
            report.append(quarterEntry.getKey()).append(":");
            for (Map.Entry<String, Integer> productEntry : quarterEntry.getValue().entrySet()) {
                report.append("\n- ").append(productEntry.getKey()).append(": ").append(productEntry.getValue());
            }
        }

        return report.toString();
    }
}