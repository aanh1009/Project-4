import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
public class Server {
    private LinkedList<Job> jobs;
    private double sysTime;
    private double remainingTime;
    private int jobsProcessed;
    private int size;
    public Server(){
        jobs = new LinkedList<>();
        sysTime = 0;
        remainingTime = 0;
        jobsProcessed = 0;
        size = 0;
    }
    public void addJob(Job job){
        jobs.offer(job);
        remainingTime += job.getProcessingTimeNeeded();
        size +=1;
    }
    public void processTo(double time){
            double timeLeft = time - sysTime;
            // System.out.println("Current time: " + sysTime);
            // System.out.println("Processing to: " + time);
            // System.out.println("My Queue: " + jobs.toString());
            while (timeLeft>0 & remainingTime>0){
                if (jobs.peek()==null){
                    break;
                }
                else{
                    Job job = jobs.peek();
                    double step = Math.min(timeLeft, job.getProcessingTimeRemaining());
                    job.process(step, sysTime);
                    if (job.isFinished()){
                        Job processedJob = jobs.poll();
                        jobsProcessed+=1;
                    }
                    remainingTime -=step;
                    timeLeft -= step;
                    sysTime = sysTime + step;
                }
            }
            sysTime = time;
    }
    public double remainingWorkInQueue(){
        return remainingTime;
    }
    public int size(){
        return jobs.size();
    }
    public void draw(Graphics g, Color c, double loc, int numberOfServers){
        double sep = (ServerFarmViz.HEIGHT - 20) / (numberOfServers + 2.0);
        g.setColor(Color.BLACK);
        g.setFont(new Font(g.getFont().getName(), g.getFont().getStyle(), (int) (72.0 * (sep * .5) / Toolkit.getDefaultToolkit().getScreenResolution())));
        g.drawString("Work: " + (remainingWorkInQueue() < 1000 ? remainingWorkInQueue() : ">1000"), 2, (int) (loc + .2 * sep));
        g.drawString("Jobs: " + (size() < 1000 ? size() : ">1000"), 5 , (int) (loc + .55 * sep));
        g.setColor(c); 
        g.fillRect((int) (3 * sep), (int) loc, (int) (.8 * remainingWorkInQueue()), (int) sep);
        g.drawOval(2 * (int) sep, (int) loc, (int) sep, (int) sep);
        if (remainingWorkInQueue() == 0) g.setColor(Color.GREEN.darker());
        else g.setColor(Color.RED.darker());
        g.fillOval(2 * (int) sep, (int) loc, (int) sep, (int) sep);
}
}