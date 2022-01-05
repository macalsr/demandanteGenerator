package com.next.demandanteGenerator.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table(schema = "application", name = "application")
public class Application extends Auditable {

    public static final Integer NEXTY = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String url;
}