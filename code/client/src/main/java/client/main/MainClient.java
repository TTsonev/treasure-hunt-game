package client.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import client.mvc.CLI;
import client.mvc.ClientController;
import client.mvc.ClientModel;
import messagesbase.UniqueGameIdentifier;

public class MainClient {

	private static Logger loggerMain = LoggerFactory.getLogger(MainClient.class);

	public static void main(String[] args) {

		loggerMain.warn("Arguments: {} {} {}", args[0], args[1], args[2]);
		
		String serverBaseUrl = args[1];
		UniqueGameIdentifier gameId = new UniqueGameIdentifier(args[2]);
		
		ClientModel model = new ClientModel();
		ClientController ai = new ClientController(serverBaseUrl, gameId, model);
		@SuppressWarnings("unused")
		CLI cli = new CLI(model, ai);

		loggerMain.info("Game Logic beginning...");
		ai.executeGameLogic();
		loggerMain.info("Game Logic ended.");
		
		System.exit(0);

	}
}
