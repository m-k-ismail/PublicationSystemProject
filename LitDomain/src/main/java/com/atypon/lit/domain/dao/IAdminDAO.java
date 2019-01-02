package com.atypon.lit.domain.dao;

public interface IAdminDAO {
    int getAdminByUserAndPass(String username, String password);
}
