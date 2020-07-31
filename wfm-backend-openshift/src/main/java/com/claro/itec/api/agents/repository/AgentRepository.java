package com.claro.itec.api.agents.repository;

import com.claro.itec.api.agents.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AgentRepository extends JpaRepository<Agent,Long> {

    List<Agent> findByEnable(boolean status);

    List<Agent> findByLastname(String lastname);

}
