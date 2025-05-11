package erepair.program.repairer.erepair;

import erepair.program.subject.SubjectStatus;

import java.util.function.Function;

/**
 **/
public class BinarySearch {
    /**
     * The Subject Return Status of the latest Binary Search
     */
    public static SubjectStatus latestBinarySearchStatus = null;

    private BinarySearch() {
    }

    /**
     * Perform a recursive binary search for the fault location and return the faulty character's index as DeltaSet:
     *
     * <ol>
     *     <li>If the size of the region to search is 1, return it (it must be precisely the faulty character)</li>
     *     <li>Try to delete the second half of the region. If the result is "incomplete", continue search in that region (start over)</li>
     *     <li>Continue the search in the first half of the region (start over)</li>
     * </ol>
     *
     * @param fileContent The whole file content
     * @param test        Test function to test an input in the subject program
     * @return the faulty location, i.e. the index of the first faulty character. This is equal to the input length, if the input is incomplete of successful.
     */
    @SuppressWarnings("AssignmentToMethodParameter")
    public static <T> int binarySearchFaultLocation(BinarySearchable<T> fileContent, int lowerbound, int upperbound, Function<BinarySearchable<T>, SubjectStatus> test) {
        latestBinarySearchStatus = test.apply(fileContent);
        if (latestBinarySearchStatus.wasIncomplete() || latestBinarySearchStatus.wasSuccessful()) {
            return fileContent.length();
        }
        while (lowerbound + 1 < upperbound) {
            final int mid = Math.floorDiv((lowerbound + (upperbound)), 2);
            //Try to remove second half
            latestBinarySearchStatus = test.apply(fileContent.substring(0, mid));
//        System.err.println(lowerbound + " " + mid + " " + upperbound + " " + latestBinarySearchStatus);
            if (latestBinarySearchStatus.wasIncorrect()) {
                // Removing the second half resulted in an incorrect file --> Continue to search in first half
                upperbound = mid;
            } else {
                // Removing the second half leads to incomplete file content --> The fault must be in the second half!
                lowerbound = mid;
            }
        }
        return lowerbound;
    }
}
