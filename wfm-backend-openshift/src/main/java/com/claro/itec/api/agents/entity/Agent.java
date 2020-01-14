package com.claro.itec.api.agents.entity;

import com.claro.itec.api.agents.util.ApiConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="AGENT")
public class Agent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "agent_Number",length = 7, nullable = false)
    private Integer agentNumber;

    @Column(name = "business_Name",nullable = false, length = 50)
    private String businessName;

    @Column(length = 36)
    private String name;

    @Column(length = 36)
    private String surname;

    @OneToOne
    @JoinColumn(name = "PHONE_TYPE")
    private PhoneType phoneType;

    @Column(length = 5,nullable = false)
    private Integer characteristic;

    @Column(name = "phone_number",length = 10,nullable = false)
    private Integer phoneNumber;

    @Column(length = 60)
    @Pattern(regexp = ApiConstants.REGEXP_EMAIL,message = "El email tiene formato invalido")
    private String email;

    @Column(length = 20)
    private String location;

    @Column(length = 20)
    private String country;

    @Column(length = 20)
    private String province;

    @Column(length = 20)
    private String city;

    @Column(length = 20)
    private String street;

    @Column(name = "ADDRESS_NUMBER")
    private Integer addressNumber;

    @Column(name = "postal_code",length = 20)
    private String postalCode;

    @Column(name="ENABLE")
    private boolean enable;

    @Column(name = "FECHA_ALTA")
    private LocalDateTime fechaAlta;

    @Column(name = "FECHA_MODIFICACION")
    private LocalDateTime fechaModificacion;

    @Column(name = "FECHA_BAJA")
    private LocalDateTime fechaBaja;


}
