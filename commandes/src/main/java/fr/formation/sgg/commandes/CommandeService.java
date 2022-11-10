package fr.formation.sgg.commandes;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CommandeService {

    private final CommandeRepository repository;

    public CommandeService(CommandeRepository repository) {
        this.repository = repository;
    }

    public Mono<Commande> create(Commande commande) {
        var result =  repository.save(commande);
        result.doOnNext(c -> System.out.println("Commande créée: " + c));
        return result;
    }

    public Mono<Commande> update(Commande commande) {
        var result =  repository.save(commande);
        result.doOnNext(c -> System.out.println("Commande mise à jour: " + c));
        return result;
    }

    public Mono<Commande> findById(String id) {
        var result =  repository.findById(id);
        result.doOnNext(c -> System.out.println("Commande trouvée: " + c));
        return result;
    }

    public Mono<Void> deleteById(String id) {
        var result =  repository.deleteById(id);
        result.doOnNext(c -> System.out.println("Commande supprimée: " + c));
        return result;
    }

    public Flux<String> findAll() {
        var result =  repository.findAll();
        return result.map(c -> "Commande trouvée: " + c);
    }
}
