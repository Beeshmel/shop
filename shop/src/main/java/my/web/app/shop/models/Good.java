package my.web.app.shop.models;

import jakarta.persistence.*;

@Entity
@Table(name="goods")
public class Good {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    private int categId;
    private String name;
    private String info;
    private int price;

    private int num;

    public Good(int categId, String name, String info, int price, int num) {
        this.categId = categId;
        this.num = num;
        this.name = name;
        this.info = info;
        this.price = price;
    }

    public int getCategId() {
        return categId;
    }

    public void setCategId(int categId) {
        this.categId = categId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Good() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
