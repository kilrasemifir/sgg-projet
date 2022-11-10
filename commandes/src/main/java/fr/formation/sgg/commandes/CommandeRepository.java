package fr.formation.sgg.commandes;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CommandeRepository extends ReactiveMongoRepository<Commande, String> {
}
