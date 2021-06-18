package main;

public class ListElement<ET>{
    //Jedes Objekt hat einen double-Wert und eine Referenz auf das nächste Listenelement.
    private ET value;
    private ListElement<ET> next;
    private ListElement<ET> prev;
    private boolean isEmpty;


    public ListElement(){
        this.prev = null;
        this.value = null;
        this.next = null;
    }


    public ET getValue(){
        return value;
    }
    public void setValue(ET value){
        this.value= value;
    }
    public ListElement getNext(){ return next; }
    public ListElement setNext(ListElement next){
        this.next = next;
        return next;
    }

    public ListElement< ET > getPrev() {
        return prev;
    }
    public void setPrev(ListElement< ET > prev) {
        this.prev = prev;
    }
}