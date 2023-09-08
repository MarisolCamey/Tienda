package com.company.intecap.apiresttienda.response;

public class ArticuloResponseRest extends ResponseRest {
    private ArticuloResponse articuloResponse = new ArticuloResponse();

    public  ArticuloResponse getArticuloResponse(){ return articuloResponse;}

    public void setArticuloResponse (ArticuloResponse articuloResponse){
        this.articuloResponse = articuloResponse;
    }
}
