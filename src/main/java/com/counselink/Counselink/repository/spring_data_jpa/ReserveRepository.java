package com.counselink.Counselink.repository.spring_data_jpa;

import com.counselink.Counselink.entity.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {
}
