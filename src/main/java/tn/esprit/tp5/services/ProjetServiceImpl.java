package tn.esprit.tp5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tp5.entities.Equipe;
import tn.esprit.tp5.entities.Projet;
import tn.esprit.tp5.entities.ProjetDetail;
import tn.esprit.tp5.repositories.EquipeRepository;
import tn.esprit.tp5.repositories.ProjetDetailRepository;
import tn.esprit.tp5.repositories.ProjetRepository;

import java.util.List;

@Service
public class ProjetServiceImpl implements IProjetService {

    @Autowired
    ProjetRepository projetRepository;

    @Autowired
    ProjetDetailRepository projetDetailRepository;

    @Autowired
    EquipeRepository equipeRepository;

    // Cas 1 — Ajouter Projet + ProjetDetail imbriqué (cascade)
    @Override
    public Projet addProjetAndProjetDetailAndAssign(Projet p) {
        return projetRepository.save(p);
    }

    // Cas 2 — Affecter un ProjetDetail existant à un Projet existant
    @Override
    public void assignProjetDetailToProjet(Long projetId, Long projetDetailId) {
        Projet projet = projetRepository.findById(projetId).orElse(null);
        ProjetDetail projetDetail = projetDetailRepository.findById(projetDetailId).orElse(null);
        if (projet != null && projetDetail != null) {
            projet.setProjetDetail(projetDetail);
            projetRepository.save(projet);
        }
    }

    // Cas 3 — Affecter un Projet existant à une Equipe existante
    @Override
    public void assignProjetToEquipe(Long projetId, Long equipeId) {
        Projet projet = projetRepository.findById(projetId).orElse(null);
        Equipe equipe = equipeRepository.findById(equipeId).orElse(null);
        if (projet != null && equipe != null) {
            projet.getEquipes().add(equipe);
            projetRepository.save(projet);
        }
    }

    // Cas 4 — Ajouter un nouveau Projet et lui affecter un ProjetDetail existant
    @Override
    public Projet addProjetAndAssignExistingProjetDetail(Projet p, Long projetDetailId) {
        ProjetDetail projetDetail = projetDetailRepository.findById(projetDetailId).orElse(null);
        if (projetDetail != null) {
            p.setProjetDetail(projetDetail);
        }
        return projetRepository.save(p);
    }

    // Cas 5 — Désaffecter un ProjetDetail de son Projet
    @Override
    public void desaffecterProjetDetail(Long projetDetailId) {
        ProjetDetail projetDetail = projetDetailRepository.findById(projetDetailId).orElse(null);
        if (projetDetail != null && projetDetail.getProjet() != null) {
            Projet projet = projetDetail.getProjet();
            projet.setProjetDetail(null);
            projetRepository.save(projet);
        }
    }

    // Cas 6 — Désaffecter un Projet d'une Equipe
    @Override
    public void desaffecterProjetDeEquipe(Long equipeId, Long projetId) {
        Projet projet = projetRepository.findById(projetId).orElse(null);
        if (projet != null) {
            projet.getEquipes().removeIf(e -> e.getId().equals(equipeId));
            projetRepository.save(projet);
        }
    }

    // Basic CRUD

    @Override
    public Projet addProjet(Projet p) {
        return projetRepository.save(p);
    }

    @Override
    public List<Projet> getAllProjets() {
        return projetRepository.findAll();
    }

    @Override
    public ProjetDetail addProjetDetail(ProjetDetail pd) {
        return projetDetailRepository.save(pd);
    }

    @Override
    public Equipe addEquipe(Equipe e) {
        return equipeRepository.save(e);
    }

    @Override
    public List<Equipe> getAllEquipes() {
        return equipeRepository.findAll();
    }
}
