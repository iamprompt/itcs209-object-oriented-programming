import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class Arena {

    public enum Row {Front, Back}

    ;    //enum for specifying the front or back row

    public enum Team {A, B}

    ;        //enum for specifying team A or B

    private Player[][] teamA = null;    //two dimensional array representing the players of Team A
    private Player[][] teamB = null;    //two dimensional array representing the players of Team B
    private int numRowPlayers = 0;        //number of players in each row

    public static final int MAXROUNDS = 100;    //Max number of turn
    public static final int MAXEACHTYPE = 3;    //Max number of players of each type, in each team.
    private final Path logFile = Paths.get("battle_log.txt");

    private int numRounds = 0;    //keep track of the number of rounds so far

    /**
     * Constructor.
     *
     * @param _numRowPlayers is the number of players in each row.
     */
    public Arena(int _numRowPlayers) {
        //INSERT YOUR CODE HERE
        this.numRowPlayers = _numRowPlayers;

        // Create 2D Array (Front & Back || Position in Row)
        this.teamA = new Player[Row.values().length][this.numRowPlayers];
        this.teamB = new Player[Row.values().length][this.numRowPlayers];
        /**
         * teamX = [{1, 2, 3, 4, 5}
         *            {6, 7, 8, 9, 10}]
         */

        ////Keep this block of code. You need it for initialize the log file.
        ////(You will learn how to deal with files later)
        try {
            Files.deleteIfExists(logFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /////////////////////////////////////////

    }

    /**
     * Returns true if "player" is a member of "team", false otherwise.
     * Assumption: team can be either Team.A or Team.B
     *
     * @param player
     * @param team
     * @return
     */
    public boolean isMemberOf(Player player, Team team) {
        //INSERT YOUR CODE HERE
        Team teamDetect = null;
        for (int i = 0; i < Row.values().length; i++) { // Front & Back Row Loop
            for (int j = 0; j < this.numRowPlayers; j++) { // Position in Row Loop
                if (player == teamA[i][j]) teamDetect = Team.A;
                if (player == teamB[i][j]) teamDetect = Team.B;
            }
        }

        return team == teamDetect;
    }


    /**
     * This methods receives a player configuration (i.e., team, type, row, and position),
     * creates a new player instance, and places him at the specified position.
     *
     * @param team     is either Team.A or Team.B
     * @param pType    is one of the Player.Type  {Healer, Tank, Samurai, BlackMage, Phoenix}
     * @param row      either Row.Front or Row.Back
     * @param position is the position of the player in the row. Note that position starts from 1, 2, 3....
     */
    public void addPlayer(Team team, Player.PlayerType pType, Row row, int position) {
        //INSERT YOUR CODE HERE
        // int iPos = row == Row.Front ? 0 : row == Row.Back ? 1 : null; // index: 0 => Front || index: 1 => Back
        int iPos = row.ordinal();
        int jPos = position - 1; // Conver Position (1, 2, 3, ....) into an array index

        if (team == Team.A)
            teamA[iPos][jPos] = new Player(pType);
        else if (team == Team.B)
            teamB[iPos][jPos] = new Player(pType);
    }

    /**
     * Validate the players in both Team A and B. Returns true if all of the following conditions hold:
     * <p>
     * 1. All the positions are filled. That is, there each team must have exactly numRow*numRowPlayers players.
     * 2. There can be at most MAXEACHTYPE players of each type in each team. For example, if MAXEACHTYPE = 3
     * then each team can have at most 3 Healers, 3 Tanks, 3 Samurais, 3 BlackMages, and 3 Phoenixes.
     * <p>
     * Returns true if all the conditions above are satisfied, false otherwise.
     *
     * @return
     */

    public boolean validatePlayers() {
        //INSERT YOUR CODE HERE

        // Create new 1D arrays for counting type of each team
        int[] aCount = new int[Player.PlayerType.values().length];
        int[] bCount = new int[Player.PlayerType.values().length];

        int totalPos = Row.values().length * this.numRowPlayers; // Total Position should have (Row x numRow)

        // No. of Players in each team
        int teamAPlayers = 0;
        int teamBPlayers = 0;

        for (int i = 0; i < Row.values().length; i++) {
            for (int j = 0; j < this.numRowPlayers; j++) {
                teamAPlayers++; // Add Player A
                teamBPlayers++; // Add Player B
                aCount[this.teamA[i][j].getType().ordinal()]++;
                bCount[this.teamB[i][j].getType().ordinal()]++;
            }
        }

        // Validate no. of type not exceed MAX Value
        boolean numMax = true;
        for (int i = 0; i < Player.PlayerType.values().length; i++) {
            if (aCount[i] > MAXEACHTYPE || bCount[i] > MAXEACHTYPE) numMax = false;
        }

        return teamAPlayers == totalPos && teamBPlayers == totalPos && numMax;
    }


    /**
     * Returns the sum of HP of all the players in the given "team"
     *
     * @param team
     * @return
     */
    public static double getSumHP(Player[][] team) {
        //INSERT YOUR CODE HERE
        double sumHP = 0; // Initial sumHP

        // Nested Loop through 2D array
        for (Player[] pA : team) {
            for (Player p : pA)
                sumHP += p.getCurrentHP();
        }

        return sumHP;
    }

    /**
     * Return the team (either teamA or teamB) whose number of alive players is higher than the other.
     * <p>
     * If the two teams have an equal number of alive players, then the team whose sum of HP of all the
     * players is higher is returned.
     * <p>
     * If the sums of HP of all the players of both teams are equal, return teamA.
     *
     * @return
     */
    public Player[][] getWinningTeam() {
        //INSERT YOUR CODE HERE
        // Count Alive in each team
        int aAlive = getNoAlive(this.teamA);
        int bAlive = getNoAlive(this.teamB);

        // Sum HP in each team
        double aHP = getSumHP(this.teamA);
        double bHP = getSumHP(this.teamB);

        // Return which team has higher no. of Alive players if equal return null
        Player[][] higherAlive = aAlive > bAlive ? this.teamA : bAlive > aAlive ? this.teamB : null;

        // Return which team has higher sumHP if equal return teamA
        Player[][] higherHP = aHP > bHP ? this.teamA : bHP > aHP ? this.teamB : this.teamA;

        // Check if higherAlive is null return higherHP
        return higherAlive != null ? higherAlive : higherHP;
    }

    private static int getNoAlive(Player[][] team) {
        int noAlive = 0; // Initial No. of Alive

        // Nested Loop through 2D array
        for (Player[] pA : team) {
            for (Player p : pA)
                noAlive += (p.isAlive() ? 1 : 0);
        }
        return noAlive;
    }

    /**
     * This method simulates the battle between teamA and teamB. The method should have a loop that signifies
     * a round of the battle. In each round, each player in teamA invokes the method takeAction(). The players'
     * turns are ordered by its position in the team. Once all the players in teamA have invoked takeAction(),
     * not it is teamB's turn to do the same.
     * <p>
     * The battle terminates if one of the following two conditions is met:
     * <p>
     * 1. All the players in a team has been eliminated.
     * 2. The number of rounds exceeds MAXROUNDS
     * <p>
     * After the battle terminates, report the winning team, which is determined by getWinningTeam().
     */
    public void startBattle() {
        //INSERT YOUR CODE HERE
        for (; this.numRounds < this.MAXROUNDS; numRounds++) { // Limit Rounds
            if (getNoAlive(this.teamA) != 0 && getNoAlive(this.teamB) != 0) {
                System.out.println("@ Round " + (numRounds + 1));

                for (Player[] a : teamA) {
                    for (Player Pa : a) {
                        Pa.takeAction(this);
                    }
                }

                for (Player[] b : teamB) {
                    for (Player Pb : b) {
                        Pb.takeAction(this);
                    }
                }
                Arena.displayArea(this, true);
                logAfterEachRound();
            } else {
                System.out.println("@@@ Team " + (this.getWinningTeam() == teamA ? Team.A : Team.B) + " won.");
                break;
            }
        }
    }


    /**
     * This method displays the current area state, and is already implemented for you.
     * In startBattle(), you should call this method once before the battle starts, and
     * after each round ends.
     *
     * @param arena
     * @param verbose
     */
    public static void displayArea(Arena arena, boolean verbose) {
        StringBuilder str = new StringBuilder();
        if (verbose) {
            str.append(String.format("%43s   %40s", "Team A", "") + "\t\t" + String.format("%-38s%-40s", "", "Team B") + "\n");
            str.append(String.format("%43s", "BACK ROW") + String.format("%43s", "FRONT ROW") + "  |  " + String.format("%-43s", "FRONT ROW") + "\t" + String.format("%-43s", "BACK ROW") + "\n");
            for (int i = 0; i < arena.numRowPlayers; i++) {
                str.append(String.format("%43s", arena.teamA[1][i]) + String.format("%43s", arena.teamA[0][i]) + "  |  " + String.format("%-43s", arena.teamB[0][i]) + String.format("%-43s", arena.teamB[1][i]) + "\n");
            }
        }

        str.append("@ Total HP of Team A = " + getSumHP(arena.teamA) + ". @ Total HP of Team B = " + getSumHP(arena.teamB) + "\n\n");
        System.out.print(str.toString());
    }

    /**
     * This method writes a log (as round number, sum of HP of teamA, and sum of HP of teamB) into the log file.
     * You are not to modify this method, however, this method must be call by startBattle() after each round.
     * <p>
     * The output file will be tested against the auto-grader, so make sure the output look something like:
     * <p>
     * 1	47415.0	49923.0
     * 2	44977.0	46990.0
     * 3	42092.0	43525.0
     * 4	44408.0	43210.0
     * <p>
     * Where the numbers of the first, second, and third columns specify round numbers, sum of HP of teamA, and sum of HP of teamB respectively.
     */
    private void logAfterEachRound() {
        try {
            Files.write(logFile, Arrays.asList(new String[]{numRounds + "\t" + getSumHP(teamA) + "\t" + getSumHP(teamB)}), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Implement Method for Player Class
    public Player[][] getMyTeam(Player player) {
        return (this.isMemberOf(player, Team.A) ? this.teamA : this.isMemberOf(player, Team.B) ? this.teamB : null);
    }

    public Player[][] getOtherTeam(Player player) {
        return (this.isMemberOf(player, Team.A) ? this.teamB : this.isMemberOf(player, Team.B) ? this.teamA : null);
    }

    public static Player getLowestHP(Player[][] team) {
        Player LowestPlayer = null;
        for (Player[] pA : team) {
            for (Player p : pA) {
                if (LowestPlayer == null && p != null && p.isAlive()) {
                    LowestPlayer = p;
                } else if (LowestPlayer != null) {
                    if ((LowestPlayer.getCurrentHP() > p.getCurrentHP()) && p.isAlive()) {
                        LowestPlayer = p;
                    }
                }
            }
            if (LowestPlayer != null) break;
        }
        return LowestPlayer;
    }

    public static Player getDead(Player[][] team) {
        for (Player[] pA : team) {
            for (Player p : pA) {
                if (!(p.isAlive()))
                    return p;
            }
        }
        return null;
    }
}
