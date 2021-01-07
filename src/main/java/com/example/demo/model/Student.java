package com.example.demo.model;

import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    @Enumerated(EnumType.ORDINAL)
    private PublicEnumType.SexType sex;

    @Column(name = "id_card_no")
    private String idCardNo;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "residence")
    @Enumerated(EnumType.ORDINAL)
    private PublicEnumType.BoolType residence;

    @Column(name = "last_school")
    private String lastSchool;

    @ManyToOne
    private Teacher teacher;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;
}
