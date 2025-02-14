
package it.polito.tdp.borders;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    @FXML // fx:id="cmbStati"
    private ComboBox<Country> cmbStati; // Value injected by FXMLLoader

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	if(txtAnno.getText()!="") {
    		this.model.createGraph(Integer.parseInt(txtAnno.getText()));
    		txtResult.setText("Vertici del grafo: \n"+this.model.stampaVertici());
    		cmbStati.getItems().clear();
    		cmbStati.setPromptText("Seleziona uno stato...");
    		cmbStati.getItems().addAll(model.getAllCountry());    		
    	}else {
    		txtResult.setText("Inserire un anno");
    	}
    	
    }
    
    @FXML
    void doStatiRaggiungibili(ActionEvent event) {
    	txtResult.clear();
    	String stampa="";
    	if(cmbStati.getValue()!=null) {
    		for(Country c : this.model.visitaGrafoIt(cmbStati.getValue())) {
    			stampa+=c.toString()+"\n";
    		}
    		txtResult.setText("Stati raggiungibili da " + cmbStati.getValue() + " sono "+ this.model.visitaGrafoIt(cmbStati.getValue()).size() +":\n" + stampa);    		
    	}else {
    		txtResult.setText("Selezionare uno stato");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	cmbStati.getItems().clear();
    	cmbStati.setPromptText("Crea prima un grafo");
    }
}
