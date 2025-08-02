package springai.text_to_image.services;


import java.util.Base64;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.ai.content.Media;
import lombok.RequiredArgsConstructor;
import springai.text_to_image.records.Question;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;

@RequiredArgsConstructor
@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final ImageModel imageModel;

    private final ChatModel chatModel;

    @Override
    public byte[] getImage(Question question) {

        // setting up image options
        OpenAiImageOptions imageOptions = OpenAiImageOptions.builder()
                .width(1170)
                .height(2532)
                .model("dall-e-3")
                .quality("hd")
                .style("natural")
                .responseFormat("b64_json")
                .build();

        ImagePrompt imagePrompt = new ImagePrompt(question.question(), imageOptions);

        ImageResponse imageResponse = imageModel.call(imagePrompt);

        return Base64.getDecoder().decode(imageResponse.getResult().getOutput().getB64Json());

    }

    @Override
    public String getDescription(MultipartFile file, String name) throws IOException {
        OpenAiChatOptions chatOptions = OpenAiChatOptions.builder()
                .model(OpenAiApi.ChatModel.GPT_4_O.getValue())
                .build();

        // Wrap the file in a Media object
        Media media = new Media(MimeTypeUtils.IMAGE_JPEG, file.getResource());

        // user Message with prompt and media
        UserMessage userMessage = UserMessage.builder()
                .text("Describe the image, and if see a superhero character, provide the name of the superhero.")
                .media(media)
                .build();

        // Create a prompt user Message
        Prompt prompt = new Prompt(userMessage, chatOptions);

        // Call the chat model with the prompt
        ChatResponse response = chatModel.call(prompt);

        return response.getResult().getOutput().getText();
    }

}
