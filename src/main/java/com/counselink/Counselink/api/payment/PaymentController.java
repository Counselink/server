package com.counselink.Counselink.api.payment;

import com.counselink.Counselink.api.register.RegisterController;
import com.counselink.Counselink.entity.Address;
import com.counselink.Counselink.entity.member.User;
import com.counselink.Counselink.service.PaymentService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping(value = "/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private PaymentService paymentService;

    @GetMapping(path = "/history")
    public ResponseEntity getPaymentHistory(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate, @RequestParam("id") Long id) {
        paymentService.findHistory(id, startDate, endDate);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
