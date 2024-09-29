package co.verisoft.fw.cucumber;
import org.springframework.context.annotation.*;

import java.util.Map;

/**
 * BaseDictionaryImpl class implements BaseDictionary, ExtendedActions, and APIDictionary.
 * Provides implementations for user interactions, UI element manipulation, API actions, and response verification.
 * Configured with @Configuration for Spring IoC container.
 */
@Configuration
public class BaseAPIDictionaryImpl implements APIDictionary{

    @Override
    public void initCertificates(String s, String s1) {
        System.out.println("implement method initCertificates");
    }

    @Override
    public void setHeaders(Map<String, String> map) {
        System.out.println("implement method setHeaders");
    }

    @Override
    public void setBearerToken(String s) {
        System.out.println("implement method setBearerToken");
    }

    @Override
    public void setJwtAsBearerToken() {
        System.out.println("implement method setJwtAsBearerToken");
    }

    @Override
    public void sendPostRequest(String s, Map<String, String> map) {
        System.out.println("implement method sendPostRequest");
    }

    @Override
    public void sendGetRequest(String s) {
        System.out.println("implement method sendGetRequest");
    }

    @Override
    public void sendGetRequestWithParams(String s, Map<String, String> map) {
        System.out.println("implement method sendGetRequestWithParams");
    }

    @Override
    public void sendPutRequest(String s, Map<String, String> map) {
        System.out.println("implement method sendPutRequest");
    }

    @Override
    public void sendDeleteRequest(String s, Map<String, String> map) {
        System.out.println("implement method sendDeleteRequest");
    }

    @Override
    public void generateJwt(String s) {
        System.out.println("implement method generateJwt");
    }

    @Override
    public void verifyStatusCode(int i) {
        System.out.println("implement method verifyStatusCode");
    }

    @Override
    public void verifyResponseFields(Map<String, String> map) {
        System.out.println("implement method verifyResponseFields");
    }

    @Override
    public void verifyResponseHeader(String s, String s1) {
        System.out.println("implement method verifyResponseHeader");
    }

    @Override
    public void verifyErrorMessage(String s) {
        System.out.println("implement method verifyErrorMessage");
    }

    @Override
    public void verifyFieldWithValue(String s, String s1) {
        System.out.println("implement method verifyFieldWithValue");
    }
}

