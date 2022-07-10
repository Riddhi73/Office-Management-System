package com.example.officemanagement.controller;

import com.example.officemanagement.dto.IssueDto;
import com.example.officemanagement.entity.Issue;
import com.example.officemanagement.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IssueController {

    @Autowired
    private IssueService issueService;

    @PostMapping("/createIssue")
    public Issue createIssue(@RequestBody Issue issue){
        return issueService.createIssue(issue);

    }

    @GetMapping("/all")
    public ResponseEntity<List<IssueDto>> showAllPosts() {
        return new ResponseEntity<>(issueService.showAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<IssueDto> getSinglePost(@PathVariable @RequestBody Long id) {
        return new ResponseEntity<>(issueService.readSinglePost(id), HttpStatus.OK);
    }
}
