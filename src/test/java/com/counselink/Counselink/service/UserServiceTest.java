package com.counselink.Counselink.service;

import com.counselink.Counselink.entity.Address;
import com.counselink.Counselink.entity.member.User;
import com.counselink.Counselink.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
class UserServiceTest {

    @Autowired UserService clientService;
    @Autowired UserRepository userRepository;

    @Test
    public void 회원가입() {
        Address address = new Address("city", "street", "zipcode");
        User user1 = new User("name1", "number", "email", "id", "password", address);
        User user2= new User("name1", "number", "email", "id", "password", address);
        clientService.join(user1);
        clientService.join(user2);
    }
}