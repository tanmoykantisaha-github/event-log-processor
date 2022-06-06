# event-log-processor
Assignment on sample event log processor implemented without multithreading

Problem statement :
Summary of task
Our custom-build server logs different events to a file named logfile.txt. Every event has 2 entries in the file - one entry when the event was started and another when the event was finished. The entries in the file have no specific order (a finish event could occur before a start event for a given id)
Every line in the file is a JSON object containing the following event data:
* id - the unique event identifier
* state - whether the event was started or finished (can have values "STARTED" or "FINISHED"
* timestamp - the timestamp of the event in milliseconds
Application Server logs also have the following additional attributes:
* type - type of log
* host - hostname

Example contents of logfile.txt:

- {"id":"scsmbstgra", "state":"STARTED", "type":"APPLICATION_LOG", "host":"12345", "timestamp":1491377495212}
- {"id":"scsmbstgrb", "state":"STARTED", "timestamp":1491377495213}
- {"id":"scsmbstgrc", "state":"FINISHED", "timestamp":1491377495218}
- {"id":"scsmbstgra", "state":"FINISHED", "type":"APPLICATION_LOG", "host":"12345", "timestamp":1491377495217}
- {"id":"scsmbstgrc", "state":"STARTED", "timestamp":1491377495210}
- {"id":"scsmbstgrb", "state":"FINISHED", "timestamp":1491377495216}


In the example above, the event scsmbstgrb duration is 1491377495216 - 1491377495213 = 3ms
The longest event is scsmbstgrc (1491377495218 - 1491377495210 = 8ms)

The program should:
* Take the path to logfile.txt as an input argument
* Parse the contents of logfile.txt
* Flag any long events that take longer than 4ms
* Write the found event details to file-based HSQLDB (http://hsqldb.org/) in the working folder
* The application should create a new table if necessary and store the following values:
    - Event id
    - Event duration
    - Type and Host if applicable
    - Alert (true if the event took longer than 4ms, otherwise false)

Execution process :
- Outside eclipse develpment setup :
    * Run the application jar and pass the log file name (with full path, if path contains space, enclose with quotes) as only parameter.
- From eclipse development setup
    * Clone the code
    * import as maven project
    * Build the project
    * from run configuration, run the class EventLogProcessorApplication and pass full name of the log file (with full path, if path contains space, enclose with quotes) as parameter.
