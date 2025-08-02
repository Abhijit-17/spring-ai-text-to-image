package springai.text_to_image.services;

import springai.text_to_image.records.Question;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
public interface OpenAIService {

    public byte[] getImage(Question question);

    public String getDescription(MultipartFile file, String name) throws IOException;

}
