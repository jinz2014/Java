import java.util.*;

/**
  * A slightly modifed example of HashMap with Object as the key
  *
  */
public class TestObjectKeyHashMap {
 
  public static void main(String a[]){
    Map<Price, String> hm = new HashMap<Price, String>();
    hm.put(new Price("Banana", 20), "Banana");
    hm.put(new Price("Apple", 40), "Apple");
    hm.put(new Price("Orange", 30), "Orange");
    printMap(hm);
    Price key = new Price("Banana", 20);
    System.out.println("Does Banana available? "+hm.containsKey(key));
  }
     
  public static void printMap(Map<Price, String> map){
       
    Set<Price> keys = map.keySet();
    for(Price p : keys){
      System.out.println(p + " ==> " + map.get(p));
    }

    // returns null as the key is not in the HashMap
    System.out.println(map.get(new Price("Pearl", 20)));
  }
}
 
class Price{
     
    private String item;
    private int price;
     
    public Price(String itm, int pr){
        this.item = itm;
        this.price = pr;
    }
     
    public int hashCode(){
        int hash = 1;
        hash = hash * 31  + price;
        hash += hash * 31 + item.hashCode();
        return hash;
    }
     
    public boolean equals(Object obj){
        System.out.println("In equals");
        if (this == obj)
          return false;
        if (obj instanceof Price) {
            Price p = (Price) obj;
            return (item.equals(p.item) && p.price == price);
        } else {
            return false;
        }
    }
     
    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
     
    public String toString(){
        return "item: "+item+"  price: "+price;
    }
}

