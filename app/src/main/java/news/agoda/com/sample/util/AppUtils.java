package news.agoda.com.sample.util;

import java.util.List;

import news.agoda.com.sample.constants.ApplicationConstants;
import news.agoda.com.sample.domain.model.MultiMediumDomain;
import news.agoda.com.sample.domain.model.ResultDomain;

public class AppUtils {
    public static String getImageUrlFromResult(ResultDomain result) {
        String url = null;
        if (result != null) {
            List<MultiMediumDomain> media = result.getMultimedia();
            if (media != null || !media.isEmpty()) {
                for(MultiMediumDomain multiMediumDomain : media){
                    if(multiMediumDomain.getFormat().equals(ApplicationConstants.BUNDLE_CONSTANT_TITLE_IMAGE_FORMAT_NORMAL)){
                        url = multiMediumDomain.getUrl();
                    }
                }
                }
            }
        return url;
    }
}
