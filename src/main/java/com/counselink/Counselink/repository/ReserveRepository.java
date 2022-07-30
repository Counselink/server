package com.counselink.Counselink.repository;

import com.counselink.Counselink.entity.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {

    @Query("SELECT r from Reserve r WHERE FUNCTION('YEAR', r.reserveDate) = :year AND FUNCTION('MONTH', r.reserveDate) = :month")
    List<Optional<Reserve>> findReservesOnCalendarByYearAndMonth(@Param("year") Integer year, @Param("month") Integer month);
}
