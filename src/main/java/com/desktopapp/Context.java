package com.desktopapp;

import java.util.List;

import com.desktopapp.model.Produto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Context {
    private EntityManagerFactory emf;
    private EntityManager em;

    public Context() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    public ObservableList<Produto> listaproduto(){

        ObservableList<Produto> produtos = FXCollections.observableArrayList();
        em = emf.createEntityManager();

        TypedQuery<Produto> query = em.createQuery("SELECT p FROM Produto p", Produto.class);
        produtos.addAll(query.getResultList());

        return produtos;
    }

    public void begin() {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            em = null;
        }
    }

    public <T> T find(Class<T> entityClass, Object primaryKey) {
        EntityManager em = emf.createEntityManager();
        T obj = null;
        try {
            obj = em.find(entityClass, primaryKey);
        } finally {
            em.close();
        }
        return obj;
    }

    public <T> TypedQuery<T> createQuery(Class<T> entityClass, String query)
    {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(query, entityClass);
        } finally {
            em.close();
        }
    }
     
    public <T> List<T> find(Class<T> entityClass, String query, Object... values)
    {
        EntityManager em = emf.createEntityManager();
        List<T> users = null;
        try {
            var queryObj = em.createQuery(query, entityClass);
            for (Integer i = 0; i < values.length; i++) {
                queryObj = queryObj.setParameter("arg" + i.toString(), values[i]);
            }
            users = queryObj.getResultList();
        } finally {
            em.close();
        }
        return users;
    }

    public void persist(Object object) {
        if (em == null) {
            System.out.println("connection is null.");
            return;
        }

        try {
            em.persist(object);
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            em = null;
        }
    }

    public void update(Object object) {
        if (em == null) {
            System.out.println("connection is null.");
            return;
        }
    
        try {
            em.getTransaction().begin();
            em.merge(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(Object object) {
        
        em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            object = em.contains(object) ? object : em.merge(object);
            em.remove(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }finally{

            em.close();
            em = null;
        }
    }

    public void commit() {
        if (em == null) {
            System.out.println("connection is null.");
            return;
        }

        try {
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            em = null;
        }
    }
}