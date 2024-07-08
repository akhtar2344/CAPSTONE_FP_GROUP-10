/**
 * -----------------------------------------------------
 * ES234211 - Programming Fundamental
 * Genap - 2023/2024
 * Group Capstone Project: Snake and Ladder Game
 * -----------------------------------------------------
 * Class    : Q
 * Group    : 10
 * Members  :
 * 1. Student ID - Akhtar Fattan Widodo
 * 2. Student ID - Axel, Luis, Albert, Gil
 * 3. Student ID - Bagas Budisatrio
 * ------------------------------------------------------
 */
public class Snake{
    int head;
    int tail;

    Snake(int head, int tail){
        this.head = head;
        this.tail = tail;
    }

    void setTail(int tail){
        this.tail = tail;
    }
    void setHead(int head){
        this.head = head;
    }
    int getTail(){
        return this.tail;
    }
    int getHead(){
        return this.head;
    }
}
