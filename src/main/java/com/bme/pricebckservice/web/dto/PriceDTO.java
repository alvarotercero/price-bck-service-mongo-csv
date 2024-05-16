package com.bme.pricebckservice.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Price
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-24T17:05:00.970072+02:00[Europe/Paris]")

public class PriceDTO {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("value")
  private Float value;

  @JsonProperty("idContract")
  private Long idContract;

  public PriceDTO id(Long id) {
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

  public PriceDTO value(Float value) {
    this.value = value;
    return this;
  }

  /**
   * Get value
   * @return value
  */
  @ApiModelProperty(example = "400.5", required = true, value = "")
  @NotNull


  public Float getValue() {
    return value;
  }

  public void setValue(Float value) {
    this.value = value;
  }

  public PriceDTO idContract(Long idContract) {
    this.idContract = idContract;
    return this;
  }

  /**
   * Get idContract
   * @return idContract
  */
  @ApiModelProperty(example = "10", required = true, value = "")
  @NotNull


  public Long getIdContract() {
    return idContract;
  }

  public void setIdContract(Long idContract) {
    this.idContract = idContract;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PriceDTO price = (PriceDTO) o;
    return Objects.equals(this.id, price.id) &&
        Objects.equals(this.value, price.value) &&
        Objects.equals(this.idContract, price.idContract);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, value, idContract);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Price {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    idContract: ").append(toIndentedString(idContract)).append("\n");
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

