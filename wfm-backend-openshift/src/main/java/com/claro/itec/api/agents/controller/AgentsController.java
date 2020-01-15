package com.claro.itec.api.agents.controller;

import com.claro.itec.api.agents.dto.AgentDTO;
import com.claro.itec.api.agents.exception.InvalidRequestException;
import com.claro.itec.api.agents.exception.NoDataFoundException;
import com.claro.itec.api.agents.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@Validated
@RequestMapping(value = "/itec/agent")
public class AgentsController {


    private AgentService agentService;

    @Autowired
    public AgentsController(AgentService agentService) {
        this.agentService = agentService;
    }

    /**
     * Gets agentDTO { @link AgenteDTO }.
     *
     * @return returns List AgentDTO { @link AgenteDTO }.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AgentDTO>> retrieveListAgents() throws NoDataFoundException {
        return new ResponseEntity<>(this.agentService.retrieveListAgents(), HttpStatus.OK);
    }


    /**
     * Creates a agent { @link AgentDTO }.
     *
     * @param   {@link AgentDTO} to be created
     * @return returns the created agent {@link AgentDTO}
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AgentDTO> createAgent(@Valid @RequestBody final AgentDTO agent) throws InvalidRequestException {
        return new ResponseEntity<>(this.agentService.createAgent(agent), HttpStatus.OK);
    }


    /**
     * Updates the agentDTO { @link ContactDTO }.
     *
     * @param agentDTO {@link AgentDTO} to be updated
     * @return returns the {@link AgentDTO} updated
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<AgentDTO> updateAgent(@Valid @RequestBody final AgentDTO agentDTO) throws InvalidRequestException{
        return new ResponseEntity<>(this.agentService.updateAgent(agentDTO), HttpStatus.OK);
    }

    /**
     * Disable the agent
     *
     * @param agent that are going to be disabled
     * @return returns the Ids of the reason that have been disabled
     */
   @PutMapping(value = "/disable/{agentId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AgentDTO> disableAgent(final @PathVariable("agentId") Long agentId) {
       final AgentDTO resultMapped = this.agentService.deleteAgent(agentId);
        return new ResponseEntity<>(resultMapped, HttpStatus.OK);
    }


}

