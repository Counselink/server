package com.counselink.Counselink.repository.spring_data_jpa;

import com.counselink.Counselink.entity.member.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // signin 호출 시, loginId, loginPasswd 를 비교할 때 사용함.
    Optional<User> findByEmailAndLoginPassword(User user);
    Optional<User> findByLoginId(String loginId);
}
