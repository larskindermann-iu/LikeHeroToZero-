package com.project.likeherotozero.dao;

import com.project.likeherotozero.entities.Co2Data;
import com.project.likeherotozero.entities.User;
import com.project.likeherotozero.utils.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class Co2DataDao {

    // Fetch all CO2 data for the public homepage
    public List<Co2Data> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Co2Data> query = em.createQuery("SELECT c FROM Co2Data c", Co2Data.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Fetch CO2 data submitted by a specific user (scientist)
    public List<Co2Data> findByUser(User user) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Co2Data> query = em.createQuery("SELECT c FROM Co2Data c WHERE c.submittedBy = :user", Co2Data.class);
            query.setParameter("user", user);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Fetch CO2 data by ID (used for editing)
    public Co2Data findById(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Co2Data.class, id);
        } finally {
            em.close();
        }
    }

    // Save new CO2 data
    public void save(Co2Data co2Data) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(co2Data);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Update existing CO2 data
    public void update(Co2Data co2Data) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(co2Data);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Delete CO2 data
    public void delete(Co2Data co2Data) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Co2Data managedCo2Data = em.find(Co2Data.class, co2Data.getId());
            if (managedCo2Data != null) {
                em.remove(managedCo2Data);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
