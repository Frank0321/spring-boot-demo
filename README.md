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
- HELP.md : spring initalizr 給予的說明，若使用其他 dependency 的話，會附上 documentation
- .gitgnore : 當使用 git、github 的時候，需要忽略掉(如需要建置產生的東西)的檔案相關設定，主要內容如下 : 
  - HELP.md : 說明文件，不需要 commit
  - target : maven 的主要結構，主要放置 Compile 的 Java 檔
  - STS : Eclipse 專案的主要檔案，是由 Eclipse 負責產生的，大多屬於特定電腦裡的絕對路徑，會影響到不同電腦間的設定，
    不需要 commit
  - InterlliJ IDEA
  - NetBeans
  - VS code 
  - 都屬於 ide 相關的設定檔，所以皆不需要 commit
  
## pom.xml 介紹
- 如果對於 maven 的 pom.xml 進行改變，皆需要執行 reload !!! 執行方式
  - 在 Actions 中，搜尋 Reload All Maven Projects
  - 點選 pom.xml 右上角的圖示
  - 點選 右側 maven 中，最上排的第一個 reload 的按鈕
- spring boot 版本
  ```xml
  <!-- 基於 Spring boot starter 建置的，Spring boot 的版本為 2.5.2，會控管 Spring boot 的版本 -->
  <parent>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-parent</artifactId>
      <version>2.5.2</version>
      <relativePath/> <!-- lookup parent from repository -->
  </parent>
  ```
- 專案的身分證
  ```xml
  <!-- 這一個 maven 的身分證 -->
  <groupId>tw.com.softleader</groupId>
  <artifactId>spring-boot-demo</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <name>spring-boot-demo</name>
  <description>Demo project for Spring Boot</description>
  ```
- 添增 web 依賴
  ```xml
  <!-- 未使用 web 依賴 -->
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter</artifactId>
  </dependency>
    <!-- 使用 web 依賴 -->
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  ```
- 依賴 (dependency)
  ```xml
  <!-- 這一個專案所使用的依賴，減少開發流程 -->
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
		</dependency>
        <dependency>....</dependency>
	</dependencies>
  ```
- 包版 (build)


## maven 主要資料夾結構
- class pass 是從 包檔後，classes 底下算起的路徑，通常表示為根目錄


## spring boot 包版
- 在 terminal 輸入 mvn package 就可以進行簡單的包版
- 會在 target 裡面產生一個 jar 檔
- resources 的資料夾與 Java 都會被包在 class 這個目錄底下
- 被包在 class 底下的東西，才可以進行讀取、運行等

## 額外補充說明
- 在 Project 中的 Modules ，可以選擇 Language level，限制開發時，使用到多少版本以上的特性
- console 印出 exit code 0
  - 表示正常被結束的，這個程式已經結束了
  - 如果是 web 的話，會需要一直監聽，有 request 則 repsonse 回去