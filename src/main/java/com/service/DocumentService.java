package com.service;

import com.model.exceptions.DocumentNotFoundException;
import com.model.documents.*;
import com.repository.DocumentRepository;

import java.util.List;

public class DocumentService {
    public void addDocument(Document doc) {
        DocumentRepository.addDocument(doc);
        System.out.println("Document added successfully.");
    }

    public void removeDocument(int docId)  {
        DocumentRepository.removeDocument(docId);
        System.out.println("Document with ID '" + docId + "' deleted successfully.");
    }

    public void updateDocument(Document doc) {
        DocumentRepository.updateDocument(doc);
        System.out.println("Document updated successfully.");
    }

    public List<Document> findDocument(String keyword) {
        return DocumentRepository.findDocument(keyword);
    }

    public List<Document> getDocuments() {
        return DocumentRepository.getDocuments();
    }


}

