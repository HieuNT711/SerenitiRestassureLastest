package example.dto.request.orderRequest;

import lombok.Data;

import java.util.List;
@Data
public class PurchaseUnit {
    private List<Item> items;
    private Amount amount;

    // Getters and Setters
}

