package service;


import dao.IDao;
import entities.Commande;
import entities.Produit;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public class ProduitService implements IDao<Produit> {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional
    public boolean create(Produit produit) {
        Session session = sessionFactory.getCurrentSession();
        session.save(produit);
        return true;
    }


    @Override
    @Transactional
    public Produit getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Produit produit = session.get(Produit.class, id);
        return produit;
    }

    @Override
    @Transactional
    public List<Produit> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Produit> produits = session.createQuery("from Produit").getResultList();
        return produits;
    }
    // Method to display products ordered between two dates
    @Transactional
    public List<Produit> getProduitsBetweenDates(Date startDate, Date endDate) {
        Session session = sessionFactory.getCurrentSession();
        List<Produit> produits = session.createQuery(
                        "SELECT p FROM Commande c JOIN c.students p WHERE c.date BETWEEN :startDate AND :endDate",
                        Produit.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
        return produits;
    }

    // Method to display products ordered in a given Commande
    @Transactional
    public Set<Produit> getProduitsInCommande(Commande commande) {
        Session session = sessionFactory.getCurrentSession();
        Commande foundCommande = session.get(Commande.class, commande.getId());
        return foundCommande != null ? foundCommande.getProduits() : null;
    }
    @Transactional
    public List<Produit> getProduitsWithPriceGreaterThan100() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Produit p WHERE p.price > :price", Produit.class)
                .setParameter("price", 100f) // Use 100.0f for float type
                .getResultList();
    }

}
