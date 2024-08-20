package com.appsigel.crud_spring.enums;

public enum Situacao {
    ABERTA("Aberta"), FECHADA("Fechada"), INATIVA("Inativa"),  ATIVA("Ativa");

    private final String value;
    
      
    private Situacao(String value) {
        this.value = value;
    }

    public String getValue(){
    return value;
    }

    @Override
    public String toString() {
        return value;
    }

  
    
}
