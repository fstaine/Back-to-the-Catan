package vue;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import model.joueur.Joueur;
import vue.jeu.DefaultButton;

import java.util.ArrayList;

public class MenuPrincipalView extends AnchorPane {

	private GridPane buttonGrid, parametresGrid;
	private Spinner<Integer> spinNbJoueurs;
	private DefaultButton nouvellePartie, regles, quitter;
	private TitledPane parametres;
	private DefaultButton valider;
	private ArrayList<TextField> nomsJoueurs;


	public MenuPrincipalView()
	{
		nomsJoueurs = new ArrayList<>();
		valider = new DefaultButton("Valider");
		
		parametres = new TitledPane("Paramètres de la partie", null);
		parametres.setPrefWidth(350);
		parametres.setPadding(new Insets(10, 10, 10, 10));
		parametres.setTranslateX(75);
		parametres.setTranslateY(500);
		parametres.setCollapsible(false);
		parametres.setVisible(false);
		spinNbJoueurs = new Spinner<>(2, 4, 4);
		spinNbJoueurs.setPrefWidth(75);
		spinNbJoueurs.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event)
			{

				actualiseJoueurs();
			}
		});
		
		parametresGrid = new GridPane();
		parametresGrid.setHgap(10);
		parametresGrid.setVgap(10);

		
		parametresGrid.getChildren().removeAll(parametresGrid.getChildren());
		parametresGrid.add(new Label("Nombre de Joueurs (2 à 4) :"), 0, 0);
		for (int i = 0; i < 4; ++i)
		{
			TextField nomJoueur = new TextField("Joueur " + (i + 1));
			nomJoueur.setEditable(true);
			parametresGrid.add(new Label("Joueur " + (i + 1) + " :"), 0, (i + 1));
			nomsJoueurs.add(nomJoueur);
			parametresGrid.add(nomJoueur, 1, i + 1);
			
		}
		parametresGrid.add(spinNbJoueurs, 1, 0);
		parametresGrid.add(valider, 1, 6);
		parametres.setContent(parametresGrid);

		nouvellePartie = new DefaultButton("Nouvelle Partie");
		nouvellePartie.setId("bouttonMenuPrincipal");
		nouvellePartie.setPrefWidth(100);
		regles = new DefaultButton("Règles");
		regles.setId("bouttonMenuPrincipal");
		regles.setPrefWidth(100);
		quitter = new DefaultButton("Quitter");
		quitter.setId("boutonMenuPrincipal");
		quitter.setPrefWidth(100);


		buttonGrid = new GridPane();
		buttonGrid.setVgap(5);
		buttonGrid.setTranslateX(200);
		buttonGrid.setTranslateY(400);
		setBackground(new Background(new BackgroundImage(new Image(URL.bg_menu), null, null, null, null)));

		buttonGrid.add(nouvellePartie, 0, 0);
		buttonGrid.add(regles, 0, 1);
		buttonGrid.add(quitter, 0, 2);
		
		getChildren().add(buttonGrid);
		getChildren().add(parametres);
		

	}
	
	private void actualiseJoueurs()
	{
		for (int i = 0; i < 4; ++i)
		{
			nomsJoueurs.get(i).setVisible(i < spinNbJoueurs.getValue());
			if (!(nomsJoueurs.get(i).isVisible()))
			{
				nomsJoueurs.get(i).setText("Joueur "+(i+1));
			}

		}
	}
	
	public ArrayList<String> getNomJoueurs()
	{
		ArrayList<String> joueurs = new ArrayList<>();
		for (TextField tf : nomsJoueurs)
		{
			if (!(tf.getText().equals(""))&& tf.isVisible())
				joueurs.add(tf.getText());
		}
			
		return joueurs;
	}
	
	public void setParametersVisible()
	{
		parametres.setVisible(true);
	}
	
	public DefaultButton getNouvellePartie()
	{
		return nouvellePartie;
	}

	public DefaultButton getRegles()
	{
		return regles;
	}

	public DefaultButton getQuitter()
	{
		return quitter;
	}
	
	public DefaultButton getValider()
	{
		return valider;
	}


}
