package com.counselink.Counselink.service;

import com.counselink.Counselink.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private PaymentRepository paymentRepository;

    public void findHistory(Long id, Date startDate, Date endDate) {

    }
}
