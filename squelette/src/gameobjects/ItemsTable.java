package gameobjects;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import items.*;

public class ItemsTable {
	
	List <Item> DropListRoom;
	List <Item> DropListMerchant;
	List <Item> DropListBossRoom;
	

	public ItemsTable() {
		this.DropListRoom = new ArrayList<Item>(); //L'instanciation se fera une fois
		/*
		 * Ok, donc celle la elle va merite une explication. Chaque item va avoir un "poid"
		 * Une iteration dans la liste, poid == 1, 2 iteration poid == 2
		 * Les poids les faible sont donc les items les plus rare
		 * Il y aura une initialisation de la table au lancement du jeu puis il n'y aura que des acces a celle-ci
		 * d'ou une arraylist.  Il sera rajouter une Lise d'item specifique au marchant. Cela evitera de boucler tant qu'on ne trouve pas
		 * un item vendable
		 */
		this.DropListMerchant = new ArrayList<Item>();
		this.DropListBossRoom = new ArrayList<Item>();
		InitDropListRoom();
		InitDropListMerchant();
		InitDropListBoss();
		
	}
	
	private void InitDropListRoom() {
		DropListRoom.add(new Bomb());
		DropListRoom.add(new Bomb());
		DropListRoom.add(new Bomb());
		
		DropListRoom.add(new Dime());
		
		DropListRoom.add(new Half_Heart());
		DropListRoom.add(new Half_Heart());
		DropListRoom.add(new Half_Heart());
		
		DropListRoom.add(new Hp_Up());
		
		DropListRoom.add(new Jesuis_Juice());
		
		DropListRoom.add(new Key());
		
		DropListRoom.add(new Lunch());
		DropListRoom.add(new Lunch());
		
		DropListRoom.add(new Magic_Mushroom());
		
		DropListRoom.add(new Nickel());
		DropListRoom.add(new Nickel());
		
		DropListRoom.add(new Penny());
		DropListRoom.add(new Penny());
		DropListRoom.add(new Penny());
		
		DropListRoom.add(new Red_Heart());
		DropListRoom.add(new Red_Heart());
		
		DropListRoom.add(new Blood_of_the_Martyr());
		
	}
	private void InitDropListMerchant() {
		DropListMerchant.add(new Bomb());
		DropListMerchant.add(new Bomb());
		DropListMerchant.add(new Bomb());
		
		DropListMerchant.add(new Half_Heart());
		DropListMerchant.add(new Half_Heart());
		DropListMerchant.add(new Half_Heart());
		
		DropListMerchant.add(new Hp_Up());
		
		DropListMerchant.add(new Jesuis_Juice());
		
		DropListMerchant.add(new Key());
		DropListMerchant.add(new Key());
		
		DropListMerchant.add(new Lunch());
		
		DropListMerchant.add(new Magic_Mushroom());
		
		DropListMerchant.add(new Red_Heart());
		DropListMerchant.add(new Red_Heart());
		DropListMerchant.add(new Red_Heart());
		
		DropListMerchant.add(new Blood_of_the_Martyr());
	}
	
	public void InitDropListBoss() {
		
		DropListBossRoom.add(new Dime());
		
		DropListBossRoom.add(new Hp_Up());
		
		DropListBossRoom.add(new Jesuis_Juice());
		
		DropListBossRoom.add(new Magic_Mushroom());
		
		DropListBossRoom.add(new Blood_of_the_Martyr());
	}
	
	/**
	 * Ps : ne pas oublier de donner une position a l'item pour l'afficher. //TODO a faire dans Room
	 * @return un Item correspond a la DropList
	 */
	public Item dropofTheRoom() {
		Random rand = new Random();
		return DropListRoom.get(rand.nextInt(DropListRoom.size()));
	}
	/**
	 * Ps : c'est une linkedList 
	 * @return Une liste de 3 objet pour le magasin
	 */
	public List<Item> MerchantRoomSellingList() {
		Random rand = new Random();
		List<Item> r = new LinkedList<Item>();
		for(int i = 0 ; i<3; i++) {
			r.add(DropListMerchant.get(rand.nextInt(DropListMerchant.size())));
		}
		return r;
	}
	
	/**
	 * Ps : ne pas oublier de donner une position a l'item pour l'afficher.
	 * @return un Item correspond a la DropList de boss
	 */
	public Item dropofTheBossRoom() {
		Random rand = new Random();
		return DropListBossRoom.get(rand.nextInt(DropListBossRoom.size()));
	}
	

}
