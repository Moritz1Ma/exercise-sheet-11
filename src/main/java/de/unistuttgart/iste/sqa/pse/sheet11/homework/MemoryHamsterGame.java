package de.unistuttgart.iste.sqa.pse.sheet11.homework;

import de.hamstersimulator.objectsfirst.external.model.Hamster;
import de.hamstersimulator.objectsfirst.external.simple.game.SimpleHamsterGame;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Class to implement homework exercises 1, 2 and 3 of sheet 11.
 * @ author Moritz Mairle, Quentin Hadar, Nora Jasharaj;
 */
public class MemoryHamsterGame extends SimpleHamsterGame {

	int grainCount;
	/**
	 * Creates a new MemoryHamsterGame.<br>
	 * Do not modify!
	 */
	public MemoryHamsterGame() {
		this.loadTerritoryFromResourceFile("/territories/order.ter");
		this.displayInNewGameWindow();
		game.startGame();
	}

	@Override
	protected void run() {
		// Comment any operation call out, to run the others on their own.
		this.inOrder();
		this.reverseOrder();
		this.sort();
		// TODO Add code for homework exercise 3 (d) here
	}


	/*@
	  @ requires paule != null;
	  @ requires paule.frontIsClear;
	  @ ensures paule.mouthEmpty();
	  @ ensures new order of grains on the field;
	*/
	/**
	 * Paule picks all grains and puts them down in the exact order as he has picked them up.
	 *
	 * Makes Paule walk in a line and pick up all grains until he has reached a wall. Once he reaches a wall, he makes a 180°
	 * turn and walks back to his starting position, leaving the grains on each field in the exact order he has picked them up.
	 *
	 */
    private void reverseOrder() {
		assert !(paule== null);
		final Queue<Integer> reversedOrderList= new LinkedList<>(pickLineAndCount());
		turnAround(paule);
		while(!reversedOrderList.isEmpty()){
			if(reversedOrderList.size()>1){
				setGrainPut(reversedOrderList.remove());
				paule.move();
			}
			else{
				setGrainPut(reversedOrderList.remove());
			}
		}
		turnAround(paule);
	}

	/*@
	  @ requires paule != null;
	  @ requires paule.frontIsClear();
	  @ ensures new order of grains on the field;
	  @ ensures paule.mouthIsEmpty();
	@*/
	/**
	 * Makes paule walk a line and pick up all grains then makes him return and leave the grains exactly like he picked them up.
	 *
	 * Paule walks a line and picks up all grains per field. Once he has reached a wall he takes a 180° turn and puts the
	 * grains back down in the exact order he has picked them up.
	 */
	private void inOrder() {
		final LinkedList<Integer> orderedList= new LinkedList<>(pickLineAndCount());
		turnAround(paule);
		while (!orderedList.isEmpty()){
			if (orderedList.size()>1){
				setGrainPut(orderedList.removeLast());
				paule.move();
			}
			else{
				setGrainPut(orderedList.removeLast());
			}
		}
		turnAround(paule);
	}
	/*@
	  @
	  @
	  @
	@*/
	/**
	 * TODO add documentation here.
	 */
	private void sort() {
		// TODO implement homework exercise 3 (b)
	}

	// TODO Add Operation for homework exercise 3 (c) here

	// TODO Add required helper operations here

	/*@
	  @ requires paule!== null;
	  @ ensures paule.getDirection= new Direction;
	 */
	/**
	 * Makes paule turn around 180°.
	 *
	 */
	private void turnAround(Hamster hamster){
		assert (paule != null);
		hamster.turnLeft();
		hamster.turnLeft();
	}

	/*@
	  @ requires grainCount >0;
	  @ requires paule.grainAvailable();
	  @ requires grainCountList.isEmpty();
	  @ ensures !grainCountList.isEmpty();
	  @ ensures grainCount is added to grainCountList;
	@*/
	/**
	 * Makes paule move a line and pick up every grain and add the number of grains per field to the linkedList grainCountList;
	 *
	 * @ return grainCountList LinkedList that has saved the number of grains picked up per field;
	 */

	private LinkedList<Integer> pickLineAndCount(){
		assert(!(paule == null));
		final LinkedList <Integer>grainCountList= new LinkedList<>();
		while(paule.frontIsClear()){
			grainCount = 0;
			while(paule.grainAvailable()) {
				paule.pickGrain();
				grainCount++;
			}
			grainCountList.add(grainCount);
			paule.move();
		}
		grainCount = 0;
		while(paule.grainAvailable()) {
			paule.pickGrain();
			grainCount++;
		}
		grainCountList.add(grainCount);
		return grainCountList;
	}

	/*@
	  @ requires paule !== null;
	  @ requires count>0;
	  @ requires !paule.mouthIsEmpty();
	  @ ensures paule has put down the set number of grains;
	  @ ensures new grainCount= grainCount- count;
	 */
	/**
	 * Makes paule put down a wanted number of grains;
	 *
	 * @param count the number of grains to be put down;
	 */
	private void setGrainPut(int count){
		for(int i=0; i < count; i++){
			paule.putGrain();
		}
	}
	}


