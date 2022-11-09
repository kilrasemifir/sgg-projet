package fr.formation.sgg.produits.kafka;

import fr.formation.sgg.produits.dto.Stock;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

@Configuration
public class KafkaConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapUrl;

    @Value("${spring.kafka.producer.topic}")
    private String topic;

    /**
     * Configuration de la connexion au cluste kafka
     * @return l'objet KafkaAdmin
     */
    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> props = new HashMap<>();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapUrl);
        return new KafkaAdmin(props);
    }

    /**
     * Configuration du topic
     * @return l'objet NewTopic
     */
    @Bean
    public NewTopic topic() {
        return new NewTopic(
                topic, // nom du topic
                1,   // nombre de partitions
                (short) 1); // nombre de replicas
    }

    @Bean
    public ProducerFactory<String, Stock> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        // Configuration de la connexion au cluster kafka
        props.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapUrl);
        // Configuration du serializer pour la cle
        // Ici la clé est une chaine de caractères, on utilise donc le serializer StringSerializer
        props.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // Configuration du serializer pour la valeur
        // Ici la valeur est un objet Stock, on utilise donc le serializer pour les objets
        props.put(VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        props.put(ACKS_CONFIG, "all");

        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, Stock> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
