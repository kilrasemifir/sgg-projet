package fr.formation.sgg.produits.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document // Specifie que la classe est une entite MongoDB
@Data // Ajouter automatiquement les getters, setters et toString
public class Produit {
    private String id;
    private String nom;
    private String description;
    private double prix;
    private String image;
}
