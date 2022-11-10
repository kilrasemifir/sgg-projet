package fr.formation.sgg.commandes;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
public class Commande {
    private String id;
    private String etat;
    private List<LigneCommande> lignes;
}
