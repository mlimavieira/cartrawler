# Cartrawler challenge

### Compile and Run
```mvn clean install spring-boot:run```

### Endpoints
* http://localhost:8080/caroffer - List all the Offers without repetitions and sorted.
* http://localhost:8080/caroffer/median - List all the Offers without te Offers above the median and fuel type is FULL_FULL.
* http://localhost:8080/caroffer/all?sorted=false - List all the Offers with the duplicates and can be sorted based on the parameter.

