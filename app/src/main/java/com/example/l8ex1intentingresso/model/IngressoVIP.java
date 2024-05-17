package com.example.l8ex1intentingresso.model;

public class IngressoVIP extends Ingresso{

    private String funcao;

    public IngressoVIP() {
        super();
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public float valorFinal(float taxa) {
        return (super.getValor()*1.18f)+ taxa;
    }

    @Override
    public String toString() {
        return "Ingresso ID "+getId()+"\nFunção: "+getFuncao()+"\nValor: "+getValor();
    }

}
