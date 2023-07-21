package it.epicode.entities.impegno.repository;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.entities.impegno.Impegno;
import it.epicode.entities.impegno.StatoImpegno;

public interface ImpegnoRepository extends JpaRepository<Impegno, UUID> {

	Page<Impegno> findByStatoImpegno(Pageable pagina, StatoImpegno statoImpegno);

	Page<Impegno> findByData(Pageable pagina, LocalDate data);
}
