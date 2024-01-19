package de.unistuttgart.iste.sqa.pse.sheet11.homework;

import de.hamstersimulator.objectsfirst.external.model.Hamster;
import de.hamstersimulator.objectsfirst.external.simple.game.SimpleHamsterGame;

import java.util.*;

/**
 * Class to implement homework exercises 1, 2 and 3 of sheet 11.
 *
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
        // this.reverseOrder();
//        this.sort();
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
     * <p>
     * Makes Paule walk in a line and pick up all grains until he has reached a wall. Once he reaches a wall, he takes a 180°
     * turn and walks back to his starting position, leaving the grains on each field in the exact order he has picked them up.
     */
    private void reverseOrder() {
        assert !(paule == null);
        final Queue<Integer> reversedOrderList = new LinkedList<>(pickLineAndCount());
        turnAround(paule);
        while (!reversedOrderList.isEmpty()) {
            if (reversedOrderList.size() > 1) {
                putMultipleGrains(paule,reversedOrderList.remove());
                paule.move();
            } else {
                putMultipleGrains(paule,reversedOrderList.remove());
            }
        }
        turnAround(paule);
    }

	/*@
	  @ requires paule !== null;
	  @ requires paule.frontIsClear();
	  @ ensures order of grains per tile hasn´t changed;
	  @ ensures paule.mouthIsEmpty();
	@*/

    /**
     * Makes paule walk a line and pick up all grains then makes him return and leave the grains exactly like he has picked them up.
     * <p>
     * Paule walks a line and picks up all grains per tile. Once he has reached a wall he takes a 180° turn and puts the
     * grains back down in the exact order he has picked them up.
     */
    private void inOrder() {
        final Stack<Integer> stack = new Stack<>();
        boolean reachedWall = false;
        //ending condition (similar to "#" in PDAs)
        stack.add(999);
        paule.move();
        while (stack.contains(999)) {
            if (!reachedWall) {
                reachedWall = walkForward(stack, reachedWall);
            } else {
                if (walkBack(stack))
                    return;
            }
        }
        turnAround(paule);
    }


    /**
     * Gets the amount of grains on a field and picks all the grains up
     *
     * @return returns
     */
    private int getAmountOfGrainsOnField(Hamster hamster) {
        int grainCount = 0;
        while (hamster.grainAvailable()) {
            hamster.pickGrain();
            grainCount++;
        }
        return grainCount;
    }


	/*@
	  @ requires paule !== null;
	  @ requires paule.frontIsClear();
	  @ ensures new order of grains on the field, sorted numerically;
	  @ ensures paule.mouthIsEmpty();
	@*/
    /**
     * Makes paule run in a line and back, putting the grains down from the smallest number of grains per tile to the highest;
     * <p>
     * paule runs a line and picks up grains until he reaches a wall. Once the wall is reached, he takes a 180° turn and moves.
     * He then proceeds to put down grains on each tile, sorting them by putting them down from the smallest amount to the highest amount of grains
     * per tile.
     */
    private void sort() {
        assert !(paule == null);
        SortedSet<Integer> sortedSet = new TreeSet<>(pickLineAndCount());
        turnAround(paule);
        for (Integer step : sortedSet) {
            paule.move();
            putMultipleGrains(paule,step);
        }
        turnAround(paule);
    }


    // TODO implement homework exercise 3 (b)


    // TODO Add Operation for homework exercise 3 (c) here

    // TODO Add required helper operations here

    private boolean walkForward(Stack<Integer> stack, boolean reachedWall) {
        int amountOfGrains = getAmountOfGrainsOnField(paule);
        stack.push(amountOfGrains);
        if (paule.frontIsClear())
            paule.move();
        else {
            turnAround(paule);
            reachedWall = true;
        }
        return reachedWall;
    }

    private boolean walkBack(Stack<Integer> stack) {
        int currentStackValue = stack.pop();
        if (currentStackValue == 999) {
            turnAround(paule);
            return true;
        }
        putMultipleGrains(paule, currentStackValue);
        paule.move();
        return false;
    }

    /*@
      @ requires numberOfGrains <= grains in paule's mouth;
      @ requires hamster !== null;
	  @ ensures new grainCount= grainCount- numberOfGrains;
     */
    /**
     * Lets the hamster put multiple Grains on the field
     *
     * @param hamster        the hamster to put down the grains
     * @param numberOfGrains the number of grains to be put to the ground
     */
    private void putMultipleGrains(Hamster hamster, int numberOfGrains) {

        for (int alreadyPutGrains = 0; alreadyPutGrains < numberOfGrains; alreadyPutGrains++) {
            hamster.putGrain();
        }
    }

	/*@
	  @ requires paule!== null;
	  @ ensures paule.getDirection.equals(/old paule.getDirection) = false;
	 */

    /**
     * Makes paule turn around 180°.
     */
    private void turnAround(Hamster hamster) {
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

    private LinkedList<Integer> pickLineAndCount() {
        assert (!(paule == null));
        final LinkedList<Integer> grainCountList = new LinkedList<>();
        while (paule.frontIsClear()) {
            grainCount = 0;
            while (paule.grainAvailable()) {
                paule.pickGrain();
                grainCount++;
            }
            grainCountList.add(grainCount);
            paule.move();
        }
        grainCount = 0;
        while (paule.grainAvailable()) {
            paule.pickGrain();
            grainCount++;
        }
        grainCountList.add(grainCount);
        return grainCountList;
    }
}