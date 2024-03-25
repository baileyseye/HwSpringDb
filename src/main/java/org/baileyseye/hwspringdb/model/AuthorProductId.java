package org.baileyseye.hwspringdb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "author_product_id", schema = "shop")
public class AuthorProductId {
    @EmbeddedId
    private AuthorProductIdId id;

    @MapsId("authorNameId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_name_id", nullable = false)
    private Author authorName;

    @MapsId("authorProductId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_product_id", nullable = false)
    private Product authorProduct;

}