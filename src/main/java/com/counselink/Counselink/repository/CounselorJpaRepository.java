package com.counselink.Counselink.repository;

import com.counselink.Counselink.entity.member.Counselor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class CounselorJpaRepository {

    @PersistenceContext
    private final EntityManager em;

    public Counselor save(Counselor counselor) {
        em.persist(counselor);
        return counselor;
    }

    public Counselor findOne(Long id) {
        return em.find(Counselor.class, id);
    }
}
