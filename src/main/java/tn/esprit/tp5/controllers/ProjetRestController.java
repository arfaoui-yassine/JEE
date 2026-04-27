package tn.esprit.tp5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tp5.entities.Equipe;
import tn.esprit.tp5.entities.Projet;
import tn.esprit.tp5.entities.ProjetDetail;
import tn.esprit.tp5.services.IProjetService;

import java.util.List;

@RestController
@RequestMapping("/projet")
public class ProjetRestController {

    @Autowired
    IProjetService projetService;

    // ========== 6 Cas ==========

    // Cas 1 — Ajouter Projet + ProjetDetail imbriqué
    @PostMapping("/ajouter-projet-et-projet-detail")
    public Projet ajouterProjetEtProjetDetail(@RequestBody Projet p) {
        return projetService.addProjetAndProjetDetailAndAssign(p);
    }

    // Cas 2 — Affecter ProjetDetail existant à Projet existant
    @PutMapping("/affecter-projet-a-projet-details/{projetId}/{projetDetailId}")
    public void affecterProjetDetailAProjet(@PathVariable Long projetId,
                                            @PathVariable Long projetDetailId) {
        projetService.assignProjetDetailToProjet(projetId, projetDetailId);
    }

    // Cas 3 — Affecter Projet existant à Equipe existante
    @PutMapping("/affecter-projetequipe/{projetId}/{equipeId}")
    public void affecterProjetAEquipe(@PathVariable Long projetId,
                                      @PathVariable Long equipeId) {
        projetService.assignProjetToEquipe(projetId, equipeId);
    }

    // Cas 4 — Ajouter nouveau Projet et affecter ProjetDetail existant
    @PostMapping("/affecter-projet-equipe/{projetDetailId}")
    public Projet ajouterProjetEtAffecterProjetDetail(@RequestBody Projet p,
                                                      @PathVariable Long projetDetailId) {
        return projetService.addProjetAndAssignExistingProjetDetail(p, projetDetailId);
    }

    // Cas 5 — Désaffecter ProjetDetail
    @PutMapping("/desaffecter-projet-detail/{projetDetailId}")
    public void desaffecterProjetDetail(@PathVariable Long projetDetailId) {
        projetService.desaffecterProjetDetail(projetDetailId);
    }

    // Cas 6 — Désaffecter Projet de Equipe
    @PutMapping("/desaffecter-projet-de-equipe/{equipeId}/{projetId}")
    public void desaffecterProjetDeEquipe(@PathVariable Long equipeId,
                                          @PathVariable Long projetId) {
        projetService.desaffecterProjetDeEquipe(equipeId, projetId);
    }

    // ========== Basic CRUD ==========

    @PostMapping("/add")
    public Projet addProjet(@RequestBody Projet p) {
        return projetService.addProjet(p);
    }

    @GetMapping("/all")
    public List<Projet> getAllProjets() {
        return projetService.getAllProjets();
    }

    @PostMapping("/add-detail")
    public ProjetDetail addProjetDetail(@RequestBody ProjetDetail pd) {
        return projetService.addProjetDetail(pd);
    }

    @PostMapping("/add-equipe")
    public Equipe addEquipe(@RequestBody Equipe e) {
        return projetService.addEquipe(e);
    }

    @GetMapping("/all-equipes")
    public List<Equipe> getAllEquipes() {
        return projetService.getAllEquipes();
    }
}
