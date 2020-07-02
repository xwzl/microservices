#!/bin/bash

# 定义颜色
BLUE_COLOR="\033[36m"
RED_COLOR="\033[31m"
GREEN_COLOR="\033[32m"
VIOLET_COLOR="\033[35m"
RES="\033[0m"

echo -e "${BLUE_COLOR}# ######################################################################${RES}"
echo -e "${BLUE_COLOR}#                       Docker ELK Shell Script                        #${RES}"
echo -e "${BLUE_COLOR}#                       Blog: xwzl.github.io                           #${RES}"
echo -e "${BLUE_COLOR}#                       Email: lmaye@lmaye.com                         #${RES}"
echo -e "${BLUE_COLOR}# ######################################################################${RES}"

# 创建目录
echo -e "${BLUE_COLOR}---> create [elasticsearch]directory start.${RES}"

echo -e "${GREEN_COLOR}---> create [mysql]directory start.${RES}"
if [ ! -d "./mysql/"  ]; then
mkdir -p ./mysql/conf/ ./mysql/data/ ./mysql/nacos/ ./mysql/init/
fi

echo -e "${GREEN_COLOR}---> create [nacos]directory start.${RES}"
if [ ! -d "./nacos/"  ]; then
mkdir -p ./nacos/conf/
fi
echo -e "${BLUE_COLOR}===> create directory success.${RES}"

if [ -d "./mysql/" ]; then
chmod 777 ./mysql/data/ ./mysql/nacos/
fi

echo -e "${BLUE_COLOR}===> directory authorize success.${RES}"

# 移动配置文件
echo -e "${BLUE_COLOR}---> move [elasticsearch]config file start.${RES}"
echo -e "${VIOLET_COLOR}---> move [mysql]config file start.${RES}"
if [ -f "./my.cnf" ]; then
mv ./my.cnf ./mysql/conf
fi

echo -e "${VIOLET_COLOR}---> move [nacos]config file start.${RES}"
if [ -f "./nacos.properties" ]; then
mv ./nacos.properties ./nacos/conf
mv ./nacos.sql ./mysql/init/
fi

echo -e "${BLUE_COLOR}===> move config files success.${RES}"

echo -e "${GREEN_COLOR}>>>>>>>>>>>>>>>>>> The End <<<<<<<<<<<<<<<<<<${RES}"

