package client.main;

import java.util.Objects;

public class Position {

	private int Xcoordinate;
	private int Ycoordinate;
	
	public Position(int x, int y) {
		//if (x<0 || x>=10 || y<0 || y>=20) throw new IllegalArgumentException("Invalid position coordinate values.");
		this.Xcoordinate = x;
		this.Ycoordinate = y;
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
