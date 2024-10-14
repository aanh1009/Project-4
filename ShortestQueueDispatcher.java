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
        for (Server server: servers){
            if (server.size()==fewestJobs){
                selectedServer = server;
                break;
            }
        }
        return selectedServer;
    }
}
