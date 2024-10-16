

public class ServerFarmSimulation {

    public static void main(String[] args) {

        // You can explore how these change your results if you want!
        // How often a new job arrives at the server farm, on average
        int meanArrivalTime = 3;
        // How long a job takes to process, on average
        int meanProcessingTime = 100;

        // Main experiment settings
        int numServers = 34; // Numbers of servers in the farm
        int numJobs = 10000000; // Number of jobs to process
        boolean showViz = true; // Set to true to see the visualization, and false to run your experiments
        // to speed up the display, you can decrease the sleep time in the ServerFarmViz class.        
        

        String dispatcherType = "shortest"; // Which jobDispatcher to use

        // Initialize the job maker with the mean arrival and processing time
        JobMaker jobMaker = new JobMaker(meanArrivalTime, meanProcessingTime);

        // Create a dispatcher of the appropriate type
        JobDispatcher dispatcher = null;
        if (dispatcherType == "random") {
            dispatcher = new RandomDispatcher(numServers, showViz);
        } 
        else if (dispatcherType == "round") {
            dispatcher = new RoundRobinDispatcher(numServers, showViz);
        } else if (dispatcherType == "shortest") {
            dispatcher = new ShortestQueueDispatcher(numServers, showViz);
        } else if (dispatcherType == "least") {
            dispatcher = new LeastWorkDispatcher(numServers, showViz);
        }

        // Have the dispatched handle the specified number of jobs
        for (int i = 0; i < numJobs; i++) {
            dispatcher.handleJob(jobMaker.getNextJob());
        }
        dispatcher.finishUp(); // Finish all of the remaining jobs in Server queues

        // Print out the mean processing time
        System.out.println("Dispatcher: " + dispatcherType + ", Avg. Wait time: " + dispatcher.getAverageWaitingTime());

    }

}