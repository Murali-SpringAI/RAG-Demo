package com.mn.ai.springai.rag_vectordb_openai;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

//When Saving the Document
//
//Document Text
//      ↓
//Embedding Model (OpenAI)
//      ↓
//Vector
//      ↓
//Stored in PGVector
//vectorStore.add(List.of(document));
//
//Spring AI:
//Sends the document text to an embedding model
//e.g. text-embedding-3-small
//Receives a vector (e.g. 1536 dimensions)
//Stores it in the vector DB
//
 //When Asking a Question
//Step A [Retrieve Context]
//Question → Embedding Model → Query Vector
//Step B
//Retrieved Context + Question → GPT model → Final Answer


@Service
public class DocumentService {

    private final VectorStore vectorStore;

    public DocumentService(VectorStore vectorStore){
        this.vectorStore = vectorStore;
    }

    public void addDocument(String content){
        Document doc = new Document(content);
        vectorStore.add(List.of(doc));
    }

    public List<Document> search(String query){
        return vectorStore.similaritySearch(query);
    }
}
