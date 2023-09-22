package com.example.demo;



public class Bestas extends Personagem {

    private Object bestaClass;



    public Bestas(String nome, int hp, int armadura, String bestaClass) {
        super(nome, hp, armadura, bestaClass);
        this.bestaClass = bestaClass;
    }
    public Bestas() {
    }

    public Bestas(String nome, int hp, int armadura) {
        super(nome, hp, armadura);
    }

    @Override
    public int atacar() {

        return rolarDado() - 10;

    }

    public Object getBestaClass() {
        return bestaClass;
    }

    public void setBestaClass(Object bestaClass) {
        this.bestaClass = this.bestaClass.getClass().getSimpleName();
    }


}
