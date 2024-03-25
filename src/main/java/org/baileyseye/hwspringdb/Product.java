package org.baileyseye.hwspringdb;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;



@Entity
@Table(name="Product")
@Getter @Setter
@EqualsAndHashCode(of = "productId")
@DynamicUpdate
@DynamicInsert

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long productId;

    @Column(name="product_name")
    private String productName;

    @Column(name="product_price")
    private double productPrice;




    public Product(){
        super();
    }

    public Product(String productName, double productPrice) {
        super();
        this.productName = productName;
        this.productPrice = productPrice;
    }

}
