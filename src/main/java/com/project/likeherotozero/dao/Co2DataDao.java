package com.project.likeherotozero.dao;


import com.project.likeherotozero.entities.Co2Data;
import com.project.likeherotozero.utils.JpaUtil;

import jakarta.persistence.EntityManager;
import java.util.List;

public class Co2DataDao {
    public List<Co2Data> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        return em.createQuery("SELECT c FROM Co2Data c", Co2Data.class).getResultList();
    }

    public void save(Co2Data co2Data) {
        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(co2Data);
        em.getTransaction().commit();
    }

    public void update(Co2Data co2Data) {
        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(co2Data);
        em.getTransaction().commit();
    }

    public void delete(Co2Data co2Data) {
        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(em.contains(co2Data) ? co2Data : em.merge(co2Data));
        em.getTransaction().commit();
    }
}

