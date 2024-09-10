package com.project.likeherotozero.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "co2_data")
public class Co2Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String country;
    private int year;
    private double emissions;

    @ManyToOne
    @JoinColumn(name = "submitted_by")
    private User submittedBy;

    // Constructors
    public Co2Data() {}

    public Co2Data(String country, int year, double emissions, User submittedBy) {
        this.country = country;
        this.year = year;
        this.emissions = emissions;
        this.submittedBy = submittedBy;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getEmissions() {
        return emissions;
    }

    public void setEmissions(double emissions) {
        this.emissions = emissions;
    }

    public User getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(User submittedBy) {
        this.submittedBy = submittedBy;
    }

    // hashCode and equals based on id
    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Co2Data co2Data = (Co2Data) obj;
        return id == co2Data.id;
    }

    // toString method
    @Override
    public String toString() {
        return "Co2Data{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", year=" + year +
                ", emissions=" + emissions +
                ", submittedBy=" + submittedBy +
                '}';
    }
}
