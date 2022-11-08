package fr.formation.sgg.produits.repositories;

import fr.formation.sgg.produits.models.Produit;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProduitRepository {

    List<Produit> findAll();
    Optional<Produit> findById(String id);
    Produit save(Produit produit);
    void deleteById(String id);

}
