package com.example.officemanagement.dao;

import com.example.officemanagement.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueDao extends JpaRepository<Issue, Long> {
}
