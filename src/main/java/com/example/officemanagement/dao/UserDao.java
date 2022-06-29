package com.example.officemanagement.dao;

import com.example.officemanagement.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User,String> {

}
