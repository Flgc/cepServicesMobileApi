package com.example.services.model;

public class Cnpj {
    private String cnpj;
    private String razao;
    private String nfantasia;
    private String dinicio;
    private String porte;
    private String telefone;
    private String email;

    public String getCnpj() {
        return cnpj;
    }

    public String getRazao() {
        return razao;
    }

    public String getfantasia() {
        return nfantasia;
    }

    public String getDinicio() {
        return dinicio;
    }

    public String getPorte() {
        return porte;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String formatCnpj(){
        return this.razao.concat("\n")
                .concat(this.nfantasia)
                .concat("\n")
                .concat(this.email);
    }
}