import java.util.ArrayList;
import java.util.Scanner;

// ================= BOOK CLASS =================
class Book {
    private int id;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issue() {
        isIssued = true;
    }

    public void returnBook() {
        isIssued = false;
    }

    // ✅ Overriding toString() method
    @Override
    public String toString() {
        return id + " - " + title + " by " + author + (isIssued ? " [Issued]" : " [Available]");
    }
}

// ================= USER CLASS =================
class User {
    private int userId;
    private String name;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    // ✅ Overriding toString() method
    @Override
    public String toString() {
        return "User ID: " + userId + ", Name: " + name;
    }
}

// ================= LIBRARY CLASS =================
class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void showAllBooks() {
        System.out.println("\n--- Book List ---");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    public void issueBook(int bookId, int userId) {
        Book book = findBookById(bookId);
        User user = findUserById(userId);

        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        if (user == null) {
            System.out.println("User not found!");
            return;
        }

        if (book.isIssued()) {
            System.out.println("Book is already issued!");
        } else {
            book.issue();
            System.out.println("Book '" + book.getTitle() + "' issued to " + user.getName());
        }
    }

    public void returnBook(int bookId) {
        Book book = findBookById(bookId);

        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        if (!book.isIssued()) {
            System.out.println("This book was not issued!");
        } else {
            book.returnBook();
            System.out.println("Book '" + book.getTitle() + "' has been returned successfully!");
        }
    }

    private Book findBookById(int id) {
        for (Book b : books) {
            if (b.getId() == id)
                return b;
        }
        return null;
    }

    private User findUserById(int id) {
        for (User u : users) {
            if (u.getUserId() == id)
                return u;
        }
        return null;
    }
}

// ================= MAIN CLASS =================
public class LibManagement {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        // Add sample data
        library.addBook(new Book(1, "The Alchemist", "Paulo Coelho"));
        library.addBook(new Book(2, "Clean Code", "Robert C. Martin"));
        library.addUser(new User(101, "Anushree"));
        library.addUser(new User(102, "Darshan"));

        int choice;
        do {
            System.out.println("\n=== Library Menu ===");
            System.out.println("1. Show all books");
            System.out.println("2. Issue a book");
            System.out.println("3. Return a book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    library.showAllBooks();
                    break;
                case 2:
                    System.out.print("Enter Book ID: ");
                    int bookId = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();
                    library.issueBook(bookId, userId);
                    break;
                case 3:
                    System.out.print("Enter Book ID: ");
                    int returnId = sc.nextInt();
                    library.returnBook(returnId);
                    break;
                case 4:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 4);

        sc.close();
    }
}
