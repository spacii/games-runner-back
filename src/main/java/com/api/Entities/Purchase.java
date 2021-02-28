package com.api.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long purchaseId;
    private String purchaseStore;
    private String purchaseLogo;

    @JsonIgnore
    @ManyToMany(mappedBy = "purchases")
    private List<Game> games;

    public Purchase(){}

    public Purchase(Long purchaseId, String purchaseStore, String purchaseLogo) {
        this.purchaseId = purchaseId;
        this.purchaseStore = purchaseStore;
        this.purchaseLogo = purchaseLogo;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getPurchaseStore() {
        return purchaseStore;
    }

    public void setPurchaseStore(String purchaseStore) {
        this.purchaseStore = purchaseStore;
    }

    public String getPurchaseLogo() {
        return purchaseLogo;
    }

    public void setPurchaseLogo(String purchaseLogo) {
        this.purchaseLogo = purchaseLogo;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
