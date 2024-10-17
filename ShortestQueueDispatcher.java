import java.util.ArrayList;
import java.util.Random;
public class ShortestQueueDispatcher extends JobDispatcher {
    public ShortestQueueDispatcher(int k, boolean showViz){
        super(k, showViz);
    }
    Server selectedServer = null;
    public Server pickServer(Job j){
        int fewestJobs = Integer.MAX_VALUE;
        for (Server server: servers){
            if (server.size()<fewestJobs){
                fewestJobs = server.size();
            }
        }
        ArrayList<Server> shortestQueues = new ArrayList<>();
        for (Server server: servers){
            if (server.size()==fewestJobs){
                shortestQueues.add(server);
            }
        }
        Random ran = new Random();
        int chosenServer = ran.nextInt(0,shortestQueues.size());
        selectedServer = shortestQueues.get(chosenServer);
        return selectedServer;
    }
}
