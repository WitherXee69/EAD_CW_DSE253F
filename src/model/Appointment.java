class Appointment {
    private String appointmentId;
    private String customerId;
    private String veterinarianId;
    private String petId;
    private String date;
    private String time;

    public Appointment(String appointmentId, String customerId, String veterinarianId, String petId, String date, String time) {
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.veterinarianId = veterinarianId;
        this.petId = petId;
        this.date = date;
        this.time = time;
    }

    // Getters
    public String getAppointmentId() {
        return appointmentId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getVeterinarianId() {
        return veterinarianId;
    }

    public String getPetId() {
        return petId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    // Setters
    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setVeterinarianId(String veterinarianId) {
        this.veterinarianId = veterinarianId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }
}