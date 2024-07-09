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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.HashMap;
import java.io.InputStream;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SnL {

    // states, variable, or properties
    private int boardSize;
    private ArrayList<Player> players;
    private ArrayList<String> playerCharacter; // Added to store player characters
    private ArrayList<Snake> snakes;
    private ArrayList<Ladder> ladders;
    private int gameStatus;
    private int currentTurn;

    // Mapping character names to emojis
    private static final HashMap<String, String> characterMap;
    static {
        characterMap = new HashMap<>();
        characterMap.put("NINJA", "\uD83E\uDD77"); // 🥷
        characterMap.put("VAMP", "\uD83E\uDDDB\u200D♂\uFE0F"); // 🧛‍♂️
        characterMap.put("WITCH", "\uD83E\uDDD9\u200D♂\uFE0F"); // 🧙‍♂️
        characterMap.put("GOBLIN", "\uD83E\uDDCC"); // 🧌
        characterMap.put("SUPERHERO", "\uD83E\uDDB8"); // 🦸
        characterMap.put("VILLAIN", "\uD83E\uDDB9"); // 🦹
    }

    // constructor
    public SnL(int size) {
        this.boardSize = size;
        this.snakes = new ArrayList<Snake>();
        this.ladders = new ArrayList<Ladder>();
        this.players = new ArrayList<Player>();
        this.playerCharacter = new ArrayList<String>(); // Initialize playerCharacters list
        this.gameStatus = 0;
    }

    public void initiateGame(){
        int [][] ladders =
                {       {2, 23},
                        {8, 34},
                        {20, 77},
                        {32, 68},
                        {41, 79},
                        {74, 88},
                        {82, 100},
                        {85, 95}
                };
        setLadders(ladders);
        int [][] snakes =
                {       {47, 5},
                        {29, 9},
                        {38, 15},
                        {97, 25},
                        {53, 33},
                        {92, 70},
                        {86, 54},
                        {97, 25}
                };
        setSnakes(snakes);
    }

    public Player getTurn() {
        if (this.gameStatus == 0) {
            double r = Math.random();
            this.currentTurn = (int) (r * this.players.size());
            return this.players.get(this.currentTurn);
        } else {
            this.currentTurn = (this.currentTurn + 1) % this.players.size();
            return this.players.get(this.currentTurn);
        }
    }

    // setter methods
    public void setSizeBoard(int size) {
        this.boardSize = size;
    }

    public void addPlayer(Player p) {
        this.players.add(p);
    }

    public void setLadders(int[][] ladders) {
        int s = ladders.length;
        for (int i = 0; i < s; i++) {
            this.ladders.add(new Ladder(ladders[i][0], ladders[i][1]));
        }
    }

    public void setSnakes(int[][] snakes) {
        int s = snakes.length;
        for (int i = 0; i < s; i++) {
            this.snakes.add(new Snake(snakes[i][0], snakes[i][1]));
        }
    }

    public int getBoardSize() {
        return this.boardSize;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public ArrayList<Snake> getSnakes() {
        return this.snakes;
    }

    public ArrayList<Ladder> getLadders() {
        return this.ladders;
    }

    public int getGameStatus() {
        return this.gameStatus;
    }

    public void play() {
        initiateGame();

        Scanner sc = new Scanner(System.in);
        int numPlayers;
        do {
            System.out.println("Enter the number of players (2-6):");
            numPlayers = sc.nextInt();
            if (numPlayers > 6) {
                System.out.println("MAX 6 Players");
            }
        } while (numPlayers < 2 || numPlayers > 6);

        sc.nextLine(); // consume the newline character
        HashSet<String> chosenCharacter = new HashSet<>();

        for (int i = 1; i <= numPlayers; i++) {
            System.out.println("Enter player " + i + " name:");
            String playerName = sc.nextLine();
            Player player = new Player(playerName);
            addPlayer(player);

            String character;
            do {
                System.out.println("Choose a Character (NINJA, VAMP, WITCH, GOBLIN, SUPERHERO, VILLAIN):");
                character = sc.nextLine().toUpperCase();
                if (chosenCharacter.contains(character)) {
                    System.out.println("Character already taken, please choose a different character.");
                }
            } while (!isValidCharacter(character) || chosenCharacter.contains(character));

            chosenCharacter.add(character);
            playerCharacter.add(characterMap.get(character));
        }

        Player nowPlaying;
        do {
            System.out.println("----------------------------------------------");
            nowPlaying = getTurn();
            System.out.println("Now Playing: " + nowPlaying.getName() + " the current position is " + nowPlaying.getPosition() + " and character is " + playerCharacter.get(currentTurn));
            System.out.println(nowPlaying.getName() + " it's your turn, please press enter to roll dice");

            String input = sc.nextLine();
            int x = 0;
            if (input.isEmpty()) {
                x = nowPlaying.rollDice();
            }

            System.out.println(nowPlaying.getName() + " is rolling dice and gets number: " + x);
            movePlayer(nowPlaying, x);
            System.out.println(nowPlaying.getName() + " new position is " + nowPlaying.getPosition());
        } while (getGameStatus() != 2);

        System.out.println("The Game is Over, the winner is: " + nowPlaying.getName() + " with character " + playerCharacter.get(currentTurn));
        playVictorySound();
    }

    public void playVictorySound() {
        try {
            URL url = getClass().getResource("/victory_sound.wav");
            if (url != null) {
                System.out.println("Sound file found: " + url.getPath());
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(url));
                clip.start();
                System.out.println("Sound is playing...");
                // Wait for the clip to finish playing
                Thread.sleep(clip.getMicrosecondLength() / 1000);
            } else {
                System.out.println("Sound file not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void movePlayer(Player p, int x) {
        this.gameStatus = 1;
        p.moveAround(x, this.boardSize);
        // Ensure the player does not go beyond the board size
        if (p.getPosition() > this.boardSize) {
            p.setPosition(this.boardSize);
        }

        for (Ladder l : this.ladders) {
            if (l.getFromPosition() == p.getPosition() && l.getToPosition() <= this.boardSize) {
                p.setPosition(l.getToPosition());
                System.out.println(p.getName() + " got ladder so jumps to " + p.getPosition());
            }
        }

        for (Snake s : this.snakes) {
            if (s.getHead() == p.getPosition()) {
                p.setPosition(s.getTail());
                System.out.println(p.getName() + " got snake so slides down to " + p.getPosition());
            }
        }

        if (p.getPosition() == this.boardSize) {
            this.gameStatus = 2;
        }
    }

    private boolean isValidCharacter(String character) {
        return characterMap.containsKey(character);
    }
}













