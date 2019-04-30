package org.isj.traitementmetier;
import org.isj.traitementmetier.entites.*;
import org.isj.traitementmetier.facade.*;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Isj {
    //public static Utilisateur utilisateurCourant = new Utilisateur();


    public static void main(String[] args) {

/*
        Securite user = new Securite();

        new Isj().persist(user);
        Date date = new Date();
       /* Filiere filiere= new Filiere("Licence Professionnelle","Concepteur/Développeur d'applications pour l'économie numérique") ;
        Utilisateur utilisateur = new Utilisateur("second utilisateur","pourra jouer des roles","NDOUMOU","Andre","ndoumouandre@gmail.com",655841232,date, Personne.Sexe.MASCULIN, Personne.Statut.ACTIVE,"andre","1234");
        UtilisateurFacade uf = new UtilisateurFacade();
        Role role = new Role("Enregistreur","pourra lire et ecrire les données");
        RoleFacade rf = new RoleFacade();
        rf.create(role);
        new Isj().insert(utilisateur,role);
        uf.create(utilisateur);
        rf.create(role);
        FiliereFacade ff=new FiliereFacade();
        ff.create(filiere);*/
       /* UtilisateurFacade uf = new UtilisateurFacade();
        Utilisateur utilisateur = uf.find(new Long(1));

        //Pour affecter un utilisateur à un role
        new Isj().affecterUtilisateurRole(utilisateur,role);
        uf.merge(utilisateur);
        rf.merge(role);*/
       /* UtilisateurFacade uf = new UtilisateurFacade();
        Utilisateur utilisateur = uf.find(new Long(1));
        List<Role> roles = utilisateur.getRoles();
        for (int i = 0; i < roles.size(); i++) {
            System.out.println(roles.get(i).getLibelle());
            List<Droit> droits = roles.get(i).getDroits();
            for (int j = 0; j < droits.size(); j++) {
                System.out.println(droits.get(j).toString());
            }

        }*/
        /*
       //test renvoyer login en fonction du numero
       Utilisateur u = new Isj().renvoyerLogin(696528881);
       System.out.println(u.getNom());*/

        //test renvoyer login en fonction du mail
        /*
        Utilisateur u = new Isj().renvoyerLoginE("mbouendenorman@gmail.com");
        System.out.println(u.getNom());*/

        //test affichage des champs

       /* Utilisateur u = new Utilisateur();
        List <String> champ = new Isj().renvoyerChamp(Utilisateur.class);
        System.out.println(champ.get(0));*/

        //test authentification
        /*
        Utilisateur u = new Isj().authentification("norman","123");
        System.out.println(u.getNom());*/

        //test isTelephone in BD
        /*
        Boolean u = new Isj().isTelephoneInBD(696528887);
        System.out.println(u);*/

        //test isEmail in BD
        /*
        Boolean u = new Isj().isEmailInBD("mbouendenorman@gmail.cm");
        System.out.println(u);*/

        //test sauvegarder email en bd

        /*Email email = new Email("test","test","Ceci est un test","isj@gmail.com","anthonyfouda@gmail.com","test");
        new EmailFacade().create(email);
        Email email = new EmailFacade().find(new Long(151));
        Candidat candidat = new CandidatFacade().find(new Long(1));
        String u= new Isj().sauvegarderEmailRecu(email,candidat);
        System.out.println(u);*/

        //test de la bd

        EtudiantFacade etudiantFacade = new EtudiantFacade();
        List <Etudiant> etudiants = etudiantFacade.findAll();
        System.out.println(etudiants.get(0).getNom());

    }
    /*public void persist(Object object) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ISJPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }*/

        public void affecterUtilisateurRole (Utilisateur user, Role role) {
                role.getUtilisateurs().add(user);
                user.getRoles().add(role);
    }

        public void creerDroitRole (Role role){
            DroitFacade df = new DroitFacade();

            Droit d1 = new Droit("droit sur Classe", "permet d'influencer les classes", "Classe", false, false, false, false, role);
            Droit d2 = new Droit("droit sur Utilisateur", "permet d'influencer les utilisateurs", "Utilisateur", false, false, false, false,role);
            Droit d3 = new Droit("droit sur AnneeAcadémique", "permet d'influencer les années académiques", "AnneeAcademique", false, false, false, false,role);
            Droit d4 = new Droit("droit sur Anonymat", "permet d'influencer les anonymats", "Anonymat", false, false, false, false,role);
            Droit d5 = new Droit("droit sur Candidat", "permet d'influencer les candidats", "Candidat", false, false, false, false,role);
            Droit d6 = new Droit("droit sur Discipline", "permet d'influencer les disciplines", "Discipline", false, false, false, false,role);
            Droit d7 = new Droit("droit sur Email", "permet d'influencer les emails", "Email", false, false, false, false,role);
            Droit d8 = new Droit("droit sur Enseignant", "permet d'influencer les enseignants", "Enseignant", false, false, false, false,role);
            Droit d9 = new Droit("droit sur Enseignement", "permet d'influencer les enseignements", "Enseignement", false, false, false, false,role);
            Droit d10 = new Droit("droit sur EnvoiMessage", "permet d'influencer les envois des messages", "EnvoiMessage", false, false, false, false,role);
            Droit d11 = new Droit("droit sur EstInscrit", "permet d'influencer les inscriptions des candidats aux enseignements", "EstInscrit", false, false, false, false,role);
            Droit d12 = new Droit("droit sur Etudiant", "permet d'influencer les etudiants", "Etudiant", false, false, false, false,role);
            Droit d13 = new Droit("droit sur Evaluation", "permet d'influencer les évaluations", "Evaluation", false, false, false, false,role);
            Droit d14 = new Droit("droit sur Filiere", "permet d'influencer les filieres", "Filiere", false, false, false, false,role);
            Droit d15 = new Droit("droit sur HistoriqueNote", "permet d'influencer les historiques des notes", "HistoriqueNote", false, false, false, false,role);
            Droit d16 = new Droit("droit sur Module", "permet d'influencer les modules", "Module", false, false, false, false,role);
            Droit d17 = new Droit("droit sur Niveau", "permet d'influencer les niveaux", "Niveau", false, false, false, false,role);
            Droit d18 = new Droit("droit sur Note", "permet d'influencer les notes", "Note", false, false, false, false,role);
            Droit d19 = new Droit("droit sur Role", "permet d'influencer les roles", "Role", false, false, false, false,role);
            Droit d20 = new Droit("droit sur Semestre", "permet d'influencer les semestres", "Semestre", false, false, false, false,role);
            Droit d21 = new Droit("droit sur Sms", "permet d'influencer les sms", "Sms", false, false, false, false,role);
            Droit d22 = new Droit("droit sur Specialite", "permet d'influencer les specialités", "Specialite", false, false, false, false,role);
            Droit d23 = new Droit("droit sur TypeEvaluation", "permet d'influencer les évaluations", "Evaluation", false, false, false, false,role);
            Droit d24 = new Droit("droit sur UE", "permet d'influencer les UE", "UE", false, false, false, false,role);
            Droit d25 = new Droit("droit sur Droit", "permet d'influencer les droits", "Droit", false, false, false, false,role);

            df.create(d1);
            df.create(d2);
            df.create(d3);
            df.create(d4);
            df.create(d5);
            df.create(d6);
            df.create(d7);
            df.create(d8);
            df.create(d9);
            df.create(d10);
            df.create(d11);
            df.create(d12);
            df.create(d13);
            df.create(d14);
            df.create(d15);
            df.create(d16);
            df.create(d17);
            df.create(d18);
            df.create(d19);
            df.create(d20);
            df.create(d21);
            df.create(d22);
            df.create(d23);
            df.create(d24);
            df.create(d25);
        }

        public Utilisateur renvoyerLoginTelephone(int numero){
            String query = "SELECT * FROM utilisateur WHERE telephone ="+numero+";";
            UtilisateurFacade uf=new UtilisateurFacade();
            List <Utilisateur> utilisateurs = uf.findAllNative(query);
            if(utilisateurs.size() != 0) {
                return utilisateurs.get(0);
            }else {
                return null;
            }
        }

        public Boolean isTelephoneInBD(int numero){
            Utilisateur utilisateur = renvoyerLoginTelephone(numero);
            return utilisateur != null;
        }

        public Utilisateur renvoyerLoginEmail(String email){
            String query = "SELECT * FROM utilisateur WHERE email like '"+email+"';";
            UtilisateurFacade uf=new UtilisateurFacade();
            List <Utilisateur> utilisateurs = uf.findAllNative(query);
            if(utilisateurs.size() != 0) {
                return utilisateurs.get(0);
            }else {
                return null;
            }
        }

        public Boolean isEmailInBD(String email){
            Utilisateur utilisateur = renvoyerLoginEmail(email);
            return utilisateur != null;
        }

        public Utilisateur authentification(String login, String password){
            String query = "SELECT * FROM utilisateur WHERE login like '"+login+"';";
            UtilisateurFacade uf = new UtilisateurFacade();
            List <Utilisateur> utilisateurs = uf.findAllNative(query);
            if(utilisateurs.size() != 0) {
                if (utilisateurs.get(0).getMotDePasse().equals(password)) {
                    return utilisateurs.get(0);
                } else {
                    Utilisateur u = new Utilisateur();
                    u.setLibelle("mot de passe incorrect");
                    return u;
                }
            }else {
                return null;
            }
        }

        public ResultSetMetaData renvoyerChamp(Class  entity){
            UtilisateurFacade uf = new UtilisateurFacade();
            List <String> champs = new ArrayList<>();
            String query= "SELECT * FROM " + entity.getSimpleName();
            try{
            Statement statement = uf.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData champ = resultSet.getMetaData();
            return champ;
            }catch (Exception ex){
                ex.printStackTrace();
            }
            return null;
        }

        public String sauvegarderEmailRecu(Email email,Candidat candidat){
            EnvoiMessage envoiMessage = new EnvoiMessage();
            Date dateEnvoi = new Date();
            EnvoiMessageFacade envoiMessageFacade = new EnvoiMessageFacade();
            EmailFacade emailFacade = new EmailFacade();

            envoiMessage.setLibelle("test");
            envoiMessage.setDescription("test");
            envoiMessage.setDateEnvoi(dateEnvoi);
            envoiMessage.setStatut(EnvoiMessage.Statut.SUCCES);
            envoiMessage.setMessage(email);
            envoiMessage.setCandidat(candidat);

            envoiMessageFacade.create(envoiMessage);
           // emailFacade.merge(email);


            return "succes";
        }

        public String sauvegarderEmailEnvoieEchec(Email email){
            Date dateEnvoi = new Date();
            String emailCandidat = email.getDestinataire();
            String query = "SELECT * FROM candidat WHERE email like '"+emailCandidat+"';";
            CandidatFacade cf = new CandidatFacade();
            EnvoiMessageFacade envoiMessageFacade = new EnvoiMessageFacade();
            EmailFacade emailFacade = new EmailFacade();
            List <Candidat> candidats = cf.findAllNative(query);

            emailFacade.create(email);
            envoiMessageFacade.enregistrer("","",dateEnvoi, EnvoiMessage.Statut.ECHEC,email,candidats.get(0));

            return "echec";
        }

        public String sauvegarderEmailEnvoieSucces(Email email){
            Date dateEnvoi = new Date();
            String emailCandidat = email.getDestinataire();
            String query = "SELECT * FROM candidat WHERE email like '"+emailCandidat+"';";
            CandidatFacade cf = new CandidatFacade();
            EnvoiMessageFacade envoiMessageFacade = new EnvoiMessageFacade();
            EmailFacade emailFacade = new EmailFacade();
            List <Candidat> candidats = cf.findAllNative(query);

            emailFacade.create(email);
            envoiMessageFacade.enregistrer("","",dateEnvoi, EnvoiMessage.Statut.SUCCES,email,candidats.get(0));

            return "succes";
        }

        public String sauvegarderSmsEnvoieEchec(Sms sms){
            Date dateEnvoi = new Date();
            int telephoneCandidat = Integer.parseInt(sms.getDestinataire());
            String query = "SELECT * FROM candidat WHERE telephone like "+telephoneCandidat+";";
            CandidatFacade cf = new CandidatFacade();
            EnvoiMessageFacade envoiMessageFacade = new EnvoiMessageFacade();
            SmsFacade smsFacade = new SmsFacade();
            List <Candidat> candidats = cf.findAllNative(query);

            smsFacade.create(sms);
            envoiMessageFacade.enregistrer("","",dateEnvoi, EnvoiMessage.Statut.ECHEC,sms,candidats.get(0));

            return "echec";
        }

        public String sauvegarderSmsEnvoieSucces(Sms sms){
            Date dateEnvoi = new Date();
            int telephoneCandidat = Integer.parseInt(sms.getDestinataire());
            String query = "SELECT * FROM candidat WHERE telephone like "+telephoneCandidat+";";
            CandidatFacade cf = new CandidatFacade();
            EnvoiMessageFacade envoiMessageFacade = new EnvoiMessageFacade();
            SmsFacade smsFacade = new SmsFacade();
            List <Candidat> candidats = cf.findAllNative(query);

            smsFacade.create(sms);
            envoiMessageFacade.enregistrer("","",dateEnvoi, EnvoiMessage.Statut.SUCCES,sms,candidats.get(0));

            return "succes";
        }

        public String sauvegarderSmsRecu(Sms sms){
            Date dateEnvoi = new Date();
            int telephoneCandidat = Integer.parseInt(sms.getEmetteur());
            String query = "SELECT * FROM candidat WHERE telephone like "+telephoneCandidat+";";
            CandidatFacade cf = new CandidatFacade();
            EnvoiMessageFacade envoiMessageFacade = new EnvoiMessageFacade();
            SmsFacade smsFacade = new SmsFacade();
            List <Candidat> candidats = cf.findAllNative(query);

            smsFacade.create(sms);
            envoiMessageFacade.enregistrer("","",dateEnvoi, EnvoiMessage.Statut.SUCCES,sms,candidats.get(0));

            return "succes";
        }
}