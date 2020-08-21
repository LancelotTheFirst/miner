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
    
    % Try to use NoSql storage.
    
        * May be Spring Data key-value no sql storage would be convenient - rejected.
        
17.07.2020

    + Store income blocks in mongo.
    
    + Get income block and then delete it. 
    
    + Add transaction support.
    
04.08.2020    

    + Repair transaction support.
    
    + Look docker lesson.
    
    % Run apps in docker containers with own databases.
    
05-07.08.2020    

    % Run apps in docker containers with own databases.
    
        + Rewrite dockerfile - change 'from' to java image
        
        + Investigate is it nesessary to forward mongo ports to local OS.
        
08.08.2020        
    
    * Use embedded mongo - rejected. 

    + Run apps in docker containers with own databases.
        
        + Create docker-compose file - run mongo and app containers for each of two clients.
        
            + Enter host of mongo as command line args.
            
                + Test without docker.
            
            + Change app port of each miners to 8080.
            
            + Create two different docker files for each miner.
            
            + Change host of block distributor to miner-one/miner-two using cmd args.
            
            + Rewrite docker compose to use different miner images.
            
    + Create script to remove images, build new images and run docker-compose.
    
09.08.2020    
    
    + Correct packages.
    
19.08.2020    
    
    % Develop unit tests.
    
        + Develop tests for Hash class.
        
21.08.2020
   
    % Develop integration tests.
        
        + Develop test for IncomeController.

    % Develop unit tests.    

    - Write good looking readme.
    
        - Draw scheme.
        
        - Tell the essence of project.
        
    - Use profiles to choose local or remote launch.
    
        - Change cmd args to properties files - profiles will help to choose of them.
    
    - Save and initialize blockchain from the storage - mongo.    
        
    - Add initialization from another miner entries because local data may be already not actual.
    
        - Add block number to Block.
        
        - Add date and time of creation to block.
        
        - For income blocks with the same number use older one.
    
    - Investigate why it is nesessary to Autowire by setters or constructors but not property itself.
    
    - Add separate application and infrastructure properties files and handle them in project.
    
        - Put number of zeros there for example.
    
    - Creat ObjectValue for port.
    
    - Check memory and processor usage with actuator.
    
    - Add competitors block validation.
        
    - Add logging not only when errors but in normal flow too.
        
    - Add MDC.
    
    - Add correct exception handling.
    
    - Write comments where you think they are nessesary.
    
    - Use liquibase.
    
    - Solve strange situation with using request and response classes from controller package in infrastructure.
    