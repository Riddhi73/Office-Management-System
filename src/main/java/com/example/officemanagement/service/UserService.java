package com.example.officemanagement.service;

import com.example.officemanagement.dao.RoleDao;
import com.example.officemanagement.dao.UserDao;
import com.example.officemanagement.entity.Role;
import com.example.officemanagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public User registerNewUser(User user){
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRoleSet(userRoles);
        user.setPassword(getEncodedPassword(user.getPassword()));
        return userDao.save(user);
    }
    public void initRolesAndUser(){

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("An admin for our application");
        roleDao.save(adminRole);
        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("Admin123");
        adminUser.setFirstName("Admin");
        adminUser.setLastName("Admin");
        adminUser.setPassword(getEncodedPassword("admin@pass"));
        adminUser.setContactNo("011222211");
        adminUser.setDesignation("HR");
        adminUser.setEmail("admin@gmail.com");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRoleSet(adminRoles);
        userDao.save(adminUser);

//        User user = new User();
//        user.setUserName("Raj123");
//        user.setFirstName("Raj");
//        user.setPassword(getEncodedPassword("raj@pass"));
//        user.setLastName("Ahmed");
//        user.setContactNo("011111111");
//        user.setDesignation("QA");
//        user.setEmail("raj@gmail.com");
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRoleSet(userRoles);
//        userDao.save(user);


    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
