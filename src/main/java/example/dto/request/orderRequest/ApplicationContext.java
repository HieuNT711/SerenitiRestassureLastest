package example.dto.request.orderRequest;

import lombok.Data;

@Data
public class ApplicationContext {
    private String return_url;
    private String cancel_url;

    // Getters and Setters
}

