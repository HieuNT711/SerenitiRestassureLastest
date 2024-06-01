package example.dto.request.orderRequest;

import lombok.Data;

@Data
public class UnitAmount {
    private String currency_code;
    private String value;

    // Getters and Setters
}

