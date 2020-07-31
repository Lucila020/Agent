package com.claro.itec.api.agents.dto;

import com.claro.itec.api.agents.util.ApiConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentDTO {


    private Long id;
    @NotNull(message = "El Número de Agente no puede ser vacío")
    private Integer agentNumber;
    @NotNull
    private String businessName;
    private String name;
    private String lastname;
    private String surname;
    @NotNull
    private String phoneType;
    @NotNull
    private Integer characteristic;
    @NotNull
    private Integer phoneNumber;

   // @Email
    @NotNull(message = "El email es un campo obligatorio")
   // @Pattern(regexp = ApiConstants.REGEXP_EMAIL,message = "El email tiene formato invalido")
    private String email;
    private String location;
    private String country;
    private String province;
    private String city;
    private String street;
    private Integer addressNumber;
    private String postalCode;

}
