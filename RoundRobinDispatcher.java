
public class RoundRobinDispatcher extends JobDispatcher {
    public RoundRobinDispatcher(int k, boolean showViz){
        super(k, showViz);
    }
    int index = 0;
    public Server pickServer(Job j){
        Server server = servers.get(index%servers.size());
        index+=1;
        return server;
    }
}
