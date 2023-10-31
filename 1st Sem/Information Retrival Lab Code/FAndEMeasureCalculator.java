//Assignment No. 5
//        Write a program to calculate harmonic mean (F-measure) and E-measure for above example


public class FAndEMeasureCalculator {
    public static void main(String[] args) {
        // Define true positives, false positives, and false negatives
        double truePositives = 60.0;
        double falsePositives = 10.0;
        double falseNegatives = 20.0;

        // Calculate precision, recall, and F-measure
        double precision = truePositives / (truePositives + falsePositives);
        double recall = truePositives / (truePositives + falseNegatives);
        double fMeasure = calculateFMeasure(precision, recall);

        // Define weights for precision and recall in E-measure calculation
        double alpha = 0.5;
        double beta = 2.0;

        // Calculate E-measure
        double eMeasure = calculateEMeasure(precision, recall, alpha, beta);

        // Print results
        System.out.println("Precision: " + precision);
        System.out.println("Recall: " + recall);
        System.out.println("F-Measure: " + fMeasure);
        System.out.println("E-Measure: " + eMeasure);
    }

    // Function to calculate F-measure
    private static double calculateFMeasure(double precision, double recall) {
        if (precision + recall == 0) {
            return 0.0;
        }
        return (2 * precision * recall) / (precision + recall);
    }

    // Function to calculate E-measure
    private static double calculateEMeasure(double precision, double recall, double alpha, double beta) {
        if (alpha * precision + beta * recall == 0) {
            return 0.0;
        }
        return 1.0 / ((alpha / precision) + (beta / recall));
    }
}
