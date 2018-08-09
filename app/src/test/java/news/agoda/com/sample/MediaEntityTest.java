package news.agoda.com.sample;

import junit.framework.Assert;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import news.agoda.com.sample.domain.mapper.NewsFeedEntityDomainMapper;
import news.agoda.com.sample.domain.model.MultiMediumDomain;
import news.agoda.com.sample.entity.model.MultiMediumEntity;
import news.agoda.com.sample.entity.model.NewsEntity;

public class MediaEntityTest {

    private static final String URL = "http://static01.nyt.com/images/2015/08/18/business/18EMPLOY/18EMPLOY-thumbLarge.jpg";
    private NewsFeedEntityDomainMapper newsFeedEntityDomainMapper;

    @Before
    public void setUp() {
        newsFeedEntityDomainMapper = new NewsFeedEntityDomainMapper();
    }

    @Test
    public void validateImageUrl() throws Exception {
        NewsEntity newsEntity = newsFeedEntityDomainMapper.parseResponse(MockResponse.newsFeedResponse);
        List<MultiMediumDomain> multiMediumDomains = newsFeedEntityDomainMapper.mapMedia(newsEntity.getResults().get(0).getMultimedia());
        Assert.assertTrue(multiMediumDomains.get(0).getUrl().equals(newsEntity.getResults().get(0).getMultimedia().get(0).getUrl()));
    }

    @Test
    public void validateMutliMediaSize() throws Exception {
        NewsEntity newsEntity = newsFeedEntityDomainMapper.parseResponse(MockResponse.newsFeedResponseEmptyProperties);
        List<MultiMediumDomain> multiMediumDomains = newsFeedEntityDomainMapper.mapMedia(newsEntity.getResults().get(0).getMultimedia());
        Assert.assertEquals(multiMediumDomains.size(),4);
    }

    @Test
    public void validateEmptyImageUrl() throws Exception {
        NewsEntity newsEntity = newsFeedEntityDomainMapper.parseResponse(MockResponse.newsFeedResponseEmptyProperties);
        List<MultiMediumDomain> multiMediumDomains = newsFeedEntityDomainMapper.mapMedia(newsEntity.getResults().get(0).getMultimedia());
        Assert.assertTrue(multiMediumDomains.get(0).getUrl().equals(""));
    }

    @Test
    public void validateEmptyMultimedia() throws Exception {
        NewsEntity newsEntity = newsFeedEntityDomainMapper.parseResponse(MockResponse.newsFeedResponseEmptyProperties);
        List<MultiMediumDomain> multiMediumDomains = newsFeedEntityDomainMapper.mapMedia(newsEntity.getResults().get(3).getMultimedia());
        Assert.assertEquals(multiMediumDomains.size(),0);
    }

//    @Test
//    public void validateImageUrl() throws Exception {
//        NewsEntity newsEntity = newsFeedEntityDomainMapper.parseResponse(MockResponse.newsFeedResponse);
//        List<MultiMediumDomain> multiMediumDomains = newsFeedEntityDomainMapper.mapMedia(newsEntity.getResults().get(0).getMultimedia());
//        Assert.assertTrue(multiMediumDomains.get(0).getUrl().equals(newsEntity.getResults().get(0).getMultimedia().get(0).getUrl()));
//    }
}
