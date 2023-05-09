import java.util.ArrayList;


/**
* The King class is responsible for the King chess pieces
*/
public class King extends Piece {


   public King(String coordinate, String side) {
       super("king", coordinate, side);
   }


   public ArrayList<String> moves() {
       ArrayList<String> kingMoves = new ArrayList<String>();
       //used to easily switch between column values
       String[] column = {"A", "B", "C", "D", "E", "F", "G"};
       int coordX = Integer.parseInt(getCoordinate().substring(1, 2));
       String coordY = getCoordinate().substring(0, 1);
       int coordYIndex = 0;
       //use this to find the index value corresponding to the column letter
       for (int i = 0; i < column.length; i++){
           if (coordY.equals(column[i])){
               coordYIndex = i;
           } 
       }
           if (getSide().equals("black")){
               //the spot in front of the king
               kingMoves.add(coordY + (coordX + 1));
               //the spot behind the king
               kingMoves.add(coordY + (coordX - 1));
               //the spot to the left of the king
               kingMoves.add(column[coordYIndex - 1] + coordX);
               //the spot to the right of the king
               kingMoves.add(column[coordYIndex + 1] + coordX);
               //the spot to the top left diagonal
               kingMoves.add(column[coordYIndex - 1] + (coordX + 1));
               //the spot to the top right diagonal
               kingMoves.add(column[coordYIndex + 1] + (coordX + 1));
               //the spot to the bottom right diagonal
               kingMoves.add(column[coordYIndex + 1] + (coordX - 1));
               //the spot to the botton left diagonal
               kingMoves.add(column[coordYIndex - 1] + (coordX - 1));


           }
       return kingMoves;
   }
}



