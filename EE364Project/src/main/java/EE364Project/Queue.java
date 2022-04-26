package EE364Project;

import java.util.Arrays;

//
/**
 * @author  www.techiedelight.com + Ali Almousa
 *
 */
class Queue
{
	/**
	 * array to store queue elements
	 */
    public Bus[] arr;      
    /**
     * front points to the front element in the queue
     */
    private int front;      
    /**
     * rear points to the last element in the queue
     */
    private int rear;       
    /**
     * maximum capacity of the queue
     */
    private int capacity;   
    /**
     * current size of the queue
     */
    private int count;      
    /**
     * Constructor to initialize a queue
     * 
     * @param size which is the number of busses in the queue 
     */
    Queue(int size)
    {
        arr = new Bus[size];
        capacity = size;
        front = 0;
        rear = -1;
        count = 0;
    }
    
    /**
     * Utility function to dequeue the front element
     */
    public void dequeue()
    {
        // check for queue underflow
        if (isEmpty())
        {
            System.out.println("Take a taxi ya wahsh!\nDon't forget your Azkar");
            System.exit(1);
        }
 
//        System.out.println("Removing " + arr[front]);
 
        front = (front + 1) % capacity;
        count--;
    }
    /**
     * Utility function to add an item to the queue
     * 
     * @param item (bus) to be added to the queue
     */
    public void enqueue(Bus item)
    {
        // check for queue overflow
        if (isFull())
        {
            System.out.println("Take a taxi ya wahsh!\nDon't forget your Azkar");
            System.exit(1);
        }
 
//        System.out.println("Inserting " + item);
 
        rear = (rear + 1) % capacity;
        arr[rear] = item;
        count++;
    }
    /**
     * Utility function to return the front element of the queue
     * 
     * @return A bus that was added first to the queue
     */
    public Bus peek()
    {
        if (isEmpty())
        {
            System.out.println("Take a taxi ya wahsh!\nDon't forget your Azkar");
            System.exit(1);
        }
        return arr[front];
    }
    /**
     * Utility function to return the size of the queue
     * 
     * @return number of busses in the queue
     */
    public int size() {
        return count;
    }
    /**
     * Utility function to check if the queue is empty or not
     * 
     * @return A boolean indicating wither the queue is empty
     */
    // 
    public Boolean isEmpty() {
        return (size() == 0);
    }
    /**
     * Utility function to check if the queue is full or not
     * 
     * @return A boolean indicating wither the queue is empty
     */
    public Boolean isFull() {
        return (size() == capacity);
    }
    /**
     * AString representation of the elements of queue
     */
    public String toString() {
    	String arrString = Arrays.toString(this.arr);
    	return "Queue: "+ arrString;
    }
}
