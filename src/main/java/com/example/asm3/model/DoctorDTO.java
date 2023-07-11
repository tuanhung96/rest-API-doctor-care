package com.example.asm3.model;

import com.example.asm3.entity.Clinic;
import com.example.asm3.entity.Role;
import com.example.asm3.entity.Specialization;
import com.example.asm3.entity.User;
import jakarta.persistence.Column;

public class DoctorDTO {
    private String name;
    private String email;
    private String password;
    private String dateOfBirth;
    private String address;
    private String phone;
    private String avatar;
    private String gender;
    private String description;
    private Integer roleId;
    private Integer clinicId;
    private Integer specializationId;

    public DoctorDTO() {
    }

    public DoctorDTO(String name, String email, String password, String dateOfBirth, String address, String phone, String avatar, String gender, String description, Integer roleId, Integer clinicId, Integer specializationId) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phone = phone;
        this.avatar = avatar;
        this.gender = gender;
        this.description = description;
        this.roleId = roleId;
        this.clinicId = clinicId;
        this.specializationId = specializationId;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getClinicId() {
        return clinicId;
    }

    public void setClinicId(Integer clinicId) {
        this.clinicId = clinicId;
    }

    public Integer getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(Integer specializationId) {
        this.specializationId = specializationId;
    }


    public User convertToUser() {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setDateOfBirth(dateOfBirth);
        user.setAddress(address);
        user.setPhone(phone);
        user.setAvatar(avatar);
        user.setGender(gender);
        user.setDescription(description);
        Role role = new Role();
        role.setId(roleId);
        user.setRole(role);
        Clinic clinic = new Clinic();
        clinic.setId(clinicId);
        Specialization specialization = new Specialization();
        specialization.setId(specializationId);

        return user;
    }
}
