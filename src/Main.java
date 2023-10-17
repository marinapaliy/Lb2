import java.util.Queue;
import java.util.LinkedList;

// Клас для представлення товару
class Item {
    private String name;  // Найменування товару
    private float price;  // Ціна товару

    // Конструктор класу Item, який ініціалізує найменування та ціну товару
    public Item(String name, float price) {
        this.name = name;
        if (price < 0) {
            this.price = 0;  // Перевірка на негативну ціну
        } else {
            this.price = price;
        }
    }

    // Метод для підвищення ціни товару на певний відсоток
    public void increasePrice(float percent) {
        price += price * percent / 100;
    }

    // Метод для зниження ціни товару на певний відсоток
    public void decreasePrice(float percent) {
        price -= price * percent / 100;
        if (price < 0) {
            price = 0;
        }
    }

    // Метод для отримання ціни товару
    public float getPrice() {
        return price;
    }
}

// Клас для представлення кошика
class Cart {
    private Queue<Item> items;  // Використовуємо чергу для зберігання товарів
    private int maxCapacity;  // Максимальна кількість елементів у кошику

    // Конструктор класу Cart, ініціалізує максимальну кількість елементів у стеку
    public Cart(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        items = new LinkedList<>();  // Використовуємо чергу на базі LinkedList
    }

    // Метод для додавання товару до кошика
    public void addItem(Item item) {
        if (items.size() < maxCapacity) {
            items.offer(item);  // Додаємо товар у чергу
        } else {
            System.out.println("Cart is full. Cannot add more items.");
        }
    }

    // Метод для видалення товару з кошика
    public void removeItem() {
        if (!items.isEmpty()) {
            items.poll();  // Видаляємо перший товар із черги
        } else {
            System.out.println("Cart is empty. Cannot remove items.");
        }
    }

    // Метод для підрахунку загальної ціни товарів у кошику
    public float calculateTotalPrice() {
        float total = 0;
        for (Item item : items) {
            total += item.getPrice();  // Додаємо ціну кожного товару до загальної суми
        }
        return total;
    }

    // Метод для підвищення цін на всі товари у кошику на певний відсоток
    public void increasePrices(float percent) {
        for (Item item : items) {
            item.increasePrice(percent);
        }
    }

    // Метод для зниження цін на всі товари у кошику на певний відсоток
    public void decreasePrices(float percent) {
        for (Item item : items) {
            item.decreasePrice(percent);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Cart cart = new Cart(10);  // Створюємо кошик з максимальною кількістю елементів

        // Додаємо товари до кошика
        cart.addItem(new Item("Item 1", 10.0f));
        cart.addItem(new Item("Item 2", 15.0f));
        cart.addItem(new Item("Item 3", 20.0f));
        cart.addItem(new Item("Item 4", 25.0f));
        cart.addItem(new Item("Item 5", 30.0f));

        // Виводимо загальну ціну товарів у кошику
        System.out.println("Total Price: " + cart.calculateTotalPrice());

        // Піднімаємо ціни на 15% і виводимо змінену суму цін
        cart.increasePrices(15);
        System.out.println("Total Price after 15% increase: " + cart.calculateTotalPrice());

        // Знижуємо ціни на 30% і виводимо змінену суму цін
        cart.decreasePrices(30);
        System.out.println("Total Price after 30% decrease: " + cart.calculateTotalPrice());
    }
}
