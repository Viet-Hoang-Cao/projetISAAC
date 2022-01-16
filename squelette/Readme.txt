Bonjour, 

Concernant le projet. Il n'est pas pas fini et beaucoup de choses devraient être refaite.
Cad que certaines choses ne sont pas faite de maniere ideal et pourrait avoir leurs propre objets.

Ce qui a était fait dans les grande lignes :

[]Deplacement dans le DJ
note : a refaire, la maniere dont sont gerer les portes. Probablement par un objet door.

[]IA extremement basique des monstres.
L'idée etait de creer un menu futur et de les rendre jouable pour le fun. D'ou le fait qu'ils soit herite de Hero.
Idealement si c'est a refaire Hero <- ABSTRACTmonstres <- les differents type de monstre
Cela permettrait a chaque monstres d'avoir acces a tout les patternes

[]Gestion partielle des collisions.

[]Gestion des differents Type de Room. (Monstre/Spawn/Boss/Magasin)
note : Ces dernieres ne sont pas absolument pas correctement relie entre elle, il faudrait refaire un diagramme de classe
au propre puis les relie par heritage

actuellement ca n'a aucun sens : Room <- Spawn <- MonsterRoom <- monsteroomdoors
Monster Room posse 3 booleen pour spawn, boss et magasin et une instance pour instancier un objet magasin
On est sur du fonctionnel, mais avec du temps, ca serait refait

Room  (agirait comme spawn)         <-  monsterRoom (bossroom == monsteroom mais un seul monstre)
(possederait les instance de Doors) 	(spikes, rocks)
(possederait les instance de Murs)      (Liste de monstre)
					(boolean bossroom)
                                    <- merchantRoom


[]Pour Hero : 
	- Ajout des projectile || Ne font pas de degats pour le moment
	- Ajout d'un inventaire
	- Les codes de triches sont fonctionnel

Certaine chose annexe ont ete faite sur le moment sans correctement prendre en compte le temps necessaire pour le projet :
[]Generation du DJ aleatoire. 
note : Cette fonction n'est pas optimise et traite de maniere particulierement brut la maniere de gerer le DJ
[]Minimap basique
[]Frame d'invulnerabilité

Note : Certaine fonctionnalite sont dans la TestRoom. C'est une classe a part entiere ou l'on peut tester les fonctionnalites
avant de les implanter. 