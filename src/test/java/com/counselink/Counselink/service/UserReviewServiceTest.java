package com.counselink.Counselink.service;

import com.counselink.Counselink.entity.Address;
import com.counselink.Counselink.entity.CounselInformation;
import com.counselink.Counselink.entity.member.Counselor;
import com.counselink.Counselink.entity.member.User;
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
class UserReviewServiceTest {

    @PersistenceContext EntityManager em;
    @Autowired private ReserveService reserveService;
    @Autowired private UserReviewService userReviewService;

    @Test
    public void 리뷰작성() {
        // 유저
        Address address = Address.builder().city("city").street("street").zipcode("zipcode").build();
        User user = new User("name", 1, "number", "email", "id", "password", address);

        em.persist(user);

        // 카운셀러
        Counselor counselor = Counselor.builder().build();
        em.persist(counselor);

        // 예약할 정보
        CounselInformation counselInformation1 = CounselInformation.createCounselInformation(
                "category",  LocalDateTime.of(2022, 7, 15, 14,0), LocalDateTime.of(2022, 7, 15, 14,0), 10000, counselor);

        CounselInformation counselInformation2 = CounselInformation.createCounselInformation(
                "category", LocalDateTime.of(2022, 7, 15, 14,0), LocalDateTime.of(2022, 7, 15, 14,0), 10000, counselor);

        em.persist(counselInformation1);
        em.persist(counselInformation2);

        List<Long> counselInformationList = new ArrayList<>();
        counselInformationList.add(counselInformation1.getId());
        counselInformationList.add(counselInformation2.getId());

        long reserveId = reserveService.reserve(user.getId(), counselInformationList);
        userReviewService.write(user.getId(), reserveId, "상담을 잘해요.", 5);
    }
}