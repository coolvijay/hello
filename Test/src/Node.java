



public class Node extends NodeInterface {
    int data;
    private Node next;
    
    @Override
    public Node addnNewNode(Node headNode, int data)
    {
        if(headNode == null) {
            Node newNode = new Node();
            newNode.data = data;
            newNode.next = null;
            return newNode;
        }else {
           Node n = new Node();
           
           n.next=headNode;
           n.data=data;
           
           headNode = n;
           return headNode;
        }
        
    }
    
    @Override
    public void printNodes(Node headNode)
    {
        Node tempNode = headNode;
        if(tempNode == null) {
            System.out.println("list is empty");
        }else {
            while(tempNode!=null) {
                
                System.out.println("element :" + tempNode.data);
                tempNode=tempNode.next;
                
            }
        }
    }
}
