package client.mvc;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.withSettings;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import client.main.Position;
import client.main.StaticMapProvider;
import messagesbase.UniqueGameIdentifier;
import messagesbase.UniquePlayerIdentifier;
import messagesbase.messagesfromserver.EPlayerGameState;
import messagesbase.messagesfromserver.FullMap;
import messagesbase.messagesfromserver.FullMapNode;
import messagesbase.messagesfromserver.GameState;
import messagesbase.messagesfromserver.PlayerState;

public class CLITests {
	private final static int outputSize = 1570; 
	@Test
	public void givenGameStateWithHalfMap_updateModel_expectedMapVisualizationInCLI() {
	// TAKEN FROM https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
	// Redirect output of Sytem.out.printl from console to a OutputStream so that it can be evaluated in the Unit Test
		
		// arrange
		String serverBaseUrl = "http://swe1.wst.univie.ac.at";
		UniqueGameIdentifier gameId = new UniqueGameIdentifier("01234");
		ClientModel mockModel = Mockito.spy(ClientModel.class);
		mockModel.setGameStateParser(new UniquePlayerIdentifier("01234"));
		ClientController mockController = Mockito.mock(ClientController.class, withSettings().useConstructor(serverBaseUrl, gameId, mockModel));
		@SuppressWarnings("unused")
		CLI cli = new CLI(mockModel, mockController);
		
		Map<Position, FullMapNode> map = StaticMapProvider.getStaticMap();
		
		PlayerState playerState = new PlayerState("John", "Johnson", "johnj", EPlayerGameState.MustAct, new UniquePlayerIdentifier("placeholder1"), false);
		Collection<PlayerState> playerStateColl = new HashSet<>();
		playerStateColl.add(playerState);
		
		GameState gameState = new GameState(new FullMap(map.values()), playerStateColl, "halfMapStateID");
		
		// TAKEN FROM START https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        // TAKEN FROM END https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
        
		// act
        mockModel.setGameState(gameState);
        
		// assert
        String consoleOutput = outputStream.toString().trim();
		assertThat(consoleOutput.length(), is(equalTo(outputSize)));
		Mockito.verify(mockModel, Mockito.atLeastOnce()).setGameState(gameState);
	} 
	
	
	@Test
	public void givenServerMessage_updateModel_expectedDisplayMessageInCLI() {
		// arrange
		String serverBaseUrl = "http://swe1.wst.univie.ac.at";
		UniqueGameIdentifier gameId = new UniqueGameIdentifier("01234");

		ClientModel mockModel = Mockito.spy(ClientModel.class);
		ClientController mockController = Mockito.mock(ClientController.class, withSettings().useConstructor(serverBaseUrl, gameId, mockModel));
		@SuppressWarnings("unused")
		CLI cli = new CLI(mockModel, mockController);
		
		// TAKEN FROM START https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        // TAKEN FROM END https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
        
        String message = "You Won :)";
        
		// act
        mockModel.setServerMessage(message);
        
		// assert
        String consoleOutput = outputStream.toString().trim();
		assertThat(consoleOutput, is(equalTo(message)));
		Mockito.verify(mockModel, Mockito.atLeastOnce()).setServerMessage(message);
	} 
}
