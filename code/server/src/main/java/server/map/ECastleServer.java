package server.map;

import server.main.EPlayerNumber;
import server.main.Player;

public enum ECastleServer {
	Player1Castle,
	Player2Castle,
	NoCastle;
	
	public static ECastleServer getCastleFromEPlayer(EPlayerNumber player) {
		if (player == EPlayerNumber.Player1) return Player1Castle;
		if (player == EPlayerNumber.Player2) return Player2Castle;
		return NoCastle; 
	}
	
}
