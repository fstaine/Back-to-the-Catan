package vue.jeu;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.jeu.Jeu;
import model.joueur.Invention;
import model.joueur.Joueur;
import model.joueur.Ressource;

public class ContentJoueur extends GridPane implements Desactivable {

	private Jeu m_jeu;
	private Fenetre m_fenetre;

	private Button construire;
	private ImageView imgAvatar;
	private Label labelPseudo;
	private Label ressources;
	private Label aConstruire;
	private Label inventions;
	private Label cartes;

	public ContentJoueur(Joueur j, Jeu jeu)
	{
		m_jeu = jeu;
		m_fenetre = m_jeu.getFenetre();

		init(j);
		update(j);
	}

	public void init(Joueur j){
		imgAvatar = new ImageView(j.getAvatar());
		labelPseudo = new Label(j.getNom());
		setPadding(new Insets(20, 20, 20, 20));
		setHgap(15);
		setVgap(15);

		imgAvatar.setFitWidth(100);
		imgAvatar.setFitHeight(100);
		ressources = new Label("Ressources");
		ressources.setId("divisions");

		construire =new Button("Construire");
		construire.setOnMouseClicked((e)->{
			m_jeu.changeConstructionActive();
			if(m_jeu.isConstructionActrive())
				construire.setEffect(new InnerShadow());
			else
				construire.setEffect(null);
		});

		aConstruire = new Label("A construire");
		aConstruire.setId("divisions");

		inventions = new Label("Inventions :");
		inventions.setId("divisions");


		cartes = new Label("Cartes :");
		cartes.setId("divisions");
	}
	
	public void update(Joueur j)
	{
		getChildren().removeAll(getChildren());

		imgAvatar.setImage(new Image(j.getAvatar()));
		labelPseudo.setText(j.getNom());
		labelPseudo.setId("labelPseudo");

		add(imgAvatar, 0, 0);
		add(labelPseudo, 1, 0);
		add(ressources, 0, 2);

		add(new Label("Métal :" + j.nbRessource(Ressource.Metal)), 0, 3);
		add(new Label("Bois :" + j.nbRessource(Ressource.Bois)), 0, 4);
		add(new Label("Roue :" + j.nbRessource(Ressource.Roue)), 0, 5);
		add(new Label("Haut-Parleurs :" + j.nbRessource(Ressource.HautParleur)), 0, 6);
		add(new Label("Morceaux de Schéma :" + j.nbRessource(Ressource.MorceauSchema)), 0, 7);
		add(new Label("Antennes :" + j.nbRessource(Ressource.Antenne)), 1, 3);
		add(new Label("Plutonium :" + j.nbRessource(Ressource.Plutonium)), 1, 4);
		add(new Label("Aimant :" + j.nbRessource(Ressource.Aimant)), 1, 5);
		add(new Label("Ventilateur :" + j.nbRessource(Ressource.Ventilateur)), 1, 6);
		
		add(new Label("Routes :" + j.getNbRoutesAConstruire()), 0, 10);
		add(new Label("Autoroutes :" + j.getNbAutoroutesAConstruire()), 0, 11);
		add(new Label("Villages :" + j.getNbVillagesAConstruire()), 1, 10);
		add(new Label("Villes :" + j.getNbVillesAConstruire()), 1, 11);

		add(aConstruire, 0, 9);
		add(construire,1,9);
		add(inventions, 0, 13);
		
		add(new Label("Train :" + j.possedeInvention(Invention.Train)), 0, 14);
		add(new Label("Radio :" + j.possedeInvention(Invention.Radio)), 1, 14);
		add(new Label("Conv. temporel :" + j.possedeInvention(Invention.ConvecteurTemporel)), 0, 15);
		add(new Label("Hoverboard :" + j.possedeInvention(Invention.HoverBoard)), 1, 15);

		add(cartes, 0, 17);
		add(new Label("Dépl. Voleur :" + j.getNbCartesDeplacerVoleur()), 0, 18);
		add(new Label("Développement :" + j.getNbCartesDev()), 0, 19);
	}

	@Override
	public void desactiver()
	{
		construire.setDisable(true);
	}

	@Override
	public void activer()
	{
		construire.setDisable(false);
	}
}
