package pt.ipvc.models;

import javax.validation.constraints.*;

public class RegisterModel {
    @NotBlank(message = "Name is required")
    public String name;

    @NotBlank(message = "Phone is required")
    public String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    public String email;

    @NotBlank(message = "Password is required")
    //@Size(min = 6, message = "Password must have at least 6 characters")
    public String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
