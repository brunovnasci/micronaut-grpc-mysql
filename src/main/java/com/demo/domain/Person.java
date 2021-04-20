package com.demo.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private Long id;
    private String nome;
    private String idade;
}
