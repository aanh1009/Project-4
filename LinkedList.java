
import java.util.Iterator;
public class LinkedList<T> implements Iterable<T>, Queue<T>{
    private class LinkedListIterator implements Iterator<T> {
        private Node current;
        public LinkedListIterator() {
            this.current = head;
        }
        public boolean hasNext() {
            return current != null;
        }
        public T next() {
            T data = current.getData();
            current = current.getNext();
            return data;
        }
    }
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }
    private class Node{
        Node next;
        T data;
        public Node(Node next, T item){
            this.next = next;
            this.data = item;
        }
        public Node(T item){
            this(null, item);
        }
        public T getData(){
            return this.data;
        }
        public void setNext(Node n){
            this.next = n;
        }
        public Node getNext(){
            return this.next;
        }
    }
    private Node head;
    private int size;
    private Node tail;
    public LinkedList(){
        this.head = null;
        this.size = 0;
        this.tail = null;
    }
    public int size(){
        return this.size;
    }
    public void clear(){
        size = 0;
        head = null;
        tail = null;
    }
    public boolean isEmpty(){
        return (size==0);
    }
    @Override
    public String toString(){
        if (isEmpty()){
            return "";
        }
        String output = "";
        Node walker = head;
        int i = 0;
        while (i<size){
            output += walker.getData() + " ";
            walker=walker.getNext();
            i++;
        }
        return output;
    }
    public void add(T item){
        Node firstNode = new Node(item);
        firstNode.setNext(head);
        head = firstNode;
        if (size==0){
            tail = firstNode;
        }
        size++;
    }
    public boolean contains(Object o){
        if (size==0){
            return false;
        }
        Node walker = head; 
        int i=0;
        while (i<size){
            if (walker.getData().equals(o)){
                return true;
            }
            walker=walker.getNext();
            i++;
        }
        return false;
    }
    @Override
    public boolean equals(Object o){
        if (!(o instanceof LinkedList)){
            return false;
        }
        LinkedList<T> oAsALinkedList = (LinkedList) o;
        Node walker1 = head;
        Node walker2 = oAsALinkedList.head;
        int i = 0;
        while (i<size){
            if (!walker1.getData().equals(walker2.getData())){
                return false;
            }
            walker1 = walker1.getNext();
            walker2 = walker2.getNext();
            i++;
        }
        return true;
    }
    public T get(int index){
        if(size==0){
            return null;
        }
        if (index==0){
            return head.data;
        }
        if (index==size-1){
            return this.getLast();
        }
        Node walker = head;
        int i = 0; 
        T answer = null; 
        while (i<size){
            walker = walker.getNext();
            i++;
            if (i==index){
                answer = walker.getData();
                break;
            }
        }
        return answer;
    }
    public T remove(){
        T firstItem = head.getData();
        Node newHead = head.getNext();
        head = newHead;
        size--;
        if (size==0){
            tail = null;
        }
        return firstItem;
    }
    public void add(int index, T item){
        if (index==0){
            this.add(item);
        }
        else{
            if (index==size){
                this.addLast(item);
            }
            else {
                Node newNode = new Node(item);
                Node walker = head;
                int i = 0;
                while (i<this.size){
                    if (i==index-1){
                        Node nextNode = walker.getNext();
                        walker.setNext(newNode);
                        newNode.setNext(nextNode);
                        break;
                    }
                    else{
                        walker = walker.getNext();
                    }
                    i++;
                }
                size++;
            }
        }
    }
    public void addLast(T item){
        if (size==0){
            this.add(item);
        }
        else{
            Node lastNode = new Node(item);
            tail.next = lastNode;
            tail = tail.next;
            size++;
        }
    }
    public T remove(int index){
        if(size==0){
            return null;
        }
        if (index==0){
            return this.remove();
        }
        if (index==size-1){
            return this.removeLast();
        }
        Node walker = head;
        int i = 0;
        T output = null;
        while (i<size){
            if (i==index-1){
                Node removedNode = walker.getNext();
                output = removedNode.getData();
                Node newNextNode = removedNode.getNext();
                walker.setNext(newNextNode);
                size--;
                break;
            }
            else{
                walker = walker.getNext();
                i++;
            }
        }
        return output;
    }
    public T removeLast(){
        Node walker = head;
        T lastItem = null;
        for (int i =0; i<size-1;++i){
            if (i==size-2){
                lastItem = walker.getNext().getData();
                walker.setNext(null);
                tail = walker;
            }
            else{
                walker = walker.getNext();
            }
        } 
        size--;
        return lastItem;
    }
    public T getLast(){
        return tail.getData();
    }
    public void offer(T data){
        this.addLast(data);
    }
    public T poll(){
        return this.remove();
    }
    public T peek(){
        return this.get(0);
    }
}
