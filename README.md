# Spring-boot-demo
本次內容包含 :
- Spring initializr 建置專案
- maven 專案檔介紹
  - pom.xml 介紹
  - maven 主要資料夾結構
  - 專案相關設定
- spring boot 包版
- Entity 介紹 (會移動到 JPA)
- 額外補充說明

## Spring boot 啟動
- 啟動 spring 框架時，會自動配置。將符合自動配置條件的 bean 載入 IoC 容器中

## Spring initializr 建置專案
- [Spring initializr](https://start.spring.io/)
- 快速建置專案的方法
- 裡面各個欄位介紹如下 :
- Project :
  - 選擇專案的管理工具
  - Maven Project
  - Gradle Project  
- Language : 
  - 所使用的 JVM 語言，JDK 除了 Java library 之外，還需要負責啟動 class 檔，需要借助 JVM 去啟動
  - Java
  - Kotlin
  - Groovy  

- Package name : spring boot 的進入點 class 被放在哪裡

- 備註 : [使用 interlliJ 建立 maven 專案](https://www.itread01.com/content/1545844517.html)
- 參考資料 : 
 > - [Java Maven 什麼是Artifact](https://matthung0807.blogspot.com/2018/08/java-maven-artifact.html)
 > - [initializr 官網介紹](https://www.javatpoint.com/spring-initializr)

## maven 專案檔介紹
- 標準的 maven 專案格式，包含 pom.xml 與 src 資料夾
- .mvn、mvnw、mvnw.cmd : 環境沒有 maven 的狀況下，可以建置專案(透過 mvnw.cmd)
- HELP.md : spring initalizr 給予的說明，若使用其他 dependency 的話，會附上 documentation
- .gitgnore : 當使用 git、github 的時候，需要忽略掉(如需要建置產生的東西)的檔案相關設定，主要內容如下 : 
  - HELP.md : 說明文件，不需要 commit
  - target : maven 的主要結構，主要放置 compile 的 Java 檔
  - STS : Eclipse 專案的主要檔案，是由 Eclipse 負責產生的，大多屬於特定電腦裡的絕對路徑，會影響到不同電腦間的設定，
    不需要 commit
  - InterlliJ IDEA : 理由同 STS 一樣 
  - NetBeans : 理由同 STS 一樣
  - VS code : 理由同 STS 一樣
  - 都屬於 ide 相關的設定檔，所以皆不需要 commit
  - 未 commit 的檔案 : 藍色
  - 未 加入 github 的檔案 : 紅色
  
### Maven 專案結構
```xml
├─ pom.xml      
├─ src            
│ ├─ main
│ │ ├─ java
│ │ ├─ resources
│ │ ├─ webapp
│ │ │ ├─ WEB-INF*
│ │ │ │ ├─ jsp*
│ ├─ test
```
- [maven 目錄結構](https://www.itread01.com/content/1541778370.html)
  
### pom.xml 介紹
- 如果對於 maven 的 pom.xml 進行改變，皆需要執行 reload !!! 執行方式
  - 在 Actions 中，搜尋 Reload All Maven Projects
  - 點選 pom.xml 右上角的圖示
  - 點選 右側 maven 中，最上排的第一個 reload 的按鈕
- pom.xml 主要做三大事情 : `身分的描述`、`使用那些依賴`、`如何進行包版`
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
  > 每一個專案會以 groupId、artifactId、version 做為唯一的識別，又稱為 Maven Coordinates
  ```xml
  <!-- 這一個 maven 的身分證 -->
  <groupId>tw.com.softleader</groupId>
  <artifactId>spring-boot-demo</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <name>spring-boot-demo</name>
  <description>Demo project for Spring Boot</description>
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
- 包版 (build)
  ```xml
  <!-- 專案建置所需要的資訊(要如何包版) -->
  <build>
      <plugins>
          <plugin>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-maven-plugin</artifactId>
          </plugin>
      </plugins>
  </build>
  ```
---  
- 移除依賴 (exclusion)
  - 常用於處理共用模組中，版本不同的問題
  - 在某個依賴底下，加上 exclusions，如移除 web 底下的 json
  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
       <!-- 移除依賴 -->
      <exclusions>
          <exclusion>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-json</artifactId>
          </exclusion>
      </exclusions>
  </dependency>
  ```
- 依賴的版本管控
  - 如要修改 web 裡面的 json 版本，則 : 
  ```xml
  <!-- 管控依賴版本 -->
  <dependencyManagement>
      <dependencies>
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-json</artifactId>
              <version>2.1.2.RELEASE</version>
          </dependency>
      </dependencies>
  </dependencyManagement>
  ```
- 自動化監控的依賴 (Spring boot Actuator)
  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-actuator</artifactId>
  </dependency>
  ```
- spring-boot-maven-plugin not found
  - 一開始的時候，容易會遇到這個問題，參考解法如下 : 
  - [中文解法](https://blog.csdn.net/wildyuhao/article/details/109721501)
  - [stackoverflow](https://stackoverflow.com/questions/64639836/plugin-org-springframework-bootspring-boot-maven-plugin-not-found)

### maven 主要資料夾結構
- class path 是從 包檔後，classes 底下算起的路徑，通常表示為根目錄
  ```
  ├─ src  
  │ ├─ main
  │ │ ├─ java *
  │ │ ├─ resources *           
  │ ├─ test     
  │ │ ├─ java * 
  ```
- src/main/java : 啟動類別及程式的開發目錄
- src/main/resources : 資源檔目錄
- src/test/java : 測試程式所在的目錄

### maven 專案建置的生命週期
- maven 定義基本的三種基本的 build lifecycle 
> - clean : 移除之前建置(build)所產生的檔案
> - defualt : 專案程式的驗證，編譯，測試，打包，確效，存檔，安裝，佈署
> - site : 產生專案的網站文件，例如專案資訊，測試報告等
      
- 而每一種lifecycle又由數個phases（階段）組成，例如default包含了下列主要的phases。
> 1. validate（驗證） : 驗證專案所需的資訊是否正確。
> 2. compile（編譯） : 編譯原始碼。
> 3. test（測試） : 對編譯的程式碼進行單元測試(unit test)。
> 4. package（打包） : 將編譯的程式碼打包成指定的檔案格式，例如jar或war。
> 5. verify（確效） : 進行整合測試。
> 6. install（安裝） : 將打包好的檔案安裝（存入）至本地端的儲存庫(local repository)。
> 7. deploy（佈署） : 將打包好的檔案上傳至遠端的儲存庫(remote repository)。

- [Maven 專案建置的生命週期](https://matthung0807.blogspot.com/2018/09/maven-maven-build-lifecycle.html)
### 專案相關設定
- application.properties 與 application.yml
  - 兩者皆為設定檔，通常會擇一進行編輯
  - 如果兩者同時存在，application.yml 檔案會被優先載入，再載入 application.properties 的設定
  >  如果 yml 和 properties 檔案都存在相同的配置，後載入的則會把先前的設定蓋掉 => 因此會以 properties 設定為主
  - application.yml 為樹狀結構，如下 : 
  ```yml
  spring:
     application:
        name: demoservice
     server:
  port: 9090
  ```
  - application.properties 為平狀結構，如下 :
  ```
  server.port = 9090
  spring.application.name = demoservice
  ```
     
  - [application.yml與bootstrap.yml的區別](https://www.itread01.com/content/1548720026.html)
  - [application.yml application.properties 優先順序](https://www.itread01.com/content/1544496318.html) 
  - [Spring Boot - Application Properties](https://www.tutorialspoint.com/spring_boot/spring_boot_application_properties.htm)
- Exception : 本身在產生 exception 的時候，不會暴露在網頁上給使用者知道
- 如果需要顯示，則到 application.properties 進行設定
  ```
  #秀出 error 的訊息
  server.error.include-message=always
  
  #秀出 exception 的訊息
  server.error.include-exception=true
  
  #秀出 stacktrace 的訊息
  server.error.include-stacktrace=always
  ```
- https 安全上的設定
  - 跟安控有關的設定
  - 程式完成後，會進行弱點掃描 (弱掃)，設定走 https
    ```
    # cookie 只能走 http
    server.servlet.session.cookie.http-only=true
    # cookie 必續耀能夠被加密
    server.servlet.session.cookie.secure=true
    
    server.servlet.session.cookie.max-age=0
    ```
- DB 相關設定
  - spring.datasource.driver-class-name=org.h2.Driver
    - 設定為使用 H2 DB (有些時候可能會不只有一個DB)
  - spring.h2.console.enabled=true
    - 設定可以網頁上，開啟 H2 DB
  - spring.datasource.url=jdbc:h2:mem:spring-boot-demo
    - 固定 DB 連線的網址(url)
  - spring.jpa.show-sql=true
    - 啟動 spring 時，底下 console 會秀出 SQL 指令
  - 設定密碼
    - 避免被弱點掃描掃到，可以設定在 Configurations -> Configuration -> VM options (啟動變數)
      - -Dspring.datasource.password=sleader，需要再加上 -D
    - 或是可以加到環境變數中 Configurations -> Configuration -> VM options (環境變數)
      - spring.datasource.password=sleader
  - 顯示 SQL 指令
    - 在 application.properties 輸入 : spring.jpa.show-sql=true，
      啟動 spring 時，底下 console 會秀出 SQL 指令
  - 設定統一 Entity 命名規則
    - 在 application.properties 設定 spring.jpa.hibernate.naming.physical-strategy=class名稱
    - 新增一個 class ，並 extends SpringPhysicalNamingStrategy
      - 建立一個 method 回傳文字
      - 覆寫 toPhysicalTableName (table 前面需要加的前綴)
      - 覆寫 getIdentifier (table 後面需要去掉的字，如 Entity)
    ```java
    public class NewTableNameRule extends SpringPhysicalNamingStrategy {
  
      //新增開頭要使用的文字內容
      protected String tablePrefix(){
      return "sl_";
      }
  
      //table 前面需要加的前綴
      @Override
      public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
      Identifier identifier = super.toPhysicalTableName(name, jdbcEnvironment);
      return new Identifier(tablePrefix() + identifier.getText(), identifier.isQuoted());
      }
  
      //table 後面需要去掉的 Entity
      @Override
      protected Identifier getIdentifier(String name, boolean quoted, JdbcEnvironment jdbcEnvironment) {
      String identifier = name.toLowerCase(Locale.ROOT).replace("_entity", "");
      return new Identifier(identifier, quoted);
      }
    }
    ```
- 讀取配置文件中參數的方法
  - [參考](http://www.mydlq.club/article/61/)
    
### 多個 application.properties 設定
在測試時與正式站兩個地方會有需要不同的設定，因此可以藉由使用不同的 application.properties 進行切換
- application-dev.properties : 常用來表示測試環境
- application.properties : 主設定檔  
- application-prod.properties : 常用來表示正式環境才要運行的設定
  - 可以到 Configurations 裡面的 Active profile 去做設定
    - 輸入 `prod` 即可
  - 在 terminal 輸入 mvn spring-boot:run -D"spring.profiles.active=prod"
    - 在 window 寫指令的時候，有"."的時候，會導致指令被切斷，所以需要 " " 將指令框起來

### ComponentScan
- 啟動 spring 時，可以將一些 class 註冊成 spring beans
- 沒有帶入參數時，他會掃描當前的 package 與 sub-packages
- [Spring Component Scanning](https://www.baeldung.com/spring-component-scanning)

## spring boot 包版
- 在 terminal 輸入 mvn package 就可以進行簡單的包版
- 會在 target 裡面產生一個 jar 檔
- resources 的資料夾與 Java 都會被包在 class 這個目錄底下
- 被包在 class 底下的東西，才可以進行讀取、運行等

## Entity 介紹
- 主要會以 H2 DB 與 MariaDB 進行介紹
- 修改 Entity 的名稱
  - 在 Entity 的物件上，標註 @Table( name = " ") 去修改
### H2 DB
H2 為虛擬資料庫，關閉後，會自動釋放資源，自動 drop 掉

- url : http://localhost:8090/h2-console/login.jsp?jsessionid=5d51eb8bdf04ae16f86dc062b4c76b9e
- 添加了 login.jsp?jsessionid=5d51eb8bdf04ae16f86dc062b4c76b9e
  - jsessionid=5d51eb8bdf04ae16f86dc062b4c76b9e 表示 session，每次重啟都會更新
  
### MariaDB
MariaDB 為實體資料庫，關閉後並不會因此而 drop 掉
- 操作方式 : 使用 MySQL Client 進行操作
- 常用指令 : 
  - 查詢已建立的資料庫 : show databases; 
  - 建立資料庫 : create database 資料庫名稱;
  - 刪除資料庫 : drop database 資料庫名稱; (沒有要用一定要刪除，不然會留著)
  - 使用某個資料庫 : use 資料庫;
  - 查詢全部表格 : show tables;
  - 下 SQL : 直接打 SQL (select * from table;)
  - 刪除資料表 : drop table table_name;  
  - 關閉資料庫 exit
  - 注意 : 主要為輸入指令的方式，因此輸入完指令後，需要加上 ";"
  - [參考](https://www.jinnsblog.com/2017/08/mysql-mariadb-sample.html)
  
- 在連到 MariaDB 的時候
  1. 先開啟使用的資料庫，(要輸入密碼) 
  2. 確認是否有要被連線的資料庫名稱，沒有就新建一個資料庫
  3. 在 application-properties 進行設定
    ```
    #使用 mariadb 資料庫
    spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
    # 設定連線的 url (trainingdb 為新建的 db)
    spring.datasource.url=jdbc:mysql://localhost:3306/trainingdb
    # 設定帳密
    spring.datasource.username=root
    spring.datasource.password=root
    ```
- [MariaDB 安裝](https://mariadb.org/)  

### 新增回傳 xml 的內容
- 新增依賴
  ```xml
  <dependency>
      <groupId>com.fasterxml.jackson.dataformat</groupId>
      <artifactId>jackson-dataformat-xml</artifactId>
  </dependency>
  ```
- @GetMapping 新增參數
  ```java
  @GetMapping(value = "/obj", produces = "application/xml")
  public HelloObj helloObj() {
      //....
      return new HelloObj("world", 2021);
  }
  ```

### http 的 status 400與500的差異
- 400 系列 : 錯在 user 身上
- 500 系列 : 錯在 service 身上

### 同時使用到兩個以上的 Http 方式
- 不能同時使用 @GetMapping、@PostMapping
- 需要回歸到 @RequestMapping，並在 參數內使用 methods 的陣列
```java
@RequestMapping(name = "/parm", method = {RequestMethod.GET, RequestMethod.POST})
```
- 可使用 Alt + Enter ，Add on-demand static import for.... 將 {RequestMethod.GET, RequestMethod.POST} 改成 {GET, POST}

### 新增 Spring 監控的細節
- 新增 Actuator
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
- 可以從 Run -> Endpoints ->Mappings 去看有哪些 方法可以使用

## 額外補充說明
- 在 Project 中的 Modules ，可以選擇 Language level，限制開發時，使用到多少版本以上的特性
- console 印出 exit code 0
  - 表示正常被結束的，這個程式已經結束了
  - 如果是 web 的話，會需要一直監聽，有 request 則 response 回去
- Maven Helper
  - 在 setting -> Piugins 中，進行安裝
  - 安裝後 pom.xml 可以看到更內層的包裝 (dependency analyzer)
