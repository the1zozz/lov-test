package com.example.lov_test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LovRepository extends JpaRepository<ListOfValues, Long> {
    List<ListOfValues> findByLovCode(String lovCode);
    @Query("SELECT DISTINCT l.lovCode FROM ListOfValues l")
    List<String> findDistinctLovCode();
}
