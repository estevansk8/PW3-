package org.example.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name= "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String ra;
    private String email;
    private BigDecimal note1;
    private BigDecimal note2;
    private BigDecimal note3;


    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getNote1() {
        return note1;
    }

    public void setNote1(BigDecimal note1) {
        this.note1 = note1;
    }

    public BigDecimal getNote2() {
        return note2;
    }

    public void setNote2(BigDecimal note2) {
        this.note2 = note2;
    }

    public BigDecimal getNote3() {
        return note3;
    }

    public void setNote3(BigDecimal note3) {
        this.note3 = note3;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ra='" + ra + '\'' +
                ", email='" + email + '\'' +
                ", note1=" + note1 +
                ", note2=" + note2 +
                ", note3=" + note3 +
                '}';
    }
}
