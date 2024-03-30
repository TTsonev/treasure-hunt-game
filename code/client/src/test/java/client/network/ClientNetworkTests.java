package client.network;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import messagesbase.UniqueGameIdentifier;


public class ClientNetworkTests {

	@Test
	public void givenInvalidGameID_attemptRegistration_expectedException() {
		
		Executable registrationCode = () -> {
			ClientNetwork network = new ClientNetwork("http://swe1.wst.univie.ac.at", new UniqueGameIdentifier("01234")); 
			network.registerForGame();
		};
		
		Assertions.assertThrows(Exception.class, registrationCode);
	}
}
