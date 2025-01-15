import java.util.*;

public class Category {

    private Map<String,Double> expensesCat = new HashMap<>();
    private Set<String> incomeCat = new HashSet<>();

    public Map<String,Double> getExpenseCat() {
        return expensesCat;
    }

    public Set<String> getIncomeCat() {
        return incomeCat;
    }


    }

//    private Map<String, List<String>> categories;
//    public Category() {
//        this.categories = new HashMap<>();
//    }
//    public void setCategories(Map<String, List<String>> categories){
//        this.categories = categories;
//    }
//
//
//    public Map<String, List<String>> getCategories() {
//        return this.categories;
//    }
//
//
//    public void addCategory(String username, String category) {
//        categories.computeIfAbsent(username, k -> new ArrayList<>()).add(category);
//    }
//
//
//    public List<String> getCategories(String username) {
//        return categories.getOrDefault(username, new ArrayList<>());
//    }

//}