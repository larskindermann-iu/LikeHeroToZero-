package com.project.likeherotozero.beans;

import com.project.likeherotozero.dao.UserDao;
import com.project.likeherotozero.entities.User;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped

public class AuthBean implements Serializable {
    private String username;
    private String password;
    private User loggedInUser;

    public AuthBean() {}

    public String login() {
        UserDao userDao = new UserDao();
        User user = userDao.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            loggedInUser = user;
            return "dashboard?faces-redirect=true";
        } else {
            return "login?faces-redirect=true";
        }
    }

    public String logout() {
        loggedInUser = null;
        return "home?faces-redirect=true";
    }

    // Getters and setters
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

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
