package fr.formation.sgg.stocks.models;

import fr.formation.sgg.stocks.StockService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class StockConsumer {

    private final StockService service;

    public StockConsumer(StockService service) {
        this.service = service;
    }

    /**
     * Met a jour la quantite du stock
     * @param dto
     */
    @KafkaListener(topics = "stock", groupId = "stock_quantite")
    public void updateStock(@Payload Stock dto) {
        service.updateStock(dto.getProduitId(), dto.getQuantite());
    }

    @KafkaListener(topics = "stock", groupId = "stock_console")
    public void console(@Payload Stock dto) {
        System.out.println("StockConsumer.console: " + dto);
    }

}
