package com.capstone.scms.repository;

import com.capstone.scms.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    interface SumOfTotalPriceInterface {
        Double getTotalPriceOfAll();

        LocalDate getCreatedDate();

        String getStatus();
    }

    @Query("SELECT SUM(o.totalPrice) as totalPriceOfAll, o.createdDate, o.status as status FROM Order o WHERE o.status = 'completed' GROUP BY o.createdDate")
    List<SumOfTotalPriceInterface> getTotalPriceByDate();

}
