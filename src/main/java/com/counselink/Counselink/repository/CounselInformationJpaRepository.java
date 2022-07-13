package com.counselink.Counselink.repository;

import com.counselink.Counselink.entity.CounselInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CounselInformationJpaRepository {

    private final EntityManager em;

    public void save(CounselInformation counselInformation) {
        if(counselInformation.getId() == null) {
            em.persist(counselInformation);
        }
        else {
            em.merge(counselInformation);
        }
    }

    public CounselInformation findOne(Long id) {
        return em.find(CounselInformation.class, id);
    }
}
