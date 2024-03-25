package org.baileyseye.hwspringdb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class AuthorProductIdId implements Serializable {
    @Serial
    private static final long serialVersionUID = -1633691197264728721L;
    @Column(name = "author_name_id", nullable = false)
    private Integer authorNameId;

    @Column(name = "author_product_id", nullable = false)
    private Integer authorProductId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AuthorProductIdId entity = (AuthorProductIdId) o;
        return Objects.equals(this.authorNameId, entity.authorNameId) &&
                Objects.equals(this.authorProductId, entity.authorProductId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorNameId, authorProductId);
    }

}