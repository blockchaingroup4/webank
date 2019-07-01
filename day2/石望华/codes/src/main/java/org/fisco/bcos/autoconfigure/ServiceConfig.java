package org.fisco.bcos.autoconfigure;

import lombok.Data;
import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.channel.handler.GroupChannelConnectionsConfig;
import org.fisco.bcos.constants.ConnectConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//   初始化channelService，返回一个Service类对象
@Data
@Configuration
@ConfigurationProperties(
        prefix =
                "channel-service") //  channel-service
                                   // 见src/resources/application.yml，里面有group-id和org-id
public class ServiceConfig {

    private String orgID;
    private static int groupId = 1;

    @Bean
    public Service getService(GroupChannelConnectionsConfig groupChannelConnectionsConfig) {
        Service channelService = new Service();
        channelService.setConnectSeconds(ConnectConstants.CONNECT_SECONDS); //  设置时间  30ms
        channelService.setOrgID(orgID);
        channelService.setConnectSleepPerMillis(
                ConnectConstants.CONNECT_SLEEP_PER_MILLIS); //  设置时间  1ms
        channelService.setGroupId(groupId);
        channelService.setAllChannelConnections(
                groupChannelConnectionsConfig); //    设置CCCC的aCCC列表, 但这里就一个连接
        return channelService;
    }
}
