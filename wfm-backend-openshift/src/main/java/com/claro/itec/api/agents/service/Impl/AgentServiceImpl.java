package com.claro.itec.api.agents.service.Impl;

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

    @Autowired
    public AgentServiceImpl(AgentRepository agentRepository, AgentDTOConverter agentConverter, PhoneTypeRepository phoneTypeRepository) {
        this.agentRepository = agentRepository;
        this.agentConverter = agentConverter;
        this.phoneTypeRepository = phoneTypeRepository;
    }


    /**
     * Gets the agent {@link AgentDTO}
     *
     * @return returns the agent {@link AgentDTO}
     */
    @Override
    public List<AgentDTO> getAgents() throws NoDataFoundException {
        List<Agent> list = this.agentRepository.findByStatus("Y");
        if(list.isEmpty()){
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
    public AgentDTO createAgent(final AgentDTO agentDTO) throws InvalidRequestException{
         Agent mapped = new Agent();

         mapped.setAgentNumber(agentDTO.getAgentNumber());
         mapped.setBusinessName(agentDTO.getBusinessName());
         mapped.setName(agentDTO.getName());
         mapped.setSurname(agentDTO.getSurname());
         mapped.setPhoneType(phoneTypeRepository.findByDescription(agentDTO.getPhoneType()).orElseThrow(() -> new InvalidRequestException("Invalid phone_type")));
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
         mapped.setStatus("Y");
         mapped.setFechaAlta(LocalDateTime.now());
         mapped.setFechaModificacion(LocalDateTime.now());
         String nro_caract = agentDTO.getPhoneNumber().toString() + agentDTO.getCharacteristic().toString();
         if(nro_caract.length()>10){
             LOGGER.error("La longitd de la caracteristica y  número debería ser menor a 10");
             throw new InvalidRequestException("La longitd de la caracteristica y  número debería ser menor a 10");

         }
         final Agent result = agentRepository.saveAndFlush(mapped);

         final AgentDTO resultMapped = this.agentConverter.convert(result);
         LOGGER.debug("For Agent  was created", resultMapped);

         return resultMapped;

    }


  /**  public AgentDTO getAgentById(final Long agentId){

        final AgentSTO agentsto = this.agentRepository.findById(agentId).get();
        final AgentDTO agentDTO = agentConverter.convert(agentsto);
        return agentDTO;

    }
**/
    public AgentDTO updateAgent(final AgentDTO agentDTO) throws InvalidRequestException{

        final Agent agentsto = this.agentRepository.findById(agentDTO.getId()).get();

        if(agentDTO.getAgentNumber()!= null) {
            agentsto.setAgentNumber(agentDTO.getAgentNumber());
        }else{
            LOGGER.error("El campo Número de agente no puede ser vacio");
        }
        if(agentDTO.getBusinessName()!= null) {
            agentsto.setBusinessName(agentDTO.getBusinessName());
        }else{
            LOGGER.error("El campo Razón Social no puede ser vacío");
        }
        agentsto.setName(agentDTO.getName());
        agentsto.setSurname(agentDTO.getSurname());
        if(agentDTO.getPhoneType()!=null){
            agentsto.setPhoneType(phoneTypeRepository.findByDescription(agentDTO.getPhoneType()).orElseThrow(() -> new InvalidRequestException("Invalid phone_type")));
        }else{
        LOGGER.error("No existe el tipo de telefono");
        }

        agentsto.setCharacteristic(agentDTO.getCharacteristic());
        agentsto.setPhoneNumber(agentDTO.getPhoneNumber());
         if(agentDTO.getEmail()!= null){
        agentsto.setEmail(agentDTO.getEmail());
        }else{
         LOGGER.error("El campo email no puede ser vacío");
          }
        agentsto.setLocation(agentDTO.getLocation());
        agentsto.setPostalCode(agentDTO.getPostalCode());
        agentsto.setFechaModificacion(LocalDateTime.now());
        final Agent result = agentRepository.saveAndFlush(agentsto);

        final AgentDTO resultMapped = this.agentConverter.convert(result);
        LOGGER.debug("For Agent  was created", resultMapped);

        return resultMapped;
    }


     public AgentDTO updateStatusAgent(final AgentDTO agentDTO, final String enable){
        final Agent agentsto = this.agentRepository.findById(agentDTO.getId()).get();
        agentsto.setStatus(enable);
        agentsto.setFechaModificacion(LocalDateTime.now());
        if(enable.equals("N")){

            agentsto.setFechaBaja(LocalDateTime.now());
        }
        Agent result = new Agent();
        try {
            result = agentRepository.saveAndFlush(agentsto);
        }catch(Exception e){
            LOGGER.error("No se pudo realizar el update status de agente " + e);
        }
        final AgentDTO resultMapped = this.agentConverter.convert(result);

        return resultMapped;
    }

    public AgentDTO deleteAgent(final AgentDTO agentDTO){

        return this.updateStatusAgent(agentDTO,"N");

    }



}
