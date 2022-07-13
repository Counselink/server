package com.counselink.Counselink.repository.spring_data_jpa;

import com.counselink.Counselink.entity.member.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<User, Long> {
}
