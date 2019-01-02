package com.atypon.lit.domain.dao;

public interface IUserDAO {
    int getUserLicense(String userId);

    int getUserByUserAndPass(String username, String password);
}
