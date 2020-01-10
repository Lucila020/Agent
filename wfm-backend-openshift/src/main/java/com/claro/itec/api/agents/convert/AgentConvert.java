package com.claro.itec.api.agents.convert;

import com.claro.itec.api.agents.dto.AgentDTO;
import com.claro.itec.api.agents.dto.PhoneTypeDTO;
import com.claro.itec.api.agents.entity.Agent;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;

@Component
public class AgentConvert implements Converter<AgentDTO, Agent> {


    @Override
    public Agent convert(AgentDTO value) {
        Agent agent  =  new Agent();
        return agent;
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

