# Spring-boot-demo

## Spring initializr 建置專案
- 快速建置專案的方法
- [Spring initializr](https://start.spring.io/)
- Project :
  - 選擇專案的管理工具
  - Maven Project (目前我們公司)
  - Gradle Project  
- Language : 
  - 所使用的 JVM 語言，JDK 除了 Java library 之外，還需要負責啟動 class 檔，需要借助 JVM 去啟動
  - Java
  - Kotlin
  - Groovy  

- Package name : spring boot 的進入點 class 被放在哪裡

## 專案檔案結構介紹
- 標準的 maven 專案格式，包含 pom.xml 與 src 資料夾
- .mvn、mvnw、mvnw.cmd : 環境沒有 maven 的狀況下，可以建置專案(透過 mvnw.cmd)
- HELP.md : spring initalizr 給予的說明
- .gitgnore : 當使用 git、github 的時候，需要忽略掉(如需要建置產生的東西)的檔案相關設定，主要內容如下 : 
  - target : maven 的主要結構