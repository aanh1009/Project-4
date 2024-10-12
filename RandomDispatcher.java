import java.util.Random;
public class RandomDispatcher extends JobDispatcher {
    public RandomDispatcher(int k, boolean showViz){
        super(k,showViz);
    }
    Random ran = new Random();
    public Server pickServer(Job j){
        int numServer = ran.nextInt(0,servers.size());
        Server server = servers.get(numServer);
        return server;
    }
}
