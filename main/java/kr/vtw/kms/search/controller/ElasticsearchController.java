package kr.vtw.kms.search.controller;

import kr.vtw.kms.search.dto.ElasticDto;
import kr.vtw.kms.search.dto.SearchResultDto;
import kr.vtw.kms.search.dto.TempDto;
import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ElasticsearchController {

    private static final String ELASTICSEARCH_INDEX = "vtw";
    private static final int DEFAULT_PAGE_SIZE = 20;
    private final RestHighLevelClient client;

    public ElasticsearchController() {
        this.client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.0.100", 9200, "http")));
    }

    @PostMapping("/api/search/elasticsearch")
    public SearchResultDto elasticsearch(@RequestBody ElasticDto requestSearch) {
        try {

            int size = (requestSearch.getLastRow() > 0) ? requestSearch.getLastRow() : 5000;
            BoolQueryBuilder boolQueryBuilder = createBoolQueryBuilder(requestSearch);
            SearchRequest searchRequest = new SearchRequest(ELASTICSEARCH_INDEX);

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
            .size(size)
            .fetchSource(new String[]{"file_code","project_name", "organi_name","file_name","type_file","project_year", "_score"}, null)
            .query(boolQueryBuilder);

            if(!requestSearch.getReqSearchContent().isEmpty()){
                searchSourceBuilder.sort(SortBuilders.scoreSort().order(SortOrder.DESC));
            }else{
                searchSourceBuilder.sort(SortBuilders.fieldSort("@timestamp").order(SortOrder.DESC));
            }

            searchRequest.source(searchSourceBuilder);
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            long totalHits = searchResponse.getHits().getTotalHits().value;
            List<TempDto> searchResults = Arrays.stream(searchResponse.getHits().getHits())
            .map(hit -> {
                TempDto result = new TempDto();
                result.setFileCode((Integer) hit.getSourceAsMap().get("file_code"));
                result.setProjectName((String) hit.getSourceAsMap().get("project_name"));
                result.setOrganiName((String) hit.getSourceAsMap().get("organi_name"));
                result.setFileName((String) hit.getSourceAsMap().get("file_name"));
                result.setTypeFile((String) hit.getSourceAsMap().get("type_file"));
                result.setProjectYear((String) hit.getSourceAsMap().get("project_year"));
                result.setScore(hit.getScore());
               return result;
            }).collect(Collectors.toList());

               return new SearchResultDto(totalHits, searchResults);
            } catch (IOException | ElasticsearchException e) {
                e.printStackTrace();
                return new SearchResultDto();
            }
    }


    private BoolQueryBuilder createBoolQueryBuilder(ElasticDto requestSearch) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("vendor_id", "vtw"))
                .must(QueryBuilders.matchQuery("file_owner", "0"))
                .filter(QueryBuilders.rangeQuery("project_year")
                        .gte(requestSearch.getReqStartYear())
                        .lte(requestSearch.getReqEndYear()));

        addMatchQuery(boolQueryBuilder, "file_source", requestSearch.getReqSearchContent());
        addWildcardQuery(boolQueryBuilder, "organi_name", requestSearch.getReqOrg());
        addMatchPhraseQuery(boolQueryBuilder, "project_name", requestSearch.getReqProjectNm());
        addMatchQuery(boolQueryBuilder, "type_code", requestSearch.getReqStypeCd());
        addQueryStringQuery(boolQueryBuilder, requestSearch.getFileExtForm());

        return boolQueryBuilder;
    }
    private void addMatchQuery(BoolQueryBuilder boolQueryBuilder, String field, String value) {
        if (value != null && !value.isEmpty()) {
            boolQueryBuilder.must(QueryBuilders.matchQuery(field, value).operator(org.elasticsearch.index.query.Operator.AND));
        }
    }

    private void addWildcardQuery(BoolQueryBuilder boolQueryBuilder, String field, String value) {
        if (value != null && !value.isEmpty()) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery(field, "*" + value + "*"));
        }
    }

    private void addMatchPhraseQuery(BoolQueryBuilder boolQueryBuilder, String field, String value) {
        if (value != null && !value.isEmpty()) {
            boolQueryBuilder.must(QueryBuilders.matchPhraseQuery(field, "*" + value + "*"));
        }
    }

    private void addQueryStringQuery(BoolQueryBuilder boolQueryBuilder, String value) {
        if (value != null && !value.isEmpty()) {
            boolQueryBuilder.must(QueryBuilders.queryStringQuery(value));
        }
    }
}
