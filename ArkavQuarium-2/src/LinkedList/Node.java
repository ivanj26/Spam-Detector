package LinkedList;


/**
 *
 * @author Mark
 * @param <E>
 */
public class Node<E extends Comparable<E>> implements Cloneable, Comparable<Node<E>>{
    private Node<E> Next;
    private Node<E> Prev;
    private E Value;

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    /**
     * This constructs Node of generic element with a given generic element.
     * @param Value Generic element.
     */
    public Node(E Value){
        this.Value = Value;
        Next = null;
        Prev = null;
    }

    /**
     * This constructs Node of generic element with specified Value, Next, Prev.
     * @param Value Generic element.
     * @param Next Node of generic element.
     * @param Prev Node of generic element.
     */
    public Node(E Value, Node<E> Next, Node<E> Prev){
      this.Value = Value;
      this.Next = Next;
      this.Prev = Prev;
    }

    /**
     * This returns Node of generic element.
     * @return Node of generic element.
     */
    public Node<E> getNext(){
        return Next;
    }

    /**
     * This method sets next of current node.
     * @param newAddress Node of generic element.
     */
    public void setNext(Node<E> newAddress){
        Next = newAddress;
    }

    /**
     * This returns Node of generic element.
     * @return
     */
    public Node<E> getPrev(){
      return Prev;
    }

    /**
     * This method sets prev of current node.
     * @param newAddress Node of generic element.
     */
    public void setPrev(Node<E> newAddress){
      Prev = newAddress;
    }

    /**
     * This returns value of Node.
     * @return Generic element.
     */
    public E getValue(){
        return Value;
    }

    /**
     * This method sets value of Node.
     * @param Value Generic element.
     */
    public void setValue(E Value){
        this.Value = Value;
    }

    /**
     * This method assigns the current node with given Node of generic.
     * @param node Node of generic element.
     */
    public void assign(Node<E> node){
      Next = node.Next;
      Prev = node.Prev;
      Value = node.Value;
    }

    @Override
    public int compareTo(Node<E> node){
      return Value.compareTo(node.Value);
    }
}
