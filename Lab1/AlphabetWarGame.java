import java.util.Scanner;

public class AlphabetWarGame {
    private static final String DEFAULT_LEFT_STRENGTH = "wpbs"; //The final keyword signifies that the entity to which it is applied cannot be changed or overridden.
    private static final String DEFAULT_RIGHT_STRENGTH = "mqdz";

    private String leftStrength;
    private String rightStrength;

    public AlphabetWarGame() {
        this.leftStrength = DEFAULT_LEFT_STRENGTH;
        this.rightStrength = DEFAULT_RIGHT_STRENGTH;
    }

    public AlphabetWarGame(String leftStrength, String rightStrength) {
        this.leftStrength = leftStrength;
        this.rightStrength = rightStrength;
    }

    public String battle(String word) {
        return determineWinner(word, leftStrength, rightStrength);
    }

    public String battle(String leftWord, String rightWord) {
        int leftScore = calculateScoreL(leftWord, leftStrength);
        int rightScore = calculateScoreR(rightWord, rightStrength);

        return determineWinner(leftScore, rightScore);
    }

    private int calculateScoreL(String word, String strength) {
        int score = 0;

        for (char letter : word.toCharArray()) {
            score += strength.indexOf(letter) != -1 ? strength.length() - strength.indexOf(letter) : 0;
        }
        System.out.println("Left Score : "  + score); 
        return score;
    }

    private int calculateScoreR(String word, String strength) {
        int score = 0;

        for (char letter : word.toCharArray()) {
            score += strength.indexOf(letter) != -1 ? strength.length() - strength.indexOf(letter) : 0;
        }
        System.out.println("Right Score : "  + score); 
        return score;
    }

    private String determineWinner(int leftScore, int rightScore) {
        if (leftScore > rightScore) {
            return "Left side wins!\n";
        } else if (rightScore > leftScore) {
            return "Right side wins!\n";
        } else {
            return "Let's fight again!\n";
        }
    }

    private String determineWinner(String word, String leftStrength, String rightStrength) {
        int leftScore = calculateScoreL(word, leftStrength);
        int rightScore = calculateScoreR(word, rightStrength);

        return determineWinner(leftScore, rightScore);
    }

    public static void main(String[] args) {
        // Example 1
        System.out.println("Test Cases: ");
        System.out.println();
        AlphabetWarGame game1 = new AlphabetWarGame();
        System.out.println(game1.battle("z")); // Right side wins!

        // Example 2
        AlphabetWarGame game2 = new AlphabetWarGame("wpbs", "mqdz");
        System.out.println(game2.battle("zdqmwpbs")); // Let's fight again!

        // Example 3
        AlphabetWarGame game3 = new AlphabetWarGame();
        System.out.println(game3.battle("wwwwwwz")); // Left side wins!

        // Example 4
        AlphabetWarGame game4 = new AlphabetWarGame("wpbs", "mqdz");
        System.out.println(game4.battle("wwwwww", "zzz")); // Let's fight again!

        // User Input Example
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the left word: ");
        String leftWord = scanner.nextLine();
        System.out.println("Enter the right word: ");
        String rightWord = scanner.nextLine();

        AlphabetWarGame userGame = new AlphabetWarGame();
        System.out.println(userGame.battle(leftWord, rightWord));
    }
}




