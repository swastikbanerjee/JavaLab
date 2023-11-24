import java.util.Scanner;

public class Performance {
    private int[] marks;
    public Performance() {
        marks = new int[10];
    }
    public void readMarks() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the marks of 10 students (between 0 and 100):");
        for (int i = 0; i < 10; i++) {
            System.out.print("Student " + (i + 1) + ": ");
            int mark = -1;
            // Keep prompting the user until a valid mark is entered
            while (mark < 0 || mark > 100) {
                System.out.print("Enter a mark between 0 and 100: ");
                mark = s.nextInt();
                if (mark < 0 || mark > 100) {
                    System.out.println("Invalid input. Please enter a mark between 0 and 100.");
                }
            }
            marks[i] = mark;
        }
    }
    public int highestMark() {
        int highest = marks[0];
        for (int i = 1; i < 10; i++) {
            if (marks[i] > highest) {
                highest = marks[i];
            }
        }
        return highest;
    }
    public int leastMark() {
        int least = marks[0];
        for (int i = 1; i < 10; i++) {
            if (marks[i] < least) {
                least = marks[i];
            }
        }
        return least;
    }
    public int getMode() {
        int mode = marks[0];
        int maxFreq = 1;
        for (int i = 0; i < 10; i++) {
            int curMark = marks[i];
            int curFreq = 1;

            for (int j = i + 1; j < 10; j++) {
                if (marks[j] == curMark) {
                    curFreq++;
                }
            }

            if (curFreq > maxFreq || (curFreq == maxFreq && curMark > mode)) {
                mode = curMark;
                maxFreq = curFreq;
            }
        }
        return mode;
    }
    public int getFreqAtMode() {
        int mode = getMode();
        int freq = 0;

        for (int i = 0; i < 10; i++) {
            if (marks[i] == mode) {
                freq++;
            }
        }
        return freq;
    }
    public void display() {
        System.out.println("Highest Mark: " + highestMark());
        System.out.println("Least Mark: " + leastMark());
        System.out.println("Mode: " + getMode());
        System.out.println("Mode Frequency: " + getFreqAtMode());
    }
    public static void main(String[] args) {
        Performance p = new Performance();
        p.readMarks();
        p.display();
    }
}
