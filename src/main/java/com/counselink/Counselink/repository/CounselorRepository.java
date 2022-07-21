package com.counselink.Counselink.repository;

import com.counselink.Counselink.entity.member.Counselor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CounselorRepository extends JpaRepository<Counselor, Long> {
    Optional<Counselor> findByLoginId(String loginId);
}
