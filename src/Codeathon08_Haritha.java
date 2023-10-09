package src;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

class Player {
  private String name;
  private int[] runsScored;
  private int ballsFaced;
  private int extras;

  public Player(String name) {
    this.name = name;
    this.runsScored = new int[60]; // Max 10 overs, 6 balls per over
    this.ballsFaced = 0;
    this.extras = 0;
  }

  public String getName() {
    return name;
  }

  public int getBallsFaced() {
    return ballsFaced;
  }

  public int getExtras() {
    return extras;
  }

  public void playInnings() {
    Random random = new Random();
    for (int balls = 0; balls < 60; balls++) { // Simulate 10 overs (60 balls)
      int ballResult = random.nextInt(8); // Simulate a ball (0-7 represents outcomes)
      if (ballResult == 7) {
        extras++; // Extra run
      } else if (ballResult == 0) {
        // Player is out
        break;
      } else {
        runsScored[ballsFaced] = ballResult;
        ballsFaced++;
      }
    }
  }

  public int getTotalRunsScored() {
    int totalRuns = 0;
    for (int i = 0; i < ballsFaced; i++) {
      totalRuns += runsScored[i];
    }
    return totalRuns;
  }

  public int[] getRunsScored() {
    return runsScored;
  }
}

class Team implements Runnable {
  private String name;
  private Player[] players;
  private int totalScore;

  private static int batting = 1;

  public Team(String name, String[] playerNames) {
    this.name = name;
    this.totalScore = 0;
    this.players = new Player[11];
    for (int i = 0; i < 11; i++) {
      this.players[i] = new Player(playerNames[i]);
    }
  }

  public int getTotalScore() {
    return totalScore;
  }

  public void run() {

    System.out.println("Team " + name + " is Batting " + batting++);
    for (Player player : players) {
      player.playInnings();
      totalScore += player.getTotalRunsScored();
    }
  }
  public void displayBattingScore() {
    System.out.println(name + "-Batting Scoresheet");
    for (Player player : players) {
      System.out.print(player.getName() + " ");
      int[] runs = player.getRunsScored();
      for (int i = 0; i < player.getBallsFaced(); i++) {
        System.out.print(runs[i]);
        if (i < player.getBallsFaced() - 1) {
          System.out.print(",");
        }
      }
      System.out.println("=" + player.getTotalRunsScored() + " (" + player.getBallsFaced() + ")");
    }
    System.out.print("Extras " );
    for(int i=1; i<= calculateTeamExtras() ; i++)
    {
      System.out.print("1 ,");
    }
    System.out.println(" =  " + calculateTeamExtras());
  }

  private int calculateTeamExtras() {
    int teamExtras = 0;
    for (Player player : players) {
      teamExtras += player.getExtras();

    }
    return teamExtras;
  }
}

public class Codeathon08_Haritha {
  public static void main(String[] args) {
    String[] team1Players = {
      "Rohit Sharma", "Shubman Gill", "Virat Kohli", "KL Rahul", "Ishan Kishan",
      "Hardik Pandya", "Ravindra Jadeja", "Washington Sundar", "Kuldeep Yadav",
      "Mohammed Siraj", "Jasprit Bumrah"
    };
    String[] team2Players = {
      "Pathum Nissanka", "Kusal Perera", "Kusal Mendis", "Sadeera Samarawickrama",
      "Charith Asalanka", "Dhananjaya de Silva", "Dasun Shanaka", "Dunith Wellalage",
      "Dushan Hemantha", "Pramod Madushan", "Matheesha Pathirana"
    };
    Team team1, team2;
    Random random = new Random();
    double tossResult = random.nextDouble() * 2;
    String tossWinner = (tossResult < 1.0) ? "SL" : "India";

    System.out.println("Toss Won By " + tossWinner);

    if (tossResult < 1.0) {
      team1 = new Team("Sri Lanka", team2Players);
      team2 = new Team("India", team1Players);
    } else {
      team1 = new Team("India", team1Players);
      team2 = new Team("Sri Lanka", team2Players);
    }

    Thread innings1 = new Thread(team1);
    Thread innings2 = new Thread(team2);
    innings1.start();
    innings2.start();
    try {
      innings1.join();
      innings2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    if (tossResult < 1.0) {
      team1.displayBattingScore();
      System.out.println("Total Score " + team1.getTotalScore() + " in 10 overs");
      System.out.println();
      team2.displayBattingScore();
      System.out.println("Total Score " + team2.getTotalScore() + " in 10 overs");
    } else {
      team2.displayBattingScore();
      System.out.println("Total Score " + team2.getTotalScore() + " in 10 overs");
      System.out.println();
      team1.displayBattingScore();
      System.out.println("Total Score " + team1.getTotalScore() + " in 10 overs");
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String matchResult = determineResult(team1, team2);
    System.out.println("\nMatch Result: " + matchResult);
    System.out.println("Today's Date: " + dateFormat.format(new Date()));
  }
  private static String determineResult(Team team1, Team team2) {
    int team1Score = team1.getTotalScore();
    int team2Score = team2.getTotalScore();
    if (team1Score > team2Score) {
      return "Team India Won By " + (team1Score - team2Score) + " Runs";
    } else if (team1Score < team2Score) {
      return "Team Sri Lanka Won By " + (team2Score - team1Score) + " Runs";
    } else {
      return "Match Tied";
    }
  }
}