package com.counselink.Counselink.service;

import com.counselink.Counselink.common.exception.ErrorCode;
import com.counselink.Counselink.common.exception.InvalidLoginException;
import com.counselink.Counselink.entity.member.User;
import com.counselink.Counselink.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
// 더티 체킹 등을 안해서 성능 면에서 좋다.(readOnly)
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    // 회원가입
    @Transactional
    public Long join(User user) {

        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    // 디비에 중복회원 검증(동시 회원가입 문제는 해결 안됨)
    private void validateDuplicateUser(User user) {
        if (userRepository.findByUserName(user.getUserName()).get().size() > 0) {
            throw new IllegalStateException("이미 존재하는 회원 입니다.");
        }
    }


    public boolean isSignupValid(String loginId, String loginPassword) {
        Optional<User> users = userRepository.findByLoginId(loginId);

        return users.isPresent();
    }

    public void isLoginValid(User user) throws InvalidLoginException {
        Optional<User> findUser = userRepository.findByLoginId(user.getLoginId());

        log.info(findUser.toString());

        if (!findUser.isPresent()) {
            throw new InvalidLoginException("invalid login", ErrorCode.USER_NOT_FOUND);
        }
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    public User saveUser(User user) {
//        if (isSignupValid(user.getLoginId())) {
//            return userRepository.saveAndFlush(user);
//        } else {
//            return null;
//        }
        return userRepository.saveAndFlush(user);
    }

    @Transactional
    public void update(Long id, String name) {
        User user = userRepository.findById(id).get();
        user.changeUserName(name);
    }
}
