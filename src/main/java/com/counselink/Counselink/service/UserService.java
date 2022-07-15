package com.counselink.Counselink.service;

import com.counselink.Counselink.entity.member.User;
import com.counselink.Counselink.repository.UserJpaRepository;
import com.counselink.Counselink.repository.spring_data_jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// 더티 체킹 등을 안해서 성능 면에서 좋다.(readOnly)
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

//    @Autowired
    private final UserJpaRepository userJpaRepository;
    private final UserRepository userRepository;

//    @Autowired
//    public UserService(UserJpaRepository userJpaRepository, UserRepository userRepository) {
//        this.userJpaRepository = userJpaRepository;
//        this.userRepository = userRepository;
//    }

    // 회원가입
    @Transactional
    public Long join(User user) {

        validateDuplicateUser(user);
        userJpaRepository.save(user);
        return user.getId();
    }

    // 디비에 중복회원 검증(동시 회원가입 문제는 해결 안됨)
    private void validateDuplicateUser(User user) {
        if (userJpaRepository.findByName(user.getUserName()).size() > 0) {
            throw new IllegalStateException("이미 존재하는 회원 입니다.");
        }
    }

    public boolean signinValidation(String loginId, String loginPassword) {
        if (userRepository.findByLoginIdAndLoginPassword(loginId, loginPassword).isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    public List<User> findAll() {
        return userJpaRepository.findAll();
    }

    public User findOne(Long id) {
        return userJpaRepository.findOne(id);
    }
}
