package co.verisoft.examples.api.responses;

import co.verisoft.examples.api.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

/**
 * This class is used to map the response from the API
 * https://dummy.restapiexample.com/api/v1/employees
 * It is used in the test class ApiTest
 *
 * @author Nir Gallner
 * @since August 2023
 */
public class Employee {
    public String id;
    public String employee_name;
    public String employee_salary;
    public String employee_age;
    public String profile_image;
}
