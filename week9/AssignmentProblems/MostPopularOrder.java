import java.util.*;

public class MostPopularOrder {


static Object[] mostPopular(String[] orders) {
    HashMap<String, Integer> count = new HashMap<>();

    for (String item : orders) {
        count.put(item, count.getOrDefault(item, 0) + 1);
    }

    String bestItem = orders[0];
    int bestCount = count.get(bestItem);

    for (String item : orders) {
        int currentCount = count.get(item);

        if (currentCount > bestCount) {
            bestCount = currentCount;
            bestItem = item;
        }
    }

    return new Object[]{bestItem, bestCount};
}

public static void main(String[] args) {
    String[] orders = {
        "dosa", "idli", "vada", "dosa",
        "idli", "dosa", "tea"
    };

    Object[] result = mostPopular(orders);

    System.out.println("(" + result[0] + ", " + result[1] + ")");
}


}
