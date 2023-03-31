// Abstract class Item that is extended by all Item types
public abstract class Item {
    private String name;
    private double price;
    private int level;

    public Item(String name, double price, int level) {
        this.name = name;
        this.price = price;
        this.level = level;
    }
    public String toString() {
        return name;
    }
    public String getName() {
        return name;
    }

    public boolean equals(Item item) {
        if(item.name == this.name){
            return true;
        }
        else{
            return false;
        }
    }

    public double getPrice() {
        return price;
    }

    public int getLevel() {
        return level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
