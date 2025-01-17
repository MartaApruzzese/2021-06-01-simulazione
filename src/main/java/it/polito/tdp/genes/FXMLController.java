/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.genes;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.genes.model.Adiacente;
import it.polito.tdp.genes.model.Genes;
import it.polito.tdp.genes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="cmbGeni"
    private ComboBox<Genes> cmbGeni; // Value injected by FXMLLoader

    @FXML // fx:id="btnGeniAdiacenti"
    private Button btnGeniAdiacenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtIng"
    private TextField txtIng; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	this.model.creaGrafo();
    	txtResult.setText("Grafo creato con "+this.model.numVertici()+" vertici e "+this.model.numArchi()+" archi.\n");
    	cmbGeni.getItems().clear();
    	for(Genes g: this.model.getVertici()) {
    		cmbGeni.getItems().add(g);
    	}
    }

    @FXML
    void doGeniAdiacenti(ActionEvent event) {
    	Genes partenza= cmbGeni.getValue();
    	if(partenza.equals(null)) {
    		txtResult.setText("Selezionare un gene dalla box");
    		return;
    	}
    	List<Adiacente> interazioni= new ArrayList<>(this.model.getGeniAdiacenti(partenza));
    	txtResult.appendText("\n\nGeni adiacenti a : "+partenza.getGeneId());
    	for(Adiacente a: interazioni) {
    		txtResult.appendText("\n"+a.getGene1()+"  peso: "+a.getPeso() );
    	}
    }

    @FXML
    void doSimula(ActionEvent event) {

    	Genes partenza= cmbGeni.getValue();
    	if(partenza==null) {
    		txtResult.setText("Inserire un gene!");
    		return;
    	}
    	
    	int n;
    	try {
    		n= Integer.parseInt(txtIng.getText());
    	}catch(NumberFormatException e) {
    		txtResult.appendText("Inserire un valore numerico.");
    		return;
    	}
    	
    	Map<Genes, Integer> studiati= model.simulaIngegneri(partenza, n);
    	
    	if(studiati==null) {
    		txtResult.appendText("Il gene selezionato è isolato!");
 
    	}
    	
    	txtResult.appendText("Risultato Simulazione: \n");
    	for(Genes g: studiati.keySet()) {
    		txtResult.appendText(g+" "+studiati.get(g)+ "\n");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbGeni != null : "fx:id=\"cmbGeni\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnGeniAdiacenti != null : "fx:id=\"btnGeniAdiacenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtIng != null : "fx:id=\"txtIng\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
    
}
