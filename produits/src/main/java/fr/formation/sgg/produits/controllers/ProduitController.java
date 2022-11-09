package fr.formation.sgg.produits.controllers;

import fr.formation.sgg.produits.models.Produit;
import fr.formation.sgg.produits.services.ProduitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/produits")
public class ProduitController {

    private final ProduitService service;

    public ProduitController(ProduitService service) {
        this.service = service;
    }

    @GetMapping
    public List<Produit> findAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public Produit findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public Produit save(@RequestBody Produit produit) {
        return service.save(produit);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id) {
        service.deleteById(id);
    }

    @PutMapping("{id}")
    public void updateStock(@PathVariable String id, @RequestParam Long quantite) {
        service.updateStockQuantite(id, quantite);
    }
}
