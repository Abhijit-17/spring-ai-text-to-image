package springai.text_to_image.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import springai.text_to_image.records.Question;
import springai.text_to_image.services.OpenAIService;


@RequiredArgsConstructor
@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final OpenAIService openAIService;

    @GetMapping("/healthz")
    public String healthCheck() {
        return "OK";
    }

    @PostMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] postMethodName(@RequestBody Question question) {
        return openAIService.getImage(question);
    }

    @PostMapping(value = "/vision", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> upload(@Validated @RequestParam("file") MultipartFile file,
                                                 @RequestParam("name") String name) throws IOException
    {
        return ResponseEntity.ok(openAIService.getDescription(file, name));
    }
    

}
