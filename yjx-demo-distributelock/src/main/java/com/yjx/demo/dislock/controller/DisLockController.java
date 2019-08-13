package com.yjx.demo.dislock.controller;


import com.coreos.jetcd.Client;
import com.coreos.jetcd.data.ByteSequence;
import com.coreos.jetcd.data.KeyValue;
import com.coreos.jetcd.kv.PutResponse;
import com.coreos.jetcd.lease.LeaseGrantResponse;
import com.coreos.jetcd.options.GetOption;
import com.coreos.jetcd.options.PutOption;
import com.daedafusion.jetcd.EtcdClient;
import com.yjx.demo.dislock.service.DisLockService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yejingxuan
 */
@Slf4j
@RestController
public class DisLockController {

    @Autowired
    private DisLockService disLockService;

    @Autowired
    private Client client;

    @PostMapping(value = "/handleDataByRedisLock")
    public String handleDataByRedisLock() {
        disLockService.handleDataByRedisLock();
        return "success";
    }


    @PostMapping(value = "/handleDataByRedissonLock")
    public String handleDataByRedissonLock() {
        disLockService.handleDataByRedissonLock();
        return "success";
    }


    @GetMapping(value = "/addEtcdKey")
    public PutResponse addEtcdKey(String key, String value){
        PutResponse putResponse = null;
        try {


            long id = client.getLeaseClient().grant(1000).get().getID();
            client.getLeaseClient().keepAlive(1000).listen().getID();
            log.info(String.valueOf(id));
            PutOption putOption = PutOption.newBuilder().withLeaseId(id).withPrevKV().build();

            putResponse = client.getKVClient()
                    .put(ByteSequence.fromString(key),
                            ByteSequence.fromBytes(value.getBytes("utf-8")), putOption).get();
        }catch (Exception e){
            log.error("添加失败", e);
        }

        return putResponse;
    }


    @GetMapping(value = "/getEtcdKey")
    public List<KeyValue> getEtcdKey(String key) {
        List<KeyValue> keyValues = new ArrayList<>();
        log.info("------------分割线----------------");
        //KeyValue res = null;
        try {
            keyValues = client.getKVClient().get(ByteSequence.fromString(key)).get().getKvs();
            //res = keyValues.size()>0 ? keyValues.get(0) : null;
            //keyValues.get(0).getValue().toStringUtf8();
            keyValues.forEach(keyValue -> {
                log.info("{}, {}, {}, {}",keyValue.getVersion(), keyValue.getKey().toStringUtf8(), keyValue.getValue().toStringUtf8(), keyValue.getLease());
            });
        } catch (Exception e) {
            log.error("获取失败", e);
        }
        return keyValues;
    }


    @GetMapping(value = "/getEtcdKeyWithPrefix")
    public List<KeyValue> getEtcdKeyWithPrefix(@RequestParam(value = "prefix") String prefix) {
        List<KeyValue> keyValues = new ArrayList<>();
        GetOption getOption = GetOption.newBuilder().withPrefix(ByteSequence.fromString(prefix)).build();
        log.info("------------分割线----------------");
        try {
            keyValues = client.getKVClient().get(ByteSequence.fromString(prefix), getOption).get().getKvs();
            keyValues.forEach(keyValue -> {
                log.info("{}, {}, {}",keyValue.getVersion(), keyValue.getKey().toStringUtf8(), keyValue.getValue().toStringUtf8());
            });
        } catch (Exception e) {
            log.error("获取失败", e);
        }
        return keyValues;
    }


}
