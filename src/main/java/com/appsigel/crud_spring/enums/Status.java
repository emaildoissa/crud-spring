package com.appsigel.crud_spring.enums;

public enum Status {
    ATIVA("Ativa"), INATIVA("Inativa");
    private String value;
    
    private Status( String value ){
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
