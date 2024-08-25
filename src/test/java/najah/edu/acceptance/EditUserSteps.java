package najah.edu.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.swing.JOptionPane;

import MySystem.EditUser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class EditUserSteps {

    private EditUser editUser;
    private String resultMessage;
    private String username, email, role;

    @Given("the user is on the Edit User page for {string}")
    public void theUserIsOnTheEditUserPageFor(String username) {
        editUser = new EditUser(username);
    }

    @When("the user enters {string} as email")
    public void theUserEntersEmail(String email) {
        this.email = email;
        editUser.getEmail();
    }

    @When("the user selects {string} as role")
    public void theUserSelectsRole(String role) {
        this.role = role;
        editUser.getRole();
    }

    @When("the user clicks on Save Changes button")
    public void theUserClicksOnSaveChangesButton() {
        editUser.updateUserData(email, role);
    }

    @Then("a success message {string} should be displayed")
    public void aSuccessMessageShouldBeDisplayed(String expectedMessage) {
        assertEquals(expectedMessage, resultMessage);
        
        editUser.loadUserData(username);
        String updatedEmail = editUser.getEmail();
        String updatedRole = editUser.getRole();
        assertEquals(email, updatedEmail);
        assertEquals(role, updatedRole);
    }

    @Then("an error message {string} should be displayed")
    public void anErrorMessageShouldBeDisplayed(String expectedMessage) {
        assertEquals(expectedMessage, resultMessage);
    }
}
