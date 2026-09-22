class Customer extends Person {
    private String customerId;

    public Customer(int id, String name, int phoneNumber, String email, String address, String customerId) {
        super(id, name, phoneNumber, email, address);
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}