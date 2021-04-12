package com.gamesys.registration.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

/**
 * Entity to store successful user registration in database.
 */


@EqualsAndHashCode
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "REGISTRATION")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID")
    private Long id;

    @Column(name ="USERNAME")
    private String username;

    @Column(name ="PASSWORD")
    private String password;

    @Column(name ="DOB")
    private LocalDate dob;

    @Column(name ="PAYMENT_CARD_NUMBER")
    private String paymentCardNumber;

}
