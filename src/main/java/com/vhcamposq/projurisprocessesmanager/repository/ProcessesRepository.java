package com.vhcamposq.projurisprocessesmanager.repository;

import com.vhcamposq.projurisprocessesmanager.entity.Processes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessesRepository extends JpaRepository<Processes, Long> {
}
