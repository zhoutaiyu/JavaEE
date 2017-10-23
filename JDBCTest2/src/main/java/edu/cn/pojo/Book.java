package edu.cn.pojo;

/**
 * Created by k01 on 2017/10/20.
 */
public class Book {
    private String isbn;
    private String title;
    private String type;
    private double price;

    public Book() {
    }

    public Book(String isbn, String title, String type, double price) {
        this.isbn = isbn;
        this.title = title;
        this.type = type;
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
