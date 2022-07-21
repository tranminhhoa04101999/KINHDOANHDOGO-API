package com.example.kddgmn.repository;

import com.example.kddgmn.model.ImportDetails;
import com.example.kddgmn.model.ImportDetailsId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportDetailsRepository extends JpaRepository<ImportDetails, ImportDetailsId> {
}
