package com.counselink.Counselink.repository.spring_data_jpa;

import com.counselink.Counselink.entity.member.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
