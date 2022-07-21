package com.counselink.Counselink.service;

import com.counselink.Counselink.entity.Reserve;
import com.counselink.Counselink.entity.UserReview;
import com.counselink.Counselink.entity.member.User;
import com.counselink.Counselink.repository.ReserveRepository;
import com.counselink.Counselink.repository.UserRepository;
import com.counselink.Counselink.repository.UserReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserReviewService {

    private final UserRepository userRepository;
    private final ReserveRepository reserveRepository;
    private final UserReviewRepository userReviewRepository;

    @Transactional
    public long write(Long userId, Long reserveId, String content, Integer stars) {

        User user = userRepository.findById(userId).get();
        Reserve reserve = reserveRepository.findById(reserveId).get();

        UserReview userReview = UserReview.createUserReview(content, stars, reserve);

        userReviewRepository.save(userReview);

        return userReview.getId();
    }
}
