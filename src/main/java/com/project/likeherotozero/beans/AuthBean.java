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

        // Validate user credentials
        if (user != null && user.getPassword().equals(password)) {
            loggedInUser = user;

            // Redirect based on user role
            return redirectToDashboard();
        } else {
            // Handle login failure
            return "login?faces-redirect=true";  // Login failed
        }
    }

    public String logout() {
        loggedInUser = null; // Clear the logged-in user
        return "home?faces-redirect=true"; // Redirect to home after logout
    }

    public String getUserRole() {
        // Return the role of the logged-in user
        return loggedInUser != null ? loggedInUser.getRole() : null;
    }

    private String redirectToDashboard() {
        // Redirect based on user role
        if ("EDITOR".equalsIgnoreCase(loggedInUser.getRole())) {
            return "editor_dashboard?faces-redirect=true";
        } else if ("SCIENTIST".equalsIgnoreCase(loggedInUser.getRole())) {
            return "scientist_dashboard?faces-redirect=true";
        } else {
            return "dashboard?faces-redirect=true";  // Default dashboard
        }
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
