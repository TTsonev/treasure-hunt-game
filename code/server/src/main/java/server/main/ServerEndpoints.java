package server.main;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import messagesbase.ResponseEnvelope;
import messagesbase.UniqueGameIdentifier;
import messagesbase.UniquePlayerIdentifier;
import messagesbase.messagesfromclient.PlayerHalfMap;
import messagesbase.messagesfromclient.PlayerMove;
import messagesbase.messagesfromclient.PlayerRegistration;
import messagesbase.messagesfromserver.GameState;
import server.exceptions.GenericExampleException;
import server.exceptions.HalfMapException;
import server.exceptions.MoveException;

@RestController
@RequestMapping(value = "/games")
public class ServerEndpoints {

	private static Logger loggerEndpoints = LoggerFactory.getLogger(ServerEndpoints.class);

	// GameID Generation
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody UniqueGameIdentifier newGame(
			@RequestParam(required = false, defaultValue = "false", value = "enableDebugMode") boolean enableDebugMode,
			@RequestParam(required = false, defaultValue = "false", value = "enableDummyCompetition") boolean enableDummyCompetition) {

		loggerEndpoints.info("Recieved GameID Generation Request");
		return GameCoordinator.createNewGame();
	}

	// Player Registration
	@RequestMapping(value = "/{gameID}/players", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody ResponseEnvelope<UniquePlayerIdentifier> registerPlayer(
			@Validated @PathVariable UniqueGameIdentifier gameID,
			@Validated @RequestBody PlayerRegistration playerRegistration) {
		
		loggerEndpoints.info("Recieved Player Registation Request for Game {}", gameID.getUniqueGameID());
		
		UniquePlayerIdentifier newPlayerID = GameCoordinator.registerPlayer(gameID, playerRegistration.getStudentFirstName(), 
																					playerRegistration.getStudentLastName(), 
																					playerRegistration.getStudentUAccount());
		
		ResponseEnvelope<UniquePlayerIdentifier> playerIDMessage = new ResponseEnvelope<>(newPlayerID);
		return playerIDMessage;
	}
	
	// HalfMap Transfer
	@RequestMapping(value = "/{gameID}/halfmaps", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody ResponseEnvelope<GameState> recieveHalfMap(
			@Validated @PathVariable UniqueGameIdentifier gameID,
			@Validated @RequestBody PlayerHalfMap playerHalfMap) {
		
		loggerEndpoints.debug("Recieved HalfMap Transfer Request for Game {} by Player {}", gameID.getUniqueGameID(), playerHalfMap.getUniquePlayerID());

		GameState gameState = GameCoordinator.transferHalfMap(gameID, playerHalfMap);
		return new ResponseEnvelope<GameState>(gameState);
	}
	
	
	// Status Request
	@RequestMapping(value = "/{gameID}/states/{playerID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody ResponseEnvelope<GameState> requestStatus(
			@Validated @PathVariable UniqueGameIdentifier gameID,
			@Validated @PathVariable UniquePlayerIdentifier playerID) {
				
		loggerEndpoints.debug("Recieved Status Update Request for Game {} by Player {}", gameID.getUniqueGameID(), playerID.getUniquePlayerID());

		GameState gameState = GameCoordinator.transferStatusRequest(gameID, playerID);
		return new ResponseEnvelope<GameState>(gameState);
	}
	
	
	// Moves Transfer
	@RequestMapping(value = "/{gameID}/moves", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody ResponseEnvelope<?> recieveMove(
			@Validated @PathVariable UniqueGameIdentifier gameID,
			@Validated @RequestBody PlayerMove playerMove) {
		
		loggerEndpoints.debug("Recieved Move Request for Game {} by Player {}", gameID.getUniqueGameID(), playerMove.getUniquePlayerID());

		GameCoordinator.transferMove(gameID, playerMove);
		
		return new ResponseEnvelope<>();
	}

	
	@ExceptionHandler({ GenericExampleException.class })
	public @ResponseBody ResponseEnvelope<?> handleException(GenericExampleException ex, HttpServletResponse response) {
		ResponseEnvelope<?> result = new ResponseEnvelope<>(ex.getErrorName(), ex.getMessage());
		loggerEndpoints.error("Error: ", ex);
		response.setStatus(HttpServletResponse.SC_OK);
		return result;
	}
	
}
