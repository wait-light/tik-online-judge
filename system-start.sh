mvn clean install
cd ./tik-online-judge-auth && mvn clean install
cd ../tik-online-judge-executor && mvn clean install
cd ../tik-online-judge-gateway && mvn clean install
cd ../tik-online-judge-message && mvn clean install
cd ../tik-online-judge-social && mvn clean install
cd ../tik-online-judge-thirdpart && mvn clean install
cd ..
docker-compose up -d
