package co.verisoft.fw.cucumber;
import org.springframework.context.annotation.*;

import java.util.Map;

/**
 * BaseDictionaryImpl class implements BaseDictionary, ExtendedActions, and APIDictionary.
 * Provides implementations for user interactions, UI element manipulation, API actions, and response verification.
 * Configured with @Configuration for Spring IoC container.
 */
@Configuration
public class BaseDictionaryImpl implements BaseDictionary, ExtendedActions , APIDictionary{

    @Override
    public void userOnPage(String pageName) {
        System.out.println("Implementation of function userOnPage");
    }

    @Override
    public void startActivity(String appActivity, String appPackage) {
        System.out.println("Implementation of function startActivity");
    }

    @Override
    public void uploadDriver() {
        System.out.println("Implementation of function uploadDriver");
    }

    @Override
    public void tapButton(String buttonId) {
        System.out.println("Implementation of function tapButton");
    }

    @Override
    public void tapField(String fieldId) {
        System.out.println("Implementation of function tapField");
    }

    @Override
    public void enterValueInField(String fieldId, String value) {
        System.out.println("Implementation of function enterValueInField");
    }

    @Override
    public void deleteValueInField(String fieldId) {
        System.out.println("Implementation of function deleteValueInField");
    }

    @Override
    public void selectDate(String date, String fieldId) {
        System.out.println("Implementation of function selectDate");
    }

    @Override
    public void tapCheckbox(String checkboxId) {
        System.out.println("Implementation of function tapCheckbox");
    }

    @Override
    public void tapIcon(String iconId) {
        System.out.println("Implementation of function tapIcon");
    }

    @Override
    public void swipe(String direction) {
        System.out.println("Implementation of function swipe");
    }

    @Override
    public void chooseOption(String optionId) {
        System.out.println("Implementation of function chooseOption");
    }

    @Override
    public void login(String userName, String password) {
        System.out.println("Implementation of function login");
    }

    @Override
    public void getElement(String elementId) {
        System.out.println("Implementation of function getElement");
    }

    @Override
    public void getScreenTitle(String screenTitleTxt) {
        System.out.println("Implementation of function getScreenTitle");
    }

    @Override
    public void getScreenSubtitle(String subtitleTxt) {
        System.out.println("Implementation of function getScreenSubtitle");
    }

    @Override
    public void getTextOnElement(String txt, String elementId) {
        System.out.println("Implementation of function getTextOnElement");
    }

    @Override
    public void seeButton(String btnID) {
        System.out.println("Implementation of function seeButton");
    }

    @Override
    public void seeField(String fieldID) {
        System.out.println("Implementation of function seeField");
    }

    @Override
    public void getFieldWithPlaceholder(String fieldId, String placeholderTxt) {
        System.out.println("Implementation of function getFieldWithPlaceholder");
    }

    @Override
    public void getErrorMessage(String errorType, String errorMessageTxt) {
        System.out.println("Implementation of function getErrorMessage");
    }

    @Override
    public void performAction(String actionId) {
        System.out.println("implement method perform action");
    }

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
