package com.example.server.services;

import com.example.server.entity.Documents;
import com.example.server.entity.UserEntity;
import com.example.server.repository.IDocumentsRepository;
import com.example.server.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentsServices {
    @Autowired
    IDocumentsRepository documentsRepository;

    @Autowired
    IUserRepository userRepository;
    //This method returns all the documents in the database
    public List<Documents> getAllDocuments() {
        return documentsRepository.findAll();
    }
    //This method returns a document by id
    public Optional<Documents> getDocumentById(Long id) {
        return documentsRepository.findById(id);
    }
    //This method returns all the documents by person card id
    public List<Documents> getDocumentsByPersonCardId(Long cardId) {
        return documentsRepository.findDocumentsByUserEntity_CardId(cardId);
    }
    //This method saves a document to the database
    public Documents saveDocument(String documentName, String documentPath, Long userId) {
        Documents document = new Documents();
        document.setDocumentName(documentName);
        document.setDocumentPath(documentPath);

        Optional<UserEntity> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            document.setUserEntity(userOptional.get());
            return documentsRepository.save(document);
        } else {
            throw new RuntimeException("Person not found with id " + userId);
        }
    }
    //This method updates a document by id
    public Documents updateDocumentById(Documents request, Long id) {
        Optional<Documents> document = documentsRepository.findById(id);
        if (document.isPresent()){
            document.get().setDocumentName(request.getDocumentName());
            document.get().setDocumentPath(request.getDocumentPath());
            return documentsRepository.save(document.get());
        } else {
            throw new RuntimeException("Document not found with id " + id);
        }

    }
    //This method deletes a document by id
    public Boolean deleteDocument(Long id) {
        try {
            documentsRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
