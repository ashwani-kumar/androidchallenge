package news.agoda.com.sample.domain.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import news.agoda.com.sample.domain.model.MultiMediumDomain;
import news.agoda.com.sample.domain.model.ResultDomain;
import news.agoda.com.sample.entity.model.MultiMediumEntity;
import news.agoda.com.sample.entity.model.NewsEntity;
import news.agoda.com.sample.entity.model.Result;

public class NewsFeedEntityDomainMapper {

    public List<ResultDomain> mapResults(List<Result> results) {
        List<ResultDomain> resultDomainList = new ArrayList<>();
        if (results != null && results.size() > 0) {
            for (Result resultEntity : results) {
                ResultDomain resultDomain = new ResultDomain();
                resultDomain.setTitle(resultEntity.getTitle());
                resultDomain.setAbstract(resultEntity.getAbstract());
                resultDomain.setPublishedDate(resultEntity.getPublishedDate());
                List<MultiMediumDomain> mediumDomain = mapMedia(resultEntity.getMultimedia());
                resultDomain.setMultimedia(mediumDomain);
                resultDomainList.add(resultDomain);
            }
        }
        return resultDomainList;
    }

    private List<MultiMediumDomain> mapMedia(List<MultiMediumEntity> media) {
        List<MultiMediumDomain> mediumDomains = new ArrayList<>();
        if (media != null && media.size() > 0) {
            for (MultiMediumEntity mediaEntity : media) {
                MultiMediumDomain mediumDomain = new MultiMediumDomain();
                mediumDomain.setUrl(mediaEntity.getUrl());
                mediumDomain.setFormat(mediaEntity.getFormat());
                mediumDomain.setHeight(mediaEntity.getHeight());
                mediumDomain.setWidth(mediaEntity.getWidth());
                mediumDomain.setCaption(mediaEntity.getCaption());
                mediumDomain.setCopyright(mediaEntity.getCopyright());
                mediumDomains.add(mediumDomain);
            }
        }
        return mediumDomains;
    }

    public NewsEntity parseResponse(String content) {
        NewsEntity newsItem = new NewsEntity();
        NewsEntity newsEntity;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            newsEntity = mapper.readValue(content, NewsEntity.class);
            newsItem = newsEntity;
        } catch (IOException e) {
            System.out.print("Fail to parse json string = " + e);
        }
        return newsItem;
    }
}
