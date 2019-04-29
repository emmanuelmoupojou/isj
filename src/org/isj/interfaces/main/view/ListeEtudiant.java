package org.isj.interfaces.main.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.isj.traitementmetier.Isj;
import org.isj.traitementmetier.entites.Candidat;
import org.isj.traitementmetier.entites.Etudiant;
import org.isj.traitementmetier.facade.EtudiantFacade;

import java.net.URL;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ListeEtudiant implements Initializable {

    @FXML
    private TextField code;

    @FXML
    private TextField matricule;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private RadioButton masculin;

    @FXML
    private RadioButton feminin;

    @FXML
    private DatePicker date;

    @FXML
    private ComboBox<String> niveau;
    ObservableList<String> listN = FXCollections.observableArrayList("1", "2", "3", "4", "5");

    @FXML
    private ComboBox<String> filiere;
    ObservableList<String> list = FXCollections.observableArrayList("Ingénieur", "Licence professionnelle");

    @FXML
    private TextField nompere;

    @FXML
    private TextField profpere;

    @FXML
    private TextField telpere;

    @FXML
    private TextField nommere;

    @FXML
    private TextField profmere;

    @FXML
    private TextField telmere;

    @FXML
    private TableView<Etudiant> table;

    @FXML
    private TableColumn<Etudiant, String> matcolumn;

    @FXML
    private TableColumn<Etudiant, String> nomcolumn;

    @FXML
    private TableColumn<Etudiant, String> prenomcolumn;

    ObservableList<Etudiant> listeEtudiants=FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> attributs;
    ObservableList<String> listAttributs = FXCollections.observableArrayList();

    public ListeEtudiant() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        filiere.setItems(list);
        niveau.setItems(listN);

        try {
            listEtud();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listEtud() throws SQLException {
        List<Etudiant> etudiants = new EtudiantFacade().findAll();
        System.out.println(etudiants.get(0));

            listeEtudiants.addAll(etudiants);

        table.setItems(listeEtudiants);
        matcolumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getMatricule()));
        nomcolumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getNom()));
        prenomcolumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getPrenom()));

        ResultSetMetaData resultSetMetaData=new Isj().renvoyerChamp(Etudiant.class);
        ResultSetMetaData resultSetMetaData2=new Isj().renvoyerChamp(Candidat.class);

        for(int i=1;i<=resultSetMetaData.getColumnCount();i++) {
            try {
                listAttributs.add(resultSetMetaData.getColumnName(i));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        for(int i=1;i<=resultSetMetaData2.getColumnCount();i++) {
            try {
                listAttributs.add(resultSetMetaData2.getColumnName(i));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        attributs.setItems(listAttributs);

    }
}
