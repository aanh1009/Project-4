
public class AverageWaitingTimeDispatcher extends JobDispatcher {
    public AverageWaitingTimeDispatcher(int k, boolean showViz){
        super(k, showViz);
    }
    Server selectedServer = null;
    @Override
    public Server pickServer(Job j){
        double fastestDispatcher = Double.MAX_VALUE;
        for (Server server: servers){
            if (server.remainingWorkInQueue()/server.size()<fastestDispatcher){
                fastestDispatcher = server.remainingWorkInQueue()/server.size();
            }
        }
        for (Server server: servers){
            if (server.remainingWorkInQueue()/server.size()==fastestDispatcher){
                selectedServer = server;
                break;
            }
        }
        return selectedServer;
    }
    
}
