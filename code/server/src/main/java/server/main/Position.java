package server.main;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import server.main.Position;
import messagesbase.messagesfromclient.EMove;
import messagesbase.messagesfromserver.FullMapNode;

public class Position {

	private int Xcoordinate;
	private int Ycoordinate;
	
	public Position(int x, int y) {
		//if (x<0 || x>=10 || y<0 || y>=20) throw new IllegalArgumentException("Invalid position coordinate values.");
		this.Xcoordinate = x;
		this.Ycoordinate = y;
	}
	
	public Position findNeighbor(EMove move) {
		switch (move) {
			case Left : return new Position(this.Xcoordinate-1, this.Ycoordinate);
			case Right : return new Position(this.Xcoordinate+1, this.Ycoordinate);
			case Up : return new Position(this.Xcoordinate, this.Ycoordinate-1);
			case Down : return new Position(this.Xcoordinate, this.Ycoordinate+1);
		}
		return null;
	}
	
	public Set<Position> findAllVisibleNeighbors() {
		Set<Position> visibleNeighbors = new HashSet<>();
		
		int xCoord = this.getX();
		int yCoord = this.getY();
		int[] shiftDirections = {-1,0,1};
		for (int xShift : shiftDirections) {
			for (int yShift : shiftDirections) {
				if (xShift == 0 && yShift == 0) continue;
				Position neighborPosition = new Position(xCoord+xShift, yCoord+yShift);
				visibleNeighbors.add(neighborPosition);
			}
		}
		
		return visibleNeighbors;
	}
	
	public int getX() {
		return Xcoordinate;
	}
	
	public int getY() {
		return Ycoordinate;
	}
	
	
    @Override
    public int hashCode() {
        return Objects.hash(Xcoordinate, Ycoordinate);
    }    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return (Xcoordinate == position.getX() && Ycoordinate == position.getY());
    }
    
	@Override
	public String toString() {
		return "Position [" + Xcoordinate + "|" + Ycoordinate + "]";
	}
    
}
