# jhipsterRqueue

This application was generated using JHipster 6.10.1, you can find documentation and help at [https://www.jhipster.tech/documentation-archive/v6.10.1](https://www.jhipster.tech/documentation-archive/v6.10.1).

This is a "microservice" application intended to be part of a microservice architecture, please refer to the [Doing microservices with JHipster][] page of the documentation for more information.

This application is configured for Service Discovery and Configuration with . On launch, it will refuse to start if it is not able to connect to .

## Bug description

This is the bug i have got in console. when running the project.

```
ERROR 19020 --- [r-rqueue-task-2] .a.i.SimpleAsyncUncaughtExceptionHandler : Unexpected exception occurred invoking async method: public void com.github.sonus21.rqueue.core.MessageScheduler.onApplicationEvent(com.github.sonus21.rqueue.models.event.RqueueBootstrapEvent)

java.lang.IllegalAccessError: failed to access class com.github.sonus21.rqueue.core.MessageScheduler from class com.github.sonus21.rqueue.core.MessageScheduler$$FastClassBySpringCGLIB$$c8e81411 (com.github.sonus21.rqueue.core.MessageScheduler is in unnamed module of loader 'app'; com.github.sonus21.rqueue.core.MessageScheduler$$FastClassBySpringCGLIB$$c8e81411 is in unnamed module of loader org.springframework.boot.devtools.restart.classloader.RestartClassLoader @588e2e8a)
	at com.github.sonus21.rqueue.core.MessageScheduler$$FastClassBySpringCGLIB$$c8e81411.invoke(<generated>)
	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:771)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)
	at org.springframework.aop.interceptor.AsyncExecutionInterceptor.lambda$invoke$0(AsyncExecutionInterceptor.java:115)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	at io.github.jhipster.async.ExceptionHandlingAsyncTaskExecutor.lambda$createWrappedRunnable$1(ExceptionHandlingAsyncTaskExecutor.java:78)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	at java.base/java.lang.Thread.run(Thread.java:834)
```
## How to reproduce the bug

send instant message: 

```
// this will send "helloworld" message in response body
GET: localhost:9018/api/test/echo/helloworld 

// Response: 200
{
  "message": "helloworld"
}
```

send delayed message: 

```
// this will queue "helloworld" message in rqueue
GET: localhost:9018/api/test/echo/delay/helloworld

// Response: 200
```

The message is queued, and visible in the rqueue dashboard:

<img src="https://i.ibb.co/n1PWpnK/image.png" />  

but it will never executed after timespan (after 1 second as referenced in the app).

The rqueue listener didn't got any ping/notification to execute the delayed/scheduled job. 

## Development

To start your application in the dev profile, run:

```
./mvnw
```

For further instructions on how to develop with JHipster, have a look at [Using JHipster in development][].

## Building for production

### Packaging as jar

To build the final jar and optimize the jhipsterRqueue application for production, run:

```

./mvnw -Pprod clean verify


```

To ensure everything worked, run:

```

java -jar target/*.jar


```

Refer to [Using JHipster in production][] for more details.

### Packaging as war

To package your application as a war in order to deploy it to an application server, run:

```

./mvnw -Pprod,war clean verify


```

## Testing

To launch your application's tests, run:

```
./mvnw verify
```

For more information, refer to the [Running tests page][].

### Code quality

Sonar is used to analyse code quality. You can start a local Sonar server (accessible on http://localhost:9001) with:

```
docker-compose -f src/main/docker/sonar.yml up -d
```

You can run a Sonar analysis with using the [sonar-scanner](https://docs.sonarqube.org/display/SCAN/Analyzing+with+SonarQube+Scanner) or by using the maven plugin.

Then, run a Sonar analysis:

```
./mvnw -Pprod clean verify sonar:sonar
```

If you need to re-run the Sonar phase, please be sure to specify at least the `initialize` phase since Sonar properties are loaded from the sonar-project.properties file.

```
./mvnw initialize sonar:sonar
```

For more information, refer to the [Code quality page][].

## Using Docker to simplify development (optional)

You can use Docker to improve your JHipster development experience. A number of docker-compose configuration are available in the [src/main/docker](src/main/docker) folder to launch required third party services.

For example, to start a mongodb database in a docker container, run:

```
docker-compose -f src/main/docker/mongodb.yml up -d
```

To stop it and remove the container, run:

```
docker-compose -f src/main/docker/mongodb.yml down
```

You can also fully dockerize your application and all the services that it depends on.
To achieve this, first build a docker image of your app by running:

```
./mvnw -Pprod verify jib:dockerBuild
```

Then run:

```
docker-compose -f src/main/docker/app.yml up -d
```

For more information refer to [Using Docker and Docker-Compose][], this page also contains information on the docker-compose sub-generator (`jhipster docker-compose`), which is able to generate docker configurations for one or several JHipster applications.

## Continuous Integration (optional)

To configure CI for your project, run the ci-cd sub-generator (`jhipster ci-cd`), this will let you generate configuration files for a number of Continuous Integration systems. Consult the [Setting up Continuous Integration][] page for more information.

[jhipster homepage and latest documentation]: https://www.jhipster.tech
[jhipster 6.10.1 archive]: https://www.jhipster.tech/documentation-archive/v6.10.1
[doing microservices with jhipster]: https://www.jhipster.tech/documentation-archive/v6.10.1/microservices-architecture/
[using jhipster in development]: https://www.jhipster.tech/documentation-archive/v6.10.1/development/
[using docker and docker-compose]: https://www.jhipster.tech/documentation-archive/v6.10.1/docker-compose
[using jhipster in production]: https://www.jhipster.tech/documentation-archive/v6.10.1/production/
[running tests page]: https://www.jhipster.tech/documentation-archive/v6.10.1/running-tests/
[code quality page]: https://www.jhipster.tech/documentation-archive/v6.10.1/code-quality/
[setting up continuous integration]: https://www.jhipster.tech/documentation-archive/v6.10.1/setting-up-ci/
