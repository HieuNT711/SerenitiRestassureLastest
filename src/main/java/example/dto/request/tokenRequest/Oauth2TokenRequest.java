package example.dto.request.tokenRequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import example.dto.Request;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties
public class Oauth2TokenRequest implements Request {
    public String grant_type;
}
