package chenyue.cp_assignment;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EdgeMap {
    private static Lock lock =new ReentrantLock();
    private HashMap <Coordinate,Coordinate>edgeMap =new HashMap<Coordinate,Coordinate>();

//    public HashMap<Coordinate, Coordinate> getEdgeMap() {
//        return edgeMap;
//    }
    public void addEdge(Coordinate first, Coordinate second){
        lock.lock();
        edgeMap.put(first,second);
        lock.unlock();
    }
    public boolean isExist(Coordinate first, Coordinate second){
        if(edgeMap.containsKey(first)||edgeMap.containsKey(second)||edgeMap.containsValue(first)||edgeMap.containsValue(second)){
            return true;
        }
        else {
            return false;
        }
    }

    public HashMap<Coordinate, Coordinate> getEdgeMap() {
        return edgeMap;
    }
}
