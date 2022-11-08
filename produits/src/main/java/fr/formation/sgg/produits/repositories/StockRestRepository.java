package fr.formation.sgg.produits.repositories;

import fr.formation.sgg.produits.dto.Stock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class StockRestRepository {

    // Si la variable d'environement STOCK_URL est definie, on l'utilise
    // Sinon on utilise la valeur par defaut apres le ":"
    @Value("${STOCK_URL:http://localhost:8081/api/stocks}")
    private String url;

    private final RestTemplate restTemplate;

    public StockRestRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void updateStock(String produitId, Long quantite) {
        this.restTemplate.put(url + "/quantite" , new Stock(produitId, quantite));
    }
}
