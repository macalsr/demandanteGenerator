package com.nexty.demandante.management;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table( name = "application")
public class Application extends com.next.demandanteGenerator.model.Auditable {

    public static final Integer NEXTY = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String url;
}
