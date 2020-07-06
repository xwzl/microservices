# 概述

主要用于各种环境搭建，比如 mysql,zookeeper,redis等等。

# 前提条件

### 安装 docker 

以下方式安装的 docker 不是最新版本的 docker 

    yum install docker 
    # 启动 docker
    systemctl start docker 
    # 允许开机启动 docker
    systemctl enable docker

### 安装 docker-compose 编排工具

最新版本的 docker-compose 对语法似乎改变，有需求的看官网文档

    #docker
    curl -sSL https://get.daocloud.io/docker | sh
    
    #docker-compose
    curl -L \
    https://get.daocloud.io/docker/compose/releases/download/1.23.2/docker-compose-`uname -s`-`uname -m` \
    > /usr/local/bin/docker-compose
    chmod +x /usr/local/bin/docker-compose
    
    #查看安装结果
    docker-compose -v
    
# 开始安装环境

### 关闭防火墙

由于防火墙的原因，可能会造成容器间通信问题，因此个人开发统一关闭防火墙

    systemctl stop firewalld.service                #停止防火墙  
    systemctl disable firewalld.service             #在开机时禁用服务

获取关闭部分端口的防火墙
    
    firewall-cmd --query-port=8080/tcp              # 查询端口是否开放
    firewall-cmd --permanent --add-port=80/tcp      # 开放80端口
    firewall-cmd --permanent --remove-port=8080/tcp # 移除端口
    firewall-cmd --reload                           #重启防火墙(修改配置后要重启防火墙)

重启 docker 服务
    
    systemctl restart docker

### 启动前的一些配置

nacos.properties 需要修改数据库的 ip 地址

赋值当前目录下的所有文件到 centons 或者其他 linux 服务器中

    vi init.sh #  进入其中后 esc + : 输入 set ff=unix 改变文件格式， : wq 保存退出
    
    chmod 777 init.sh

    sh init.sh # 运行初始化脚本
  
docker-compose 编排启动环境
    
    docker-compose up    # 直接启动
    
    docker-compose up -d # 后台方式启动服务    

    docker-compose up logs --tf --tail 50 # 后台方式启动查看 docker 运行日志
    
    docker-compose down  # 停止服务
    
到此，环境初始化完毕

 

