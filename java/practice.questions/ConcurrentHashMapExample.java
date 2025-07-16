import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentHashMapExample<K, V> {
    int SEGMENT_SIZE = 10;
    List<Segment<K, V>> segments = new ArrayList<>();

    public ConcurrentHashMapExample() {
        System.out.println("[INIT] Creating ConcurrentHashMap with " + SEGMENT_SIZE + " segments");
        addSegments(SEGMENT_SIZE);
        System.out.println("[INIT] ConcurrentHashMap initialization completed");
    }

    private void addSegments(int size) {
        for (int i = 0; i < size; i++) {
            segments.add(new Segment<>(i));
            System.out.println("[INIT] Created segment " + i);
        }
    }

    private int decideSegmentNumber(K key) {
        String qualifiedValueOfKey = key.toString();
        int segmentNum = (int) qualifiedValueOfKey.charAt(0) % SEGMENT_SIZE;
        System.out.println("[ROUTING] Thread " + Thread.currentThread().getName() + 
                          " - Key '" + key + "' routed to segment " + segmentNum + 
                          " (first char: '" + qualifiedValueOfKey.charAt(0) + "')");
        return segmentNum;
    }

    private boolean putValueInSegment(int segmentNum, K key, V value) {
        try {
            System.out.println("[PUT-START] Thread " + Thread.currentThread().getName() + 
                              " attempting to put '" + key + "=" + value + "' in segment " + segmentNum);
            
            Segment<K, V> segment = segments.get(segmentNum);
            segment.put(key, value);
            
            System.out.println("[PUT-SUCCESS] Thread " + Thread.currentThread().getName() + 
                              " successfully put '" + key + "=" + value + "' in segment " + segmentNum);
            return true;
        } catch (Exception ex) {
            System.err.println("[PUT-ERROR] Thread " + Thread.currentThread().getName() + 
                              " failed to put '" + key + "=" + value + "' in segment " + segmentNum + 
                              " - Error: " + ex.getMessage());
            return false;
        }
    }

    private V getValueFromSegment(int segmentNum, K key) {
        System.out.println("[GET-START] Thread " + Thread.currentThread().getName() + 
                          " attempting to get '" + key + "' from segment " + segmentNum);
        
        V result = segments.get(segmentNum).get(key);
        
        System.out.println("[GET-RESULT] Thread " + Thread.currentThread().getName() + 
                          " got '" + key + "=" + result + "' from segment " + segmentNum);
        return result;
    }

    public void put(K key, V value) {
        System.out.println("[PUBLIC-PUT] Thread " + Thread.currentThread().getName() + 
                          " called put('" + key + "', '" + value + "')");
        
        int segmentNumber = decideSegmentNumber(key);
        putValueInSegment(segmentNumber, key, value);
        
        System.out.println("[PUBLIC-PUT-DONE] Thread " + Thread.currentThread().getName() + 
                          " completed put operation for '" + key + "'");
    }

    public V get(K key) {
        System.out.println("[PUBLIC-GET] Thread " + Thread.currentThread().getName() + 
                          " called get('" + key + "')");
        
        int segmentNumber = decideSegmentNumber(key);
        V result = getValueFromSegment(segmentNumber, key);
        
        System.out.println("[PUBLIC-GET-DONE] Thread " + Thread.currentThread().getName() + 
                          " completed get operation for '" + key + "' with result: " + result);
        return result;
    }

    class Segment<K, V> {
        HashMap<K, V> hashMap = new HashMap<>();
        ReentrantLock lock = new ReentrantLock();
        private final int segmentId;

        public Segment(int segmentId) {
            this.segmentId = segmentId;
        }

        public void put(K key, V value) {
            String threadName = Thread.currentThread().getName();
            
            System.out.println("[SEGMENT-" + segmentId + "] Thread " + threadName + 
                              " waiting for lock to PUT '" + key + "=" + value + "'");
            
            lock.lock();
            try {
                System.out.println("[SEGMENT-" + segmentId + "] 🔒 LOCK ACQUIRED by " + threadName + 
                                  " for PUT operation. Queue length: " + lock.getQueueLength());
                
                long startTime = System.currentTimeMillis();
                hashMap.put(key, value);
                long endTime = System.currentTimeMillis();
                
                System.out.println("[SEGMENT-" + segmentId + "] Thread " + threadName + 
                                  " stored '" + key + "=" + value + "' (took " + (endTime - startTime) + "ms)");
                
            } catch (Exception ex) {
                System.err.println("[SEGMENT-" + segmentId + "] ERROR in PUT by " + threadName + 
                                  ": " + ex.getMessage());
                ex.printStackTrace();
            } finally {
                System.out.println("[SEGMENT-" + segmentId + "] 🔓 LOCK RELEASED by " + threadName + 
                                  " after PUT operation");
                lock.unlock();
            }
        }

        public V get(K key) {
            String threadName = Thread.currentThread().getName();
            
            System.out.println("[SEGMENT-" + segmentId + "] Thread " + threadName + 
                              " waiting for lock to GET '" + key + "'");
            
            lock.lock();
            try {
                System.out.println("[SEGMENT-" + segmentId + "] 🔒 LOCK ACQUIRED by " + threadName + 
                                  " for GET operation. Queue length: " + lock.getQueueLength());
                
                long startTime = System.currentTimeMillis();
                V result = hashMap.get(key);
                long endTime = System.currentTimeMillis();
                
                System.out.println("[SEGMENT-" + segmentId + "] Thread " + threadName + 
                                  " retrieved '" + key + "=" + result + "' (took " + (endTime - startTime) + "ms)");
                
                return result;
            } catch (Exception ex) {
                System.err.println("[SEGMENT-" + segmentId + "] ERROR in GET by " + threadName + 
                                  ": " + ex.getMessage());
                ex.printStackTrace();
                return null;
            } finally {
                System.out.println("[SEGMENT-" + segmentId + "] 🔓 LOCK RELEASED by " + threadName + 
                                  " after GET operation");
                lock.unlock();
            }
        }
        
        // Additional utility method for debugging
        public int getSegmentSize() {
            lock.lock();
            try {
                return hashMap.size();
            } finally {
                lock.unlock();
            }
        }
    }
    
    // Utility method to print map state
    public void printMapState() {
        System.out.println("\n[MAP-STATE] Current ConcurrentHashMap state:");
        for (int i = 0; i < segments.size(); i++) {
            Segment<K, V> segment = segments.get(i);
            int size = segment.getSegmentSize();
            System.out.println("[MAP-STATE] Segment " + i + " contains " + size + " entries");
        }
        System.out.println("[MAP-STATE] End of map state\n");
    }
}
