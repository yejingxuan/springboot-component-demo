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
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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




    @ApiOperation(value = "测试redisson可重入锁")
    @PostMapping(value = "/testRedissonLock")
    public String testRedissonLock() {
        try {
            disLockService.testRedissonLock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }


    @ApiOperation(value = "测试redisson公平锁")
    @GetMapping(value = "/testRedissonFairLock")
    public String testRedissonFairLock(@RequestParam(value = "key") String key) {
        try {
            log.info("key:{}", key);
            disLockService.testRedissonFairLock(key);
            /*ExecutorService executorService = Executors.newFixedThreadPool(20);
            for (int i = 0; i<100; i++) {
                int finalI = i;
                executorService.execute(() -> {
                    try {
                        final int a = finalI;
                        disLockService.testRedissonFairLock(String.valueOf(a));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }




    @ApiOperation(value = "测试zookeeper可重入锁")
    @PostMapping(value = "/testZkLock")
    public String testZkLock() {
        try {
            //for (int i=0; i<10; i++){
                disLockService.testZkLock();
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }













    @PostMapping(value = "/handleDataByRedisLock")
    public String handleDataByRedisLock() {
        disLockService.handleDataByRedisLock();
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
