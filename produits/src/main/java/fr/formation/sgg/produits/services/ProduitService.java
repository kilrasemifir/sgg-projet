package fr.formation.sgg.produits.services;

import fr.formation.sgg.produits.dto.Stock;
import fr.formation.sgg.produits.kafka.StockProducer;
import fr.formation.sgg.produits.models.Produit;
import fr.formation.sgg.produits.repositories.ProduitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {

    private final ProduitRepository repository;

    private final StockProducer stockProducer;

    public ProduitService(ProduitRepository repository, StockProducer stockProducer) {
        this.repository = repository;
        this.stockProducer = stockProducer;
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

    public void updateStockQuantite(String id, Long stock) {
        this.findById(id);
        stockProducer.send(new Stock(id, stock));
    }

}
