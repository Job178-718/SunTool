# 日志log4j使用

## 1.引入log4j依赖

```xml
      <dependency>
          <groupId>log4j</groupId>
          <artifactId>log4j</artifactId>
          <version>1.2.17</version>
      </dependency>
```

## 2.创建配置文件

```properties
#定义LOG输出级别
log4j.rootLogger=INFO,Console,File,D,E

#mybatis级别
log4j.logger.org.mybatis=DEBUG

#定义日志输出目的地为控制台
log4j.appender.Console=org.apache.log4j.ConsoleAppender
log4j.appender.Console.Target=System.out
#可以灵活地指定日志输出格式，下面一行是指定具体的格式
log4j.appender.Console.layout = org.apache.log4j.PatternLayout
log4j.appender.Console.layout.ConversionPattern=[%c] - %m%n

#定义文件最大大小
log4j.appender.File.MaxFileSize = 10MB
### log file ###
log4j.appender.D = org.apache.log4j.DailyRollingFileAppender
log4j.appender.D.File = E:/logs/Qingchun/link_info.log
log4j.appender.D.DatePattern=yyyy-MM-dd'.txt'
log4j.appender.D.Append = true
log4j.appender.D.Threshold = INFO
log4j.appender.D.layout = org.apache.log4j.PatternLayout
log4j.appender.D.layout.ConversionPattern = [%p] [%-d{yyyy-MM-dd HH:mm:ss}] %C.%M(%L) | %m%n

### exception ###
log4j.appender.E = org.apache.log4j.DailyRollingFileAppender
log4j.appender.E.File = E:/logs/Qingchun/link_error.log
log4j.appender.E.DatePattern=yyyy-MM-dd'.txt'
log4j.appender.E.Append = true
log4j.appender.E.Threshold = ERROR
log4j.appender.E.layout = org.apache.log4j.PatternLayout
log4j.appender.E.layout.ConversionPattern = [%p] [%-d{yyyy-MM-dd HH:mm:ss}] %C.%M(%L) | %m%n
```

## 3.web.xml中引入

```xml
  <context-param>
    <param-name>log4jConfigLocation</param-name>
    <param-value>classpath:log4j.properties</param-value>
  </context-param>
```

## 4.mybatis.xml中的配置

```xml
   <settings>
        <!--日志-->
        <setting name="logImpl" value="STDOUT_LOGGING"/>
    </settings>
```

