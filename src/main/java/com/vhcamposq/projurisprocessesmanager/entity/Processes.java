package com.vhcamposq.projurisprocessesmanager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade representando um processo.
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Processes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String caseNumber;

    @Column(nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "lawyer_id")
    private Lawyer lawyer;
}