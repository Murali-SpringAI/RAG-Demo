package com.mn.ai.springai.rag_vectordb_openai;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QAService {

    private final DocumentService documentService;
    private final ChatClient chatClient;

    public QAService(DocumentService documentService,
                     ChatClient.Builder builder){
        this.documentService=documentService;
        this.chatClient=builder.build();
    }

    public String askQuestion(String question){
        List<Document> documents = documentService.search(question);

        String context = documents.stream()
                .map(Document::getContent)
                .collect(Collectors.joining("\n"));

        String prompt = """
                Answer the question using ONLY context below.
                
                Context:
                %s
                
                Question:
                %s
                """.formatted(context,question);

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();

    }

}
