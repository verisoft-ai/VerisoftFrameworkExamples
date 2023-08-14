package co.verisoft.examples.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiRequest {
    private String endpoint;
    private String method;
    private String body;
    private String headers;
    private String authToken;
}
