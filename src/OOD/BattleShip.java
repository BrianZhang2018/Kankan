package OOD;

import java.util.HashMap;
import java.util.List;

/**
 * https://github.com/danielmiaomtm/Algorithm-OOD-java/blob/master/OOD%20Battleship.java
 * 
 * Created by brianzhang on 1/20/19.
 */
public class BattleShip {
    public static void main(String[] args){}
}

enum AttackResponse {
    HIT, MISS, SUNK, END
}

class Coordination {
    private int x;
    private int y;
}

class Ship {
    private List<Coordination> parts;
    GameBoard board;

    void addPart(Coordination coor) {
        parts.add(coor);
    }

    void removePart(Coordination coor) {
        parts.remove(coor);
    }
    boolean hasSunk() {
        return parts == null || parts.size() == 0;
    }
}

class Player {
    int id;
    GameBoard board;

    AttackResponse Attack(GameBoard rivalBoard, Coordination shot) {
        if(!rivalBoard.checkCoordinationInfo(shot)) {
            return AttackResponse.MISS;
        }
        else {
            Ship hittedShip = rivalBoard.getRelatedShip(shot);
            rivalBoard.removeRelation(shot);
            if(!rivalBoard.isShipSunk(hittedShip)) {
                return AttackResponse.HIT;
            }
            else if(!rivalBoard.isGameEnd()){
                return AttackResponse.SUNK;
            }
            else {
                return AttackResponse.END;
            }
        }
    }

}

class GameBoard {
    //List<Ship> ships;
    //boolean[][] hasPart;
    private HashMap<Coordination, Ship> relations;
    private Player player;

    boolean checkCoordinationInfo(Coordination coor) {
        return relations.containsKey(coor);
    }

    void removeRelation(Coordination coor) {
        relations.remove(coor);
    }

    Ship getRelatedShip(Coordination coor) {
        if(relations.containsKey(coor)){
            return relations.get(coor);
        }
     /*   else {
            throw new Exception("wrong coordination");
        }*/
     return null;
    }
    boolean isShipSunk(Ship ship) {
        return !relations.containsValue(ship);
    }

    boolean isGameEnd() {
        return relations == null || relations.size() == 0;
    }
}