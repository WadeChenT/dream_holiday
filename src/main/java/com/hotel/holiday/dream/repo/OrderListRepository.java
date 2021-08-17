package com.hotel.holiday.dream.repo;

import com.hotel.holiday.dream.entity.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderListRepository extends JpaRepository<OrderList, String>, JpaSpecificationExecutor<OrderList> {

}
