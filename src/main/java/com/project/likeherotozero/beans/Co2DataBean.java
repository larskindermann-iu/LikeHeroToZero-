package com.project.likeherotozero.beans;

import com.project.likeherotozero.dao.Co2DataDao;
import com.project.likeherotozero.entities.Co2Data;
import com.project.likeherotozero.entities.User;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
public class Co2DataBean {
    private Co2Data newCo2Data = new Co2Data(); // For adding new data
    private Co2Data selectedCo2Data; // For editing data
    private Long dataId; // To store the ID from the URL

    @Inject
    private AuthBean authBean; // Inject the AuthBean

    private final Co2DataDao dao = new Co2DataDao(); // Instantiate the DAO

    // Initialize method to load data based on the ID from the URL
    @PostConstruct
    public void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String idParam = facesContext.getExternalContext().getRequestParameterMap().get("id");

        if (idParam != null) {
            try {
                dataId = Long.parseLong(idParam); // Parse the ID from the URL
                selectedCo2Data = dao.findById(dataId); // Load the data based on the ID
            } catch (NumberFormatException e) {
                // Handle error in case ID is not a valid number
                dataId = null;
            }
        }
    }

    // Fetch all CO2 data for the public homepage
    public List<Co2Data> getAllCo2Data() {
        return dao.findAll(); // Display all data on the homepage
    }

    // Fetch CO2 data specific to the logged-in scientist
    public List<Co2Data> getScientistCo2Data() {
        User loggedInUser = authBean.getLoggedInUser();
        return dao.findByUser(loggedInUser); // Display only the data submitted by the logged-in scientist
    }

    // Add new CO2 data
    public String addCo2Data() {
        newCo2Data.setSubmittedBy(authBean.getLoggedInUser()); // Set the logged-in user as the submitter
        dao.save(newCo2Data);
        newCo2Data = new Co2Data(); // Reset the form after saving

        return redirectToDashboard();
    }

    // Edit method for selecting data to edit
    public String editCo2Data(Co2Data data) {
        this.selectedCo2Data = data; // Store the selected data
        return "edit_data?faces-redirect=true&id=" + data.getId(); // Redirect to edit form
    }

    // Update method for saving the edited CO2 data
    public String updateCo2Data() {
        dao.update(selectedCo2Data);
        return redirectToDashboard();
    }

    // Delete CO2 data
    public void deleteCo2Data(Co2Data data) {
        dao.delete(data); // Delete the selected data
    }

    // Helper method to redirect based on user role
    private String redirectToDashboard() {
        String role = authBean.getLoggedInUser().getRole();
        if ("SCIENTIST".equalsIgnoreCase(role)) {
            return "scientist_dashboard?faces-redirect=true";
        } else {
            return "dashboard?faces-redirect=true"; // Editor dashboard
        }
    }

    // Getters and setters
    public Co2Data getNewCo2Data() {
        return newCo2Data;
    }

    public void setNewCo2Data(Co2Data newCo2Data) {
        this.newCo2Data = newCo2Data;
    }

    public Co2Data getSelectedCo2Data() {
        return selectedCo2Data;
    }

    public void setSelectedCo2Data(Co2Data selectedCo2Data) {
        this.selectedCo2Data = selectedCo2Data;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }
}
