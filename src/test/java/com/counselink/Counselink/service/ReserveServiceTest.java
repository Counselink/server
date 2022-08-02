package com.counselink.Counselink.service;

import com.counselink.Counselink.entity.Address;
import com.counselink.Counselink.entity.CounselInformation;
import com.counselink.Counselink.entity.member.Counselor;
import com.counselink.Counselink.entity.member.User;
import com.counselink.Counselink.repository.ReserveRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)
class ReserveServiceTest {

    @PersistenceContext EntityManager em;
    @Autowired ReserveService reserveService;
    @Autowired
    ReserveRepository reserveRepository;

    @Test
    public void 예약하기() {

        Address address = Address.builder()
                .city("city")
                .build();

        User user = User.builder()
                .userName("user1")
                .address(address)
                .build();

        em.persist(user);

        Counselor counselor = Counselor.builder().build();

        em.persist(counselor);

        CounselInformation counselInformation1 = CounselInformation.createCounselInformation(
                "category",  LocalDateTime.of(2022, 7, 15, 14,0), LocalDateTime.of(2022, 7, 15, 14,0), 10000, counselor);

        CounselInformation counselInformation2 = CounselInformation.createCounselInformation(
                "category", LocalDateTime.of(2022, 7, 15, 14,0), LocalDateTime.of(2022, 7, 15, 14,30), 10000, counselor);

        em.persist(counselInformation1);
        em.persist(counselInformation2);

        List<Long> counselInformationList = new ArrayList<>();
        counselInformationList.add(counselInformation1.getId());
        counselInformationList.add(counselInformation2.getId());

        reserveService.reserve(user.getId(), counselInformationList);
    }
}