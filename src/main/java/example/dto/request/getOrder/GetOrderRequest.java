package example.dto.request.getOrder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class GetOrderRequest {
    String order_id;
}
