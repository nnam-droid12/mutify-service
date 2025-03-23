package com.mutify.mutify.business.repository;


import com.mutify.mutify.business.entities.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessRepository extends JpaRepository<Business, String> {
    Optional<Business> findByEmail(String email);
}

