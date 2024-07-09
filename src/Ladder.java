/**
 * -----------------------------------------------------
 * ES234211 - Programming Fundamental
 * Genap - 2023/2024
 * Group Capstone Project: Snake and Ladder Game
 * -----------------------------------------------------
 * Class    : Q
 * Group    : 10
 * Members  :
 * 1. 5026231044 - Akhtar Fattan Widodo
 * 2. 5999232025 - Axel, Luis, Albert, Gil
 * 3. 5026231045 - Bagas Budisatrio
 * ------------------------------------------------------
 */

public class Ladder{
    int fromPosition;
    int toPosition;

    Ladder(int from, int to){
        this.fromPosition = from;
        this.toPosition = to;
    }

    void setFromPosition(int from){
        this.fromPosition =  from;

    }

    void setToPosition(int to){
        this.toPosition = to;

    }

    int getFromPosition(){
        return fromPosition;

    }
    int getToPosition(){
        return toPosition;

    }
}
