package lk.ijse.dep10.util;

public class SinglyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public void add(String value) {

        Node nodeAdd = new Node(Integer.parseInt(value), null);
        if(size == 0 ) {
            head = nodeAdd;
            tail = head;

        }
        else{
            tail.next = nodeAdd;
            tail = nodeAdd;
        }
        size++;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean contains(int value) {
        Node temp = head;
        while(temp != null){
            if(temp.data == value ) return true;
            temp =  temp.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public String get(int index) {
        Node temp = head;
        String g = "";
        int i = 0;
        while (i <= index){        // temp.next
            if(i == index){
                g += temp.data;
            }
            temp = temp.next;

            i++;

    }
        return g;
    }

    public void remove(int index) {
        if(size == 0) return;
        Node temp =head;
        Node previous = null;
        int i = 0;
        while (i <= index  ){

            if(index == 0){
                head = head.next;
                return;
            }else if(index == size -1) {
                tail = temp;
                return;
            } else if(i == index -1){
                previous = temp;
            }
            else if(i == index){
                temp= temp.next;
                previous.next = temp;
                return;
            }





            temp = temp.next;

            i++;

        }
        size--;
    }

    @Override
    public String toString() {
        String data = "[";
        Node temp = head;
        while (temp != null){        // temp.next
            data += temp.data + ",";
            temp = temp.next;

        }
        if(size != 0) data += "\b";
        data += "]";
       return data;
    }
}

class Node {
    int data;
    Node next;

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}
