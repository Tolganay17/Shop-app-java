package com.example.proglanglab.controltool;

import com.example.proglanglab.classes.Manga;
import com.example.proglanglab.classes.Product;
import com.example.proglanglab.classes.Ranobe;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProductHiberTool {
    private EntityManagerFactory emf = null;

    public ProductHiberTool(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public void createProduct(Product product) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editProduct(Product product) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(product);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void removeProduct(int id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Product product = null;
            try {
                product = em.getReference(Product.class, id);
                product.getProduct_id();
            } catch (Exception e) {
                System.out.println("No such user by given Id");
            }
            em.remove(product);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public Product getProductById(int id) {
        EntityManager em = null;
        Product product= null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            product = em.getReference(Product.class, id);
            product.getProduct_id();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No such user by given Id");
        }
        return product;
    }
    public Manga getMangaById(int id) {
        EntityManager em = null;
        Manga manga= null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            manga = em.getReference(Manga.class, id);
            manga.getProduct_id();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No such user by given Id");
        }
        return manga;
    }
    public Ranobe getRanobeById(int id) {
        EntityManager em = null;
        Ranobe ranobe= null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ranobe = em.getReference(Ranobe.class, id);
            ranobe.getProduct_id();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No such user by given Id");
        }
        return ranobe;
    }


    public List<Product> getAllProducts() {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb=em.getCriteriaBuilder();
            CriteriaQuery<Product>cq=cb.createQuery(Product.class);
            Root<Product>productRoot=cq.from(Product.class);
            cq.select(productRoot);
            List<Product>productList=em.createQuery(cq).getResultList();
            return  productList;
           /* CriteriaQuery query = em.getCriteriaBuilder().createQuery();
            query.select(query.from(Product.class));
            Query q = em.createQuery(query);
            return q.getResultList();*/
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
