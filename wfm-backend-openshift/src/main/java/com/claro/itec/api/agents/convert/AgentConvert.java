package com.claro.itec.api.agents.convert;

import com.claro.itec.api.agents.dto.AgentDTO;
import com.claro.itec.api.agents.dto.PhoneTypeDTO;
import com.claro.itec.api.agents.entity.Agent;
import com.claro.itec.api.agents.entity.PhoneType;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AgentConvert implements Converter<AgentDTO, Agent> {

    @Override
    public Agent convert(AgentDTO value) {
        Agent agent  =  new Agent();
        return agent;
    }

    public Agent convert(AgentDTO agentDTO, PhoneType phoneType) {
        Agent mapped  =  new Agent();
        mapped.setAgentNumber(agentDTO.getAgentNumber());
        mapped.setBusinessName(agentDTO.getBusinessName());
        mapped.setName(agentDTO.getName());
        mapped.setSurname(agentDTO.getSurname());
        mapped.setPhoneType(phoneType);
        mapped.setCharacteristic(agentDTO.getCharacteristic());
        mapped.setPhoneNumber(agentDTO.getPhoneNumber());
        mapped.setCity(agentDTO.getCity());
        mapped.setCountry(agentDTO.getCountry());
        mapped.setEmail(agentDTO.getEmail());
        mapped.setLocation(agentDTO.getLocation());
        mapped.setStreet(agentDTO.getStreet());
        mapped.setAddressNumber(agentDTO.getAddressNumber());
        mapped.setPostalCode(agentDTO.getPostalCode());
        mapped.setProvince(agentDTO.getProvince());
        mapped.setEnable(true);
        mapped.setFechaAlta(LocalDateTime.now());
        mapped.setFechaModificacion(LocalDateTime.now());
        return mapped;
    }

    public Agent convert(AgentDTO agentDTO,Agent agentsto, PhoneType phoneType) {
         agentsto.setAgentNumber(agentDTO.getAgentNumber());

         agentsto.setBusinessName(agentDTO.getBusinessName());
         agentsto.setName(agentDTO.getName());
         agentsto.setSurname(agentDTO.getSurname());
         agentsto.setPhoneType(phoneType);
         agentsto.setCharacteristic(agentDTO.getCharacteristic());
         agentsto.setPhoneNumber(agentDTO.getPhoneNumber());
         agentsto.setEmail(agentDTO.getEmail());
         agentsto.setLocation(agentDTO.getLocation());
         agentsto.setPostalCode(agentDTO.getPostalCode());
         agentsto.setFechaModificacion(LocalDateTime.now());

         return agentsto;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }

}

