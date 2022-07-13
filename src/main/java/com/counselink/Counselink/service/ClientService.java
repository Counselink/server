package com.counselink.Counselink.service;

import com.counselink.Counselink.entity.member.User;
import com.counselink.Counselink.repository.ClientJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
// 더티 체킹 등을 안해서 성능 면에서 좋다.(readOnly)
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ClientService {

    private final ClientJpaRepository clientJpaRepository;

    // 회원가입
    @Transactional
    public Long join(User user) {
        clientJpaRepository.save(user);
        return user.getId();
    }

    public List<User> findAll() {
        return clientJpaRepository.findAll();
    }

    public User findOne(Long id) {
        return clientJpaRepository.findOne(id);
    }
}
