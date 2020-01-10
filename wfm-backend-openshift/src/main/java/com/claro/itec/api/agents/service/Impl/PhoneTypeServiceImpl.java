package com.claro.itec.api.agents.service.Impl;

import com.claro.itec.api.agents.convert.PhoneTypeConverter;
import com.claro.itec.api.agents.dto.PhoneTypeDTO;
import com.claro.itec.api.agents.repository.PhoneTypeRepository;
import com.claro.itec.api.agents.service.PhoneTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneTypeServiceImpl implements PhoneTypeService {

    private PhoneTypeRepository phoneTypeRepository;
    private PhoneTypeConverter phoneTypeConverter;

    @Autowired
    public PhoneTypeServiceImpl(PhoneTypeRepository phoneTypeRepository, PhoneTypeConverter phoneTypeConverter) {
        this.phoneTypeRepository = phoneTypeRepository;
        this.phoneTypeConverter = phoneTypeConverter;
    }

    /**
     * Gets the phone types {@link PhoneTypeDTO}
     *
     * @return returns the phone types {@link PhoneTypeDTO}
     */
    @Override
    public List<PhoneTypeDTO> getPhoneTypes() {
        final List<PhoneTypeDTO> list =this.phoneTypeRepository.findAll().stream().map(item -> this.phoneTypeConverter.convert(item)).collect(Collectors.toList());

        return list;
    }


}
