# Mybatis神器-逆向工程

  > mybatis逆向工程就是自动生成实体对象的domain,mapper和映射文件的工具。
  > 内部使用反射进行了封装，只需要简单的配置就能够将创建好的数据库中的表格数据转化成为可以使用的java类，节省了大量的时间。

## 1.官网

> 官方网站： <a href="http://mybatis.org/generator/">http://mybatis.org/generator/</a>


## 2.引入依赖包

```xml
	  <dependency>
		  <groupId>org.mybatis.generator</groupId>
		  <artifactId>mybatis-generator-core</artifactId>
		  <version>1.3.5</version>
	  </dependency>
```

## 3.创建MBG.xml文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>
    <context id="DB2Tables" targetRuntime="MyBatis3">
        <commentGenerator>
            <property name="suppressAllComments" value="true" />
        </commentGenerator>
        <jdbcConnection
                driverClass="com.mysql.cj.jdbc.Driver"
                        connectionURL="jdbc:mysql://localhost:3306/ssm_curd?useUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=false&amp;serverTimezone=GMT%2B8"
                        userId="root"
                        password="123456">
        </jdbcConnection>

        <javaTypeResolver >
            <property name="forceBigDecimals" value="false" />
        </javaTypeResolver>

        <!--指定javaBean生成位置-->
        <javaModelGenerator targetPackage="com.sun.curd.bean" targetProject=".\src\main\java">
            <property name="enableSubPackages" value="true" />
            <property name="trimStrings" value="true" />
        </javaModelGenerator>

        <!--指定映射文件生成位置-->
        <sqlMapGenerator
                targetPackage="mapper"
                targetProject=".\src\main\resources">
            <property name="enableSubPackages" value="true" />
        </sqlMapGenerator>

        <!--指定dao接口生成位置-->
        <javaClientGenerator type="XMLMAPPER"
                             targetPackage="com.sun.curd.dao"
                             targetProject=".\src\main\java">
            <property name="enableSubPackages" value="true" />
        </javaClientGenerator>

        <!--table表生成规则-->
        <table tableName="tbl_emp" domainObjectName="Employee"></table>
        <table tableName="tnl_dept" domainObjectName="Department"></table>

    </context>
</generatorConfiguration>
```

## 4.创建MBGTest.java

```java
public class MbgTest {
    public static void main(String[] args) throws InterruptedException, SQLException, IOException, XMLParserException, InvalidConfigurationException {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("MBG.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
```


## 注意
> 配置中的信息由几部分需要改动，复制粘贴时需要小心；
