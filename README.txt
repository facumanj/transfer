TRANSFER APPLICATION
1. Design
Involves controllers -REST calls, services -logic and repositories -data. As JPA entities: Account and Transfer.

2. Technology
- Spring Boot 2.0.5.
- JPA.
- H2 embedded.

3. Tests
Different tests scenarios are included in TransferIntegrationTest running as unit test.

4. Usages
- Creates a valid account ->
    http://localhost:8080/account
    Method POST
    {
        "name" : "Facu",
        "balance" : 300
    }
- Creates an account with a wrong balance ->
    http://localhost:8080/account
    Method POST
    {
        "name" : "Facu",
        "balance" : -300
    }
- Creates transfer OK ->
    http://localhost:8080/account
    Method POST
    {
        "name" : "Facu",
        "balance" : 300
    }
    http://localhost:8080/account
    Method POST
    {
        "name" : "Manu",
        "balance" : 200
    }
    http://localhost:8080/transfer
    Method POST
    {
    	"accountFrom" : 1,
    	"accountTo" : 2,
    	"money" : 100
    }
- Creates transfer account overdrawn ->
    http://localhost:8080/account
    Method POST
    {
        "name" : "Facu",
        "balance" : 300
    }
    http://localhost:8080/account
    Method POST
    {
        "name" : "Manu",
        "balance" : 200
    }
    http://localhost:8080/transfer
    Method POST
    {
    	"accountFrom" : 1,
    	"accountTo" : 2,
    	"money" : 400
    }
- Creates a transfer account not found ->
    http://localhost:8080/account
    Method POST
    {
        "name" : "Facu",
        "balance" : 300
    }
    http://localhost:8080/account
    Method POST
    {
        "name" : "Manu",
        "balance" : 200
    }
    http://localhost:8080/transfer
    Method POST
    {
    	"accountFrom" : 9999,
    	"accountTo" : 2,
        "money" : 400
   }
- Browses H2-embedded database -> http://localhost:8080/h2-console/

5. Limitations
- No endpoint to find an account (that should be needed in a prod app)
- No test scenario to check optimistic lock.
- Tests are not checking persistence.


