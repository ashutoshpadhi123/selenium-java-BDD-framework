package pages;

import api.ApiHelper;
import enums.APIEndPoints;
import io.restassured.response.Response;

public class PetStoreAPIPage {
    ApiHelper apiHelper = new ApiHelper();
    public Response addPet(Object body) {
        return apiHelper.post(APIEndPoints.CREATE_PET.getType(), body);
    }

    public Response getPetById(long petId) {
        String endpoint = APIEndPoints.GET_PET_BY_ID.getType().replace("{petId}", String.valueOf(petId));
        return apiHelper.get(endpoint);
    }

    public Response updatePet(Object body) {
        return apiHelper.put(APIEndPoints.UPDATE_PET.getType(), body);
    }

    public Response deletePet(long petId) {
        String endpoint = APIEndPoints.DELETE_PET.getType().replace("{petId}", String.valueOf(petId));
        return apiHelper.delete(endpoint);
    }

    public Response findPetsByStatus(String status) {
        String endpoint = APIEndPoints.FIND_PET_BY_STATUS.getType() + "?status=" + status;
        return apiHelper.get(endpoint);
    }
}
