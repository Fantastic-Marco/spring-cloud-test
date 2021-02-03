###Eureka系Netflix家族中的服务治理服务，后被Spring集成到Spring Clound中

Maven依赖
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    <version>2.1.2.RELEASE</version>
</dependency>
```

该代码块主要展示了如何创建一个单体Eureka-Server服务以及高可用Eureka-Server

####单体应用的配置文件
```
server.port=9500
eureka.instance.hostname=localhost
#当前实例是否为客户端
eureka.client.register-with-eureka=false
#是否同步其他Eureka-Server的数据
eureka.client.fetch-registry=false
#注册中心访问地址
eureka.client.serviceUrl.defaultZone=http://${eureka.instance.hostname}:${server.port}/eureka/
```

####高可用
单体应用不需要作为客户端注册别的Eureke-Server，而高可用则需要

Eureka-Server 节点一
```
spring.application.name=eureka-server-peer2
server.port=1112

#Host name
eureka.instance.hostname=eureka-2
#当前实例是否为客户端
eureka.client.register-with-eureka=true
#是否同步其他Eureka-Server的数据
eureka.client.fetch-registry=true
#服务续约的调用时间间隔，默认30秒 设置成10s
eureka.instance.lease-renewal-interval-in-seconds=10
#服务失效的时间，默认90秒
eureka.instance.lease-expiration-duration-in-seconds=90
#注册到哪个Eureka服务实例
eureka.client.serviceUrl.defaultZone=http://localhost:1113/eureka/
```


Eureka-Server 节点二

```
spring.application.name=eureka-server-peer3
server.port=1113

#Host name
eureka.instance.hostname=eureka-3
#当前实例是否为客户端
eureka.client.register-with-eureka=true
#是否同步其他Eureka-Server的数据
eureka.client.fetch-registry=true
#服务续约的调用时间间隔，默认30秒 设置成10s
eureka.instance.lease-renewal-interval-in-seconds=10
#服务失效的时间，默认90秒
eureka.instance.lease-expiration-duration-in-seconds=90
#注册到哪个Eureka服务实例
eureka.client.serviceUrl.defaultZone=http://localhost:1112/eureka/
```

与单节点不同的是高可用需要开启客户端注册功能，2个节点互相注册对方，并且互相同步数据</br>
使用多实例的时候需要在启动项增加环境变量 --spring.profiles.active=peer2/3 指定示例应用的配置文件

Eureka-Server 配置示例

```
server:
  port: 10000

#服务发现客户端
eureka:
  instance:
    #向Eureka注册时，是否使用IP地址+端口号作为服务实例的唯一标识。推荐设置为true
    prefer-ip-address: true

    #============分界线================
    #以下为基本不需要配置的属性，属性的值为默认值

    #服务续约的调用时间间隔，默认30秒
    lease-renewal-interval-in-seconds: 30
    #服务失效的时间，默认90秒
    lease-expiration-duration-in-seconds: 90
    #非安全的通信端口号
    non-secure-port: 80
    #安全的通信端口号
    secure-port: 443
    #是否启用非安全的通信端口号
    non-secure-port-enabled: true
    #是否启用安全的通信端口号
    secure-port-enabled: false

  client:
    #是否将自身的实例信息注册到Eureka服务端
    register-with-eureka: true
    #是否拉取并缓存其他服务注册表副本到本地
    fetch-registry: true
    #注册到哪个Eureka服务实例
    service-url:
      defaultZone: http://localhost:8761/eureka/

    #============分界线================
    #以下为基本不需要配置的属性，属性的值为默认值

    #更新其他服务注册表时间间隔，默认30秒
    registry-fetch-interval-seconds: 30
    #更新实例信息的变化到Eureka服务端的间隔时间，单位为秒
    instance-info-replication-interval-seconds: 30
    #初始化实例信息到Eureka服务端的间隔时间，单位为秒
    initial-instance-info-replication-interval-seconds: 40
    #轮询Eureka服务端地址更改的间隔时间，单位为秒。
    #当我们与Sping Cloud Config配合，动态刷新Eureka的service url地址时需要关注该参数
    eureka-service-url-poll-interval-seconds: 300
    #读取Eureka Server信息的超时时间，单位为秒
    eureka-server-read-timeout-seconds: 8
    #连接Eureka Server的超时时间，单位为秒
    eureka-server-connect-timeout-seconds: 5
    #从Eureka客户端到所有Eureka服务端的连接总数
    eureka-server-total-connections: 200
    #从Eureka客户端到每个Eureka服务端主机的连接总数
    eureka-server-total-connections-per-host: 50
    #Eureka服务连接的空闲关闭时间，单位为秒
    eureka-connection-idle-timeout-seconds: 30
    #心跳连接池的初始化线程数
    heartbeat-executor-thread-pool-size: 2
    #心跳超时重试延迟时间的最大乘数值
    heartbeat-executor-exponential-back-off-bound: 10
    #缓存刷新线程池的初始化线程数
    cache-refresh-executor-thread-pool-size: 2
    #缓存刷新重试延迟时间的最大乘数值
    cache-refresh-executor-exponential-back-off-bound: 10
    #使用DNS来获取Eureka服务端的service url
    use-dns-for-fetching-service-urls: false
    #是否优先使用处于相同Zone的Eureka服务端
    perfer-same-zone-eureka: true
    #获取实例时是否过滤，仅保留UP状态的实例
    filter-only-up-instances: true

#数据源的配置
spring:
  datasource:
    platform: h2
    schema: classpath:schema.sql
    data: classpath:data.sql
    driver-class-name: org.h2.Driver
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: none
```