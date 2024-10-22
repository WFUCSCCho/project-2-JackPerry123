import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Arrays;
import java.io.*;
public class Proj2 {
    public static void main(String[] args) throws IOException {
        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }

        String SpyCsv = args[0];
        int numLines = Integer.parseInt(args[1]);

        // For file input
        FileInputStream SpyCsvStream = null;
        Scanner SpyCsvScanner = null;

        // Open the input file
        SpyCsvStream = new FileInputStream(SpyCsv);
        SpyCsvScanner = new Scanner(SpyCsvStream);

        // ignore first line
        SpyCsvScanner.nextLine();

        // FINISH ME

        ArrayList<SPY> Array = new ArrayList<SPY>();

        String line;
        int count = 0;
        while (SpyCsvScanner.hasNext() && count < numLines) {

            line = SpyCsvScanner.nextLine();
            line = line.trim();

            if (line.isEmpty()) continue;

            //separates by the correct commas (avoids separating by commas that are within quotes.
            String[] p = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            if (p.length != 16 || oneElementIsEmpty(p))
                continue;

            SPY s = new SPY(p[0],
                    p[1],
                    p[2],
                    p[3],
                    p[4],
                    p[5],
                    Double.parseDouble(p[6]),
                    Double.parseDouble(p[7]),
                    Double.parseDouble(p[8]),
                    Double.parseDouble(p[9]),
                    p[10],
                    p[11],
                    p[12],
                    p[13],
                    p[14],
                    Double.parseDouble(p[15]));
            //check this
            Array.add(s);

            count++;

        }

        //Sort ArrayList

        Collections.sort(Array);

        //Creating Lists

        BST<SPY> BST_Sorted = new BST<>();
        BST<SPY> BST_Shuffled = new BST<>();
        AvlTree<SPY> AVL_Sorted = new AvlTree<>();
        AvlTree<SPY> AVL_Shuffled = new AvlTree<>();

        // BST_Sorted_Insert

        long start, stop;

        writeToFile("BST_Sorted_Insert", "./result.txt");

        //counter variable
        int n = 0;
        start = System.nanoTime();
        for(SPY s: Array) {
            n++;
            BST_Sorted.insert(s);
            stop = System.nanoTime();
            Long BST_Sorted_Insert = stop - start;
            writeToFile(Long.toString(BST_Sorted_Insert) + ", " + Integer.toString(n), "./result.txt");
        }





        // AVL_Sorted_Insert

        writeToFile("AVL_Sorted_Insert", "./result.txt");

        n = 0;
        start = System.nanoTime();
        for(SPY s: Array) {
            n++;
            AVL_Sorted.insert(s);
            stop = System.nanoTime();
            Long AVL_Sorted_Insert = stop - start;
            writeToFile(Long.toString(AVL_Sorted_Insert) + ", " + Integer.toString(n), "./result.txt");

        }




        // BST_Sorted_Search

        writeToFile("BST_Sorted_Search", "./result.txt");

        start = System.nanoTime();
        n = 0;
        for(SPY s: Array){
            n++;
            BST_Sorted.searchRecursive(BST_Sorted.getRoot(), s);
            stop = System.nanoTime();
            Long BST_Sorted_Search = stop - start;
            writeToFile(Long.toString(BST_Sorted_Search) + ", " + Integer.toString(n), "./result.txt");
        }



        // AVL_Sorted_Search

        writeToFile("AVL_Sorted_Search", "./result.txt");

        start = System.nanoTime();
        n = 0;
        for(SPY s: Array){
            n++;
            AVL_Sorted.contains(s);
            stop = System.nanoTime();
            Long AVL_Sorted_Search = stop - start;
            writeToFile(Long.toString(AVL_Sorted_Search) + ", " + Integer.toString(n), "./result.txt");
        }




        // Shuffle

        Collections.shuffle(Array);

        // BST_Shuffled_Insert

        writeToFile("BST_Shuffled_Insert", "./result.txt");

        start = System.nanoTime();
        n = 0;
        for(SPY s: Array) {
            n++;
            BST_Shuffled.insert(s);
            stop = System.nanoTime();
            Long BST_Shuffled_Insert = stop - start;
            writeToFile(Long.toString(BST_Shuffled_Insert) + ", " + Integer.toString(n), "./result.txt");
        }


        // AVL_Shuffled_Insert

        writeToFile("AVL_Shuffled_Insert", "./result.txt");

        start = System.nanoTime();
        n = 0;
        for(SPY s: Array) {
            n++;
            AVL_Shuffled.insert(s);
            stop = System.nanoTime();
            Long AVL_Shuffled_Insert = stop - start;
            writeToFile(Long.toString(AVL_Shuffled_Insert) + ", " + Integer.toString(n), "./result.txt");
        }



        // BST_Shuffled_Search

        writeToFile("BST_Shuffled_Search", "./result.txt");

        start = System.nanoTime();
        n = 0;
        for(SPY s: Array){
            n++;
            BST_Shuffled.searchRecursive(BST_Sorted.getRoot(), s);
            stop = System.nanoTime();
            Long BST_Shuffled_Search = stop - start;
            writeToFile(Long.toString(BST_Shuffled_Search) + ", " + Integer.toString(n), "./result.txt");
        }



        // AVL_Shuffled_Search

        writeToFile("AVL_Shuffled_Search", "./result.txt");

        start = System.nanoTime();
        n = 0;
        for(SPY s: Array){
            n++;
            AVL_Shuffled.contains(s);
            stop = System.nanoTime();
            Long AVL_Shuffled_Search = stop - start;
            writeToFile(Long.toString(AVL_Shuffled_Search) + ", " + Integer.toString(n), "./result.txt");
        }


        //end of main
    }

    public static boolean oneElementIsEmpty(String[] s){
        for (String e : s) {
            if (e.isEmpty())
                return true;
        }
        return false;
    }

    public static void writeToFile(String content, String filePath) {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content);
            writer.newLine();
            writer.newLine();
        } catch(IOException e){
            System.out.println("error " + e);
        }

    }

}
