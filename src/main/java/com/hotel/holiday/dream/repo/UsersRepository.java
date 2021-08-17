package com.hotel.holiday.dream.repo;

import com.hotel.holiday.dream.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UsersRepository extends JpaRepository<Users, String>, JpaSpecificationExecutor<Users> {

}