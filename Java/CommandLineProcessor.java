import java.util.ArrayList;
import java.util.List;

public class CommandLineProcessor {
    private UserManager userManager = new UserManager();

    /**
     * Обработка одной строки.
     * @param commandLine строка команды. Например: "add_user Alice 4". Другой пример: "get_users"
     * @return вывод команды, или null если команда не возвращает строку
     */
    public String processInput(String commandLine) {
        String[] parts = commandLine.split(" ");
        String command = parts[0];

        switch (command) {
            case "add_user":
                if (parts.length == 3) {
                    String name = parts[1];
                    int accessLevel = Integer.parseInt(parts[2]);
                    userManager.addUser(name, accessLevel);
                } else if (parts.length == 2) {
                    String name = parts[1];
                    userManager.addUser(name);
                }
                break;
            case "remove_user":
                if (parts.length == 2) {
                    String name = parts[1];
                    userManager.removeUser(name);
                }
                break;
            case "promote":
                if (parts.length == 2) {
                    String name = parts[1];
                    userManager.promote(name);
                }
                break;
            case "demote":
                if (parts.length == 2) {
                    String name = parts[1];
                    userManager.demote(name);
                }
                break;
            case "get_users":
                return userManager.getUsers();
            default:
                System.out.println("Неизвестная команда: " + command);
        }
        return null;
    }
}

class UserManager {
    private List<User> users;

    public UserManager() {
        users = new ArrayList<>();
    }

    public UserManager addUser(String name, int accessLevel) {
        users.add(new User(name, accessLevel));
        return this;
    }

    public UserManager addUser(String name) {
        return addUser(name, 1);
    }

    public UserManager removeUser(String name) {
        users.removeIf(user -> user.getName().equals(name));
        return this;
    }

    public UserManager promote(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                user.setAccessLevel(user.getAccessLevel() + 1);
            }
        }
        return this;
    }

    public UserManager demote(String name) {
        for (User user : users) {
            if (user.getName().equals(name) && user.getAccessLevel() > 0) {
                user.setAccessLevel(user.getAccessLevel() - 1);
            }
        }
        return this;
    }

    public String getUsers() {
        if (users.isEmpty()) {
            return "Не найдено";
        }
        StringBuilder result = new StringBuilder();
        for (User user : users) {
            result.append(user.getName()).append(": ").append(user.getAccessLevel()).append("\n");
        }
        return result.toString().trim();
    }
}

class User {
    private String name;
    private int accessLevel;

    public User(String name, int accessLevel) {
        this.name = name;
        this.accessLevel = accessLevel;
    }

    public String getName() {
        return name;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }
}
