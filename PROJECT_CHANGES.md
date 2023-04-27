# Wine breakdown API — bug hunt & refactor challenge (EXPLANATION)


## Context

Prior to the changes, the following apis were returning response with all the data and not particularly what it were meant for. 
For example, the first one (breakdown of total percentage by year) was returning with records with duplicate years and we wanted to group the results based on a year and return total percentage for that particular year.

- **/api/breakdown/year/{lotCode}** — breakdown of total percentage by year
- **/api/breakdown/variety/{lotCode}** — breakdown of total percentage by variety
- **/api/breakdown/region/{lotCode}** — breakdown of total percentage by region
- **/api/breakdown/year-variety/{lotCode}** — breakdown of total percentage by year + variety combination


## Part 1: Bug hunt

So, in order to get the results based on the year, region, variety and year-variety, I made use of Collectors.groupingBy method provided by Java 8 feature Stream API. Using that we can group our data based on a field of a DTO and calculate sum or avg, etc for the other field. So, I created a map from that with the field used for grouping as key and the sum of their percentage of the same keys. This lead to passing of all the test cases without modifying any test case.
The JSON data created from the provided sample files for actal and expected data matched. 

## Part 2: Refactor

The other improvements I found were basically related to MVC pattern used in the project. I inferred this from the controller class annotated with @RestController and similar annotations used for request mapping. The helper methods written in the controller class should have been in a different package (service) with a service class annotated with @Service. Also, the DTOs shall be placed in another package and created as POJO class. 
Also, we could add try catch for handling exceptions for avoiding errors as we were using file operations, catching if we got null data, if pat variable was null, if any helper method returned null, etc. 
To handle many more errors like this we could create exception package with a class as @RestControllerAdvice and handler methods in it so that we can throw our custom exceptions with suitable error codes and message.

