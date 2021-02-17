import java.util.Arrays;
import java.util.Scanner;

public class Gano {
    String name;
    int Age;


    static Scanner in = new Scanner(System.in);


    public static void main(String[] args) {


        Gano a = new Gano();
        System.out.println(a.Gano());

    }

    public float Gano() {
        double result = 0;

        System.out.print("How many Lessons do you have? >> ");
        int a = in.nextInt();
        String[] LessonsName = new String[a];
        String[] LessonsMark = new String[a];
        int[] LessonsCredit = new int[a];
        double[] FinalScore = new double[a];


        int i = 0;
        while (i < a) {
            System.out.println("Write your Lessons Name(no space) and Mark in Case(exc:BA-AA) and Credit ");
            LessonsName[i] = in.next();
            LessonsMark[i] = in.next();
            LessonsCredit[i] = in.nextInt();

            i++;
        }
        i = 0;
        System.out.println("\nName\tMark\tCredit");
        while (i < a) {

            System.out.println(LessonsName[i] + "\t" + LessonsMark[i] + "\t"+"\t" + LessonsCredit[i] + "\n");


            i++;
        }
        i = 0;
        int totalcredit = 0;
        while (i < a) {

            totalcredit = totalcredit + LessonsCredit[i];

            i++;
        }
        System.out.println("TotalCredit = " + totalcredit);

        i = 0;
        while (i < a) {
            switch (LessonsMark[i].toUpperCase()) {
                case "AA":
                    FinalScore[i] = 4.0 * LessonsCredit[i] / totalcredit;
                    break;
                case "BA":
                    FinalScore[i] = 3.5 * LessonsCredit[i] / totalcredit;
                    break;
                case "BB":
                    FinalScore[i] = 3.0 * LessonsCredit[i] / totalcredit;
                    break;
                case "CB":
                    FinalScore[i] = 2.5 * LessonsCredit[i] / totalcredit;
                    break;
                case "CC":
                    FinalScore[i] = 2.0 * LessonsCredit[i] / totalcredit;
                    break;
                case "DC":
                    FinalScore[i] = 1.5 * LessonsCredit[i] / totalcredit;
                    break;
                case "DD":
                    FinalScore[i] = 1.0 * LessonsCredit[i] / totalcredit;
                    break;
                case "FD":
                    FinalScore[i] = 0.5 * LessonsCredit[i] / totalcredit;
                    break;
                case "FF":
                    FinalScore[i] = 0.0 * LessonsCredit[i] / totalcredit;
                    break;

            }

            i++;
        }
        i = 0;
        while (i < a) {
            result = FinalScore[i] + result;
            i++;
        }
        System.out.println("Your Gano");
        return (float) result;
    }
}
