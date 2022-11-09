package fr.formation.sgg.stocks;

import fr.formation.sgg.stocks.models.Stock;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;


@Configuration
public class KafkaConfiguration {

    private String bootstrapUrl = "localhost:9092";

    private String topic = "stock";

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

    /**
     * Configuration du deserializer pour la valeur.
     * @return
     */
    @Bean
    public JsonDeserializer<Stock> jsonDeserializer() {
        // Création d'un deserializer pour les objets de type Stock
        JsonDeserializer<Stock> deserializer = new JsonDeserializer<>(Stock.class);
        // Configuration du deserializer pour ignorer les champs inconnus
        deserializer.setRemoveTypeHeaders(false);
        // Configuration du deserializer pour ignorer les packages inconnus
        deserializer.addTrustedPackages("*");
        // Confuguration du deserializer pour utiliser les type dans les champs
        deserializer.setUseTypeMapperForKey(true);
        return deserializer;
    }

    @Bean
    public ConsumerFactory<String, Stock> consumerFactory(JsonDeserializer<Stock> deserializer) {

        Map<String, Object> props = new HashMap<>();
        // Configuration de la connexion au cluster kafka
        props.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapUrl);
        // Configuration du serializer pour la cle
        // Ici la clé est une chaine de caractères, on utilise donc le serializer StringSerializer
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        // Configuration du serializer pour la valeur
        // Ici la valeur est un objet Stock, on utilise donc le serializer pour les objets
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

    /**
     * Configuration du listener
     * @param consumerFactory
     * @return
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Stock> kafkaListenerContainerFactory(ConsumerFactory<String, Stock> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, Stock> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}
