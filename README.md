# Hypertrace View Generator

###### org.hypertrace.viewgenerator

[![CircleCI](https://circleci.com/gh/hypertrace/hypertrace-view-generator.svg?style=svg)](https://circleci.com/gh/hypertrace/hypertrace-view-generator)

This repository contains Hypertrace view creator bootstrap job and Hypertrace view generation streaming job.

Hypertrace view creator:
It is a bootstrap job that creates required views in pinot like spanEventView, backendEntityView, etc.

Hypertrace view generator: 
It is a streaming job that materializes enriched traces into pinot views

## Description

| ![space-1.jpg](https://hypertrace-docs.s3.amazonaws.com/ingestion-pipeline.png) | 
|:--:| 
| *Hypertrace Ingestion Pipeline* |

Hypertrace view creator is a bootstrap job that runs ones and created required views in pinot which involves `spanEventView`, `serviceCallView`, `backendEntityView`, `rawServiceView`, `rawTraceView`.

Ones this views are there in pinot, Hypertrace view generator takes enriched traces which [Hypertrace trace enricher](https://github.com/hypertrace/hypertrace-trace-enricher) pushed to kafka and materializes them into pinot views. 

For example, Let's say we got a trace with 50 attributes After enrichement by Hypertrace trace enricher and `spanEventView` only needs 10 attributes so Hypertrace view generator will fill that view with only those 10 attributes.

## Building locally
`hypertrace-view-generator` uses gradlew to compile/install/distribute. Gradle wrapper is already part of the source code. To build `hypertrace-view-generator`, run:

```
./gradlew dockerBuildImages
```

## Testing

### Running unit tests
Run `./gradlew test` to execute unit tests. 


### Testing image

You can test the image you built after modification by running docker-compose or helm setup. 

#### docker-compose
Change the tag for `hypertrace-view-generatorr ` from `:main` to `:test` in [docker-compose file](https://github.com/hypertrace/hypertrace/blob/main/docker/docker-compose.yml) like this.

```yaml
  hypertrace-view-generator:
    image: hypertrace/hypertrace-view-generator:test
    container_name: hypertrace-view-generator
    ...
```

and then run `docker-compose up` to test the setup.

## Docker Image Source:
- [DockerHub > Hypertrace view generator](https://hub.docker.com/r/hypertrace/hypertrace-view-generator)
