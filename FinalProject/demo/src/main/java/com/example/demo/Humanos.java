package com.example.demo;

public class Humanos extends Herois{
    public Humanos() {
    }

    public Humanos(String nome, int hp, int armadura) {
        super(nome, hp, armadura);
    }

    @Override
    public int atacar() {
        return super.atacar();
    }
}
