package org.baileyseye.hwspringdb.repositories;

import org.baileyseye.hwspringdb.model.AuthorProductId;
import org.baileyseye.hwspringdb.model.AuthorProductIdId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuthorProductIdRepository extends JpaRepository<AuthorProductId, AuthorProductIdId>, JpaSpecificationExecutor<AuthorProductId> {
}