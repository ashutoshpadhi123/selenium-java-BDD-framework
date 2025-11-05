package enums;

import Config.ConfigReader;

public enum APIEndPoints {
    CREATE_PET("createPet"),
    GET_PET_BY_ID("getPetById"),
    UPDATE_PET("updatePet"),
    DELETE_PET("deletePet"),
    FIND_PET_BY_STATUS("findPetByStatus");

    private final String key;

    APIEndPoints(String key) {
        this.key = key;
    }

    public String getType() {
        return ConfigReader.getEndpoint(this.key);
    }
}
