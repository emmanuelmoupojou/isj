/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isj.traitementmetier.facade;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.isj.traitementmetier.entites.Securite;
import org.isj.traitementmetier.entites.Utilisateur;

import static org.isj.traitementmetier.Isj.utilisateurCourant;

public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    Method methodeSetCreateur;
    Method methodeSetModificateur;
    Method methodeSetDateCreation;
    Method methodeSetDateModification;
    Method methodeSetSignature;
    Method methodeSetStatutVie;


    public AbstractFacade(Class<T> entityClass) {

        this.entityClass = entityClass;
        try {
            methodeSetCreateur = entityClass.getMethod("setCreateur", new Class[]{Utilisateur.class});
            methodeSetCreateur.setAccessible(true);
            methodeSetDateCreation = entityClass.getMethod("setDateCreation", new Class[]{Date.class});
            methodeSetDateCreation.setAccessible(true);
            methodeSetModificateur = entityClass.getMethod("setModificateur", new Class[]{Utilisateur.class});
            methodeSetModificateur.setAccessible(true);
            methodeSetDateModification = entityClass.getMethod("setDateModification", new Class[]{Date.class});
            methodeSetDateModification.setAccessible(true);
            methodeSetSignature = entityClass.getMethod("setSignature", new Class[]{String.class});
            methodeSetSignature.setAccessible(true);
            methodeSetStatutVie = entityClass.getMethod("setStatutVie", new Class[]{Securite.StatutVie.class});
            methodeSetStatutVie.setAccessible(true);
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(AbstractFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    EntityManager em;
    EntityManagerFactory emf;

    public EntityManager getEntityManager() {
        em = (em == null || !em.isOpen()) ? (emf == null ? Persistence.createEntityManagerFactory("ISJPU").createEntityManager() : emf.createEntityManager()) : em;
        return em;
    }

    //Utilisateur utilisateurCourant = getEntityManager().find(Utilisateur.class, new Long(1));


    public String create(T entity) {
        
        String result;
        try {
            Date dateCourante = new Date();
            methodeSetCreateur.invoke(entity, utilisateurCourant);
            methodeSetDateCreation.invoke(entity, dateCourante);
            methodeSetModificateur.invoke(entity, utilisateurCourant);
            methodeSetDateModification.invoke(entity, dateCourante);
            methodeSetSignature.invoke(entity, "");
            methodeSetStatutVie.invoke(entity, Securite.StatutVie.ACTIVE);
            
            getEntityManager().getTransaction().begin();
            getEntityManager().persist(entity);
            getEntityManager().getTransaction().commit();
            
            //Après l'enregistrement de l'entité, la fonction de mise à jour de l'entité permettra de recalculer sa signature avec la valeur de son code
            getEntityManager().merge(entity);
            merge(entity);
            
            result = "succes";
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            getEntityManager().getTransaction().rollback();
            result = "echec";
        } finally {
//            getEntityManager().close();
        }
        return result;
    }

    public String merge(T entity) {
        String result;
        try {
            
            Date dateCourante = new Date();
            methodeSetModificateur.invoke(entity, utilisateurCourant);
            methodeSetDateModification.invoke(entity, dateCourante);
            methodeSetSignature.invoke(entity, "");
            
            getEntityManager().getTransaction().begin();
            getEntityManager().merge(entity);
            getEntityManager().getTransaction().commit();
            result = "succes";
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            getEntityManager().getTransaction().rollback();
            result = "echec";
        } finally {
            getEntityManager().close();
        }
        return result;
    }

    public String remove(T entity) {
        String result;
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().remove(getEntityManager().merge(entity));
            getEntityManager().getTransaction().commit();
            result = "succes";
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            getEntityManager().getTransaction().rollback();
            result = "echec";
        } finally {
            //getEntityManager().close();
//            getEntityManager().flush();
        }
        return result;

    }

    public T find(Object id) {
        T objet = getEntityManager().find(entityClass, id);
        if (objet != null) {
            getEntityManager().refresh(objet);
            getEntityManager().merge(objet);
        }
        return objet;
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findAllNative(String query) {
        return getEntityManager().createNativeQuery(query, entityClass).getResultList();
    }

    public List<T> findAllJPQL(String request) {
        Query query = getEntityManager().createQuery(request, entityClass);
        System.out.println("requete = " + query.toString());
        return query.getResultList();
    }

    public List<T> findAllJPQL(Query request) {
        System.out.println("requete = " + request.toString());
        return request.getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    java.sql.Connection con;

    public java.sql.Connection getConnection() throws SQLException {

        if (con == null || con.isClosed()) {
            try {
                getEntityManager().getTransaction().begin();
                con = getEntityManager().unwrap(java.sql.Connection.class);
                System.out.println(con.toString());
                getEntityManager().getTransaction().commit();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return con;
    }


}
