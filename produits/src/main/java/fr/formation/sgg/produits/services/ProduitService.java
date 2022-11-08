package fr.formation.sgg.produits.services;

import fr.formation.sgg.produits.models.Produit;
import fr.formation.sgg.produits.repositories.ProduitRepository;
import fr.formation.sgg.produits.repositories.StockRestRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProduitService {

    private final ProduitRepository repository;
    private final StockRestRepository stockRestRepository;

    public ProduitService(ProduitRepository repository, StockRestRepository stockRestRepository) {
        this.repository = repository;
        this.stockRestRepository = stockRestRepository;
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

    public void upateStok(String id, Long stock) {
        this.findById(id);
        stockRestRepository.updateStock(id, stock);
    }

}
