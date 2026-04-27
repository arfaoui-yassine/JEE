package tn.esprit.tp5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tp5.entities.Projet;

public interface ProjetRepository extends JpaRepository<Projet, Long> {
}
