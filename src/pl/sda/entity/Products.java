package pl.sda.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * author:
 * Mateusz
 * Marczak
 **/
@XmlRootElement
public class Products {

    private List<Product>productList;

    @XmlElement
    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
