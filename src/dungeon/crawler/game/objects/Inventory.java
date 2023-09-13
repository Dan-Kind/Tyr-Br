/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon.crawler.game.objects;
import items.GameItem;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 0607dacs
 */
public class Inventory {
    private int weight;
    private final int size;
    private List<GameItem> items;

    public Inventory(int size) {
        this.weight = 0;
        this.size = size;
        this.items = new ArrayList<>();
    }
    
    public List<GameItem> getItems(){
        return items;
    }
    
    
    public boolean addItem(GameItem item) {
        // Return ture if item has been added, false is item not added (inventory full)
        System.out.println(this.getItemCount() + "..." + this.getSize() );
        if (this.getItemCount() <= this.getSize()){
            System.out.println("Adding item");
            items.add(item);
            weight += item.getWeight();
            return true;
        }
        else {
            System.out.println("Inventory full");
            return false;
        } 
        
    }

    public void removeItem(GameItem item) {
        items.remove(item);
        weight -= item.getWeight();
    }

    public GameItem getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
    }

    public int getItemCount() {
        return items.size();
    }

    public int getWeight() {
        return weight;
    }

    public int getSize() {
        return size;
    }
}
