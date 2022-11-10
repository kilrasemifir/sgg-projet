package fr.formation.sgg.stocks.models;

import fr.formation.sgg.stocks.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class StockConsumer {

    static final Logger logger = LoggerFactory.getLogger(StockConsumer.class);
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
        logger.info("StockConsumer.console: " + dto);
    }

}
