package com.yjx.demo.dislock.config;


import com.coreos.jetcd.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yejingxuan
 */
@Configuration
public class EtcdConfig {

    @Value("${etcd.address}")
    private String etcdAddress;

    @Value("${etcd.user}")
    private String etcdUser;

    @Value("${etcd.password}")
    private String etcdPassword;

    @Bean
    public Client getEtcdClient(){
        Client client = Client.builder().endpoints("http://" + etcdAddress)
                .authority(etcdUser+":"+etcdPassword).build();
        return client;
    }

}
