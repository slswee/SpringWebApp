package com.example.restservice.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import java.util.UUID;

@Entity
@Table(name = "CustomerCredit")
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class CustomerCreditInfo {
  @Id
  @Column(name="uuid")
  private final UUID uuid = UUID.randomUUID();
  @Column(name="name")
  private String name;
  @Column(name="ssn")
  private int ssn;
  @Type(type = "list-array")
  @Column(name = "tags", columnDefinition = "integer[]")
  private List<Integer> tags;

    public CustomerCreditInfo (String name, int ssn, List<Integer> tags) {
      this.name = name;
      this.ssn = ssn;
      this.tags = tags;
  }
    public CustomerCreditInfo(){}

    public String getName() { return name;}
    public void setName(String name) { this.name = name; }
    public int getSSN() { return ssn; }
    public void setSSN(int ssn) { this.ssn = ssn;}
    public List<Integer> getTags(){return this.tags;}
    public void setTags(List<Integer> tags) {this.tags = tags;}
    public UUID getUuid() { return uuid; }
}

