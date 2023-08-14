package co.verisoft.examples.api.responses;

import co.verisoft.examples.api.Views;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.util.List;

/**
 * This class is used to map the response from the API
 * https://dummy.restapiexample.com/api/v1/employees
 * It is used in the test class ApiTest
 *
 * @author Nir Gallner
 * @since August 2023
 */
@Data
public class EmployeesWrapper {

    @JsonView(Views.Mandatory.class)
    private String status;

    @JsonView(Views.Mandatory.class)
    private List<Employee> data;

    @JsonView(Views.Optional.class)
    private String message;

    @JsonView(Views.Optional.class)
    private OptionalObject optionalObject;
}
