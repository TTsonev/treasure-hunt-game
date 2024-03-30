package client.mvc;

import java.beans.PropertyChangeListener;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import client.main.Position;
import messagesbase.messagesfromserver.EFortState;
import messagesbase.messagesfromserver.EPlayerPositionState;
import messagesbase.messagesfromserver.ETreasureState;
import messagesbase.messagesfromserver.FullMapNode;

public class CLI {
	@SuppressWarnings("unused")
	private ClientController controller;
	private static Logger loggerCLI = LoggerFactory.getLogger(CLI.class);
	private int turnCounter;
	private static final int enemyHiddenTurns = 16;
	
	public CLI(ClientModel model, ClientController controller) {
		this.controller = controller;
		model.addPropertyChangeListener(modelChangedListener);
		turnCounter = 0;
	}

    final PropertyChangeListener modelChangedListener = event -> {

    		ClientModel model = (ClientModel) event.getSource();
	    	    
	   	String oldValue = (String) event.getOldValue();
	    	String newValue = (String) event.getNewValue();
	    		  
	    	if (oldValue.equals("message")) { 
	    		System.out.println(newValue);
	    	}
	    	
	    	else if (oldValue.equals(newValue) == false ) {
	    		turnCounter++;
	    		loggerCLI.debug("Displaying Game State: {}", newValue);
	    		printMap(model.getMap(), model.isFoundMyTreasure(), model.isFoundEnemyTreasure());			    	
	    	}
	    	
    };
    
    private void printNode(Entry<Position, FullMapNode> entry) {
		String backgroundColor = "";
		switch(entry.getValue().getTerrain()) {
			case Grass : {backgroundColor = "\u001B[42m"; break;}
			case Water : {backgroundColor = "\u001B[44m"; break;}
			case Mountain : {backgroundColor = "\033[100m"; break;}
		}
    	
    		String content = "    ";
    		if (entry.getValue().getFortState() == EFortState.MyFortPresent || entry.getValue().getFortState() == EFortState.EnemyFortPresent) {
    			content = " \ud83c\udff0 ";
    		}
    		if (entry.getValue().getPlayerPositionState().equals(EPlayerPositionState.MyPlayerPosition)) {
    			content = " \uD83C\uDFC3 ";
    			if (entry.getValue().getFortState() == EFortState.EnemyFortPresent) 
        			backgroundColor = "\033[43m";
    		}
    		if (entry.getValue().getPlayerPositionState().equals(EPlayerPositionState.EnemyPlayerPosition) && turnCounter >= enemyHiddenTurns) {
    			content = " \ud83d\ude08 ";
    			if (entry.getValue().getFortState() == EFortState.MyFortPresent) 
        			backgroundColor = "\033[41m";
    		}
    		if (entry.getValue().getPlayerPositionState().equals(EPlayerPositionState.BothPlayerPosition)) {
    			if (turnCounter >= enemyHiddenTurns) {
        			content = " \u2694 ";
        			if (entry.getValue().getFortState() == EFortState.MyFortPresent) 
            			backgroundColor = "\033[41m";
        			if (entry.getValue().getFortState() == EFortState.EnemyFortPresent) 
            			backgroundColor = "\033[43m";
    			} 
    			else content = " \uD83C\uDFC3 ";

    		}
    		if (entry.getValue().getTreasureState().equals(ETreasureState.MyTreasureIsPresent)) {
    			content = "  \u2666  ";
    		}
    		    		
    		System.out.print(backgroundColor + content + "\u001B[0m" + " ");
    }
    
    private void printMap(Map<Position, FullMapNode> map, boolean foundTreasure, boolean foundEnemyTreasure) {
        //System.out.print("\033[H\033[2J");
        //System.out.flush();
    		String myTreasureStatus = (foundTreasure) ? "\u2666 ]" : " ]" ;
    		String enemyTreasureStatus = (foundEnemyTreasure) ? "\u2666 ]" : " ]" ;
    		
    		System.out.println("My Treasure: [ " + myTreasureStatus);
    		System.out.println("Enemy Treasure: [ " + enemyTreasureStatus + '\n');

        int rowLimit = (int) map.entrySet().stream().map(entry -> entry.getKey().getY()).distinct().count();
    		for(int i=0; i<rowLimit; i++) {
    			final int row = i;
        		map.entrySet().stream().filter(entry -> entry.getValue().getY()==row)
        							   .sorted(Comparator.comparing(entry -> entry.getKey().getX()))
        							   .forEach(entry -> printNode(entry));
        		System.out.println("\n");
    		}
    		System.out.println("--------------------------------------------------------------------------------");
    }
    
}
