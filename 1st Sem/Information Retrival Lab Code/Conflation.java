//Assignment No. 1
//        Implementation of Conflation Algorithm to generate document representative of a text file.

package assign1;
import java.io.*;
import java.util.Scanner;

public class Conflation {
    public static void main(String[] args) throws IOException {
        try {
            File fi = new File("input.txt");
            Scanner sc1 = new Scanner(new File("Input.txt"));
            int ch, i, ans;
            do {
                System.out.println("1. Display the file");
                System.out.println("2. Remove Stop Words");
                System.out.println("3. Suffix Stripping");
                System.out.println("4. Count Frequency");
                System.out.println("Enter your choice");
                Scanner sc = new Scanner(System.in);
                ch = sc.nextInt();
                switch (ch) {
                    case 1:
                        while (sc1.hasNext()) {
                            System.out.print(sc1.next() + " ");
                        }
                        System.out.println(" ");
                        break;
                    case 2:
                        remove_punctutation(fi);
                        break;
                    case 3:
                        suffix_stripping();
                        break;
                    case 4:
                        frequency_count();
                        break;
                }
            } while (ch != 4);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    private static void remove_punctutation(File fi) {
        try {
            Scanner sc_punctuation = new Scanner(fi);
            BufferedWriter out = new BufferedWriter(new FileWriter("without_punctuation_and_stopwords.txt"));
            while (sc_punctuation.hasNext()) {
                String str_p = sc_punctuation.next();
                String str_r = str_p.replaceAll("[^a-zA-Z\\s]", "");
                if (!str_r.toLowerCase().equals("the") && !str_r.toLowerCase().equals("is")
                        && !str_r.toLowerCase().equals("and") && !str_r.toLowerCase().equals("of")
                        && !str_r.toLowerCase().equals("are") && !str_r.toLowerCase().equals("for")
                        && !str_r.toLowerCase().equals("in")) {
                    out.write(str_r + " ");
                }
            }
            out.close();
            System.out.println("File after punctuation and stopwords:");
            File testfile = new File("without_punctuation_and_stopwords.txt");
            BufferedReader br = new BufferedReader(new FileReader(testfile));
            String z;
            while ((z = br.readLine()) != null)
                System.out.println(z);
            br.close();
        } catch (IOException e) {
            System.out.println("exception occurred" + e);
        }
    }

    private static void suffix_stripping() throws FileNotFoundException, IOException {
        Scanner sc1 = new Scanner(new File("without_punctuation_and_stopwords.txt"));
        BufferedWriter out = new BufferedWriter(new FileWriter("suffix_stripping2.txt"));
        while (sc1.hasNext()) {
            String str = sc1.next();
            str = str + "/";
            if (str.endsWith("ier/")) {
                str = str.replaceAll("ier/", "y");
            }
            else if (str.endsWith("ied/")) {
                str = str.replaceAll("ied/", "y");
            }
            else if (str.endsWith("ing/")) {
                str = str.replaceAll("ing/", "");
            }
            else if (str.endsWith("ation/")) {
                str = str.replaceAll("ation/", "");
            }
            else if (str.endsWith("ations/")) {
                str = str.replaceAll("ations/", "");
            }
            else if (str.endsWith("ard/")) {
                str = str.replaceAll("ard/", "");
            }
            else if (str.endsWith("er/")) {
                str = str.replaceAll("er/", "");
            }
            else if (str.endsWith("l/")) {
                str = str.replaceAll("l/", "");
            }
            else if (str.endsWith("slowly/")) {
                str = str.replaceAll("slowly/", "slowli");
            }
            else if (str.endsWith("y/")) {
                str = str.replaceAll("t/", "i");
            }
            else if (str.endsWith("plenty/")) {
                str = str.replaceAll("plenty/", "plenti");
            }
            else if (str.endsWith("ies/")) {
                str = str.replaceAll("ies/", "i");
            }


            // Add more cases here for other suffixes

            str = str.replace("/", " ");
            out.write(str + " ");
        }
        out.close();
        sc1.close();
        System.out.println("File after suffix Stripping:");
        File testfile = new File("suffix_stripping2.txt");
        BufferedReader br = new BufferedReader(new FileReader(testfile));
        String z;
        while ((z = br.readLine()) != null)
            System.out.println(z);
        br.close();
    }

    private static void frequency_count() throws FileNotFoundException, IOException {
        Scanner sc3 = new Scanner(new File("suffix_stripping2.txt"));
        int flag = 0, i = 0, l = 0, ct = 0, flag_w = 0;
        String w[] = new String[1000];
        int cnt[] = new int[1000];
        while (sc3.hasNext()) {
            w[i] = sc3.next();
            i++;
        }
        sc3.reset();
        Scanner sc5 = new Scanner(new File("suffix_stripping2.txt"));
        while (sc5.hasNext()) {
            String str1 = sc5.next();
            for (int j = 0; j < i; j++) {
                if (str1.equalsIgnoreCase(w[j])) {
                    flag = 1;
                    cnt[j]++;
                }
            }
            if (flag == 0) {
                w[i] = str1;
                cnt[i] = 1;
                i++;
            }
        }
        for (int j = 0; j < i; j++) {
            for (int k = j + 1; k < i; k++) {
                if (w[j].equalsIgnoreCase(w[k])) {
                    flag_w = 0;
                    break;
                } else {
                    flag_w = 1;
                }
            }
            if (flag_w == 1) {
                System.out.println(w[j] + "." + cnt[j] + " ");
            }
        }
    }
}
