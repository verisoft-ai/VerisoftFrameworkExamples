package co.verisoft.fw.cucumber;

/**
 * this is self own dictionary definition
 **/
public interface ExtendedActions {

    void performAction(String actionId);
    void foundOnPage(String actionId);
    void receivingResult(String actionId);
}
