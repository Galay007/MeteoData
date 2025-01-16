import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Customer {
    private User user;
    private Link link;
    private Map<String, Link> shortLinks = new HashMap<>();

    public Customer(UUID uuid) {
        this.user = new User(uuid);
    }

    public User getUser() {
        return  user;
    }

    public Link getLink() {
        return link;
    }

    public Map<String, Link> getShortLinks() {
        return shortLinks;
    }
}
