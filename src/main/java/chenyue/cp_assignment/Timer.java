package chenyue.cp_assignment;

public class Timer {

    private long startTime;
    private long finishTime;

    public void start(){
        this.startTime=System.nanoTime();
    }

    public void finish(){
        this.finishTime=System.nanoTime();
    }

    public long timeTaken(){
        return this.finishTime-this.startTime;
    }

}
