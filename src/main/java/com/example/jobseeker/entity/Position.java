package com.example.jobseeker.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Position {

    @Id
    @SequenceGenerator(name = "position_seq", sequenceName = "position_seq")
    @GeneratedValue(generator = "position_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "job_title")
    private String jobTitle ;

    @Column(name = "location")
    private String location;
}
