<img src="https://user-images.githubusercontent.com/42909013/46256555-ce01ce80-c49b-11e8-91d1-0f03320f66db.png"></img>

# Octopus
When you run the service, you will accumulate data. The amount of data grows day by day, and the Hadoop ecosystem, which is a large-scale data analysis platform, is built to efficiently manage and analyze the data. There are various platforms for analyzing large amounts of data from authentic Hadoop ecosystems to Spruce to Druid. 
Service developers had to manage their data by building the mass data analysis platforms listed above once the data had accumulated. Druid and other large data analysis platforms are open source enabled for deployment, so there is no difficulty in building it, but you can not ignore system operating costs & infrastructure costs.
Octopus wants to solve this. One large pre-built data analysis platform is integrated with Octopus. Each service developer can access and collect data from Octopus. Octopus supports Druid among various large data analysis platforms. After this, hive, spark, and so on.

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

### Release
https://github.com/kang0921ok/octopus/releases

## License
Copyright team.gobaby 

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
