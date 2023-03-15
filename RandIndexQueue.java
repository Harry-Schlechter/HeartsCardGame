import java.util.Random;
import java.lang.RuntimeException;
public class RandIndexQueue<T> {

  private T [] data;
  private int back;
  private int front;
  private int doubleSize;
  private int movie;

  int size;
  //
    public RandIndexQueue(int sz){
      data = (T []) new Object[sz];
      size = 0;
      back = -1;
      front = 0;

    }//array initialized of specific length-constructor

    private void resetPositionData() {
     T[] newData = (T []) new Object[data.length];
      int j = 0;

      while (j<size){
        front = wrapAround(front);
        newData[j] = data[front];
        front++;
        j++;
      }//while
      data=newData;
      front = 0;
      back = size-1;
    }
    public void add(T one, T two, T three, T four){
      this.enqueue(one);
      this.enqueue(two);
      this.enqueue(three);
      this.enqueue(four);
    }

// enqueue and dequeue -------------------------------------------------
    public void enqueue(T newEntry){
      if (size==data.length){
        T[] newData = (T []) new Object[size*2];
        int j = 0;

        while (j<data.length){
          front = wrapAround(front);
          newData[j]=data[front];
          front++;
          j++;
        }//while
        data=newData;
        front = 0;
        back = size-1;
        data[back+1]=newEntry;
        movie++;
        back++;
        size++;
      }//if
      else if (back==data.length-1){
        data[0] = newEntry;
        back = 0;
        movie++;
        size++;
      }//else if
      else {
        data[back+1] = newEntry;
        movie++;
        back++;
        size++;
      }//else
    }//method

    public T dequeue(){
    if (size==0)
      throw new EmptyQueueException();
    else{
    T remove = data[front];
    //data[front] = null;
    movie++;
    size--;
    front++;
    front = wrapAround(front);
    return remove;
      }
    }
  //MyQ----------------------------------------------------------------

    public int capacity(){
      int capacity = data.length;
      return capacity;
    }

    public int getMoves(){
      return movie;
    }
    public void setMoves(int moves){
      movie = moves;
    }
  //Wrap around wrapAround-------------------------------------------------
  public int wrapAround(int test){
    if (test == data.length)
      return 0;
    else
      return test;
  }
  //Testing and functionality -----------------------------------------
    public RandIndexQueue(RandIndexQueue<T> old){
      data = (T []) new Object[old.data.length];
      size = 0;
      back = -1;
      front = 0;
      int i = 0;
      int j = old.front;
        while (i<old.size){
          j = wrapAround(j);
          data[i] = old.data[j];
          j++;
          i++;
        }
        size = old.size;
        back = old.size-1;
    }//copy constructor

    public boolean equals(RandIndexQueue<T> rhs){
      if (size != rhs.size) return false;
      int rhsFront = rhs.front;
      int thisFront = front;

      // System.out.println("size: " + size + "rhs Size: " + rhs.size);

      for (int j = 0; j<size; j++){
        thisFront = wrapAround(thisFront);
        // System.out.println("this: " + data[thisFront] + " rhs: " + rhs.data[rhsFront]);
        if (!data[thisFront].equals(rhs.data[rhsFront])) {
          return false;
        }
        else {
          rhsFront++;
            if (rhsFront==rhs.data.length)
              rhsFront = 0;
          }//if
          thisFront += 1;
        }//loop
        return true;

    }//returns true if queues are logically equivlant

    public String toString(){
      String ret = "Contents: ";
      int thisFront = front;
      while (thisFront != back+1) {
        if (thisFront == data.length) thisFront = 0;
        ret += (data[thisFront].toString()+" ");
        thisFront += 1;
      }
      return ret;

    }//make a string of all data from front to back and return
  //indexable--------------------------------------------------------
    public T get(int i){
      if (size<i+1)
        throw new IndexOutOfBoundsException();
      else if (front+i>=data.length)
        return data[(front+i) - data.length];
      else
        return data[front+i];
    }
    //
    public void set(int i, T item){
      if (size<i+1)
        throw new IndexOutOfBoundsException();
      else if (front+i>=data.length)
        data[(front+i)-data.length] = item;
      else
        data[front+i] = item;
    }

    public int size(){
      return size;
    }//size method
  //Shufflable --------------------------------------------------------
    public void shuffle(){
      //arraylist instead
      T [] temp = (T []) new Object[data.length];
      Random rando = new Random();
      int index;
      resetPositionData();
      for (int k=0; k<size; k++){
        index = front + rando.nextInt(back+1);
        temp[k] = data[index];
        if (index!=back){
          data[index] = data[back];
          data[back] = null;
          back--;
          }
        else{
          data[index] = null;
          back--;
        }
      }//for
      data = temp;
      front = 0;
      back = size-1;
    }

    // QueInterface --------
    public T getFront() {
      if (size>0)
        return data[front];
      else
        throw new EmptyQueueException("The Queue is empty");
    }

  /** Detects whether this queue is empty.
      @return  True if the queue is empty, or false otherwise. */
  public boolean isEmpty() {
    return size==0;
  }

  /** Removes all entries from this queue. */
  public void clear() {
    data = (T []) new Object[data.length];
    size = 0;
    front = 0;
    back = -1;
  }
}//end class
