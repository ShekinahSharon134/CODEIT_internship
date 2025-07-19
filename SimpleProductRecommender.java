import java.util.*;

public class SimpleProductRecommender {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Product data with tags (features)
        Map<String, List<String>> productTags = new HashMap<>();
        productTags.put("Smartphone", Arrays.asList("Android", "Battery", "Touch"));
        productTags.put("DSLR Camera", Arrays.asList("Camera", "Zoom", "Lens"));
        productTags.put("Gaming Laptop", Arrays.asList("Gaming", "GPU", "RAM"));
        productTags.put("Smartwatch", Arrays.asList("Fitness", "Bluetooth", "Battery"));
        productTags.put("Bluetooth Headphones", Arrays.asList("Music", "Bluetooth", "Noise Cancelling"));

        // Step 2: Get user preferences
        System.out.print("Enter your preferences (comma separated): ");
        String[] input = sc.nextLine().split(",");
        List<String> userPrefs = new ArrayList<>();
        for (String pref : input) {
            userPrefs.add(pref.trim());
        }

        // Step 3: Calculate similarity (count of matching tags)
        Map<String, Integer> scoreMap = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : productTags.entrySet()) {
            int score = 0;
            for (String tag : entry.getValue()) {
                if (userPrefs.contains(tag)) {
                    score++;
                }
            }
            scoreMap.put(entry.getKey(), score);
        }

        // Step 4: Sort products by match score
        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(scoreMap.entrySet());
        sorted.sort((a, b) -> b.getValue() - a.getValue());

        // Step 5: Show top recommendations
        System.out.println("\n Your preferences: " + userPrefs);
        System.out.println(" Top recommended products:");

        boolean found = false;
        for (Map.Entry<String, Integer> entry : sorted) {
            if (entry.getValue() > 0) {
                System.out.println(" " + entry.getKey() + " (Match Score: " + entry.getValue() + ")");
                found = true;
            }
        }

        if (!found) {
            System.out.println(" No matching products found.");
        }

        sc.close();
    }
}

