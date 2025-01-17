/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.imdb;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.imdb.model.Adiacenza;
import it.polito.tdp.imdb.model.Director;
import it.polito.tdp.imdb.model.Model;
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

    @FXML // fx:id="btnAdiacenti"
    private Button btnAdiacenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaAffini"
    private Button btnCercaAffini; // Value injected by FXMLLoader

    @FXML // fx:id="boxAnno"
    private ComboBox<Integer> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="boxRegista"
    private ComboBox<Director> boxRegista; // Value injected by FXMLLoader

    @FXML // fx:id="txtAttoriCondivisi"
    private TextField txtAttoriCondivisi; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
 
    	txtResult.clear();
    	
    	int anno = this.boxAnno.getValue();
    	
    	if (anno == 0) {
    		txtResult.appendText("errore, selezionare un anno ");
    		return;
    	}
    	
    	String msg = this.model.creag(anno);
    	txtResult.appendText(msg);
    	
    	
    	
    	this.boxRegista.getItems().clear();
    	this.boxRegista.getItems().addAll(this.model.getdire());
    	
    }

    @FXML
    void doRegistiAdiacenti(ActionEvent event) {
  
    	
    	Director d = this.boxRegista.getValue();
    	
    	if(d == null) {
    		txtResult.appendText("errore, selelzionare un dire");
    		return;
    	}
    	
    	List<Adiacenza> a = this.model.getAdiacenti(d);
    	txtResult.appendText("Registi Adiacenti: \n"+a);
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	/*	
    	txtResult.clear();
    	Integer a = this.boxAnno.getValue();
    	if(a==null) {
    		txtResult.appendText("Seleziona un anno!");
    		return;
    	}
    	Director d = this.boxRegista.getValue();
    	if(d==null) {
    		txtResult.appendText("seleziona un regista!");
    		return;
    	}
    	
    	model.getArchi(a);
    	txtResult.appendText("Regista Adiacente di: \n"+ d);
    	txtResult.appendText("\n"+this.model.getAdiacenza(d, a) );
    	
*/
    }

    @FXML
    void doRicorsione(ActionEvent event) {
   /* 	txtResult.clear();
    	Director r = this.boxRegista.getValue();
    	
    	try {
    		int c = Integer.parseInt(txtAttoriCondivisi.getText());
    		List<Director> lista = this.model.cercaMAx(r, c);
    		for(Director d: lista) {
        		txtResult.appendText("attori connesi :\n"+ d.toString()+"\n");
        		
        	}
    		
    	}catch(NumberFormatException e) {
    		txtResult.appendText("Inserisci un numero di attori condivisi valido");
    	}
    	

    	txtResult.appendText("\n\n  #attori condivisi: "+this.model.getlottimo());
*/
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAdiacenti != null : "fx:id=\"btnAdiacenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaAffini != null : "fx:id=\"btnCercaAffini\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxRegista != null : "fx:id=\"boxRegista\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAttoriCondivisi != null : "fx:id=\"txtAttoriCondivisi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
   public void setModel(Model model) {
    	
    	this.model = model;
    	
    	for(int i = 2004; i<=2006; i++) {
    		this.boxAnno.getItems().add(i);
    	}
    }
    
}
