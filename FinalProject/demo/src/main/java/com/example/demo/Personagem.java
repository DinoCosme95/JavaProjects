package com.example.demo;

import java.util.Random;

public class Personagem extends Tabuleiro {

    private static final Random aleatorio = new Random();

    private String nome;
    private int hp;
    private int armadura;
    private String pClasse;

    public String getpClasse() {
        return this.getClass().getSimpleName();
    }

    public void setpClasse(String pClasse) {

        this.pClasse = pClasse;
    }

    public Personagem(String nome, int hp, int armadura, String pClasse) {
        this.nome = nome;
        this.hp = hp;
        this.armadura = armadura;
        this.pClasse = this.getClass().getSimpleName();
    }

    public Personagem() {
    }

    public Personagem(String nome, int hp, int armadura) {
        this.nome = nome;
        this.hp = hp;
        this.armadura = armadura;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getArmadura() {
        return armadura;
    }

    public void setArmadura(int armadura) {
        this.armadura = armadura;
    }

    @Override
    public String toString() {
        return "Personagem{" +
                "nome='" + nome + '\'' +
                ", hp=" + hp +
                ", armadura=" + armadura +
                '}';
    }

public void levarDano(int dano){
        hp -= dano;

        if (hp < 0){
            hp = 0;
        }
}

    @Override
    public int atacar() {

        return 0;
    }

    @Override
    public int danoRecebido(int ataque, int armadura) {
        if (ataque > armadura){
            return ataque - armadura;
        }
        return 0;
    }



    public  int rolarDado(){
        return aleatorio.nextInt(101);
    }
}
