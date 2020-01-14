package com.claro.itec.api.agents.controller;


import com.claro.itec.api.agents.dto.PhoneTypeDTO;

import com.claro.itec.api.agents.service.PhoneTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
@RequestMapping(value = "/itec/phoneType")
public class PhoneTypeController {


    private PhoneTypeService phoneTypeService;

    @Autowired
    public PhoneTypeController(PhoneTypeService phoneTypeService) {
        this.phoneTypeService = phoneTypeService;
    }

    /**
     * @param
     * @return List<PhoneTypeDTO>
     */
   @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PhoneTypeDTO>> retrieveListPhoneTypes() {
        return new ResponseEntity<>(this.phoneTypeService.retrieveListPhoneTypes(), HttpStatus.OK);
    }

}
