package vue.jeu.plateau;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import model.jeu.Case;
import model.joueur.Ressource;
import vue.jeu.Constants;

public class ViewCase extends Group {

	private Case m_case;
	private ImageView imgRessource;

	public ViewCase(float p_x, float p_y, Case p_case)
	{
		super();
		m_case = p_case;
		setTranslateX(p_x + Constants.roadWidth/2);
		setTranslateY(p_y + Constants.roadWidth/2);
		
		/*Affichage de l'image correspondant à la ressource*/
		imgRessource = new ImageView("textures/hex" + m_case.getRessource() + ".png");
		imgRessource.setFitHeight(Constants.hexHeight - Constants.roadWidth);
		imgRessource.setFitWidth(Constants.hexWidth - Constants.roadWidth);
		
		getChildren().add(imgRessource);
		
		if(m_case.getRessource()!=Ressource.Autoroute)//on ajoute un numéro que s'il ne s'agit pas d'une case autoroute
		{
		
		/*Affichage du numéro*/
		double milieuX = imgRessource.getFitWidth()/2;
		double milieuY = imgRessource.getFitHeight()/2;
		Circle cercleNoir = new Circle(milieuX,milieuY+15,10,Color.BLACK);
		Circle cercleBlanc = new Circle(milieuX,milieuY+15,9,Color.WHITE);
		Label numero = new Label(""+m_case.getM_valeur());
		numero.setTranslateX(milieuX-7);
		numero.setTranslateY(milieuY+7);
		numero.setTextAlignment(TextAlignment.CENTER);
		numero.setId("gras");
		
		getChildren().add(cercleNoir);
		getChildren().add(cercleBlanc);
		getChildren().add(numero);
		}
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event)
			{
				System.out.println("Clicked on point : " + m_case);
			}
		});
	}
}
