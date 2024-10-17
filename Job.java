

public class Job {

	private double arrivalTime;
	private double finishTime;
	private double processingTimeNeeded;
	private double processingTimeSpent;

	public Job(double arrivalTime, double processingTimeNeeded) {
		this.arrivalTime = arrivalTime;
		this.processingTimeNeeded = processingTimeNeeded;
		this.processingTimeSpent = 0.;
	}

	public double getArrivalTime() {
		return this.arrivalTime;
	}

	public double getProcessingTimeNeeded() {
		return this.processingTimeNeeded;
	}

	public double getProcessingTimeRemaining() {
		return this.processingTimeNeeded - this.processingTimeSpent;
	}

	public boolean isFinished() {
		return getProcessingTimeRemaining() <= 0;
	}

	public double timeInQueue() {
		return this.finishTime - this.arrivalTime;
	}

	public void process(double timeToProcessFor, double timeProcessingStarted) {
		double timeRemaining = getProcessingTimeRemaining();
		this.processingTimeSpent = this.processingTimeSpent + timeToProcessFor;
		if (this.isFinished()) {
			this.finishTime = timeProcessingStarted + timeRemaining;
		}
	}
	@Override
	public String toString() {
		return "[Arrival: " + arrivalTime + ", Finish: " + finishTime + ", Time Needed: " + processingTimeNeeded
				+ ", Time Spent: " + processingTimeSpent + "]";
	}

}