package tn.esprit.tp5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tp5.entities.Equipe;

public interface EquipeRepository extends JpaRepository<Equipe, Long> {
}
