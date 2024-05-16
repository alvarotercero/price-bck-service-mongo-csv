package com.bme.pricebckservice.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Contract
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-24T16:32:32.582306+02:00[Europe/Paris]")

public class ContractDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("idCompany")
    private Long idCompany;

    public ContractDTO id(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     * @return id
     */
    @ApiModelProperty(example = "10", required = true, value = "")
    @NotNull


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContractDTO name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     * @return name
     */
    @ApiModelProperty(example = "BME", required = true, value = "")
    @NotNull


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContractDTO date(LocalDate date) {
        this.date = date;
        return this;
    }

    /**
     * Get date
     * @return date
     */
    @ApiModelProperty(example = "Tue Apr 09 02:00:00 CEST 2024", value = "")

    @Valid

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ContractDTO idCompany(Long idCompany) {
        this.idCompany = idCompany;
        return this;
    }

    /**
     * Get idCompany
     * @return idCompany
     */
    @ApiModelProperty(example = "10", required = true, value = "")
    @NotNull


    public Long getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Long idCompany) {
        this.idCompany = idCompany;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ContractDTO contract = (ContractDTO) o;
        return Objects.equals(this.id, contract.id) &&
                Objects.equals(this.name, contract.name) &&
                Objects.equals(this.date, contract.date) &&
                Objects.equals(this.idCompany, contract.idCompany);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date, idCompany);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Contract {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    date: ").append(toIndentedString(date)).append("\n");
        sb.append("    idCompany: ").append(toIndentedString(idCompany)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}


