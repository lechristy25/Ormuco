import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Lenoy Christy
 * This class is responsible for the creation of an LRU cache with create, update, and get operations, and time expiry. 
 * A hashmap and doubly-linked list are used to build the underlying data structure of this cache
 *
 */


public class GeoLRU {

	//Node class for the doubly-linked list.
	private class DLNode{
		int key; 
		int value; 
		long lastUseTime; //time since the node was last accessed

		DLNode previous;
		DLNode next;
	}
	
	DLNode head;
	DLNode tail; 
	private static Map<Integer, DLNode> hashmap = new HashMap<Integer, DLNode>(); //init hashmap
	
	int capacity; //user defined cache capacity
	static int itemTotal;
	static long expiration; //user defined cache expiration time in milliseconds
	
	
	//constructor for the cache
	public GeoLRU(int capacity, long expirationTime) {
		this.capacity = capacity; 
		expiration = expirationTime;
		itemTotal = 0;
		head = new DLNode();
		tail = new DLNode();
		head.next = tail;
		tail.previous = head;
	}
	
	//create a new cache element or update an existing one.
	public void putOrUpdate(int key, int value) {
		DLNode node = hashmap.get(key);
		if (node != null) { //if an element with the specified key already exists, update it.
			node.value = value;
			listRemove(node);     
			addToListFront(node);
			updateUseTime(node);  //updating the position and lastUseTime of the element after it has been accessed.
		}
		else { // element with the specified key does not exist, therefore a new element is created.
			DLNode newNode = new DLNode();
			newNode.key = key;
			newNode.value = value;
			newNode.lastUseTime = System.currentTimeMillis();
			addToListFront(newNode);
			hashmap.put(key, newNode);
			addToListFront(newNode); //the newly created element is the most recently accessed, therefore it gets added to the list front.
			itemTotal++;
			
			if(itemTotal > capacity) cacheLRURemove(); //respecting cache capacity, remove the LRU element if need be. 
			
		}
	}
	
	//get an existing cache element's value
	public Integer get(int key) {
		DLNode node = hashmap.get(key);
		if(node == null) return null; // if no cache element exists with the specified key
		listRemove(node);
		addToListFront(node);
		updateUseTime(node);
		return node.value;
	}
	
	private static void listRemove(DLNode node) {// remove a node from the doubly linked list
		node.previous.next = node.next; 
		node.next.previous = node.previous;
	}
	
	private void addToListFront(DLNode node) { // add a specified node to the front of the doubly linked list
		node.previous = head;
		node.next = head.next; 
		head.next.previous = node;
		head.next = node;
	}
	
	private void cacheLRURemove() { // remove an element entirely from the cache
		DLNode LRUNode = tail.previous; 
		listRemove(LRUNode); 
		hashmap.remove(LRUNode.key);
		--itemTotal;
	}
	
	public void updateUseTime(DLNode node) { // update the last use time of a node
		node.lastUseTime = System.currentTimeMillis();
	}
	
	public static void deleteIfExpired(DLNode node) { //method to delete an expired cache element
		if((node.lastUseTime + expiration) < System.currentTimeMillis()) {
			listRemove(node);
			hashmap.remove(node.key);
			--itemTotal;
		}
	}
	
	static {
	    //concurrent thread to continuously scan for and remove expired cache elements.
	    new Thread(new Runnable() {
	      @Override
	      public void run() {
	        try {
	          while (true) {
	        	  for(DLNode node: hashmap.values()) deleteIfExpired(node);
	          }
	        } catch (Exception e) {
	          e.printStackTrace();
	        }
	      }
	    }).start();
	  }
	
}
