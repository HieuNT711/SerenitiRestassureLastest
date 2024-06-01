package example.dto.request.orderRequest;

import lombok.Data;

@Data
public class Amount {
    private String currency_code;
    private String value;
    private Breakdown breakdown;

    // Getters and Setters
    @Data
    public static class Breakdown {
        private ItemTotal item_total;

        // Getters and Setters
        @Data
        public static class ItemTotal {
            private String currency_code;
            private String value;

            // Getters and Setters
        }
    }

    // Getters and Setters
}

