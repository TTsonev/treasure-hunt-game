package client.network;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import client.main.Position;
import messagesbase.ResponseEnvelope;
import messagesbase.UniqueGameIdentifier;
import messagesbase.UniquePlayerIdentifier;
import messagesbase.messagesfromclient.EMove;
import messagesbase.messagesfromclient.ERequestState;
import messagesbase.messagesfromclient.PlayerHalfMap;
import messagesbase.messagesfromclient.PlayerHalfMapNode;
import messagesbase.messagesfromclient.PlayerMove;
import messagesbase.messagesfromclient.PlayerRegistration;
import messagesbase.messagesfromserver.GameState;
import reactor.core.publisher.Mono;

public class ClientNetwork {
	private WebClient baseWebClient;
	private UniquePlayerIdentifier uniqueID;
	private UniqueGameIdentifier uniqueGameID;
	
	private static Logger loggerNetwork = LoggerFactory.getLogger(ClientNetwork.class);

	
	public ClientNetwork(String serverBaseUrl, UniqueGameIdentifier gameId) {
		baseWebClient = WebClient.builder().baseUrl(serverBaseUrl + "/games")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE) 
				.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_XML_VALUE).build();
		
		this.uniqueGameID = gameId; 
	}

	
	public UniquePlayerIdentifier registerForGame() throws GameRegistrationException {
		PlayerRegistration playerReg = new PlayerRegistration("Trayan", "Tsonev", "trayant21");
		
		@SuppressWarnings("rawtypes")
		Mono<ResponseEnvelope> webAccess = baseWebClient.method(HttpMethod.POST).uri("/" + uniqueGameID.getUniqueGameID() + "/players")
				.body(BodyInserters.fromValue(playerReg)) // specify the data which is sent to the server
				.retrieve().bodyToMono(ResponseEnvelope.class); // specify the object returned by the server

		@SuppressWarnings("unchecked")
		ResponseEnvelope<UniquePlayerIdentifier> resultReg = webAccess.block();

		if (resultReg.getState() == ERequestState.Error) {
			loggerNetwork.error("Failed Registration: {}", resultReg.getExceptionMessage());
			throw new GameRegistrationException(uniqueGameID, resultReg.getExceptionMessage());
		} 
		else {
			uniqueID = resultReg.getData().get();
			loggerNetwork.warn("My Player ID: {}", uniqueID);
			return uniqueID;
		}
	}
	
	
	public GameState statusUpdate() throws Exception {
		@SuppressWarnings("rawtypes")
		Mono<ResponseEnvelope> stateRequest = baseWebClient.method(HttpMethod.GET)
				.uri("/" +uniqueGameID.getUniqueGameID() + "/states/" + uniqueID.getUniquePlayerID())
				.retrieve().bodyToMono(ResponseEnvelope.class); 
																																																																																												
		@SuppressWarnings("unchecked")
		ResponseEnvelope<GameState> requestResult = stateRequest.block();
			
		if (requestResult.getState() == ERequestState.Error) {
			loggerNetwork.error("Failed Status Update Request: {}", requestResult.getExceptionMessage());
			throw new Exception("Failed Status Update Request: {}" + requestResult.getExceptionMessage());
		}
		
		loggerNetwork.debug("Retrieved Game State: ", requestResult.getData().get().getGameStateId());
		return requestResult.getData().get();
				
	}
	
	
	public void sendHalfMap(Map<Position, PlayerHalfMapNode> halfMap) throws Exception {
		PlayerHalfMap playerHMap = new PlayerHalfMap(uniqueID, halfMap.values());	//converter???
		
		@SuppressWarnings("rawtypes")
		Mono<ResponseEnvelope> halfMapTransfer = baseWebClient.method(HttpMethod.POST).uri("/" + uniqueGameID.getUniqueGameID() + "/halfmaps")
				.body(BodyInserters.fromValue(playerHMap))
				.retrieve().bodyToMono(ResponseEnvelope.class); 

		ResponseEnvelope<?> resultHalfMapTransfer = halfMapTransfer.block();
		
		if (resultHalfMapTransfer.getState() == ERequestState.Error) {
			loggerNetwork.error("Failed HalfMap Transfer: {}", resultHalfMapTransfer.getExceptionMessage());
			throw new Exception("Failed HalfMap Transfer: " + resultHalfMapTransfer.getExceptionMessage());
		} 
		else {
			loggerNetwork.warn("HalfMap sent successfully!");
		}
	}
	
	
	public void sendMove(EMove moveDirection) throws Exception {
		PlayerMove playerMove = PlayerMove.of(uniqueID, moveDirection);
		
		@SuppressWarnings("rawtypes")
		Mono<ResponseEnvelope> moveTransfer = baseWebClient.method(HttpMethod.POST).uri("/" + uniqueGameID.getUniqueGameID() + "/moves")
				.body(BodyInserters.fromValue(playerMove))
				.retrieve().bodyToMono(ResponseEnvelope.class); 

		
		ResponseEnvelope<?> resultMove = moveTransfer.block();

		if (resultMove.getState() == ERequestState.Error) {
			loggerNetwork.error("Failed Move Transfer: {}", resultMove.getExceptionMessage());
			throw new Exception("Failed Move Transfer: " + resultMove.getExceptionMessage());
		} 
		else {
			loggerNetwork.debug("Move {} sent successfully!", moveDirection);
		}
	}
	


	
}
