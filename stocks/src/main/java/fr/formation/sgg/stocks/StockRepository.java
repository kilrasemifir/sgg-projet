package fr.formation.sgg.stocks;

import fr.formation.sgg.stocks.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {

}
