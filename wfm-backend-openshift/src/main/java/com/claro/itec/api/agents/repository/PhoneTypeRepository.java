package com.claro.itec.api.agents.repository;


import com.claro.itec.api.agents.entity.PhoneType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PhoneTypeRepository extends JpaRepository<PhoneType,Long> {

    Optional<PhoneType> findByDescription(String description);

}
