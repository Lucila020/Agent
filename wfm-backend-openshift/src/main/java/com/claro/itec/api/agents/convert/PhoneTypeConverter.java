package com.claro.itec.api.agents.convert;

import com.claro.itec.api.agents.dto.PhoneTypeDTO;
import com.claro.itec.api.agents.entity.PhoneType;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class PhoneTypeConverter implements Converter<PhoneType, PhoneTypeDTO>{


    /**
     * @param item
     *
     */
    @Override
    public PhoneTypeDTO convert(final PhoneType item) {
        return new PhoneTypeDTO(item.getDescription());

    }


}
