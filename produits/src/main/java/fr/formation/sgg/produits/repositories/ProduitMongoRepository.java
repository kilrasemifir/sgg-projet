package fr.formation.sgg.produits.repositories;

import fr.formation.sgg.produits.models.Produit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProduitMongoRepository extends MongoRepository<Produit, String>, ProduitRepository {

}
