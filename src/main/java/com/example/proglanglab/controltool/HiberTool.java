package com.example.proglanglab.controltool;

import com.example.proglanglab.classes.Order;
import com.example.proglanglab.classes.Product;
import com.example.proglanglab.classes.ShopAssistant;
import com.example.proglanglab.classes.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.Transaction;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HiberTool {

    private EntityManagerFactory emf = null;

    public HiberTool(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void createUser(User user) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editUser(User user) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void removeUser(int id) {
       EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            //Papildomai pries trinant reikia visus rysius ir priklausomybes patikrinti
            User user = null;
            try {
                user = em.getReference(User.class, id);
                user.getId();
            } catch (Exception e) {
                System.out.println("No such user by given Id");
            }
            em.remove(user);
            em.getTransaction().

            commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

    public User getUserByLoginData(String login, String psw) {
        EntityManager em = getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(cb.like(root.get("username"), login));
        query.select(root).where(cb.like(root.get("password"), psw));
        Query q;
        try {
            q = em.createQuery(query);
            return (User) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public User getUserById(int id) {
        EntityManager em = null;
        User user = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            user = em.getReference(User.class, id);
            user.getId();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No such user by given Id");
        }
        return user;
    }
    public ShopAssistant getAssistantById(int id) {
        EntityManager em = null;
        ShopAssistant user = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            user = em.getReference(ShopAssistant.class, id);
            user.getId();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No such user by given Id");
        }
        return user;
    }



    public List<User> getAllUsers() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery query = em.getCriteriaBuilder().createQuery();
            query.select(query.from(User.class));
            Query q = em.createQuery(query);


            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return null;
    }
    public List<ShopAssistant> getAllShopAssistants() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery query = em.getCriteriaBuilder().createQuery();
            query.select(query.from(ShopAssistant.class));
            Query q = em.createQuery(query);


            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return null;
    }
    }



