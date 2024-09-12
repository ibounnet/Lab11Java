package model;

public class Order {
    private String id;
    private String menu;
    private String sweetness;
    private String temperature;
    private String topping;

    // Constructor
    public Order(String id, String menu, String sweetness, String temperature, String topping) {
        this.id = id;
        this.menu = menu;
        this.sweetness = sweetness;
        this.temperature = temperature;
        this.topping = topping;
    }

    public Order(String menu, String sweetness, String temperature, String topping) {
        this(null, menu, sweetness, temperature, topping);
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getMenu() {
        return menu;
    }

    public String getSweetness() {
        return sweetness;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getTopping() {
        return topping;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", menu='" + menu + '\'' +
                ", sweetness='" + sweetness + '\'' +
                ", temperature='" + temperature + '\'' +
                ", topping='" + topping + '\'' +
                '}';
    }
}
