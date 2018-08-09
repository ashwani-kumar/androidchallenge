package news.agoda.com.sample;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import news.agoda.com.sample.domain.mapper.NewsFeedEntityDomainMapper;
import news.agoda.com.sample.entity.model.NewsEntity;

public class MediaParserTest {

    private NewsFeedEntityDomainMapper newsFeedEntityDomainMapper;
    private NewsEntity parsedNewsEntity;

    @Before
    public void setUp() {
        newsFeedEntityDomainMapper = new NewsFeedEntityDomainMapper();
        parsedNewsEntity = newsFeedEntityDomainMapper.parseResponse(MockResponse.newsFeedResponse);
    }

    @Test
    public void testParsedResposeNotNull() {
        Assert.assertNotNull(parsedNewsEntity);
    }

    @Test
    public void testParsedResposeNull() {
        parsedNewsEntity = newsFeedEntityDomainMapper.parseResponse(null);
        Assert.assertNull(parsedNewsEntity.getStatus());
    }

    @Test
    public void testParsedRespose() {
        Assert.assertTrue(parsedNewsEntity.getNumResults()==4);
    }

}
