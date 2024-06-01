package example.dto.request.orderRequest;

import lombok.Data;

import java.util.List;
@Data
public class OrderRequest {
    private String intent;
    private List<PurchaseUnit> purchase_units;
    private ApplicationContext application_context;

    // Getters and Setters
}

