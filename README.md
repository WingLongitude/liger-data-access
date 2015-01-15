liger-data-access
======================

Data access layer containing DAO and models, forked from [canadensys-data-access](http://github.com/canadensys/canadensys-data-aceess).

Code Status
-----------
[![Build Status](https://travis-ci.org/WingLongitude/liger-data-access.png)](https://travis-ci.org/WingLongitude/liger-data-access)

Maven
-----
liger-data-access artifacts are deployed to [jCenter](https://bintray.com/winglongitude/maven/liger-data-access/view).
```
	<repositories>
    ...
		<repository>
			<id>jcenter</id>
			<url>http://jcenter.bintray.com/</url>
		</repository>
	</repositories>
  ...
	<dependency>
		<groupId>winglongitude</groupId>
		<artifactId>liger-data-access</artifactId>
		<version>${data-access.version}</version>
	</dependency>
```

Dependencies
------------
* [Apache Maven 3](http://maven.apache.org/)
* [Spring Framework 4.0.9.RELEASE](http://www.springsource.org/spring-framework)
* [Hibernate 4.3.7.Final](http://www.hibernate.org/)
* [Apache Commons BeanUtils 1.8.3](http://commons.apache.org/beanutils/)
* [Jackson 2.2.3](http://wiki.fasterxml.com/JacksonHome)
* [GBIF DarwinCore Archive Reader 1.18](https://github.com/gbif/dwca-reader)
* [JSON.org](http://www.json.org/java/)
* [Canadensys Core 1.8](https://github.com/Canadensys/canadensys-core)

Optional
* [Elastic Search 0.90.12](http://www.elasticsearch.org/)

Testing only
* [H2 Database 1.3.176](http://www.h2database.com)

Build
-----
Build a jar file:
```
mvn package
```
Install to your local repository:
```
mvn install
```

Tests
-----
Unit tests
```
mvn test
```
