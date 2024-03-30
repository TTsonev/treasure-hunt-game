package sever.map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import messagesbase.messagesfromclient.PlayerHalfMap;
import server.exceptions.GenericExampleException;
import server.main.EPlayerNumber;
import server.map.MapValidator;

public class MapValidatorTests {
	@Test
	public void givenHalfMapWithLessNodes_validateHalfMap_expectedException() {
		// arrange
		PlayerHalfMap playerHalfMap = StaticMapProvider.getHalfMapWrongSize();
		
		//act
		Executable mapValidation = () -> {
			MapValidator.validateHalfMap(playerHalfMap);
		};
	
		//assert
		Assertions.assertThrows(GenericExampleException.class, mapValidation);	
	}
	
	
	@Test
	public void givenHalfMapWithNotEnoughWater_validateHalfMap_expectedException() {
		// arrange
		PlayerHalfMap playerHalfMap = StaticMapProvider.getHalfMapLessWater();
		
		//act
		Executable mapValidation = () -> {
			MapValidator.validateHalfMap(playerHalfMap);
		};
	
		//assert
		Assertions.assertThrows(GenericExampleException.class, mapValidation);	
	}
	
	
	@Test
	public void givenHalfMapWrongShape_validateHalfMap_expectedException() {
		// arrange
		PlayerHalfMap playerHalfMap = StaticMapProvider.getHalfMapWrongShape();
		
		//act
		Executable mapValidation = () -> {
			MapValidator.validateHalfMap(playerHalfMap);
		};
	
		//assert
		Assertions.assertThrows(GenericExampleException.class, mapValidation);	
	}
	
	
	@Test
	public void givenHalfMapIslands_validateHalfMap_expectedException() {
		// arrange
		PlayerHalfMap playerHalfMap = StaticMapProvider.getHalfMapIsland();
		
		//act
		Executable mapValidation = () -> {
			MapValidator.validateHalfMap(playerHalfMap);
		};
	
		//assert
		Assertions.assertThrows(GenericExampleException.class, mapValidation);	
	}
	
	
	@Test
	public void givenHalfTooMuchWaterOnEdge_validateHalfMap_expectedException() {
		// arrange
		PlayerHalfMap playerHalfMap = StaticMapProvider.getHalfMapWaterEdge();
		
		//act
		Executable mapValidation = () -> {
			MapValidator.validateHalfMap(playerHalfMap);
		};
	
		//assert
		Assertions.assertThrows(GenericExampleException.class, mapValidation);	
	}
	
	
	@Test
	public void givenHalfNoCastle_validateHalfMap_expectedException() {
		// arrange
		PlayerHalfMap playerHalfMap = StaticMapProvider.getHalfMapNoCastle();
		
		//act
		Executable mapValidation = () -> {
			MapValidator.validateHalfMap(playerHalfMap);
		};
	
		//assert
		Assertions.assertThrows(GenericExampleException.class, mapValidation);	
	}
	
	
	@Test
	public void givenHalfCastleWrongTerrain_validateHalfMap_expectedException() {
		// arrange
		PlayerHalfMap playerHalfMap = StaticMapProvider.getHalfMapCastleOnWater();
		
		//act
		Executable mapValidation = () -> {
			MapValidator.validateHalfMap(playerHalfMap);
		};
	
		//assert
		Assertions.assertThrows(GenericExampleException.class, mapValidation);	
	}



}
