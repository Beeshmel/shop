package my.web.app.shop.models;

import jakarta.persistence.*;

@Entity
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String info;

    public Category(String name, String info) {
        this.name = name;
        this.info = info;
    }

    public Category(){
    }
}
