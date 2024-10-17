
public class LeastWorkDispatcher extends JobDispatcher {
    public LeastWorkDispatcher(int k, boolean showViz){
        super(k, showViz);
    }
    Server selectedServer = null;
    @Override
    public Server pickServer(Job j){
        double fastestDispatcher = Double.MAX_VALUE;
        for (Server server: servers){
            if (server.remainingWorkInQueue()<fastestDispatcher){
                fastestDispatcher = server.remainingWorkInQueue();
            }
        }
        for (Server server: servers){
            if (server.remainingWorkInQueue()==fastestDispatcher){
                selectedServer = server;
                break;
            }
        }
        return selectedServer;
    }
}
