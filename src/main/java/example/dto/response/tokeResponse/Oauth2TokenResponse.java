package example.dto.response.tokeResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Oauth2TokenResponse {
    String access_token;
    String token_type;
}
