package com.project.likeherotozero.utils;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("likeHeroToZeroPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
