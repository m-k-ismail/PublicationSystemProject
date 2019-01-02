package com.atypon.lit.domain.model;

import java.util.Objects;


public class UserEntity {
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LicenseEntity licenseByLicenseId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, firstName, lastName);
    }

    public LicenseEntity getLicenseByLicenseId() {
        return licenseByLicenseId;
    }

    public void setLicenseByLicenseId(LicenseEntity licenseByLicenseId) {
        this.licenseByLicenseId = licenseByLicenseId;
    }
}
