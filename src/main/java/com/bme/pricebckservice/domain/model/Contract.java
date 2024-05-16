package com.bme.pricebckservice.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * Contract
 */

@Data
@Entity
@Table(name="contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")

    private Long id;

    @Column(name="name")
    @CsvBindByName
    private String name;

    @Column(name="date")
    @CsvBindByName
    @CsvDate("yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name="id_company")
    @CsvBindByName
    private Long idCompany;


}

