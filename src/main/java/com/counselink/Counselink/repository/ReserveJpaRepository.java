package com.counselink.Counselink.repository;

import com.counselink.Counselink.entity.Reserve;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class ReserveJpaRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Reserve reserve) {
        em.persist(reserve);
    }

    public Reserve findOne(Long id) {
        return em.find(Reserve.class, id);
    }
//    public List<ReservedCounsel> findByUserCounselor(String userName, String counselorName) {
//        return em.createQuery("select r from ReservedCounsel r where r.user.userName = :userName and r.counselInformation.counselor.counselorName =:counselorName").getResultList();
//    }
}
