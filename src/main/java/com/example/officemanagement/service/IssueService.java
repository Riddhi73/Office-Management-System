package com.example.officemanagement.service;

import com.example.officemanagement.dao.IssueDao;
import com.example.officemanagement.dto.IssueDto;
import com.example.officemanagement.entity.Issue;
import com.example.officemanagement.exception.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class IssueService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IssueDao issueDao;

    public Issue createIssue(Issue issue){
        Issue createIssue = mapFromDtoToIssue(issue);
        return issueDao.save(createIssue);
    }

    @Transactional
    public List<IssueDto> showAllPosts() {
        List<Issue> issues = issueDao.findAll();
        return issues.stream().map(this::mapFromIssueToDto).collect(toList());

    }

    @Transactional
    public IssueDto readSinglePost(Long id) {
        Issue issue = issueDao.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id));
        return mapFromIssueToDto(issue);
    }

    private IssueDto mapFromIssueToDto(Issue issue) {
        IssueDto issueDto = new IssueDto();
        issueDto.setId(issue.getId());
        issueDto.setTitle(issue.getTitle());
        issueDto.setContent(issue.getContent());
        issueDto.setUsername(issue.getUsername());
        return issueDto;
    }

    private Issue mapFromDtoToIssue(Issue issueDto) {
        Issue issue = new Issue();
        issue.setTitle(issueDto.getTitle());
        issue.setContent(issueDto.getContent());
        User loggedInUser = jwtService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        issue.setCreatedOn(Instant.now());
        issue.setUsername(loggedInUser.getUsername());
        issue.setUpdatedOn(Instant.now());
        return issue;
    }

}
