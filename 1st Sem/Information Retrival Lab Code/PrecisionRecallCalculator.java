//Assignment No. 4
//        Implement a program to calculate precision and recall for sample input. (Answer set A, Query q1, Relevant documents to query q1- Rq1 )

import java.util.HashSet;
import java.util.Set;

public class PrecisionRecallCalculator {
    public static void main(String[] args) {
        // Sample input: Answer set A, Query q1, Relevant documents to query q1 (Rq1)
        Set<String> answerSetA = new HashSet<>();
        answerSetA.add("Doc1");
        answerSetA.add("Doc2");
        answerSetA.add("Doc3");
        answerSetA.add("Doc4");

        Set<String> relevantDocumentsRq1 = new HashSet<>();
        relevantDocumentsRq1.add("Doc1");
        relevantDocumentsRq1.add("Doc3");

        // Calculate precision and recall
        double precision = calculatePrecision(answerSetA, relevantDocumentsRq1);
        double recall = calculateRecall(answerSetA, relevantDocumentsRq1);

        // Print results
        System.out.println("Precision: " + precision);
        System.out.println("Recall: " + recall);
    }

    // Function to calculate precision
    private static double calculatePrecision(Set<String> answerSet, Set<String> relevantDocuments) {
        int truePositives = 0;

        for (String doc : answerSet) {
            if (relevantDocuments.contains(doc)) {
                truePositives++;
            }
        }

        if (answerSet.isEmpty()) {
            return 0.0;
        }

        return (double) truePositives / answerSet.size();
    }

    // Function to calculate recall
    private static double calculateRecall(Set<String> answerSet, Set<String> relevantDocuments) {
        int truePositives = 0;

        for (String doc : answerSet) {
            if (relevantDocuments.contains(doc)) {
                truePositives++;
            }
        }

        if (relevantDocuments.isEmpty()) {
            return 0.0;
        }

        return (double) truePositives / relevantDocuments.size();
    }
}
