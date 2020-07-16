23.06.2020

    + Create diagram of miner.
    
    + Create Boot project with Web, Data JPA, Dev Tools, Integration and Actuator.
    
    + Add to VCS.

26.06.2020

    + Correct hash calculation.

11.07.2020

    Test.
    
        + Prepare correct Json of block representation.
    
        + Test controller is invoking.
    
        + Test blocks are saving to in memory storage.
    
        + Test mining process handle income blocks correctly.

16.07.2020	

    + Do async block distribution using jms.
    
    - Investigate why it is nesessary to Autowire by setters or constructors but not property itself.
    
    - Add separate application and infrastructure properties files and handle them in project.
    
        - Put number of zeros there for example.
    
    - Creat ObjectValue for port.
    
    - Check memory and processor usage with actuator.
    
    - Add competitors block validation.
    
    - Add initialization from another miner entries because local data may be already not actual.
    
    - Add logging not only when errors but in normal flow too.
    
    - Add correct exception handling.
    
    - Write comments where you think they are nessesary.
    
    - Try to use NoSql storage.
    
        - May be Spring Data key-value no sql storage would be convenient.
    
    - Use liquibase.
    
    - Solve strange situation with using request and response classes from controller package in infrastructure.
    
    - Add MDC.