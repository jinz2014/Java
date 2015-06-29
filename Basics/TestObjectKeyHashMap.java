import java.util.*;

/**
  * A slightly modifed example of HashMap with Object as the key
  * We must override the HashCode and equals methods to make the
  * Object key comparable
  */

public class TestObjectKeyHashMap {
 
  public static void main(String a[]){
    Map<Sale, String> hm = new HashMap<>();
    hm.put(new Sale("Banana", 20), "Mexico");
    hm.put(new Sale("Apple", 40),  "New York");
    hm.put(new Sale("Orange", 30), "Florida");
    printMap(hm);
    Sale key = new Sale("Banana", 20);
    System.out.println("Does Banana available? "+hm.containsKey(key));
  }
     
  public static void printMap(Map<Sale, String> map){
       
    //Set<Sale> keys = map.keySet();
    for(Sale p : map.keySet()){
      System.out.println(p);
    }


    // returns null as the key is not in the HashMap
    System.out.println(map.get(new Sale("Pearl", 20)));
  }
}
 
class Sale{
     
    private String item;
    private int price;
     
    public Sale(String item, int price){
        this.item = item;
        this.price = price;
    }
     
    public int hashCode(){
        int hash = 1;
        hash = hash * 31  + price;
        hash += hash * 31 + item.hashCode();
        return hash;
    }
     
    public boolean equals(Object obj) {
        System.out.println("In equals");
        if (this == obj)
          return false;
        if (obj instanceof Sale) {
          Sale p = (Sale) obj;
          return (item.equals(p.item) && p.price == price);
        }
        return false;
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
     
    public String toString() {
        return "item: "+item+"  price: "+price;
    }
}

