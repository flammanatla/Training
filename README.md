# Training

run jenkins with allure reporting:

java "-Dhudson.model.DirectoryBrowserSupport.CSP=default-src 'self'; script-src 'self' 'unsafe-inline' 'unsafe-eval'; style-src 'self' 'unsafe-inline';" "-Djenkins.model.DirectoryBrowserSupport.CSP=default-src 'self'; script-src 'self' 'unsafe-inline' 'unsafe-eval'; style-src 'self' 'unsafe-inline';" -jar jenkins.war --httpPort=9090


run selenium grid

java -jar selenium-server-standalone-2.53.0.jar -role hub


run selenium node 

java -jar selenium-server-standalone-2.53.0.jar -role node -hub http://192.168.0.5:4444/grid/register


run docker-selenium-hub

docker run -d -P -p 4444:4444 selenium/hub


run docker-selenium-node

docker run -d --link selenium-hub:hub selenium/node-firefox


run apache web server into xampp

sudo /Applications/XAMPP/xamppfiles/bin/apachectl start
