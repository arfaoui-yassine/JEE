package tn.esprit.tp5.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_projet_detail")
public class ProjetDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String technologie;

    private Long cout;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    // Inverse side – Projet owns the FK
    @OneToOne(mappedBy = "projetDetail")
    private Projet projet;

    public ProjetDetail() {
    }

    public ProjetDetail(String description, String technologie, Long cout, Date dateDebut) {
        this.description = description;
        this.technologie = technologie;
        this.cout = cout;
        this.dateDebut = dateDebut;
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnologie() {
        return technologie;
    }

    public void setTechnologie(String technologie) {
        this.technologie = technologie;
    }

    public Long getCout() {
        return cout;
    }

    public void setCout(Long cout) {
        this.cout = cout;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }
}
