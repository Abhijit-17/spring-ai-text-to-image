package springai.text_to_image.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import springai.text_to_image.records.Answer;
import springai.text_to_image.records.Question;
import springai.text_to_image.services.OpenAIService;



@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final OpenAIService openAIService;

    public QuestionController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @GetMapping("/healthz")
    public String healthCheck() {
        return "OK";
    }

    @PostMapping("/image")
    public Answer postMethodName(@RequestBody Question question) {
        return openAIService.getAnswer(question);
    }
    
    

}
