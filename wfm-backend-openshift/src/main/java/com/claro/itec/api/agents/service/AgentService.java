package com.claro.itec.api.agents.service;

import com.claro.itec.api.agents.dto.AgentDTO;
import com.claro.itec.api.agents.exception.InvalidRequestException;
import com.claro.itec.api.agents.exception.NoDataFoundException;

import java.util.List;


public interface AgentService {

  public List<AgentDTO> getAgents() throws NoDataFoundException;

  public AgentDTO createAgent(final AgentDTO agentDTO) throws InvalidRequestException;

  public AgentDTO updateAgent(final AgentDTO agentDTO) throws InvalidRequestException;

  public AgentDTO updateStatusAgent(final AgentDTO agentDTO, final String enable);

  public AgentDTO deleteAgent(final AgentDTO agentDTO);
}
