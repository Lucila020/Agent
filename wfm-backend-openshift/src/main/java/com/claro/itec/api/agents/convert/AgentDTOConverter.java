package com.claro.itec.api.agents.convert;

import com.claro.itec.api.agents.dto.AgentDTO;
import com.claro.itec.api.agents.entity.Agent;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class AgentDTOConverter implements Converter<Agent, AgentDTO> {

    @Override
    public AgentDTO convert(final Agent item) {
        AgentDTO agentDTO =  new AgentDTO(item.getId(),item.getAgentNumber(),
                item.getBusinessName(), item.getName(), item.getSurname(),
                item.getPhoneType().getDescription(),
                item.getCharacteristic(),
                item.getPhoneNumber(), item.getEmail(), item.getLocation(), item.getCountry(), item.getProvince(), item.getCity(),
                item.getStreet(), item.getAddressNumber(), item.getPostalCode());
        return agentDTO;
    }






}
