package com.example.HeroesCRUD.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="Hero")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String superPower;
}
