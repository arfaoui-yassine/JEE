package tn.esprit.tp5.services;

import tn.esprit.tp5.entities.Equipe;
import tn.esprit.tp5.entities.Projet;
import tn.esprit.tp5.entities.ProjetDetail;

import java.util.List;

public interface IProjetService {

    // Affectation / Désaffectation
    Projet addProjetAndProjetDetailAndAssign(Projet p);
    void assignProjetDetailToProjet(Long projetId, Long projetDetailId);
    void assignProjetToEquipe(Long projetId, Long equipeId);
    Projet addProjetAndAssignExistingProjetDetail(Projet p, Long projetDetailId);
    void desaffecterProjetDetail(Long projetDetailId);
    void desaffecterProjetDeEquipe(Long equipeId, Long projetId);

    // Basic CRUD
    Projet addProjet(Projet p);
    List<Projet> getAllProjets();
    ProjetDetail addProjetDetail(ProjetDetail pd);
    Equipe addEquipe(Equipe e);
    List<Equipe> getAllEquipes();
}
