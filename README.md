# Octopus
기술의 발전과 함께 데이터의 양도 무섭도록 증가하고 있습니다. 수많은 데이터 속에서 가치를 내기 위해서 대용량 데이터와 이를 분석하기 위한 시스템 구축이 필요합니다. 

대용량 데이터를 분석하기 위해서 다양한 플랫폼이 존재합니다. 그 중 [**Druid**](http://druid.io/)는 대량의 이벤트 데이터를 신속하게 수집하며 빠른 수준으로 쿼리에 대한 응답을 제공합니다.


Druid를 비롯한 대용량 데이터 분석 플랫폼은 구축을 위한 오픈 소스가 활성화 되어 있어 구축에는 크게 어려움이 없습니다. 하지만 플랫폼을 정확히 이해하고 사용하기에는 많은 수고가 따르게 됩니다. 

이를 Octopus가 해결합니다. 몇 가지 설정 정보들만 입력해주면 됩니다. 입력이 완료되면 Octopus는 Druid를 통한 HDFS로의 데이터 적재와 Druid를 통한 쿼리 결과 또한 시각화 시켜 제공합니다. 

결과적으로 서비스 개발자들은 간편한 Open API인 Octopus를 통해 쉽고 빠르게 대용량 데이터를 분석 할 수 있습니다. 


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
It needs to input below command line to install Statistics Cloud. 
> Installation command

### Usage
```
example code
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
