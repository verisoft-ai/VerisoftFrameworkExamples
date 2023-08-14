package co.verisoft.examples;

import co.verisoft.examples.api.ApiRequest;
import co.verisoft.examples.api.responses.Employee;
import co.verisoft.examples.api.responses.EmployeeWrapper;
import co.verisoft.examples.api.responses.EmployeesWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 * This class is used to test the API
 * https://dummy.restapiexample.com/api/v1/employees
 * It is used in the test class ApiTest
 * It uses the classes ApiRequest, EmployeeWrapper, Employee, OptionalObject
 * It uses the classes Views.Mandatory, Views.Optional
 * <p>
 * TODO: Add confluence link and update the documentation
 */
public class ApiTest {

    @Test
    public void getAllEmployees() throws JsonProcessingException {

        // Create the API request
        ApiRequest apiRequest = ApiRequest.builder()
                .endpoint("https://dummy.restapiexample.com")
                .method(Method.GET.toString())
                .build();

        // Build a RestAssured request
        RestAssured.baseURI = apiRequest.getEndpoint();
        RequestSpecification request = prepareRequest(apiRequest);

        // Send the request and get the response
        Response response = request.get("/api/v1/employees");
        ObjectMapper objectMapper = new ObjectMapper();
        EmployeesWrapper employeesWrapper = objectMapper.readValue(response.getBody().asString(), EmployeesWrapper.class);

        // Assert that the response is successful and that the response body is not empty
        Assertions.assertTrue(employeesWrapper.getData().size() > 0);

    }


    @Test
    public void getASingleEmployee() throws JsonProcessingException {

        // Create the API request
        ApiRequest apiRequest = ApiRequest.builder()
                .endpoint("https://dummy.restapiexample.com")
                .method(Method.GET.toString())
                .build();

        // Build a RestAssured request
        RestAssured.baseURI = apiRequest.getEndpoint();
        RequestSpecification request = prepareRequest(apiRequest);

        // Send the request and get the response
        Response response = request.get("/api/v1/employee/1");
        ObjectMapper objectMapper = new ObjectMapper();
        EmployeeWrapper employeesWrapper = objectMapper.readValue(response.getBody().asString(), EmployeeWrapper.class);

        // Assert that the response is successful and that the response body is not empty
        Assertions.assertTrue(Objects.nonNull(employeesWrapper.getData()));

    }


    /**
     * This method is used to build a RestAssured request
     *
     * @param apiRequest The API request
     * @return The RestAssured request
     */
    private RequestSpecification prepareRequest(ApiRequest apiRequest) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", ContentType.JSON);
        request.header("Accept", ContentType.JSON);
        if (apiRequest.getAuthToken() != null) {
            request.header("Authorization", "Bearer " + apiRequest.getAuthToken());
        }
        if (apiRequest.getBody() != null) {
            request.body(apiRequest.getBody());
        }
        return request;
    }
}
