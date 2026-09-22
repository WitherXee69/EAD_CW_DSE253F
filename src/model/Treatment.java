class Treatment {
    private String treatmentId;
    private String name;
    private String description;
    private double cost;

    public Treatment(String treatmentId, String name, String description, double cost) {
        this.treatmentId = treatmentId;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    // Getters
    public String getTreatmentId() {
        return treatmentId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    //Setters
    public void setTreatmentId(String treatmentId) {
        this.treatmentId = treatmentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}