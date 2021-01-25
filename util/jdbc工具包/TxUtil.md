# TxUtil.java文件

## configFile
> 主要是一些配置文件

+ 1.连接DruidDataResource数据池
+ 2.内部含有操作数据库时保护方法，及其事务处理机制
> startTransaction():设置事务不能自动提交,创建一个连接交给线程池。
> rollback():创建连接失败，事务回滚
> commit() ：提交事务
> close():将连接放回连接池中


## util
> 创建的一些工具包