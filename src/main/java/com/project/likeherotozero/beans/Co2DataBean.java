package com.project.likeherotozero.beans;

import com.project.likeherotozero.dao.Co2DataDao;
import com.project.likeherotozero.entities.Co2Data;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class Co2DataBean {
    private Co2Data newCo2Data = new Co2Data();
    private Co2Data selectedCo2Data = new Co2Data(); // For editing

    public Co2DataBean() {}

    public List<Co2Data> getCo2DataList() {
        Co2DataDao dao = new Co2DataDao();
        return dao.findAll();
    }

    public String addCo2Data() {
        Co2DataDao dao = new Co2DataDao();
        dao.save(newCo2Data);
        newCo2Data = new Co2Data(); // Reset the form
        return "dashboard?faces-redirect=true";
    }

    public String editCo2Data(Co2Data data) {
        this.selectedCo2Data = data; // Store the selected data
        return "edit_data?faces-redirect=true&id=" + data.getId(); // Redirect to edit form
    }

    public void deleteCo2Data(Co2Data data) {
        Co2DataDao dao = new Co2DataDao();
        dao.delete(data);
    }

    // New update method
    public String updateCo2Data() {
        Co2DataDao dao = new Co2DataDao();
        dao.update(selectedCo2Data); // Update the selected CO2 data
        return "dashboard?faces-redirect=true"; // Redirect after updating
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
}

