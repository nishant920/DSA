class LRUCache {
    
    public class Node{
        int key;
        int value;
        Node prev;
        Node next;
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    HashMap<Integer, Node> map;
    Node head;
    Node tail;
    int totalCap;
    int currentCap;

    public LRUCache(int capacity) {
        this.totalCap = capacity;
        this.currentCap = 0;
        this.map = new HashMap<>();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    public void breakConnection(Node n){
         // Breaking connection
        Node prev = n.prev;
        Node next = n.next;
        prev.next = next;
        next.prev = prev;
    }

    public void addNodeInHeadNext(Node n){
        // Establish connection 
        Node headNext = head.next;
        head.next = n;
        headNext.prev = n;
        n.next = headNext;
        n.prev = head;
    }

    public void makeNodeLatestUsed(Node n){
        breakConnection(n);
        addNodeInHeadNext(n);
    }
    
    public int get(int key) {
        Node n = map.get(key);
        if(n == null){
            return -1;
        }
        this.makeNodeLatestUsed(n);
        return n.value;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            // update
            Node n = map.get(key);
            n.value = value;
            this.makeNodeLatestUsed(n);
        }else{
            // insert 
            if(currentCap == totalCap){
                // Insert new Node in the starting 
                // And from tail removed least recently used node. 
                map.remove(tail.prev.key);
                this.breakConnection(tail.prev);
            }
            Node nn = new Node(key, value);
            this.addNodeInHeadNext(nn);
            this.map.put(key, nn);
            if(currentCap < totalCap){
                currentCap++;
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
