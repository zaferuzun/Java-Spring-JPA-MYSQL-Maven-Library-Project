# Java-Spring-JPA-MYSQL-Maven-Library-Project

# Kurulum
## Windows

Projeyi derleyebilmek için maven ve java jdk ortamları sistem değişkenlerinde bulunması gerekmektedir.

Java https://www.oracle.com/tr/java/technologies/javase/javase-jdk8-downloads.html adresinden indirebilirsiniz.

Jdk yı sistem değişkenlerine eklemek için;
1. Bilgisayarım
2. Özellikler
3. Gelişmiş Sistem Ayarları
4. Ortam Değişkenleri
5. Sistem Değişkenleri
    *  Yeni
    * Değişken Adı = JAVA_HOME
    * Değişken Değeri =  C:\Program Files\Java\jdk1.8.0_241  Javanın kurulu olduğu yer olarak eklenir. 
    * Aynı ekranda sistem değişkenlerinin içinde; 
    * Path 
    * Düzenle
    *  Yeni %JAVA_HOME%\bin şeklinde düzenlenip sistem değişkenlerine eklenmelidir. 

Kontrolü  bu şekilde sağlanabilir. 
```bash
C:\Users\User>java -version
java version "1.8.0_241"
Java(TM) SE Runtime Environment (build 1.8.0_241-b07)
Java HotSpot(TM) 64-Bit Server VM (build 25.241-b07, mixed mode) 
```

Maven http://maven.apache.org/download.cgi adresinden indirebilirsiniz. Dosyaları Javanın bulunduğu konuma atabilirsiniz.

Maven sistem değişkenlerine eklemek için;
1. Bilgisayarım
2. Özellikler
3. Gelişmiş Sistem Ayarları
4. Ortam Değişkenleri
5. Sistem Değişkenleri
    *  Yeni
    * Değişken Adı = MAVEN_HOME
    * Değişken Değeri =  C:\Program Files\Java\apache-maven-3.6.3  Mavenin kurulu olduğu yer olarak eklenir. 
    * Aynı ekranda sistem değişkenlerinin içinde; 
    * Path 
    * Düzenle
    *  Yeni %MAVEN_HOME%\bin şeklinde düzenlenip sistem değişkenlerine eklenmelidir. 

Kontrolü  bu şekilde sağlanabilir. 

```bash
C:\Users\uznza>mvn -version
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: C:\Program Files\Java\apache-maven-3.6.3\bin\..
Java version: 1.8.0_241, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk1.8.0_241\jre
Default locale: tr_TR, platform encoding: Cp1254
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```

mvn derleme sırasında hata veriyor ise JAVA_HOME yolunu kontrol edebilirsiniz. Maven jdk kullanarak derleyebilmektedir. runtime kısmı jdk ile başlamalıdır. 

Java ve Maven yüklendikten sonra bir klasör içerisine git bash komut penceresini açarak aşağıdaki komutlar ile proje derlenir.

```bash
$ git clone https://github.com/zaferuzun/Java-Spring-JPA-MYSQL-Maven-Library-Project.git
```
```bash
$ cd Java-Spring-JPA-MYSQL-Maven-Library-Project/
$ cd libraryproject/
```

```bash
$ mvn package
```
```bash
$ cd target
$ java -jar libraryproject-0.0.1-SNAPSHOT.jar
```

Herhangi bir internet tarayıcısından 

http://localhost:8080

adresi üzerinden proje deneyimlenir.

Admin için Email= admin@g.com Şifre = admin
Editor için Email editor@g.com  Şifre = editor
User için Email user@g.com Şifre = user
