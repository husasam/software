package najah.edu.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.swing.JOptionPane;

import MySystem.CommunicateWithStoreOwners;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class CommunicateWithStoreOwnersSteps {

    private MySystem.CommunicateWithStoreOwners communicateWithStoreOwners;
    private String resultMessage;
    private String receiverName;
    private String message;
    private String res;

    @Given("the user is on the Communicate with Store Owners page")
    public void theUserIsOnTheCommunicateWithStoreOwnersPage() {
        communicateWithStoreOwners = new CommunicateWithStoreOwners("husam");
    }

    @When("the user enters {string} as receiver username")
    public void theUserEntersReceiverUsername(String receiverName) {
        this.receiverName = receiverName;
    }

    @When("the user enters {string} as the message")
    public void theUserEntersMessage(String message) {
        this.message = message;
    }

    @When("the user clicks on Send Message button")
    public void theUserClicksOnSendMessageButton() {
    	res = communicateWithStoreOwners.sendMessage("husam",receiverName,message);
    }

    @Then("a success message {string} should be displayed")
    public void aSuccessMessageShouldBeDisplayed(String expectedMessage) {
        
        assertEquals(res, "Message sent successfully!");
    }

    @Then("an error message {string} should be displayed")
    public void anErrorMessageShouldBeDisplayed(String expectedMessage) {
    	res = communicateWithStoreOwners.sendMessage("husam","error",message);
    	assertEquals(res, "Error");
    }

}
