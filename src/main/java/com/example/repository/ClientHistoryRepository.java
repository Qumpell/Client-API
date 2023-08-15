package com.example.repository;

import com.example.model.Client;
import com.example.model.ClientHistory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClientHistoryRepository extends JpaRepository<ClientHistory,Long>, JpaSpecificationExecutor<ClientHistory> {
    static Specification<ClientHistory> hasClientId(Long id){
        return (root,criteriaQuery,criteriaBuilder) -> criteriaBuilder.equal(root.get("clientId"),id);
    }
}
