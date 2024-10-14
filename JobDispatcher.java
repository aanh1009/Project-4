import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
public abstract class JobDispatcher {
    ArrayList<Server> servers;
    LinkedList<Job> jobs;
    double sysTime;
    ServerFarmViz visualization;
    int jobsProcessed;
    public JobDispatcher(int k, boolean showViz){
        servers = new ArrayList<>();
        for (int i=0; i<k;++i){
            Server server = new Server();
            servers.add(server);
        }
        jobsProcessed = 0;
        sysTime = 0;
        visualization = new ServerFarmViz(this , showViz );
        jobs = new LinkedList<>();
    }
    public abstract Server pickServer(Job j);
    public double getTime(){
        return sysTime;
    }
    public ArrayList<Server> getServerList(){
        return servers;
    }
    public void advanceTimeTo(double time){
        sysTime = time;
        for (Server server: servers){
            server.processTo(time);
        }
        visualization.repaint();
    }
    public void handleJob(Job job){
        jobs.add(job);
        this.advanceTimeTo(job.getArrivalTime());
        pickServer(job).addJob(job);
        visualization.repaint();
    }
    public int getNumJobsHandled(){
        return jobs.size();
    }
    public void finishUp(){
        for (Server server: servers){
            if (server.remainingWorkInQueue()>0){
                this.advanceTimeTo(sysTime + server.remainingWorkInQueue());
            }
        }
    }
    public double getAverageWaitingTime(){
        double totalWaitingTime = 0;
        for (Job job: jobs){
            System.out.println(job.timeInQueue());
            totalWaitingTime += job.timeInQueue();
        }
        return totalWaitingTime/jobs.size();
    }
    public void draw(Graphics g){
        double sep = (ServerFarmViz.HEIGHT - 20) / (getServerList().size() + 2.0);
        g.drawString("Time: " + getTime(), (int) sep, ServerFarmViz.HEIGHT - 20);
        g.drawString("Jobs handled: " + getNumJobsHandled(), (int) sep, ServerFarmViz.HEIGHT - 10);
        for(int i = 0; i < getServerList().size(); i++){
            getServerList().get( i ).draw(g, (i % 2 == 0) ? Color.GRAY : Color.DARK_GRAY, (i + 1) * sep, getServerList().size());
        }
    }
    
}
