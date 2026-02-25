package com.mn.ai.springai.rag_vectordb_openai;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rag")
public class RagController {

    private final QAService qaService;
    private final DocumentService documentService;

    public RagController(QAService qaService, DocumentService documentService){
        this.qaService=qaService;
        this.documentService=documentService;
    }

    @PostMapping("/documents")
    private ResponseEntity<String> addDocument(@RequestBody String content){
        documentService.addDocument(content);
        return ResponseEntity.ok("Document Added");
    }

    @GetMapping("/ask")
    private ResponseEntity<String> askQuestion(@RequestParam String question){
        String answer = qaService.askQuestion(question);
        return ResponseEntity.ok(answer);
    }


}
