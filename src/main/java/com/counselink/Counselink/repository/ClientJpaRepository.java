package com.counselink.Counselink.repository;

import com.counselink.Counselink.entity.member.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClientJpaRepository {

    private final EntityManager em;

    public User save(User user) {
        em.persist(user);
        return user;
    }

    public User findOne(Long id) {
        return em.find(User.class, id);
    }

    public List<User> findAll() {
        return em.createQuery("select c from User c", User.class).getResultList();
    }

    public List<User> findByName(String name) {
        return em.createQuery("select c from User c where c.clientName =: name", User.class)
                .setParameter("name", name)
                .getResultList();
    }
}
