package org.fisco.bcos.autoconfigure;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.fisco.bcos.channel.handler.ChannelConnections;
import org.fisco.bcos.channel.handler.GroupChannelConnectionsConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//  加载群组配置
@Data
@Configuration
@ConfigurationProperties(
        prefix =
                "group-channel-connections-config") //  group-channel-connections-config
                                                    // 见src/resources/application.yml
public class GroupChannelConnectionsPropertyConfig {

    List<ChannelConnections> allChannelConnections = new ArrayList<>();

    @Bean
    public GroupChannelConnectionsConfig getGroupChannelConnections() {
        GroupChannelConnectionsConfig groupChannelConnectionsConfig =
                new GroupChannelConnectionsConfig(); //   新建一个CCCC类
        groupChannelConnectionsConfig.setAllChannelConnections(
                allChannelConnections); //  设置CCCC的aCCC列表
        return groupChannelConnectionsConfig;
    }
}
