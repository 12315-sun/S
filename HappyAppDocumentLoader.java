package com.example.demo.rag;


import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.markdown.MarkdownDocumentReader;
import org.springframework.ai.reader.markdown.config.MarkdownDocumentReaderConfig;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class HappyAppDocumentLoader {

    private final ResourcePatternResolver resourcePatternResolver;

    HappyAppDocumentLoader(ResourcePatternResolver resourcePatternResolver) {
        this.resourcePatternResolver = resourcePatternResolver;
    }

    /**
     * 加载本地markdown文档
     * @return
     */
    public List<Document> loadDocuments() {
        List<Document> allDocuments = new ArrayList<>();
        try{
            //读取markdown文件
            Resource[] resources = resourcePatternResolver.getResources("classpath:markdown/*.md");
            for (Resource resource : resources) {
                log.info("resource:{}",resource.getFilename());
                MarkdownDocumentReaderConfig config = MarkdownDocumentReaderConfig.builder()
                        .withHorizontalRuleCreateDocument(true)
                        .withIncludeCodeBlock(false)
                        .withIncludeCodeBlock(false)
                        .withAdditionalMetadata("filename", resource.getFilename())
                        .build();
                MarkdownDocumentReader reader = new MarkdownDocumentReader(resource,config);
                allDocuments.addAll(reader.get());
            }
        }catch (Exception e){
            log.error("Markdown 文档加载失败",e);
        }
        return allDocuments;
    }
}
