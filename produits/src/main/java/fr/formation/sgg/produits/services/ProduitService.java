package fr.formation.sgg.produits.services;

import fr.formation.sgg.produits.models.Produit;
import fr.formation.sgg.produits.repositories.ProduitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    private final ProduitRepository repository;

    public ProduitService(ProduitRepository repository) {
        this.repository = repository;
    }

    public List<Produit> findAll() {
        return repository.findAll();
    }

    public Produit findById(String id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Produit non trouvé"));
    }

    public Produit save(Produit produit) {
        return repository.save(produit);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
