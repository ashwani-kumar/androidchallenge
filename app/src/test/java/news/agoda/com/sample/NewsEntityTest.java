package news.agoda.com.sample;

import junit.framework.Assert;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import news.agoda.com.sample.domain.mapper.NewsFeedEntityDomainMapper;
import news.agoda.com.sample.entity.model.NewsEntity;

public class NewsEntityTest {

    private NewsFeedEntityDomainMapper newsFeedEntityDomainMapper;
    private NewsEntity parsedNewsEntity;

    String storyUrl = "http://www.nytimes.com/2015/08/18/business/work-policies-may-be-kinder-but-brutal-competition-isnt.html";

    @Before
    public void setUp() {
        newsFeedEntityDomainMapper = new NewsFeedEntityDomainMapper();
        parsedNewsEntity = newsFeedEntityDomainMapper.parseResponse(MockResponse.newsFeedResponse);
    }

    @Test
    public void validateStoryUrl() throws Exception {
        Assert.assertEquals(parsedNewsEntity.getResults().get(0).getUrl(), storyUrl);
    }

    @Test
    public void validateStatus() throws Exception {
        Assert.assertEquals(parsedNewsEntity.getStatus(), "OK");
    }

    @Test
    public void validateSResultSize() throws Exception {
        Assert.assertEquals(parsedNewsEntity.getResults().size(), 4);
    }

    @Test
    public void validateSection() throws Exception {
        Assert.assertEquals(parsedNewsEntity.getSection(), "technology");
    }

    @Test
    public void validateLastUpdated() throws Exception {
        Assert.assertEquals(parsedNewsEntity.getLastUpdated(), "2015-08-18T10:15:06-05:00");
    }

    @Test
    public void validateEmptySection() throws Exception {
        parsedNewsEntity = newsFeedEntityDomainMapper.parseResponse(MockResponse.newsFeedResponseEmptyProperties);
        Assert.assertEquals(parsedNewsEntity.getSection(), "");
    }

    @Test
    public void validateEmptyLastUdated() throws Exception {
        parsedNewsEntity = newsFeedEntityDomainMapper.parseResponse(MockResponse.newsFeedResponseEmptyProperties);
        Assert.assertEquals(parsedNewsEntity.getLastUpdated(), "");
    }
}
