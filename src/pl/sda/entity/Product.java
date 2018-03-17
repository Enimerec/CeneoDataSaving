package pl.sda.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * author:
 * Mateusz
 * Marczak
 **/


public class Product {
    private String img;
    private String title;
    private String price;

    @XmlElement
    public void setImg(String img) {
        this.img = img;
    }
    @XmlElement
    public void setTitle(String title) {
        this.title = title;
    }
    @XmlElement
    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }
}
