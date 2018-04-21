package LinkedList;

/**
 *
 * @author Mark
 * @param <E> Generic element.
 */

public class LinkedList<E extends Comparable<E>>{
  private Node<E> Head;
  private int currentSize;

    /**
     *  This constructs LinkedList of generic element with no parameter.
     */
    public LinkedList(){
    Head = null;
    currentSize = 0;
  }

    /**
     * This constructs LinkedList of Generic element with a specified Node of generic element.
     * @param Head Head of LinkedList.
     */
    public LinkedList(Node<E> Head){
    this.Head = Head;
    currentSize = 1;
  }

    /**
     * This returns LinkedList empty condition.
     * @return true if LinkedList is empty.
     */
    public boolean isEmpty(){
    return Head == null;
  }

    /**
     * This method inserts a given element to LinkedList.
     * @param e Generic element 
     */
    public void add(E e){
    if (isEmpty()){
      Head = new Node<E>(e);
    } else {
      Node<E> newElmt = new Node<E>(e);
      while (Head.getNext() != null){
        Head = Head.getNext();
      }
      newElmt.setPrev(Head);
      Head.setNext(newElmt);
      while (Head.getPrev() != null){
        Head = Head.getPrev();
      }
    }
    currentSize++;
  }

    /**
     * This method removes the element of LinkedList with a given index.
     * @param idx index of the element.
     */
    public void remove(int idx){
    if (idx < currentSize){
      for (int i = 0; i < idx; i++){
        Head = Head.getNext();
      }
      Node<E> prev = Head.getPrev();
      prev.setNext(Head.getNext());
      if (Head.getNext() != null)
        Head.getNext().setPrev(prev);
      Head = null;
      Head = prev;

      while (Head.getPrev() != null){
        Head = Head.getPrev();
      }
      currentSize--;
    }
  }

    /**
     * This method sets Head of LinkedList. 
     * @param Head Node of generic element.
     */
    public void setHead(Node<E> Head){this.Head = Head;}

    /**
     * This returns Head of LinkedList.
     * @return Head.
     */
    public Node<E> getHead(){return Head;}

    /**
     * This returns current size of LinkedList.
     * @return current size of LinkedList.
     */
    public int getCurrentSize(){return currentSize;}

    /**
     * This method sets current size of LinkedList.
     * @param currentSize current size.
     */
    public void setCurrentSize(int currentSize){this.currentSize = currentSize;}

    /**
     * This returns index of element in LinkedList with a given parameter Node of generic element.
     * @param otherNode Node of generic element.
     * @return index of given parameter.
     */
    @SuppressWarnings("unchecked")
    public int find(Node<E> otherNode){
      Node<E> node = null;
      try{
        node = (Node<E>) Head.clone();
        for (int i = 0; i < currentSize; i++){
          if (node.compareTo(otherNode) == 0){
            return i;
          }
          node = node.getNext();
        }
      } catch (CloneNotSupportedException ex){
        ex.printStackTrace();
      }
      return -1; //Tidak ketemu atau CloneNotSupportedException
    }

    /**
     * This returns Node of generic element with a given index. 
     * @param idx index of element.
     * @return Node of generic element.
     */
    @SuppressWarnings("unchecked")
    public Node<E> getIndex(int idx){
      Node<E> node = null;
      if (idx < currentSize){
        try {
          node = (Node<E>) Head.clone();
          for (int i = 0; i < idx; i++){
            node = node.getNext();
          }
        } catch(CloneNotSupportedException ex){
          ex.printStackTrace();
        }
      }
      return node;
    }

    /*Ini driver dihapus aja*/
    public static void main(String[] args){
      // Kalau Integer class masih mudah
      System.out.println("--------INI TEST CASE 1----------");

      LinkedList<Integer> list = new LinkedList<Integer>();
      list.add(10);
      list.add(20);
      list.add(30);
      System.out.println(list.getHead().getValue());
      System.out.println(list.getIndex(2).getValue());

      list.remove(1);
      Integer i = list.find(new Node<Integer>(20));
      System.out.println("Index dari element 20 adalah " + i + "\n");


      System.out.println("\n--------INI TEST CASE 2----------");
      //Gimana kalau class lain(?) bukan Integer, Float, ataupun Double
      //Solusinya buat kelas yang mengimplementasikan interface Comparable
      class Fish implements Comparable<Fish>{ //harus implement comparable
        public int ID;
        private int x;
        private int y;

        public Fish(int ID){x = 0; y = 0; this.ID = ID;}
        public Fish(int x, int y, int ID){this.x = x; this.y = y; this.ID = ID;}
        public int getX(){return x;}
        public int getY(){return y;}
        public void setX(int x){this.x = x;}
        public void setY(int y){this.y = y;}

        @Override
        public int compareTo(Fish fish){
          if (ID == fish.ID)
            return 0;
          else if (ID > fish.ID)
            return 1;
          else
            return -1;
        }
      }

      Fish fish = new Fish(10,10,1);
      Fish fish2 = new Fish(11,15,2);
      Fish fish3 = new Fish(10,20,3);

      LinkedList<Fish> listOfFish = new LinkedList<Fish>(new Node<Fish>(fish));
      listOfFish.add(fish2);
      listOfFish.add(fish3);

      System.out.println("Index dari elemen fish2:\t" + listOfFish.find(new Node<Fish>(fish2)));
    }
}
