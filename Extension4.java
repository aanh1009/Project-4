
import java.util.Random;
public class Extension4 {

    public static void main(String[] args) {
            Random ran = new Random();
            // You can explore how these change your results if you want!
            // How often a new job arrives at the server farm, on average
            int meanArrivalTime = ran.nextInt(2,5);
            // How long a job takes to process, on average
            int meanProcessingTime = ran.nextInt(60,100);

            // Main experiment settings
            int numServers = meanProcessingTime/meanArrivalTime + 1; // Numbers of servers in the farm
            int numJobs = 10000000; // Number of jobs to process
            boolean showViz = true; // Set to true to see the visualization, and false to run your experiments
            // to speed up the display, you can decrease the sleep time in the ServerFarmViz class.        
            

            JobDispatcher dispatcher = new LeastWorkDispatcher(numServers, showViz);
            JobMaker jobMaker = new JobMaker(meanArrivalTime, meanProcessingTime);
            // Have the dispatched handle the specified number of jobs
            for (int i = 0; i < numJobs; i++) {
                dispatcher.handleJob(jobMaker.getNextJob());
            }
            dispatcher.finishUp(); // Finish all of the remaining jobs in Server queues

            // Print out the mean processing time
            System.out.println("Dispatcher: least, " + "Mean Arrival Time: " + meanArrivalTime + ", Mean Processing Time: " + meanProcessingTime + " "+ "Average Waiting Time: " + dispatcher.getAverageWaitingTime());

        }
}
