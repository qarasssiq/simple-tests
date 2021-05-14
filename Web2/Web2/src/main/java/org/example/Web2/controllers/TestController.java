package org.example.Web2.controllers;

import org.example.Web2.domain.Question;
import org.example.Web2.domain.Test;
import org.example.Web2.domain.User;
import org.example.Web2.repos.QuestionRepo;
import org.example.Web2.repos.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tests/test")
public class TestController {
    @Autowired
    private TestRepo testRepo;

    @Autowired
    private QuestionRepo questionRepo;

    @GetMapping("/create")
    public String createTest(){
        return "createTest";
    }

    @PostMapping("/create")
    public String createTest(@AuthenticationPrincipal User user, @RequestParam String testName, @RequestParam String description){
        Test test = new Test(testName, description, user);
        testRepo.save(test);
        return "redirect:/profile";
    }

    @GetMapping("/{id}/delete")
    public String deleteTest(@PathVariable Long id){
        testRepo.delete(testRepo.getOne(id));
        return "redirect:/profile";
    }

    @GetMapping("/edit/{id}")
    String editTest(@PathVariable("id") Long testId, Model model) {
        Test test = testRepo.findByTestId(testId);
        test.setNumberOfQuestions(questionRepo.countByTestId(test));
        testRepo.save(test);
        Iterable<Question> questions = questionRepo.findByTestId(test);
        model.addAttribute("test", test);
        model.addAttribute("questions", questions);
        return "editTest";
    }

    @PostMapping("/edit/{id}")
    String editTest(@PathVariable("id") Long testId, @RequestParam String testName, @RequestParam String description) {
        Test test = testRepo.findByTestId(testId);
        test.setTestName(testName);
        test.setDescription(description);
        testRepo.save(test);
        return "redirect:/tests/test/edit/" + testId;
    }

    @GetMapping("/{id}")
    String passTest(@PathVariable ("id") Long testId, Model model) {
        Test test = testRepo.findByTestId(testId);
        model.addAttribute("test", test);
        return "testPass";
    }

    @PostMapping("/{id}")
    String passTest(@PathVariable ("id") Long testId, @RequestParam ("answer") List<String> answers) {
        Test test = testRepo.findByTestId(testId);
        Iterable<Question> questions = questionRepo.findByTestId(test);
        List<String> a = answers;

        return "redirect:/tests/test/result/" + testId;
    }

    @GetMapping("/result/{id}")
    String testResult(@PathVariable ("id") Long testId, Model model) {
        Test test = testRepo.findByTestId(testId);
        model.addAttribute("test", test);
        return "testResult";
    }

    @GetMapping("/edit/{test_id}/question/edit/{question_id}")
    String editQuestion(@PathVariable("test_id") Long testId, @PathVariable("question_id") Long questionId, Model model) {
        Question question = questionRepo.getQuestionByQuestionId(questionId);
        model.addAttribute("question", question);
        return "editQuestion";
    }

    @PostMapping("/edit/{test_id}/question/edit/{question_id}")
    String editQuestion(@PathVariable("test_id") Long testId,
                        @PathVariable("question_id") Long questionId,
                        @RequestParam ("questionText") String questionText,
                        @RequestParam ("answer") String answer) {
        Question question = questionRepo.getQuestionByQuestionId(questionId);
        question.setQuestionText(questionText);
        question.setAnswer(answer);
        questionRepo.save(question);
        return "redirect:/tests/test/edit/" + testId;
    }

    @GetMapping("/{id}/createQuestion")
    public String createQuestion(@PathVariable("id") Long testId, Model model){
        Test test = testRepo.findByTestId(testId);
        model.addAttribute("test", test);
        return "createQuestion";
    }

    @PostMapping("/{id}/createQuestion")
    public String createQuestion(@RequestParam String questionText,
                                 @RequestParam String answer,
                                 @PathVariable ("id") Long testId){
        Test test = testRepo.findByTestId(testId);
        Question question = new Question(test, questionText, answer);
        questionRepo.save(question);
        return "redirect:/tests/test/edit/" + testId;
    }

    @GetMapping("/edit/{test_id}/question/delete/{question_id}")
    public String deleteQuestion(@PathVariable ("test_id") Long testId,
                                 @PathVariable ("question_id") Long questionId){
        questionRepo.delete(questionRepo.getOne(questionId));
        return "redirect:/tests/test/edit/" + testId;
    }
}
