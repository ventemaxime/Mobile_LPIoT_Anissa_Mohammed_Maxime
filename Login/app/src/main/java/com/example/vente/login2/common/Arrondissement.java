package com.example.vente.login2.common;

public class Arrondissement {
    private final int name;
    private final int imageResource;
    private boolean isFavorite = false;


    public Arrondissement(int name,  int imageResource) {
        this.name = name;
        this.imageResource = imageResource;

    }

    public int getName() {
        return name;
    }


    public int getImageResource() {
        return imageResource;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }
    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public void toggleFavorite() {
        isFavorite = !isFavorite;
    }
}
