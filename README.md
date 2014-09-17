liger-data-access
======================

Data access layer containing DAO and models, forked from [canadensys-data-access](http://github.com/canadensys/canadensys-data-aceess).

Code Status
-----------
[![Build Status](https://travis-ci.org/WingLongitude/liger-data-access.png)](https://travis-ci.org/WingLongitude/liger-data-access)

Maven
-----
liger-data-access artifacts are deployed to [jCenter](https://bintray.com/bintray/jcenter).
```
	<repositories>
    ...
		<repository>
			<id>jcenter</id>
			<url>http://jcenter.bintray.com/</url>
		</repository>
	</repositories>
```

Dependencies
------------
* [Apache Maven 3](http://maven.apache.org/)
* [Spring Framework 4.0.2.RELEASE](http://www.springsource.org/spring-framework)
* [Hibernate 4.3.2.Final](http://www.hibernate.org/)
* [Apache Commons BeanUtils 1.8.3](http://commons.apache.org/beanutils/)
* [Jackson 2.2.3](http://wiki.fasterxml.com/JacksonHome)
* [GBIF DarwinCoreArchiveReader 1.9.1](http://code.google.com/p/darwincore/wiki/DarwinCoreArchiveReader)
* [JSON.org](http://www.json.org/java/)
* [Canadensys Core 1.8](https://github.com/Canadensys/canadensys-core)

Optional
* [Elastic Search 0.90.12](http://www.elasticsearch.org/)

Testing only
* [H2 Database 1.3.175](http://www.h2database.com)

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
