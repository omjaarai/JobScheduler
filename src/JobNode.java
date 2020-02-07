/**
 * File Header COMES HERE
 */

/**
 * JavaDoc Class Header COMES HERE
 *
 */

public class JobNode {

    // Class Fields
    private static int jobCount = 0; // number of jobs already created

    // Object Fields
    private int jobId;          // unique job identifier
    private float arrivalTime;  // arrival time in seconds
    private int userId;         // identifier of the user that created the job
    private int priority;       // job priority
    private int timeToLive;     // job Time To Live in seconds
    private String description; // job description



    private JobNode next; // reference to the next job in the linked list

    // Constructor using fields
    /**
     * Description of the constructor comes here
     * @param arrivalTime
     * @param userId
     * @param priority
     * @param ttl
     * @param description
     */
    public JobNode(float arrivalTime, int userId, int priority, 
        int ttl, String description) {
        // TODO implementation details come here

        jobCount++;
        this.jobId = jobCount;
        this.arrivalTime = arrivalTime;
        this.userId = userId;
        this.priority = priority;
        this.timeToLive = ttl;
        this.description = description;
        this.next = null;


    }
    public JobNode(JobNode node) {

        this.jobId = node.jobId;
        this.arrivalTime = node.arrivalTime;
        this.userId = node.userId;
        this.priority = node.priority;
        this.timeToLive = node.timeToLive;
        this.description = node.description;
        this.next = null;

    }

    /*  public JobNode(int jobID,JobNode next) {
        jobID=++jobCount;
        this.next=next;
    }   */
    // You can overload your class by other constructors

    // TODO Add Getters and Setters Methods as needed

    public int getJobId() {
        return this.jobId;
    }

    public float getArrivalTime() {
        return this.arrivalTime;

    }
    public int getUserId() {
        return this.userId;

    }
    public int getPriority() {
        return this.priority;

    }
    public int getTimeToLive() {
        return this.timeToLive;

    }

    public String getDescription() {
        return this.description;
    }
    public void setNext(JobNode next) {
        this.next = next;
    }



    public void setJobId(int jobId) {
        this.jobId=jobId;
    }

    public void setArrivalTime(float arrivalTime) {
        this.arrivalTime=arrivalTime;

    }
    public void setUserId(int userId) {
        this.userId=userId;

    }
    public void setPriority(int priority) {
        this.priority=priority;

    }
    public void setTimeToLive(int timeToLive) {
        this.timeToLive=timeToLive;

    }

    public void setDescription(String description) {
        this.description=description;
    }


    public JobNode getNext() {
        return next;
    } 

    //    public void setNext(JobNode jobnode) {
    //        next=jobnode;
    //    }


    /**
     * This method returns a new reference to a copy of the current JobNode
     * @return a new reference to a copy of this (instanceof JobNode)
     */
    //  public JobNode copy() {
    //        
    //        return new JobNode(this);
    //        
    //    }
    public JobNode copy() {
        // TODO Implementation details come here

        JobNode jobnode=new JobNode( arrivalTime,  userId,  priority, 
            timeToLive,  description);
        jobnode.setJobId(jobId);
        return jobnode;



    }

}

