package org.isj.traitementmetier.entites;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;


@Entity
@XmlRootElement
@Table(name = "est_inscrit")

public class EstInscrit extends Securite implements Serializable {

   public enum Statut {
       VALIDE, NONVALIDE
   }

    @Column(name = "statut")
    @Enumerated(EnumType.STRING)
    private Statut statut;

    @ManyToOne
    @JoinColumn(name = "candidat_inscrit")
    private Candidat candidatInscrit;

    @OneToOne(mappedBy = "estInscrit")
    private Note note;
        
    @ManyToOne
    @JoinColumn(name="enseignement")
    private Enseignement enseignement;

    public EstInscrit(String libelle, String description, Statut statut, Candidat candidatInscrit, Enseignement enseignement) {
        super(libelle, description);
        this.statut = statut;
        this.candidatInscrit = candidatInscrit;
        this.enseignement = enseignement;
    }

    public  EstInscrit(){}

    public EstInscrit.Statut getStatut() {
        return statut;
    }

    public void setStatut(EstInscrit.Statut statut) {
        this.statut = statut;
    }

    public Candidat getCandidatInscrit() {
        return candidatInscrit;
    }

    public void setCandidatInscrit(Candidat candidatInscrit) {
        this.candidatInscrit = candidatInscrit;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public Enseignement getEnseignement() {
        return enseignement;
    }

    public void setEnseignement(Enseignement enseignement) {
        this.enseignement = enseignement;
    }

    @Override
    public String getLibelle(){
        return candidatInscrit.getLibelle() +"-" +enseignement.getLibelle()+ "-"+statut
                +"-"+ note.getLibelle();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.statut);
        hash = 41 * hash + Objects.hashCode(this.candidatInscrit);
        hash = 41 * hash + Objects.hashCode(this.note);
        return hash;
    }

    @Override
    public String toString() {
        return "EstInscrit{" + "statut=" + statut + ", candidatInscrit=" + candidatInscrit.toString() + ", note=" + note.toString() + ", enseignement=" + enseignement.toString() + '}'+super.toString();
    }

}
