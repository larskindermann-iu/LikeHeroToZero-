package com.project.likeherotozero.beans;

import com.project.likeherotozero.dao.UserDao;
import com.project.likeherotozero.entities.User;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class UserBean {
    private User newUser = new User();

    public UserBean() {}

    public String register() {
        UserDao dao = new UserDao();
        dao.save(newUser);
        newUser = new User();
        return "login?faces-redirect=true";
    }

    // Getters and setters
    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }
}
