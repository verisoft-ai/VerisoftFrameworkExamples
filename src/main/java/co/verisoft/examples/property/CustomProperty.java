package co.verisoft.examples.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CustomProperty {

    @Value("${custom.property}")
    private String customProperty;

}
