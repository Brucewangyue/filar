### mybatis

1、配置文件中配置
```yaml
mybatis:
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: com.bruce.filar.entity.**
```
Application类配置MapperScan
