package com.example.demo.rag;


import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.rag.DashScopeDocumentRetriever;
import com.alibaba.cloud.ai.dashscope.rag.DashScopeDocumentRetrieverOptions;
import org.springframework.ai.rag.retrieval.search.DocumentRetriever;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
@Slf4j
public class HappyAppRagCloudAdviseConfig {


    @Value("${spring.ai.dashscope.api-key}")
    private String dashScopeApiKey;

    @Bean
    public Advisor happyAppRagCloudAdvisor() {
        DashScopeApi dashscopeApi = new DashScopeApi(dashScopeApiKey);
        final String KNOWLEDGE_INDEX = "爷们的库";//根据百炼平台的实际名称来写
        DocumentRetriever documentRetriver = new DashScopeDocumentRetriever(dashscopeApi,
                DashScopeDocumentRetrieverOptions.builder()
                        .withIndexName(KNOWLEDGE_INDEX)
                        .build());
        return RetrievalAugmentationAdvisor.builder().documentRetriever(documentRetriver).build();
    }
}
