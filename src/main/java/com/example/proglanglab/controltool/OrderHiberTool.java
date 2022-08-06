package com.example.proglanglab.controltool;

import com.example.proglanglab.classes.Order;
import com.example.proglanglab.classes.Product;
import com.example.proglanglab.classes.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class OrderHiberTool {
    private EntityManagerFactory emf = null;

    public OrderHiberTool(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public void createOrder(Order order) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(order);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editOrder(Order order) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(order);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void removeOrder(int id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Order order = null;
            try {
                order = em.getReference(Order.class, id);
                order.getOrderId();
            } catch (Exception e) {
                System.out.println("No such user by given Id");
            }
            em.remove(order);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public Order getOrderById(int id) {
        EntityManager em = null;
        Order order= null;
        try { /*
            assert false;
            CriteriaBuilder cb=em.getCriteriaBuilder();
            CriteriaQuery<Order>cq=cb.createQuery(Order.class);
            Root<Order>productRoot=cq.from(Order.class);
            cq.select(productRoot);
            List<Order>OrderList=em.createQuery(cq).getResultList();
            for(Order o:OrderList){
                if(o.getOrderId()==id){
                    order=o;
                }
            }
            return order;
            }*/


            em = getEntityManager();
            em.getTransaction().begin();
            order = em.find(Order.class, id);
            order.getOrderId();
            em.getTransaction().commit();
        }catch (Exception e) {
            System.out.println("No such user by given Id");
        }
        return order;
    }
    //order=project
    //Product=task
    //User = user

    public List<Order> getAllOrders() {
        return getAllOrders(true, -1, -1);
    }

    public List<Order> getAllOrders(boolean all, int resMax, int resFirst) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery query = em.getCriteriaBuilder().createQuery();
            query.select(query.from(Order.class));
            Query q = em.createQuery(query);

            if (!all) {
                q.setMaxResults(resMax);
                q.setFirstResult(resFirst);
            }

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
    public List<Order> getProjectByUserId(int id) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> query = cb.createQuery(Order.class);

        Root<Order> root = query.from(Order.class);

        CriteriaBuilder.In<Integer> inClause = cb.in(root.get("id"));
        User user = em.getReference(User.class, id);
        for (Order p : user.getMyOrders()) {
            inClause.value(p.getOrderId());
        }
        query.select(root).where(inClause);
        Query q;
        try {
            q = em.createQuery(query);
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
