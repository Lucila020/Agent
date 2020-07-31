package com.claro.itec.api.agents.service.Impl;

import com.claro.itec.api.agents.convert.AgentConvert;
import com.claro.itec.api.agents.convert.AgentDTOConverter;
import com.claro.itec.api.agents.dto.AgentDTO;
import com.claro.itec.api.agents.exception.InvalidRequestException;
import com.claro.itec.api.agents.exception.NoDataFoundException;
import com.claro.itec.api.agents.entity.Agent;
import com.claro.itec.api.agents.repository.AgentRepository;
import com.claro.itec.api.agents.repository.PhoneTypeRepository;
import com.claro.itec.api.agents.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AgentServiceImpl implements AgentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AgentService.class);

    private AgentRepository agentRepository;
    private AgentDTOConverter agentConverter;
    private PhoneTypeRepository phoneTypeRepository;
    private AgentConvert agentConvert;


    @Autowired
    public AgentServiceImpl(AgentRepository agentRepository, AgentDTOConverter agentConverter, PhoneTypeRepository phoneTypeRepository, AgentConvert agentConvert) {
        this.agentRepository = agentRepository;
        this.agentConverter = agentConverter;
        this.phoneTypeRepository = phoneTypeRepository;
        this.agentConvert = agentConvert;

    }

    /**
     * Gets the agent {@link AgentDTO}
     *
     * @return returns the agent {@link AgentDTO}
     */
    @Override
    public List<AgentDTO> retrieveListAgents() throws NoDataFoundException {
        List<Agent> list = this.agentRepository.findByEnable(true);
        if (list.isEmpty()) {
            throw new NoDataFoundException("No agents founds");
        }
        return list.stream().map(item -> this.agentConverter.convert(item)).collect(Collectors.toList());

    }


    /**
     * Creates the {@link AgentDTO}.
     *
     * @param agentDTO the {@link AgentDTO} to be created
     * @return returns the created {@link AgentDTO}
     */
    @Override
    public AgentDTO createAgent(final AgentDTO agentDTO) throws InvalidRequestException {

        Agent mapped = new Agent();
        this.verifyValidateRequestData(agentDTO);

        if (phoneTypeRepository.findByDescription(agentDTO.getPhoneType()).orElseThrow(() -> new InvalidRequestException("Invalid phone_type")) != null) {

            mapped = agentConvert.convert(agentDTO, phoneTypeRepository.findByDescription(agentDTO.getPhoneType()).get());
        }
        final Agent result = agentRepository.saveAndFlush(mapped);

        final AgentDTO resultMapped = this.agentConverter.convert(result);
        LOGGER.debug("For Agent  was created", resultMapped);

        return resultMapped;

    }

    public AgentDTO updateAgent(final AgentDTO agentDTO) throws InvalidRequestException {

        Agent agentSto = this.agentRepository.findById(agentDTO.getId()).get();

        this.verifyValidateRequestData(agentDTO);

        if (phoneTypeRepository.findByDescription(agentDTO.getPhoneType()).orElseThrow(() -> new InvalidRequestException("Invalid phone_type")) != null) {

            agentSto = agentConvert.convert(agentDTO, agentSto, phoneTypeRepository.findByDescription(agentDTO.getPhoneType()).get());
        }
        final Agent result = agentRepository.saveAndFlush(agentSto);

        final AgentDTO resultMapped = this.agentConverter.convert(result);
        LOGGER.debug("For Agent  was created", resultMapped);

        return resultMapped;
    }

    public AgentDTO deleteOtro(final Long agentID) {
        final Agent agentSto = this.agentRepository.findById(agentID).get();
        return this.updateStatusAgent(agentSto, false);

    }

    public AgentDTO updateStatusAgent(final Agent agentSto, final Boolean enable) {

        agentSto.setEnable(enable);
        agentSto.setFechaModificacion(LocalDateTime.now());
        if (!enable) {

            agentSto.setFechaBaja(LocalDateTime.now());
        }
        Agent result = new Agent();
        try {
            result = agentRepository.saveAndFlush(agentSto);
        } catch (Exception e) {
            LOGGER.error("No se pudo realizar el update status de agente " + e);
        }
        final AgentDTO resultMapped = this.agentConverter.convert(result);

        return resultMapped;
    }

    public AgentDTO deleteAgent(final Long agentID) {
        final Agent agentSto = this.agentRepository.findById(agentID).get();
        return this.updateStatusAgent(agentSto, false);

    }

    private void verifyValidateRequestData(final AgentDTO agentDTO) throws  InvalidRequestException{

        if (agentDTO.getAgentNumber() == 0) {
            LOGGER.error("El Número de Agente no puede ser vacío");
            throw new InvalidRequestException("El Número de Agente no puede ser vacío");
        }
        if (agentDTO.getBusinessName().isEmpty()) {
            LOGGER.error("La Razón Social no puede ser vacia");
            throw new InvalidRequestException("La Razón Social no puede ser vacia");
        }
        if (!agentDTO.getBusinessName().matches("^((?![\\^!@#$*~ <>?]).)((?![\\^!@#$*~<>?]).){0,73}((?![\\^!@#$*~ <>?]).)")) {
            LOGGER.error("La Razón Social  tiene formato invalido");
            throw new InvalidRequestException("La Razón Social tiene formato invalido");
        }

        if(agentDTO.getName() != null){
            if(!agentDTO.getName().matches("[A-Z][a-zA-Z][^#&<>\"~;$^%{}?]{1,20}$")){
                LOGGER.error("El nombre tiene formato invalido" );
                throw new InvalidRequestException("El nombre tiene formato invalido");
            }
        }

        if(agentDTO.getSurname() != null){
            String rex = "^[^\\d!¡@#$`°%^&~*(),;.¬¿?´\":{}/|<>+_\"¨\\\\=-]*$";
            if(!agentDTO.getSurname().matches(rex)){
                LOGGER.error("El apellido tiene formato invalido " + agentDTO.getSurname());
                throw new InvalidRequestException("El apellido tiene formato invalido");
           }
        }

        if (agentDTO.getPhoneNumber().toString() != null && agentDTO.getCharacteristic().toString() != null) {
          if ((agentDTO.getPhoneNumber().toString() + agentDTO.getCharacteristic().toString()).length() > 10) {
              LOGGER.error("La longitd de la caracteristica y  número debería ser menor a 10");
              throw new InvalidRequestException("La longitd de la caracteristica y  número debería ser menor a 10");
        }
        }
        if(!agentDTO.getEmail().matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9][a-zA-Z0-9._-]+\\.)+[a-zA-Z]{2,6}$")){
            LOGGER.error("El email tiene formato invalido");
            throw new InvalidRequestException("El email tiene formato invalido");
        }

    }

    public AgentDTO deleteAgentPrueba(final Long agentID) {
        final Agent agentSto = this.agentRepository.findById(agentID).get();
        return this.updateStatusAgent(agentSto, false);

    }

    public AgentDTO deleteAgentLogico(final Long agentID) {
        final Agent agentSto = this.agentRepository.findById(agentID).get();
        return this.updateStatusAgent(agentSto, false);

    }


}
