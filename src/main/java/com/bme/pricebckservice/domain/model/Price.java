package com.bme.pricebckservice.domain.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

/**
 * Price
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-24T17:05:00.970072+02:00[Europe/Paris]")
@Data
@Entity
@Table(name="prices")
public class Price   {
  @Id
  @JsonProperty("id")
  @CsvBindByName
  private Long id;

  @JsonProperty("value")
  @CsvBindByName
  private Double value;

  // @Column(name="idcontract")
  @JsonProperty("idContract")
  @CsvBindByName
  private Long idContract;

  public Price id(Long id) {
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

  public Price value(Double value) {
    this.value = value;
    return this;
  }

  /**
   * Get value
   * @return value
  */
  @ApiModelProperty(example = "400.5", required = true, value = "")
  @NotNull


  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public Price idContract(Long idContract) {
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
    Price price = (Price) o;
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

  public Price(Long id, Double value, Long idContract) {
    this.id = id;
    this.value = value;
    this.idContract = idContract;
  }

  public Price() {
  }
}

