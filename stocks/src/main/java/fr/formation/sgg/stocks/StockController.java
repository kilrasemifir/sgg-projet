package fr.formation.sgg.stocks;

import fr.formation.sgg.stocks.models.Stock;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/stocks")
public class StockController {

    private final StockService service;

    public StockController(StockService service) {
        this.service = service;
    }

    @GetMapping
    public List<Stock> findAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public Stock findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Stock save(@RequestBody Stock stock) {
        return service.save(stock);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
