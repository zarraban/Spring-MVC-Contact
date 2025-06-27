package org.example.app.service.ai;

import com.openai.client.OpenAIClient;
import com.openai.models.ChatCompletion;
import com.openai.models.ChatCompletionCreateParams;
import org.springframework.stereotype.Service;


@Service("aiService")
public class AiService {

    OpenAIClient client;

    public AiService(OpenAIClient client){
        this.client = client;
    }


    private ChatCompletionCreateParams createPrompt(String prompt){

        return ChatCompletionCreateParams
                .builder()
                .addUserMessage(prompt)
                .model("deepseek-chat")
                .build();

    }

    public String callAiWithPrompt(String prompt){
        ChatCompletion chatCompletion = client.chat().completions().create(createPrompt(prompt));
        return chatCompletion.choices().getFirst().message().content().orElse("No message was received");
    }
}