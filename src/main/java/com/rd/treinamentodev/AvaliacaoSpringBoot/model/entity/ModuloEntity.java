package com.rd.treinamentodev.AvaliacaoSpringBoot.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "TB_MODULO")
public class ModuloEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idModulo;

    @Column(name = "ds_nome")
    private String descNome;

    @OneToOne
    @JoinColumn(name = "id_instrutor")
    private InstrutorEntity instrutor;
}
