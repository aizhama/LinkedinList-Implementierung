package main;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class MyLinkedList< T > implements List< T > {
    ListElement< T > anfangsKnotte;
    private static int counter = 0;

    public void incrementCounter() {
        counter++;
    }

    public void decrementCounter() {
        counter--;
    }

    //ToDo: Main
    public static void main(String[] args) {
        List< String > list = new MyLinkedList< String >();
        list.add("Erstes Element");

        List< String > origLinkedList = new MyLinkedList< String >();
        origLinkedList.add(new String("Erstes Element"));
        origLinkedList.add(new String("Zweites Element"));
        origLinkedList.add(new String("Drites Element"));

        origLinkedList.remove("Erstes Element");
        System.out.println("Are all the contents equal?: " + origLinkedList.containsAll(list));


        /***
         * for (String i : origLinkedList) {
         *             System.out.println(i);
         *         }
         *
         */


//        Integer list2[] = new Integer[origLinkedList.size()];
//        list2 = origLinkedList.toArray(list2);

        //String array[] = origLinkedList.toArray(new String[3]);

/***    for (Iterator i = origLinkedList.iterator(); i.hasNext();) {
 System.out.println("Iterator:" + i.next());
 }   */

/***        Stream<String> stream =origLinkedList.stream();
 stream.forEach(x->{});
 */

/***
 for (Object o : origLinkedList.toArray()) {
 System.out.println(o.toString());
 }
 System.out.println("Set: " + origLinkedList.set(1, "Test"));
 System.out.println("Contains: " + origLinkedList.contains(new String("Zweites Element")));
 */
    }

    public MyLinkedList() {
    }

    @Override
    public int size() {
        int size = 0;
        ListElement head = anfangsKnotte;//head is null
        //while(null.getNext()!=null)
        //if(head==null)
        //  return 0;
        if (head != null) {
            while (head.getNext() != null) {        //solange dass seine getNext ist nicht null
                head = head.getNext();      //head.getNext and size++
                size++;
            }
            head.getValue();
            size++;
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size() == 0)
            return true;
        else {
            return false;
        }
    }

    @Override
    public boolean contains(Object o) { //Überprüfung ob einen Element mit einer Value existiert
        //ListElement< T > element;
        //ToDo: Frage: wie und warum beide objecten haben gleichen strucktur?
        //in Obj o = liegt "gesuchte Element" mit seiner Value
        for (int i = 0; i < size(); i++) { //die Liste dürch gehen, solange bis die richtige Element mit gesuchten Value wird gefunden
            T elementValue = get(i); //elementValue= get(index) mit get(i) wir holen jedes Mal die Werte und vergleichen das unten mit Obj. o

            if (elementValue.equals(o)) {   //insolange beide sind objecten wird equals benutzt
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator< T > iterator() {
        return new Iterator< T >() {
            private ListElement< T > currentE = anfangsKnotte;

            @Override
            public boolean hasNext() {
                return currentE != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T data = currentE.getValue();
                currentE = currentE.getNext();
                return data;
            }
        };
    }

    /*** ToDo: Welcher von beiden Lösungen ist richtiger?

     return new Iterator< T >() {
     ListElement< T > currentE = anfangsKnotte;

     @Override public boolean hasNext() {      //return die nicht gleich null-->currE !=null
     return currentE != null;
     }   //ob gibt es die nächste und return currentE, bei denm ist next ist nicht null

     @Override public T next() {   //solange die methode getNext ist true
     T data = currentE.getValue(); //mit getValue kommt die neu Value ind currentE und dann in data wird es überschrieben
     currentE = currentE.getNext();      //in currentE index ist immer
     return data;
     }
     @Override public void forEachRemaining(Consumer<? super T> action) {
     Objects.requireNonNull(action);
     while (hasNext())
     action.accept(next());
     }
     };
     }
     */


    @Override
    public Object[] toArray() {
        int size = size();      //ToDo: warum in eine prim var ist möglich eine methode rein tun?

        Object[] array = new Object[size];
        if (array.length == size) {
            for (int j = 0; j < size; j++) {

                T elementValue = get(j);    //alle elemente durch gehen
                array[j] = elementValue;     //erstes E
                //array[j] = get(j);
            }
        }
        return array;
    }

    //ToDo: 1
    @Override
    //Aray with Generics <T>
    //create Array
    //a - это массив, в котором должны храниться элементы списка,
    // если он достаточно большой; в противном случае для этой цели
    // выделяется новый массив того же типа среды выполнения.
    public < T1 > T1[] toArray(T1[] a) {
        return a;
    }

    @Override
    public boolean add(T t) {
        ListElement< T > neuerKnote;//Reference
        neuerKnote = new ListElement< T >();//es wird immer diese var erstellt, worüber werden alle neu Nodes mit kommendes value(t) erstellt
        neuerKnote.setValue(t);
        neuerKnote.setNext(null);
        if (anfangsKnotte == null) {
            anfangsKnotte = neuerKnote;//1.anfangsKnotte==null-> dann ist das neuerKnote

        } else if (anfangsKnotte != null) {         //2. anfangsKnotte !=null-> dann ist 2.nKnotte>weiter>weiter
            ListElement nKnotte = anfangsKnotte;
            while (nKnotte.getNext() != null) {
                nKnotte = nKnotte.getNext();
            }
            nKnotte.setNext(neuerKnote);       //3. nach der else nKnotte muss auf neuerKnote zeigen
        }
        return false;
    }

    @Override
    public void add(int index, T element) {
        if (index > size()) {
            throw new IndexOutOfBoundsException(index);
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException(index);
        }

        if (index == size()) {
            ListElement< T > afterE = getElementAtIndex(index - 1);//letzte Element
            ListElement< T > neueE = new ListElement();
            neueE.setValue(element);
            neueE.setNext(null);
            afterE.setNext(neueE);//die neueE am Ende und jetzt afterE wird als vorherige und zeigt auf neueE

        } else if (index == 0) {
            ListElement< T > affectedE = getElementAtIndex(index);
            ListElement< T > neuElement = new ListElement();
            neuElement.setValue(element);
            neuElement.setNext(affectedE);//neue Element steht am Anfang und zeigt auf affectedE
            anfangsKnotte = neuElement;//1.anfangsKnotte==null-> dann ist das neuerKnote

        } else {
            ListElement< T > betroffeneE = getElementAtIndex(index);//current
            ListElement< T > vorE = getElementAtIndex(index - 1);//vorherige
            ListElement< T > neuElement = new ListElement();
            neuElement.setValue(element);
            neuElement.setNext(betroffeneE);//neuE->auf betroffene
            vorE.setNext(neuElement);//vorE->auf neu
        }
    }

    //ToDo: 2
    /* removes the first Node that contains Object o */
    //1)Liste dürch gehen
    //2) Find the previous node of the node to be deleted.
    //3) Change the next of the previous node.
    //4) Free memory for the node to be deleted.
    //5)wenn o.equals()currE.getValue) ->
    //vorE-> auf afterE

    @Override
    public boolean remove(Object o) {
        int i =0;
        if (i > size()) {
            throw new IndexOutOfBoundsException(i);
        }
        if (i < 0) {
            throw new IndexOutOfBoundsException(i);
        }
        boolean remove = false;
        int size=size();

        for(i= 0; i< size; i++){
            T elementV= get(i);
            if (elementV.equals(o)) {
                if(i==0){       //erste Element
                    ListElement< T > currE=getElementAtIndex(i);
                    ListElement< T > vorE=getElementAtIndex(i-1);
                    ListElement< T > afterE=getElementAtIndex(i+1);
                    anfangsKnotte.setNext(afterE);
                    return true;
                }else if(i ==size){

                }
            }

        }

        return remove;
    }
    /***        int i = 0;
        ListElement< T > currE;
        ListElement< T > vorE = null;
        ListElement< T > afterDeleteElement;

        for (currE = anfangsKnotte; currE != null; ) {

            if (o.equals(currE.getValue())) {
                afterDeleteElement = currE.getNext();//beim dritte zu löschen ist null

                if (vorE == null) {//currE= "Erste Knotte"
                    anfangsKnotte = afterDeleteElement; //dann in anf= "Zweite Knote" uberschreiben
                    //und afte= "Dritte Elemet"
                    return true;
                }
                vorE.setNext(afterDeleteElement);//vorE->afterE; currE->afterE
                remove = true;
                break;
            }
            vorE = currE;//vorE.equals(currE), also erstes
            currE = currE.getNext();//erstes->zweits->drites
            i++;
        }
        return remove;
    }       */

    //ToDo: 3- containsAll
    @Override
    public boolean containsAll(Collection< ? > c) {
        for(Object e: c)
            if(!contains(e))
                return false;
        return true;
    }

    //ToDo: 4
    @Override
    public boolean addAll(Collection< ? extends T > c) {
        return false;
    }

    //ToDo: 5
    @Override
    public boolean addAll(int index, Collection< ? extends T > c) {
        return false;
    }

    //ToDo: 6
    @Override
    public boolean removeAll(Collection< ? > c) {
        return false;
    }

    //ToDo: 7
    @Override
    public boolean retainAll(Collection< ? > c) {
        return false;
    }

    @Override
    public void clear() {
        anfangsKnotte = null;
    }


    @Override
    public T get(int index) {
        ListElement< T > elementAtindex = getElementAtIndex(index);//var elementAtIndex== getElementAtIndex()--alles was kommt asu der methode
        T returnValue = elementAtindex.getValue();       //in var returnValue speichern wir die Value von Knotte
        return returnValue;

    }

    private ListElement< T > getElementAtIndex(int index) {       //wird nur index von Knote zurückgegeben
        if (index >= size()) {
            throw new IndexOutOfBoundsException(index);
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException(index);
        }
        int i = 0;
        ListElement< T > knotte = anfangsKnotte;
        for (knotte = anfangsKnotte; knotte.getNext() != null; ) {  // von anfang=null solange die liste dürch gehen, bis zum wann index== i
            //System.out.println(knotte.getValue());
            if (index == i) {
                //System.out.println(index);
                return knotte;  //index von dieser knotte wird ausgegeben
            }
            knotte = knotte.getNext();          //sonst die liste weiterhin dürch gehen
            i++;
        }
        return knotte;  //index von der knote wird zurückgegeben
    }


    @Override
    public T set(int index, T element) {

        if (index >= size()) {
            throw new IndexOutOfBoundsException(index);
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException(index);
        }
        ListElement< T > currentE = getElementAtIndex(index);
        ListElement< T > vorE = getElementAtIndex(index);
        T vorElementValue = vorE.getValue();
        currentE.setValue(element);
        return vorElementValue;
    }

    @Override
    public T remove(int index) {
        //wenn index >als size; wenn Element ist null; letztes
        if (index < 0 || index > this.size() - 1)
            throw new IndexOutOfBoundsException(index);

        ListElement< T > vorE = getElementAtIndex(index - 1);//von methode()  ->varE==die ausgegebene index-1
        ListElement< T > deleteE = getElementAtIndex(index);//in deleteE==liegt index, was muss gelöscht werden
        ListElement< T > afterE = getElementAtIndex(index + 1); //von methode()  ->afterE==die ausgegebene index+1
        T returnDeleteE = deleteE.getValue();      //
        vorE.setNext(afterE);       //hier wird Zeiger von varE schon zu dem afterE gezeigt
        return returnDeleteE;
    }

    /*** simple Lösung
     int i = 0;
     ListElement<T> elementForDelete;
     ListElement<T> vorElementDel = null;
     ListElement<T> afterDeleteElement;

     for (elementForDelete = anfangsKnotte; elementForDelete.getNext() != null; ) {
     if(elementForDelete==null)
     throw new RuntimeException("can not be delete");
     if (index == i) {
     vorElementDel.getValue();           // Element Value for dem löschen == drittes Element
     elementForDelete.getValue();        //enthält viertes Element
     afterDeleteElement=elementForDelete.getNext();  //Vale Fünf und reference auf letztes
     vorElementDel.setNext(afterDeleteElement);      //vor dem gelöschten Element zeigt jetzt auf näachste Reference, nicht auf gelöschtes, so ist die Verkettete Knotte bleiben
     return elementForDelete.getValue();
     }
     vorElementDel=elementForDelete;
     elementForDelete=elementForDelete.getNext();
     i++;
     }
     return elementForDelete.getValue();
     }


    //ToDo: 8
    @Override
    public int indexOf(Object o) {
        return 0;
    }

    //ToDo: 9
    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    //ToDo: 10
    @Override
    public ListIterator< T > listIterator() {
        return null;
    }

    //ToDo: 11
    @Override
    public ListIterator< T > listIterator(int index) {
        return null;
    }

    //ToDo: 12
    @Override
    public List< T > subList(int fromIndex, int toIndex) {
        return null;
    }
}
