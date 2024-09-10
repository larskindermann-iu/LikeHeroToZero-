package com.project.likeherotozero.dao;


import com.project.likeherotozero.entities.User;
import com.project.likeherotozero.utils.JpaUtil;

import jakarta.persistence.EntityManager;
import java.util.List;

public class UserDao {
    public User findByUsername(String username) {
        EntityManager em = JpaUtil.getEntityManager();
        List<User> users = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

    public void save(User user) {
        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }
}
