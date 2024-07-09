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

public class Player{
    //states
    private String name;
    private int position;

    //constructor method
    public Player (String name){
        this.name=name;
    }
    //setter methods

    public void setName (String name){
        this.name=name;
    }

    public void setPosition(int position){
        this.position = position;
    }

    //getter methods
    public String getName() {
        return this.name;
    }


    public int getPosition() {
        return this.position;
    }

    //another method
    public int rollDice()
    {
        return (int)((Math.random()*6)+1);
    }

    public void moveAround(int x, int boardSize)
    {
        if (this.position + x > boardSize){
            this.position = (boardSize - this.position) + (boardSize - x);
        }
        else {
            this.position = this.position + x;

        }
    }
}
