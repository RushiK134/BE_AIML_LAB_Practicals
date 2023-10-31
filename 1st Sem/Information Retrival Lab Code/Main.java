//Assignment No. 3
//        To implement a program for Retrieval of documents using inverted files

import java.util.*;
import java.io.*;

class Document {
    String fileName;
    int line;
    int index;

    Document(String fileName, int line, int index) {
        this.fileName = fileName;
        this.line = line;
        this.index = index;
    }
}

class InvertedIndex {
    private Map<String, List<Document>> dictionary;
    private List<String> fileList;

    InvertedIndex() {
        dictionary = new HashMap<>();
        fileList = new ArrayList<>();
    }

    void addFile(String filename) {
        try {
            File file = new File(filename + ".txt");
            Scanner scanner = new Scanner(file);
            fileList.add(filename);

            int line_number = 0;

            while (scanner.hasNextLine()) {
                line_number++;
                int word_number = 0;
                String line = scanner.nextLine();
                Scanner lineScanner = new Scanner(line);

                while (lineScanner.hasNext()) {
                    word_number++;
                    String word = lineScanner.next();
                    Document doc = new Document(filename, line_number, word_number);
                    dictionary.computeIfAbsent(word, k -> new ArrayList<>()).add(doc);
                }

                lineScanner.close();
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
    }

    void showFiles() {
        int size = fileList.size();
        for (int i = 0; i < size; i++) {
            System.out.println(i + 1 + ": " + fileList.get(i));
        }

        if (size == 0) {
            System.out.println("No files added");
        }
    }

    void search(String word) {
        if (!dictionary.containsKey(word)) {
            System.out.println("No instances found");
            return;
        }

        List<Document> documents = dictionary.get(word);
        int size = documents.size();

        for (int counter = 0; counter < size; counter++) {
            System.out.println(counter + 1 + ":");
            Document doc = documents.get(counter);
            System.out.println(" Filename: " + doc.fileName);
            System.out.println(" Line Number: " + doc.line);
            System.out.println(" Index: " + doc.index);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        InvertedIndex data = new InvertedIndex();

        for (String arg : args) {
            data.addFile(arg);
        }

        Scanner scanner = new Scanner(System.in);

        int choice = 0;

        do {
            System.out.println("1: See files\n2: Add File\n3: Query Word\n4: Exit");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    data.showFiles();
                    break;
                case 2:
                    System.out.println("Enter File Name: ");
                    String name = scanner.next();
                    data.addFile(name);
                    break;
                case 3:
                    System.out.println("Enter Word: ");
                    String word = scanner.next();
                    data.search(word);
                    break;
                case 4:
                    break;
                default:
                    continue;
            }
        } while (choice != 4);

        scanner.close();
    }
}
