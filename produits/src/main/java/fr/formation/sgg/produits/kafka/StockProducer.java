package fr.formation.sgg.produits.kafka;

import fr.formation.sgg.produits.dto.Stock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class StockProducer {
    @Value("${spring.kafka.producer.topic}")
    private String topic;

    private final KafkaTemplate<String, Stock> kafkaTemplate;

    public StockProducer(KafkaTemplate<String, Stock> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Envoie un message sur le topic "stock" avec la quantite a mettre a jour
     * @param stock
     */
    public void send(Stock stock) {
        kafkaTemplate.send(topic, stock);
    }
}
