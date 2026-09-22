class Pet {
    private String petId;
    private String name;
    private String species;
    private int age;
    private String ownerId;
    private char gender;
    private String breed;

    public Pet(String petId, String name, String species, int age, String ownerId, char gender, String breed) {
        this.petId = petId;
        this.name = name;
        this.species = species;
        this.age = age;
        this.ownerId = ownerId;
        this.gender = gender;
        this.breed = breed;
    }

    //Getters
    public String getPetId() {
        return petId;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public int getAge() {
        return age;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public char getGender() {
        return gender;
    }

    public String getBreed() {
        return breed;
    }

    //Setters
    public void setPetId(String petId) {
        this.petId = petId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}