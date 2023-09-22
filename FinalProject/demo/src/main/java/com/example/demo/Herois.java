package com.example.demo;

public class Herois extends Personagem  {

    private Object heroClass;

    public Object getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(Object heroClass) {
        this.heroClass = heroClass.getClass().getSimpleName();
    }

    public Herois(String nome, int hp, int armadura, String heroClass) {
        super(nome, hp, armadura, heroClass);
        this.heroClass = heroClass;
    }



    public Herois() {
    }

    public Herois(String nome, int hp, int armadura) {
        super(nome, hp, armadura);
    }

    @Override
    public int atacar() {
        int dado1 = rolarDado();
        int dado2 = rolarDado();

        return Math.max(dado1, dado2);
    }

    public String getName() {

        return getNome();
    }


}
