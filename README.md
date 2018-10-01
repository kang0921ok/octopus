# Octopus
서비스를 운영 하다 보면 데이터가 쌓이게 됩니다. 데이터의 양은 날이 갈 수록 커지게 되고 해당 데이터를 효율적으로 관리&분석하기 위하여 대용량 데이터 분석 플랫폼인 하둡에코시스템등을구축하곤 합니다. 정통적인 하둡에코시스템들과 Spark를 거쳐 Druid까지 대용량 데이터를 분석하기 위해서 다양한 플랫폼이 존재합니다. 
서비스개발자들은 데이터가 쌓이게 되면 위의 나열한 대용량 데이터 분석 플랫폼을 직접 구축하여 데이터를 관리해야 했습니다. Druid를 비롯한 대용량 데이터 분석 플랫폼은 구축을 위한 오픈 소스가 활성화 되어 있어 구축자체에는 크게 어려움이 없으나 시스템 운영 비용 & 인프라 비용을 무시 할 수 없습니다. 
이를 Octopus가 해결하고자 합니다. 미리 구축 된 하나의 대용량 데이터 분석 플랫폼을 Octopus와 연동 시킵니다. 각각의 서비스 개발자들은 Octopus로 접근하여 데이터를 수집&가공 할 수 있습니다. Octopus는 다양한 대용량 데이터 분석 플랫폼 중 Druid를 지원합니다. 이 후 계획으로는 hive, spark등이 있습니다.


## Features
* Easy to make statistics data without understanding Druid system.
* Flexibility of statistics analysis system regardless of service category.


## Structure
Below figure shows Whole flow of Octopus.
<img src="https://user-images.githubusercontent.com/16604419/46259747-3f7f6280-c518-11e8-80c9-c4de8621629d.png"></img>
Octopus connects the user with the statistics system. Also it supports multi tenancy architecture.

Below figures show data flow of Octopus.

1. Store data
<img src="https://user-images.githubusercontent.com/16604419/46290219-2d5bfd80-c5c6-11e8-879d-1602ade4660f.png"></img>

2. Query Data
<img src="https://user-images.githubusercontent.com/16604419/46259397-d0a00a80-c513-11e8-8bd0-65a4deea28a4.png"></img>

## Getting started
### Dependencies
* spring-webmvc-5.0.8.RELEASE
* spring-boot-2.0.4.RELEASE

### Installation
```
git clone https://github.com/kang0921ok/octopus.git
cd octopus 
mvn clean install
java -jar target/octopus-{version}.war
```



## License
Copyright @2018- GoBaby

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
