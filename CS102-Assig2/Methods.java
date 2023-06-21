import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Methods{
    private static ArrayList<String> words = new ArrayList<>();
    private static ArrayList<Integer> occurrences = new ArrayList<>();

    public static void reverseOperate(File mapTxt, File encodedTxt){
        try {
            File decodedFile = new File("myTexts\\decoded.txt");
            decodedFile.createNewFile();
            Scanner reader = new Scanner(encodedTxt);
            FileWriter writer = new FileWriter(decodedFile);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                writer.write(decode(line, mapTxt) + "\n");
            
            }

            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void methodOne(File txtFile){
        words.clear();
        occurrences.clear();

        File mapFile = new File("myTexts\\map1.txt");
        File encodedFile = new File("myTexts\\encoded1.txt");

        try {
            Scanner reader = new Scanner(txtFile);

            mapFile.createNewFile();
            encodedFile.createNewFile();

            while (reader.hasNextLine()){
                String line = reader.nextLine();
                read(line);
            }

            map(mapFile);
            encode(txtFile, encodedFile);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void methodTwo(File txtFile){
        words.clear();
        occurrences.clear();

        File mapFile = new File("myTexts\\map2.txt");
        File encodedFile = new File("myTexts\\encoded2.txt");

        try {
            Scanner reader = new Scanner(txtFile);

            mapFile.createNewFile();
            encodedFile.createNewFile();

            while(reader.hasNextLine()){
                String line = reader.nextLine();
                read(line);
            }

            sort();
            map(mapFile);
            encode(txtFile, encodedFile);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void read(String line) {
        Scanner reader = new Scanner(line);

        while (reader.hasNext()) {
            String word = reader.next();

            if (words.contains(word)){
                occurrences.set(words.indexOf(word), occurrences.get(words.indexOf(word)) + 1);
            }
            else {
                words.add(word);
                occurrences.add(1);
            }
        }
        reader.close();
    }

    private static void map(File newFile){

//      Write to the file
        try {
            FileWriter writer = new FileWriter(newFile);
            for (int i = 0; i < words.size(); i++) {
                writer.write(i + ": " + words.get(i) + "\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void encode(File inputTxt, File newFile){
//      Write to the file
        try {
            FileWriter writer = new FileWriter(newFile, true);
            Scanner reader = new Scanner(inputTxt);
            Scanner wordReader = null;

            while(reader.hasNextLine()){
                String line = reader.nextLine();
                wordReader = new Scanner(line);

                while (wordReader.hasNext()) {
                    String word = wordReader.next();
                    writer.write(words.indexOf(word) + " ");
                }
                writer.write("\n");
            }

            reader.close();
            wordReader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String decode(String line, File mapTxt) {
        Scanner reader = new Scanner(line);
        StringBuilder encodedLine = new StringBuilder();

        try {
            String codedWord = "";

            while (reader.hasNext()) {
                Scanner mapReader = new Scanner(mapTxt);
                String code = reader.next();
    
                for (int i = 1; i <= Integer.parseInt(code) + 1; i++) {
                    codedWord = mapReader.nextLine();
                }

                String encodedWord = codedWord.substring(code.length() + 2);
                encodedLine.append(encodedWord + " ");
                mapReader.close();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return encodedLine.toString();
    }

    // Sort 2 arraylist words and occurrences together
    private static void sort(){
        for (int i = 0; i < words.size() - 1; i++) {
            for (int j = 0; j < words.size() - i - 1; j++) {
                if (occurrences.get(j) < occurrences.get(j + 1)) {
                    int tmpI = occurrences.get(j);
                    occurrences.set(j, occurrences.get(j + 1));
                    occurrences.set(j + 1, tmpI);

                    String tmpS = words.get(j);
                    words.set(j, words.get(j + 1));
                    words.set(j + 1, tmpS);
                }
            }
        }

    }
}