package test.plateau.application;


import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.jeu.Epoque;
import model.jeu.Jeu;
import vue.VuePlateau;

public class Fenetre extends AnchorPane
{
	private StackPane stack;
	private ArrayList<VuePlateau> plateaux;
	private int plateauActuel;//index du plateau dans la liste des plateaux (0 � 3)
	private Button suiv, prec;
	private Label numPlateau;
	private ContentTabConstructions cTC = new ContentTabConstructions();
	private ContentTabInventions cTI = new ContentTabInventions();
	private ContentTabCartes CTCards = new ContentTabCartes();
	private Jeu modelJeu;
	private Label statusBar = new Label("Joueur1 - Lancez les d�s pour commencer");
	private VueDes des = new VueDes();

	public Fenetre(Jeu p_modelJeu)
	{
		modelJeu = p_modelJeu;
		
		TitledPane PanneauMarche;
		TitledPane PanneauCarte;
		TitledPane PanneauJoueur;
		
		Tab TabConstructions = new Tab("Constructions");
		Tab TabCartes = new Tab("Cartes");
		Tab TabInventions = new Tab("Inventions");
		TabPane TabsMarche = new TabPane(TabConstructions, TabInventions, TabCartes);
		TabsMarche.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		VBox VGauche = new VBox();
		VBox VMilieu = new VBox();
		VBox VDroite = new VBox();
		
		GridPane gridJoueurs = new GridPane();
		gridJoueurs.setHgap(40);
		gridJoueurs.setVgap(10);
		gridJoueurs.setAlignment(Pos.CENTER);
		gridJoueurs.setPadding(new Insets(20,10,10,10));
		
		for(int i =0; i<modelJeu.getNbJoueurs();++i)
		{
			ImageView avatar = new ImageView("textures/Avatar"+(i+1)+".jpg");
			Button echanger = new Button("Echanger");
			avatar.setFitWidth(50);
			avatar.setPreserveRatio(true);
			gridJoueurs.add(avatar, i, 0);
			gridJoueurs.add(new Label(modelJeu.getJoueurs().get(i).getNom()), i, 1);
			gridJoueurs.add(echanger, i, 2);
		}
		gridJoueurs.add(new Button("Fin du tour"), modelJeu.getNbJoueurs(), 1);


		plateauActuel = 0;
		numPlateau = new Label("VuePlateau n� :" + (plateauActuel + 1));
		plateaux = new ArrayList<>();
		
		plateaux.add(new VuePlateau(0, 0, modelJeu.getPlateaux().get(Epoque._1855)));
		plateaux.add(new VuePlateau(0, 0, modelJeu.getPlateaux().get(Epoque._1955)));
		plateaux.add(new VuePlateau(0, 0, modelJeu.getPlateaux().get(Epoque._1985)));
		plateaux.add(new VuePlateau(0, 0, modelJeu.getPlateaux().get(Epoque._2015)));
		
		stack = new StackPane(plateaux.get(plateauActuel));
		suiv = new Button("> Suivant");
		prec = new Button("Pr�c�dent <");
		
		
		
		TabConstructions.setContent(cTC);
		TabInventions.setContent(cTI);
		TabCartes.setContent(CTCards);

		
		VGauche.getChildren().add(TabsMarche);
		VGauche.getChildren().add(new Separator());
		VGauche.getChildren().add(des);
		VGauche.setMinWidth(300);
		VGauche.setPrefWidth(300);
		VGauche.setMaxWidth(300);
		VGauche.setId("VGauche");
		
		VMilieu.getChildren().add(numPlateau);
		VMilieu.getChildren().add(new HBox(10,prec, suiv));
		VMilieu.getChildren().add(stack);
		VMilieu.getChildren().add(gridJoueurs);
		VMilieu.setMinWidth(800);
		VMilieu.setPrefWidth(800);
		VMilieu.setMaxWidth(800);
		
		
		VDroite.getChildren().add(new ContentJoueur("textures/Avatar1.jpg", "joueur1", Color.YELLOW));
		VDroite.setMinWidth(300);
		VDroite.setPrefWidth(300);
		VDroite.setMaxWidth(300);
		VDroite.setId("VDroite");

		PanneauMarche = new TitledPane("March�", VGauche);
		PanneauMarche.setCollapsible(false);
		PanneauMarche.setPrefHeight(800);
		
		
		PanneauCarte = new TitledPane("Carte", VMilieu);
		PanneauCarte.setCollapsible(false);
		PanneauCarte.setPrefHeight(800);
		
		PanneauJoueur = new TitledPane("Joueur", VDroite);
		PanneauJoueur.setCollapsible(false);
		PanneauJoueur.setPrefHeight(800);
		
		statusBar.setPadding(new Insets(10));
		
		getChildren().add(new VBox(new HBox(PanneauMarche, PanneauCarte, PanneauJoueur),statusBar));
	
		
		// Gestion des evenements
		suiv.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				plateauActuel = (plateauActuel + 1)%4;
				System.out.println("pa :" + plateauActuel);
				stack.getChildren().removeAll(stack.getChildren());
				stack.getChildren().add(plateaux.get(plateauActuel));
				numPlateau.setText("VuePlateau n� :" + (plateauActuel + 1));

			}
		});

		prec.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				plateauActuel = (plateauActuel + 3)%4;
				System.out.println("pa :" + plateauActuel);
				stack.getChildren().removeAll(stack.getChildren());
				stack.getChildren().add(plateaux.get(plateauActuel));
				numPlateau.setText("VuePlateau n� :" + (plateauActuel + 1));

			}
		});
		
		des.getLancer().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				des.actualiserResultats(p_modelJeu.lancerDes());
				des.setEnabled(false);
				
			}
			
		});
	}
}
