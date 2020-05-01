# Tech Assignment

The assignment is to write a 
**sql query to display the records which have 3 or more consecutive events and the amount of people more than 100(inclusive)**
 and **program to parse data**
* sql query is in  **sql.txt**
* Program Drives from **DataParsingSparkJob**
 
## Prerequisite
For this project there should be
 * **sbt** installed
 * **spark 1.5.2** present

## To Run Test
* sbt test

## Specific Input and Output Path
We can setup a input and output path for execution in **application.conf**
```
{
  input : "src/main/resources/data.csv"
  output : "src/main/resources/parse.json"
}

```

## Execution
* for execution run  cd to project (cd one-championship)
* Run command **sbt run**
