package com.claro.itec.api.agents.dto;

import com.claro.itec.api.agents.util.ApiConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentDTO {

    private Long id;
    @NotNull
    private Integer agentNumber;
    @NotNull
    private String businessName;
    private String name;
    private String surname;
    @NotNull
    private String phoneType;
    @NotNull
    private Integer characteristic;
    @NotNull
    private Integer phoneNumber;
    @NotNull
    @Pattern(regexp = ApiConstants.REGEXP_EMAIL)
    private String email;
    private String location;
    private String country;
    private String province;
    private String city;
    private String street;
    private Integer addressNumber;
    private String postalCode;

}
