package com.counselink.Counselink.repository;

import com.counselink.Counselink.entity.Address;
import com.counselink.Counselink.entity.member.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
// jpa의 모든 데이터 변경은 트랜젝션을 통해서 이루어진다.
// Test 코드에서는 트랜젝션이 롤백(@Rollback(True)된다.(디비에 쿼리가 안 날라간다.)
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void 회원가입() {
        Address address = new Address("city", "street", "zipcode");
        User user = new User("name", "number", "email", "id", "password", address);
        User savedUser = userRepository.save(user);
        Optional<User> byId = userRepository.findById(savedUser.getId());

        if(byId.isPresent()) {
            User findUser = byId.get();
            assertThat(findUser.getId()).isEqualTo(user.getId());
            assertThat(findUser.getUserName()).isEqualTo(user.getUserName());
            assertThat(findUser).isEqualTo(user);
        }
    }
}