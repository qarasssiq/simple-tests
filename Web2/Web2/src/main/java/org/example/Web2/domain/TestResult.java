package org.example.Web2.domain;

import javax.persistence.*;

@Entity
public class TestResult {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long testId;

    private String testResult;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User userId;

    public TestResult() {

    }

    public TestResult(String test_result, User user_id) {
        this.testResult = test_result;
        this.userId = user_id;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long test_id) {
        this.testId = test_id;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String test_result) {
        this.testResult = test_result;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User user_id) {
        this.userId = user_id;
    }
}
