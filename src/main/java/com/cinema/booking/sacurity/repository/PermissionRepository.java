package com.cinema.booking.sacurity.repository;

import com.cinema.booking.sacurity.securityEntites.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,Long> {
}
