package co.verisoft.examples.api.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeRequest {
    private String employee_name;
    private String employee_salary;
    private String employee_age;
}
