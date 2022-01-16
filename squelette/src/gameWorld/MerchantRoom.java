package gameWorld;

import java.util.List;

import gameobjects.Hero;
import items.*;
import libraries.StdDraw;
import resources.RoomInfos;

public class MerchantRoom {
	
	List<Item> EnVente;
	
	/**
	 *TODO le probleme du fait qu'il  n'y est que 2 objet ou 1 vient de la TABLE ITEM
	 * @param enVente Ne pas oublier de fournir les positions 2,3; 4,3; 6,3; pour les items
	 */
	public MerchantRoom(List<Item> enVente) {
		EnVente = enVente;
	}

	public void updateRoom(Hero h) {
		for (Item I : EnVente) {
			if(I.physics(h) && h.getInventaire().getGold()>= I.getPrice()) {
				h.getInventaire().setGold(h.getInventaire().getGold()-I.getPrice());
				I.effect(h);
				deleteObjetVendu(I);
				break; // S'il reste des  element a itere, la boucle crash. Il vaut mieux l'arretez a defaut de faire qqch de plus propre
			}
		}
	}
	
	public void drawRoom() {
		
		for (Item I : EnVente) {
			if(I.getPos()!=null) {
				I.drawitem();
				StdDraw.text(I.getPos().getX(), I.getPos().getY()-RoomInfos.HALF_TILE_SIZE.getY(), I.getPrice()+"");
			}
		}
	}
	
	public void deleteObjetVendu(Item I) {
		EnVente.remove(I);
	}

}
