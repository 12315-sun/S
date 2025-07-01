package com.example.demo.rag;


import jakarta.annotation.Resource;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class HappyAppVectorStoreConfig {


    @Resource
    HappyAppDocumentLoader happyAppDocumentLoader;


    @Bean
    VectorStore happyAppvectorStore(@Qualifier("dashscopeEmbeddingModel") EmbeddingModel embeddingModel){
        SimpleVectorStore simpleVectorStore = SimpleVectorStore.builder(embeddingModel)
                .build();
        //读取本地情感专家的文档
        List<Document> documents = happyAppDocumentLoader.loadDocuments();
        return simpleVectorStore;
    }

}
