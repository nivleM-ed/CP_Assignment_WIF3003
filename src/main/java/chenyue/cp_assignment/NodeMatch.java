package chenyue.cp_assignment;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NodeMatch {
    private static ArrayList<Coordinate> coordinates;
    private static EdgeMap edgeMap=new EdgeMap();
    private static boolean failure;

    public static class AddMatchedNotes implements Runnable{
        private int failCount = 0;
        public void run(){
            while (!failure){
                Random random=new Random();
                Coordinate first =coordinates.get(random.nextInt(coordinates.size()));
                Coordinate second =coordinates.get(random.nextInt(coordinates.size()));
                if(first.equals(second)||edgeMap.isExist(first,second)){

                    failCount++;
                    System.out.println(Thread.currentThread().getName() + " "+failCount + " " + first + " " + second);
                }
                else {
                    edgeMap.addEdge(first,second);
                    System.out.println(Thread.currentThread().getName() + " add coordinate pair [" + first + " , " + second + " ]"  );
                }
                if (failCount>=20){
                    failure = true;
                    System.out.println(Thread.currentThread().getName() +  " has failed " + failCount);
                }
            }
            if(failure){
                Thread.currentThread().interrupt();
            }

        }
    }

    public static void main (String[] args){
        int n =100;
        int m = 60;
        int t=10;
        ExecutorService executor= Executors.newCachedThreadPool();
        getNodes(n);
        for(int i=0; i<t;i++){
            executor.execute(new AddMatchedNotes());

        }
        executor.shutdownNow();
        try {
            executor.awaitTermination(30, TimeUnit.SECONDS );
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        while(!executor.isTerminated()){
        }
        edgeMap.getEdgeMap().entrySet().forEach(entry->{
                    System.out.println(entry.getKey() + " " + entry.getValue());
                });
    }
    public static void getNodes (int n){
        ArrayList <Coordinate> temp =new ArrayList<Coordinate>();
        while (!(temp.size() > n)){
            Random random=new Random();
            float x =random.nextFloat();
            float y=random.nextFloat();
            Coordinate coordinate =new Coordinate(x,y);
            if(!temp.contains(coordinate)){
                temp.add(coordinate);
            }
        }
        coordinates=temp;
    }



}
