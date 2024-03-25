package org.baileyseye.hwspringdb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "categories", schema = "shop")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categories_id", nullable = false)
    private Integer id;

    @Column(name = "categories_name", nullable = false, length = 100)
    private String categoriesName;

}