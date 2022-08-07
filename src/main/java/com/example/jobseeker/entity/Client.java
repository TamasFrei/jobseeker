package com.example.jobseeker.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Client {

    @Id
    @SequenceGenerator(name = "client_seq", sequenceName = "client_seq")
    @GeneratedValue(generator = "client_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "api_key")
    private String apiKey;
}
