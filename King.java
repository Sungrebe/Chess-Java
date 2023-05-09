import java.util.ArrayList;


/**
* The King class is responsible for the King chess pieces
*/
public class King extends Piece {

    /**
     * the King constructor
     * @param coordinate the coordinate of the piece
     * @param side the side that the piece is on
     */
   public King(String coordinate, String side) {
       super("king", coordinate, side);
   }

   /**
    * the moves method determines what moves the King can make
    * @return an arraylist with moves that the King can make
    */
   public ArrayList<String> moves() {
       ArrayList<String> kingMoves = new ArrayList<String>();
       //used to easily switch between column values
       String[] column = {"A", "B", "C", "D", "E", "F", "G", "H"};
       int coordX = Integer.parseInt(getCoordinate().substring(1, 2));
       String coordY = getCoordinate().substring(0, 1);
       int coordYIndex = 0;
       //use this to find the index value corresponding to the column letter
       for (int i = 0; i < column.length; i++){
           if (coordY.equals(column[i])){
               coordYIndex = i;
           } 
       }

       System.out.println(coordYIndex);

       System.out.println(coordY + "" + coordX);

       //the spot in front of the king
       kingMoves.add(coordY + (coordX + 1));
       //the spot behind the king
       kingMoves.add(coordY + (coordX - 1));
       
       if (coordYIndex - 1 >= 0) {
        //the spot to the left of the king
       kingMoves.add(column[coordYIndex - 1] + coordX);
        //the spot to the top left diagonal
        kingMoves.add(column[coordYIndex - 1] + (coordX + 1));

        //the spot to the botton left diagonal
        kingMoves.add(column[coordYIndex - 1] + (coordX - 1));
       }

       if (coordYIndex + 1 < 8) {
        //the spot to the right of the king
       kingMoves.add(column[coordYIndex + 1] + coordX);
       }

       if (coordYIndex + 1 < 8) {
        //the spot to the top right diagonal
       kingMoves.add(column[coordYIndex + 1] + (coordX + 1));
        //the spot to the bottom right diagonal
        kingMoves.add(column[coordYIndex + 1] + (coordX - 1));
       }

       return kingMoves;
   }
}



