package Backend.Entities;

public class Author {
    private int isbn;
    private String name;

    public Author(int isbn, String name) {
        this.isbn = isbn;
        this.name = name;
    }

    public int getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public void setName(String name) {
        this.name = name;
    }
}
