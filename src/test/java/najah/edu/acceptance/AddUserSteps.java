package najah.edu.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import javax.swing.JOptionPane;

import MySystem.AddUser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.assertTrue;

public class AddUserSteps {

    private MySystem.AddUser addUsers = new MySystem.AddUser();
    private String resultMessage;
    private MySystem.LoginGUI Login = new MySystem.LoginGUI();
    private String username,email,role;

    @Given("the user is on the Add User page")
    public void theUserIsOnTheAddUserPage() {
        
    }

    @When("the user enters {string} as username")
    public void theUserEntersUsername(String username) {
        this.username=username;
    }

    @When("the user enters {string} as email")
    public void theUserEntersEmail(String email) {
        this.email=email;
    }

    @When("the user selects {string} as role")
    public void theUserSelectsRole(String role) {
        this.role=role;
    }

    @When("the user clicks on {string} button")
    public void theUserClicksOnButton(String string) {
    	addUsers.addUser(username,email,role);
    }

    @Then("a success message {string} should be displayed")
    public void aSuccessMessageShouldBeDisplayed(String expectedMessage) {
    	assertTrue(MySystem.LoginGUI.login(username, "123") == role);
    }

	@Then("an error message {string} should be displayed")
    public void anErrorMessageShouldBeDisplayed(String expectedMessage) {
		assertTrue(MySystem.LoginGUI.login(username, "12") != role);
    }
}