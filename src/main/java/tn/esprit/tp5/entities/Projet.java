package tn.esprit.tp5.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_projet")
public class Projet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sujet;

    // Owning side – Projet owns the FK to ProjetDetail
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "projet_detail_pd_id")
    private ProjetDetail projetDetail;

    // Owning side – Projet owns the join table
    @ManyToMany
    @JoinTable(
        name = "projet_equipe",
        joinColumns = @JoinColumn(name = "projet_id"),
        inverseJoinColumns = @JoinColumn(name = "equipe_id")
    )
    private List<Equipe> equipes = new ArrayList<>();

    public Projet() {
    }

    public Projet(String sujet) {
        this.sujet = sujet;
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public ProjetDetail getProjetDetail() {
        return projetDetail;
    }

    public void setProjetDetail(ProjetDetail projetDetail) {
        this.projetDetail = projetDetail;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = equipes;
    }
}
