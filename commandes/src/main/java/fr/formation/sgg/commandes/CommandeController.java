package fr.formation.sgg.commandes;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/commandes")
public class CommandeController {

    private final CommandeService service;

    public CommandeController(CommandeService service) {
        this.service = service;
    }

    @GetMapping
    public Flux<String> findAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public Mono<Commande> findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public Mono<Commande> create(@RequestBody Commande commande) {
        return service.create(commande);
    }

    @PutMapping
    public Mono<Commande> update(@RequestBody Commande commande) {
        return service.update(commande);
    }


}
