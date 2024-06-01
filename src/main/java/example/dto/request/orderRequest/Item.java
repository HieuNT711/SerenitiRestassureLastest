package example.dto.request.orderRequest;


import lombok.Data;

@Data
public class Item {
    private String name;
    private String description;
    private String quantity;
    private UnitAmount unit_amount;

    // Getters and Setters
}

