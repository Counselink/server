package com.counselink.Counselink.repository.counselor;

import com.counselink.Counselink.entity.member.Counselor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CounselorRepository extends JpaRepository<Counselor, Long> {

    Optional<Counselor> findByEmailAndLoginPassword(String loginId, String loginPassword);

    Optional<Counselor> findByLoginId(String loginId);

    @Query("select new com.counselink.Counselink.repository.counselor.HomePageCounselorDto(c.profileImageUrl, c.career) from Counselor c")
    List<HomePageCounselorDto> findCounselorsWithImageUrl();
}
