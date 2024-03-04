public class User {

    private String name;
    private String email;
    private String address;
    private String phone;
    private String pass;
    private Cart cart = new Cart();


    User() {
    name = "";
    email = "";
    address = "";
    phone = "";
    pass = "";
    }


    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAdr() {
        return address;
    }

    public String getPass() {
        return pass;
    }

    public String getPhone() {
        return phone;
    }

    public Cart getCart() {
        return cart;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String adr) {
        this.address = adr;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
