package fr.formation.sgg.stocks;


import fr.formation.sgg.stocks.models.Stock;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    private final StockRepository repository;

    public StockService(StockRepository repository) {
        this.repository = repository;
    }

    public Stock findById(Long id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Stock non trouvé"));
    }

    public Stock save(Stock stock) {
        return repository.save(stock);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Stock> findAll() {
        return repository.findAll();
    }

    public void updateStock(String id, int stock) {
        Stock stockToUpdate = this.repository.findByProduitId(id);
        if (stockToUpdate == null) {
            stockToUpdate = new Stock();
            stockToUpdate.setProduitId(id);
        }
        stockToUpdate.setQuantite(stockToUpdate.getQuantite() + stock);
        this.save(stockToUpdate);
    }

}
