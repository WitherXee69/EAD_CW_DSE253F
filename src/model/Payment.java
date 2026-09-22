class Payment {
    private String paymentId;
    private String appointmentId;
    private double amount;
    private String currency;
    private String paymentMethod;

    public Payment(String paymentId, String appointmentId, double amount, String currency, String paymentMethod) {
        this.paymentId = paymentId;
        this.appointmentId = appointmentId;
        this.amount = amount;
        this.currency = currency;
        this.paymentMethod = paymentMethod;
    }

    // Getters
    public String getPaymentId() {
        return paymentId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    //Setters
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}