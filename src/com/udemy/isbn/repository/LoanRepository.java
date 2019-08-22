package com.udemy.isbn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.isbn.bean.LoanApplication;

public interface LoanRepository extends JpaRepository<LoanApplication, Integer> {

}
