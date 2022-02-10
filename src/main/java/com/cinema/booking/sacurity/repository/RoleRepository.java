package com.cinema.booking.sacurity.repository;

import com.cinema.booking.sacurity.securityEntites.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
