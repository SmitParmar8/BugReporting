package DSDB;

public class List1 {
    //Node Class
    class Node{
        Node next;
        String description,priority;
        public Node(String description,String priority){
            this.description = description;
            this.priority =priority;
            next = null;
        }
    }
    
    public Node head;
    // Method to add First
   
    public void add(String description,String priority){
        if (priority.equalsIgnoreCase("High")) {
            addFirst(description, priority);
        }
        else if (priority.equalsIgnoreCase("Low")) {
            addBetween(description, priority);
        }
        else if(priority.equalsIgnoreCase("Medium")){
            addBetween(description, priority);
        }
    }
    public void addFirst(String description,String priority){
        Node n = new Node(description,priority);
        if (head==null) {
            head = n;
        }
        else{
            n.next = head;
            head=n;
            
        }
    }

    public void addBetween (String description,String priority) {
        Node n = new Node(description,priority);
        if (head==null) {
            head = n;
        }
        else{
        if(head.next==null&&head.priority.equalsIgnoreCase("high")){
            head.next =n;
        }
        else if(head.next==null&&head.priority.equalsIgnoreCase("low")){
            n.next = head;
            head =n;
        }
        else{
            Node temp = head;
            while (temp.next!=null&&temp.next.priority!="Low") {
             temp =temp.next;   
            }
            n.next = temp.next;
            temp.next = n;
        }
    }
    }
    public void addLast(String description,String priority){
        Node n = new Node(description,priority);
        if (head==null) {
            head = n;
        }
        else if(head.next==null){
            head.next=n;
        }
        else{
            Node temp = head;
            while (temp.next!=null) {
                temp = temp.next;
            }
            temp.next =n;
        }

    }
  public  void display(){
        Node temp = head;
        if(temp==null)
        {
            System.out.println("empty");
        }
        else
        {
            int i=1;
        while (temp!=null) {
            System.out.println(i+". "+temp.description + "----"+temp.priority);
            temp= temp.next;
            i++;
            }
        }
    }
   public void deleteFirst(){
        if(head==null){
            System.out.println("its Empty");
        }
        else{
            Node temp =head;
            head = head.next;
            temp =null;
        }
        
    }
    public void deleteLast(){
        if(head==null){
            System.out.println("its Empty");
        }
        else{
            Node temp =head;
            while (temp.next.next!=null) {
                temp = temp.next;
            }
            temp.next=null;
        }
    }
    public void deleteList(List1 l){
        l= null;

    }
}
