/*
   Brian Arlantico
   cssc1453
*/


public class StockItem implements Comparable<StockItem> {
    String SKU;
    String description;
    String vendor;
    float cost;
    float retail;

    // Constructor. Creates a new StockItem instance.
    public StockItem(String SKU, String description, String vendor,
                     float cost, float retail) {

        this.SKU = SKU;
        this.description = description;
        this.vendor = vendor;
        this.cost = cost;
        this.retail = retail;

    }

    // Follows the specifications of the Comparable Interface.
    // The SKU is always used for comparisons, in dictionary order.
    public int compareTo(StockItem n) {
        return this.SKU.compareTo(n.SKU);
    }

    // Returns an int representing the hashCode of the SKU.
    public int hashCode() {
        return this.SKU.hashCode();
    }

    // standard get methods
    public String getDescription() {
        return this.description;
    }

    public String getVendor() {
        return this.vendor;
    }

    public float getCost() {
        return (float) this.cost;
    }

    public float getRetail() {
        return (float) this.retail;
    }

    // All fields in one line, in order
    public String toString() {
        return "SKU: " + this.SKU + " Description: " + this.description + " Vendor: " + this.vendor + " Cost: " + this.cost + " Retail: " + this.retail;
    }
} 