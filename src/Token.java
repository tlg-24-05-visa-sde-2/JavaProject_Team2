public class Token {
    boolean teamSafeSpace;
    public TeamColor tokenTeamColor;
    int currentTokenLocation; // store current token location for player

    public void moveForward(int die){
        //TODO recognize white space move number of spaces equal to die roll
        /*
         * variable for moves = die
         * WHILE (moves > 0){
             * IF condition: if currentPosition plus 1 == teamColorSpace
             *      {skip that color}
             * ELSE IF condition: if currentPosition plus 1 == trigger space
             *      {call changeDirection()} <-- this sets teamSafeSpace true??
             * move token one space
             * moves = moves - 1}
         */
    }

    public void changeDirection(){
        //TODO change direction to safe zone towards home when safe space triggered
        /*
         * teamSafeSpace is now true?
         * call (moveFoward() or goHome()?) with remaining moves if any on the teamSafeSpace?
         *
         */
    }

    public void goHome(){
        //TODO move token piece to safe zone
        /*
         *  IF condition: space == teamSafeSapce
         *      {move forward}
         */
    }

    public void goToJail(){
        //TODO move token piece back to jail/start zone
        /*
         *  IF condition: Player1CurrentTokenLocation == Player2CurrentTokenLocation
         *          {send player}
         */
    }
}