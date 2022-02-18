package com.gent00.ms.hache.dto;


import javax.persistence.*;

@Entity
@Table
public class XMLInput {

    @Column
    String xml;

    @Column
    String digest;

    @Id
    @GeneratedValue
    Long id;

    public XMLInput() {
    }

    public XMLInput(String xml, String digest) {
        this.xml = xml;
        this.digest = digest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }
}
