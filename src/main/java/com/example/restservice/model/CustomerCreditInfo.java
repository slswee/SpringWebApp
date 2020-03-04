package com.example.restservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
// import org.hibernate.annotations.Type;

// import java.util.UUID;

@Entity
@Table(name = "CustomerCredit")
public class CustomerCreditInfo {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  // @Column(name="uuid")
  // private final UUID uuid = UUID.randomUUID();
  @Column(name="name")
  private String name;
  @Column(name="ssn")
  private int ssn;
  // @Type(type = "com.example.mytastyserver.util.GenericArrayUserType")
  // @Column(
  //   name = "tags",
  //   columnDefinition = "int[]"
  // )
  // private int[] tags;

    // public CustomerCreditInfo (String name, int ssn, int[] tags) {
    //     this.name = name;
    //     this.ssn = ssn;
    //     this.tags = tags;
    // }


    public CustomerCreditInfo (String name, int ssn) {
      this.name = name;
      this.ssn = ssn;
  }

    public long getId(){ return id; }
    public void setId(long id) { this.id = id;}
    public String getName() { return name;}
    public void setName(String name) { this.name = name; }
    public int getSSN() { return ssn; }
    public void setSSN(int ssn) { this.ssn = ssn;}
    // public int[] getTags(){return this.tags;}
    // public void setTags(int[] tags) {this.tags = tags;}
}

