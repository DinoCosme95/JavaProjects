package com.example.demo;

import com.example.demo.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.*;

public class ProjetoFinalController implements Initializable {
    @FXML
    private ChoiceBox<String> heroChoice;
    @FXML
    private ChoiceBox<String> bestaChoice;

    @FXML
    private TableView heroView;

    @FXML
    private TableColumn nomeColumn;
    @FXML
    private TableColumn classe;
    @FXML
    private TableColumn hpArmor;
    @FXML
    TableColumn heroArmorColumn;


    @FXML
    private TextField heroName;
    @FXML
    private TextField heroHp;
    @FXML
    private TextField heroArmor;

    @FXML
    private TextField bestaName;
    @FXML
    private TextField bestaHp;
    @FXML
    private TextField bestaArmor;

    @FXML
    private TableView bestaView;
    @FXML
    private TableColumn bestaNameColumn;
    @FXML
    private TableColumn bestaHpColumn;
    @FXML
    private TableColumn bestaClasseColumn;
    @FXML
    private TableColumn bestaArmorColumn;
    @FXML
    private TextArea combatText;


    private final List<Herois> listaHerois = new ArrayList<>();
    private final List<Bestas> listaBestas = new ArrayList<>();


    public ProjetoFinalController() {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        bestaView.setEditable(true);


        List<Herois> heroisList = new ArrayList<>();
        heroisList.add(new Elfos());
        heroisList.add(new Humanos());
        heroisList.add(new Hobbits());

        List<Bestas> bestasList = new ArrayList<>();
        bestasList.add(new Orques());
        bestasList.add(new Trolls());

        for (Herois heroi : heroisList) {
            heroChoice.getItems().add(heroi.getClass().getSimpleName());
        }

        for (Bestas bestas : bestasList) {
            bestaChoice.getItems().add(bestas.getClass().getSimpleName());
        }


    }
    //-----------------------------------------------------LOGICA DE BATALHA---------------------------------------------------------------------------

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    @FXML
    public void onClickStartBattle(){
        int turno = 1;
        boolean heroisVivos = personagemViva(listaHerois);
        boolean bestasVivas = personagemViva(listaBestas);


        while (heroisVivos && bestasVivas) {

            combatText.appendText("Turno " + turno + ": " +"\n");


            Iterator<Herois> heroisIterator = listaHerois.iterator();
            Iterator<Bestas> bestasIterator = listaBestas.listIterator();


            while (heroisIterator.hasNext() && bestasIterator.hasNext()) {

                Personagem herois = heroisIterator.next();
                Personagem bestas = bestasIterator.next();

                if (herois.getHp() > 0 && bestas.getHp() > 0) {
                    combatText.appendText("Luta entre "+herois.getNome()+"( Hp = "+herois.getHp()+" Armadura = "+herois.getArmadura()+") e "
                            +bestas.getNome()+"( Hp = "+bestas.getHp()+" Armadura = "+bestas.getArmadura()+")"+"\n");

                    int poderDeHeroi = herois.atacar();
                    int poderDeBestas = bestas.atacar();


                    if (herois instanceof Elfos && bestas instanceof Orques) {
                        poderDeHeroi += 10;
                    }

                    if (herois instanceof Hobbits && bestas instanceof Trolls) {
                        poderDeHeroi -= 5;
                    }

                    int danoHeroi = herois.danoRecebido(poderDeHeroi, bestas.getArmadura());
                    int danoBesta = bestas.danoRecebido(poderDeBestas, herois.getArmadura());

                    herois.levarDano(danoBesta);
                    bestas.levarDano(danoHeroi);


                    combatText.appendText( herois.getNome() +" saca "+ poderDeHeroi+" e tira "+danoHeroi +" de vida a "+bestas.getNome()
                            +"\n"+ bestas.getNome()+ " saca "+ poderDeBestas+" e tira "+danoBesta +" de vida a "+herois.getNome()+"\n");

                    if (herois.getHp() <= 0) {
                        heroisIterator.remove();
                    }

                    if (bestas.getHp() <= 0) {
                        bestasIterator.remove();
                    }
                }



            }
            heroisVivos = personagemViva(listaHerois);
            bestasVivas = personagemViva(listaBestas);
            turno++;


            if (!personagemViva(listaHerois) || !personagemViva(listaBestas)) {
                break;
            }

        }


        if (!personagemViva(listaBestas)) {
            combatText.appendText("Herois venceram esta batalha!");
        } else {
            combatText.appendText("Bestas venceram esta batalha!");

        }

        ChoiceBox<String> heroChoiceBox = new ChoiceBox<>();
        for (Herois heroi: listaHerois){
            heroChoiceBox.getItems().add(heroi.getNome());
        }


    }
    private static <T extends Personagem> boolean personagemViva(Collection<T> personagem) {
        for (Personagem vida : personagem) {
            if (vida.getHp() >= 0) {
                return true;
            }
        }

        return false;
    }

    //-----------------------------------------------------ADICIONAR PERSONAGENS HEROIS/BESTAS---------------------------------------------------------------------------

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @FXML
    public void onClickAddHero() {

        try {
            String nome = this.heroName.getText();
            String heroClass = heroChoice.getValue();
            Integer hp = Integer.parseInt(this.heroHp.getText());
            Integer armor = Integer.parseInt(this.heroArmor.getText());


            Herois herois = new Herois(nome, hp, armor, heroClass);
            System.out.println(herois);
            listaHerois.add(herois);


            nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
            classe.setCellValueFactory(new PropertyValueFactory<Herois, String>("heroClass"));
            hpArmor.setCellValueFactory(new PropertyValueFactory<>("hp"));
            heroArmorColumn.setCellValueFactory(new PropertyValueFactory<>("armadura"));

            ObservableList<Herois> data = FXCollections.observableList(listaHerois);
            heroView.setItems(data);


        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


    }

    @FXML
    public void onClickAddBesta() {

        try {
            String bestaNome = this.bestaName.getText();
            String bestaClasse = bestaChoice.getValue();
            Integer hp = Integer.parseInt(this.bestaHp.getText());
            Integer armor = Integer.parseInt(this.bestaArmor.getText());

            bestaNameColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
            bestaClasseColumn.setCellValueFactory(new PropertyValueFactory<>("bestaClass"));
            bestaHpColumn.setCellValueFactory(new PropertyValueFactory<>("hp"));
            bestaArmorColumn.setCellValueFactory(new PropertyValueFactory<>("armadura"));

            Bestas bestas = new Bestas(bestaNome, hp, armor, bestaClasse);
            listaBestas.add(bestas);

            ObservableList<Bestas> bestasData = FXCollections.observableList(listaBestas);
            bestaView.setItems(bestasData);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }
    //-----------------------------------------------------FUNCIONALIDADES DOS BOTOES--------------------------------------------------------------------------------------

    //------------------------------------------------------SUBIR/DESCER/ELIMINAR-------------------------------------------------------------------------------------------

    @FXML
    public void removeHero() {

        Herois selectedHero = (Herois) heroView.getSelectionModel().getSelectedItem();
        if (selectedHero != null) {
            listaHerois.remove(selectedHero);
            refreshTable();
        }
    }

    public void onClickMoveHeroUp() {

        int posicaoHeroi = heroView.getSelectionModel().getSelectedIndex();
        if (posicaoHeroi > 0) {
            Herois localHeroi = listaHerois.remove(posicaoHeroi);
            listaHerois.add(posicaoHeroi - 1, localHeroi);
            refreshTable();
            bestaView.getSelectionModel().select(posicaoHeroi - 1);
        }

    }

    public void onClickMoveHeroDown() {

        int posicaoHeroi = heroView.getSelectionModel().getSelectedIndex();
        if (posicaoHeroi >= 0 && posicaoHeroi < listaHerois.size() - 1) {
            Herois localHeroi = listaHerois.remove(posicaoHeroi);
            listaHerois.add(posicaoHeroi + 1, localHeroi);
            refreshTable();
            bestaView.getSelectionModel().select(posicaoHeroi + 1);
        }
    }

    private void refreshTable() {
        ObservableList<Herois> refreshData = FXCollections.observableList(listaHerois);
        heroView.setItems(refreshData);
    }


    @FXML
    public void removerBesta() {

        Bestas selectedBesta = (Bestas) bestaView.getSelectionModel().getSelectedItem();
        if (selectedBesta != null) {
            listaBestas.remove(selectedBesta);
            refreshBestaTable();

        }
    }

    public void onClickMoveBestaUp() {

        int posicaoBesta = bestaView.getSelectionModel().getSelectedIndex();
        if (posicaoBesta > 0) {
            Bestas localBesta = listaBestas.remove(posicaoBesta);
            listaBestas.add(posicaoBesta - 1, localBesta);
            refreshBestaTable();
            bestaView.getSelectionModel().select(posicaoBesta - 1);
        }

    }

    public void onClickMoveBestaDown() {

        int posicaoBesta = bestaView.getSelectionModel().getSelectedIndex();
        if (posicaoBesta >= 0 && posicaoBesta < listaBestas.size() - 1) {
            Bestas localBesta = listaBestas.remove(posicaoBesta);
            listaBestas.add(posicaoBesta + 1, localBesta);
            refreshBestaTable();
            bestaView.getSelectionModel().select(posicaoBesta + 1);
        }
    }

    private void refreshBestaTable() {
        ObservableList<Bestas> refreshBestaData = FXCollections.observableList(listaBestas);
        bestaView.setItems(refreshBestaData);
    }
}