package com.demo.gateway.mysql.model;

import javax.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "person")
public class PersonDatabase {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String idade;
}
