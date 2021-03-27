package atmosphere;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

public class SimpleFeatureTest {
    int firstNumber;
    int secondNumber;
    int result;

    @Given("one and two")
    public void oneAndTwo() {
        firstNumber = 1;
        secondNumber = 2;
    }

    @When("add given number")
    public void addGivenNumber() {
        result = firstNumber + secondNumber;
    }

    @Then("result is three")
    public void resultIsThree() {
        Assertions.assertThat(result).isEqualTo(1 + 2);
    }
}
