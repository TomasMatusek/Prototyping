package solutions.matusek.userdataservice.resource;

import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TeamData {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getTeamItemFallback", commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
        @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "2000"),
        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
    }, threadPoolKey = "getTeamThreadPool", threadPoolProperties = {
        @HystrixProperty(name = "coreSize", value = "5"),
        @HystrixProperty(name = "maxQueueSize", value = "2")
    })
    TeamItemResponse getTeamItem(Integer teamId) {
     return restTemplate.getForObject("http://team-data-service/teams/" + teamId, TeamItemResponse.class);
    }

    TeamItemResponse getTeamItemFallback(Integer teamId) {
        return new TeamItemResponse();
    }
}
