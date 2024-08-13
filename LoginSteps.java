package najah.edu.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import javax.swing.JOptionPane;

import MySystem.LoginGUI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class LoginSteps {

    private String loginResult;
    private String dashboard;

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
    	MySystem.LoginGUI Login = new MySystem.LoginGUI();
    }

    @When("the user enters {string} and {string}")
    public void theUserEntersUsernameAndPassword(String username, String password) {
        loginResult = MySystem.LoginGUI.login(username, password);
        
    }

    @Then("the user should be redirected to the {string}")
    public void theUserShouldBeRedirectedToTheDashboard(String dashboard) {
    	JOptionPane.showMessageDialog(null, dashboard+"ls"+loginResult);
        assertEquals(dashboard, loginResult);
    }

    @Then("an error message should be displayed")
    public void anErrorMessageShouldBeDisplayed() {
    	assertFalse(loginResult=="Invalid username or password");
    }
}
