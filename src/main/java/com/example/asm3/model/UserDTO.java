package com.example.asm3.model;

import com.example.asm3.entity.Role;
import com.example.asm3.entity.User;

public class UserDTO {

    private String name;
    private String email;
    private String password;
    private String rePassword;
    private String address;
    private String phone;
    private String gender;
    private Integer roleId;

    public UserDTO() {
    }

    public UserDTO(String name, String email, String password, String rePassword, String address, String phone, String gender, Integer roleId) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.rePassword = rePassword;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.roleId = roleId;
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

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public User toUser() {
        User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setAddress(this.address);
        user.setPhone(this.phone);
        user.setGender(this.gender);

        Role role = new Role();
        role.setId(this.roleId);
        user.setRole(role);

        return user;
    }
}
