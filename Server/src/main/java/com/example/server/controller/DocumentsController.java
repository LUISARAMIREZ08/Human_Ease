package com.example.server.controller;

import com.example.server.controller.request.CreateDocumentsDTO;
import com.example.server.entity.Documents;
import com.example.server.services.DocumentsServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class DocumentsController {
    @Autowired
    private DocumentsServices documentsServices;

    @GetMapping
    public List<Documents> getAllDocuments() {
        return this.documentsServices.getAllDocuments();
    }

    @GetMapping(path = "/{id}")
    public Optional<Documents> getDocumentById(@PathVariable("id") Long id) {
        return this.documentsServices.getDocumentById(id);
    }

    @GetMapping(path = "/person/{cardId}")
    public List<Documents> getDocumentsByPersonCardId(@PathVariable("cardId") Long cardId) {
        return this.documentsServices.getDocumentsByPersonCardId(cardId);
    }

    @PostMapping
    public Documents saveDocument(@RequestBody CreateDocumentsDTO request) {
        return this.documentsServices.saveDocument(request.getDocumentName(), request.getDocumentPath(), request.getCardId());
    }

    @PutMapping(path = "/{id}")
    public Documents updateDocumentById(@RequestBody Documents request, @PathVariable Long id) {
        return this.documentsServices.updateDocumentById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteDocument(@PathVariable("id") Long id) {
        boolean ok = this.documentsServices.deleteDocument(id);
        if (ok) {
            return "Document with id: " + id + " deleted";
        } else {
            return "Document with id: " + id + " not deleted";
        }
    }
}
