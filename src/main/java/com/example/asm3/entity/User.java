package com.example.asm3.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "gender")
    private String gender;

    @Column(name = "description")
    private String description;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "verification_code")
    private String verificationCode;

    @Column(name = "reset_password_code")
    private String resetPasswordCode;

    public User() {
    }

    public User(String name, String email, String password, String dateOfBirth, String address, String phone, String avatar, String gender, String description, Role role, boolean enabled, String verificationCode, String resetPasswordCode) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phone = phone;
        this.avatar = avatar;
        this.gender = gender;
        this.description = description;
        this.role = role;
        this.enabled = enabled;
        this.verificationCode = verificationCode;
        this.resetPasswordCode = resetPasswordCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getResetPasswordCode() {
        return resetPasswordCode;
    }

    public void setResetPasswordCode(String resetPasswordCode) {
        this.resetPasswordCode = resetPasswordCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", avatar='" + avatar + '\'' +
                ", gender='" + gender + '\'' +
                ", description='" + description + '\'' +
                ", role=" + role +
                ", enabled=" + enabled +
                '}';
    }
}
