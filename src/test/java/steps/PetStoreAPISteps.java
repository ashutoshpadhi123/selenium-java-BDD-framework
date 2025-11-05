package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;
import pages.PetStoreAPIPage;

import java.util.concurrent.ThreadLocalRandom;

public class PetStoreAPISteps {
    PetStoreAPIPage petStoreAPIPage = new PetStoreAPIPage();
    Response response;
    long testPetId = ThreadLocalRandom.current().nextLong(100000, 999999);

    @Given("I add a new pet with name {string} and status {string}")
    public void addNewPet(String name, String status) {
        String body = String.format("""
        {
          "id": %d,
          "category": {"id": 1, "name": "Dogs"},
          "name": "%s",
          "photoUrls": ["string"],
          "tags": [{"id": 0, "name": "tag1"}],
          "status": "%s"
        }
        """, testPetId, name, status);

        response = petStoreAPIPage.addPet(body);
        Assert.assertEquals(response.getStatusCode(), 200, "Pet creation failed!");
    }

    @When("I fetch the pet by ID")
    public void fetchPetById() {
        response = petStoreAPIPage.getPetById(testPetId);
    }

    @Then("the pet details should match name {string}")
    public void petDetailsShouldMatch(String expectedName) throws InterruptedException {
        Response response = null;
        String actualName = null;

        for (int i = 0; i < 5; i++) {
            response = petStoreAPIPage.getPetById(testPetId);
            actualName = response.jsonPath().getString("name");
            if (actualName != null && !actualName.isEmpty()) break;
            Thread.sleep(1000);
        }

        Assert.assertEquals(actualName, expectedName, "Pet name mismatch after creation!");
    }

    @When("I delete the pet")
    public void deletePet() {
        response = petStoreAPIPage.deletePet(testPetId);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Then("the pet should no longer exist")
    public void petShouldNoLongerExist() {
        response = petStoreAPIPage.getPetById(testPetId);
        Assert.assertEquals(response.getStatusCode(), 404);
    }
}
