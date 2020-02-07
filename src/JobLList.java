public class JobLList implements WaitingListADT<JobNode> {

    private JobNode head;//head of the list
    private int size;



    //    public JobLList(JobNode head, int size) {
    //        head=waitingList.head;
    //        size=waitingList.size();
    //
    //
    //    }
    public JobLList(JobLList waitingList) {
        head = waitingList.head;
        size = waitingList.size();
    }


    public JobLList() {
        this.head=null;
        this.size=0;
    }

    public void schedule(JobNode newJob) {
        // int priority=newJob.getPriority();
        //newjob is added at the end of the list

        //if list is empty, make head the jobnode
        //jobnode has priority 0. put in end
        //if u want to put in end, stick it in there
        //if 1, put it after all 1s, iterate through list
        //if all 1s, put at end.

        if(isEmpty()) {
            head = newJob;
            size++;
        } 
        else { 

            JobNode runner = head;  
            JobNode tracker = runner;
            int trackList = 0;



            if(newJob.getPriority() == 0) {
                while(runner.getNext() != null) {
                    runner = runner.getNext();
                }
                runner.setNext(newJob);
                runner = null;
                size++;

            }
            if(newJob.getPriority()==1) {
                while(runner.getNext() != null && runner.getPriority() != 0) {
                    tracker = runner;
                    runner = runner.getNext();
                    trackList++;
                }
                if(runner.getNext() != null) {

                    if(trackList != 0) {
                        tracker.setNext(newJob);
                        newJob.setNext(runner);
                    } else {
                        head = newJob;
                        newJob.setNext(runner);       
                    }
                    //JobNode nextRunner = runner.getNext();
                    //newJob.setNext(nextRunner);
                    //runner.setNext(newJob);
                } else {
                    if(runner.getPriority() == 1) {
                        runner.setNext(newJob);
                    }
                    else {
                        if(this.size != 0 && this.size != 1) {
                            tracker.setNext(newJob);
                            newJob.setNext(runner);
                        }
                        else {
                            if(this.size == 0) {
                                head = newJob;
                            } else {
                                head = newJob;
                                newJob.setNext(runner);
                            }
                        }
                    }
                }
                runner = null;
                size++;
            }
        }

    }



    public int clean(float cleaningTime) {
        //JobNode jobNode=new JobNode()
        int originalSize = size;
        JobNode runner = head;
        JobNode newTracker = runner;
        if(runner != null && runner.getNext() != null) {
            while(runner != null && runner.getArrivalTime() + runner.getTimeToLive() < cleaningTime) {
                if(runner.getNext() != null) {
                    head = runner.getNext();
                    runner = head;
                    size--;
                } else {
                    head = null; 
                    runner = head;
                    size = 0;
                }

            }
            JobNode removeLocation = null;
            if(runner != null && runner.getNext() != null) {
                removeLocation = runner.getNext();
            } else {
                removeLocation = runner;
            }
            while(removeLocation != null) {
                int ogSize = this.size;
                int trackList = this.size;
                if(removeLocation.getArrivalTime() + removeLocation.getTimeToLive() < cleaningTime) {  
                    if(removeLocation.getNext() != null) {
                        if(runner != removeLocation) {
                            JobNode nextRunner = removeLocation.getNext();
                            runner.setNext(nextRunner);
                            size--;
                            trackList--;} else {
                                if(removeLocation.getNext() != null) {
                                    newTracker.setNext(removeLocation.getNext());
                                    trackList--;
                                    size--;

                                } 
                            } 
                    } else {
                        if(removeLocation == runner && size == 0) {
                            head = null;
                            size--;
                            trackList--;
                        } else if (removeLocation == runner) {
                            newTracker.setNext(null);
                            trackList--;
                            size--;
                        } else {
                            runner.setNext(null);
                            size--; 
                            trackList--;
                        }
                    }

                }          
                if(runner != null) {
                    newTracker = runner;
                    runner = newTracker.getNext();   
                }
                if(runner != null && runner.getNext() != null) {
                    if(trackList != ogSize) {
                        removeLocation = runner;  
                    } else {
                        removeLocation = runner.getNext();
                    }
                } else {
                    removeLocation = null;
                }
            }
            runner = null;
            return originalSize - size;
        }
        else {
            if(runner != null) {
                if(runner.getTimeToLive() + runner.getArrivalTime() < cleaningTime) {
                    head = null;
                    size--;
                }
            }
            return originalSize - size;
        }
    }





    public boolean isEmpty() {
        if(head == null) {
            return true;   
        } else {
            return false;
        }
    }


    //returns the number of items in the waiting list
    public int size() {

        return size;
    }


    public WaitingListADT<JobNode> duplicate() {
        WaitingListADT<JobNode> duplicateList;
        JobLList duplicate = new JobLList();
        JobNode runner = this.head;
        while(runner != null) {
            duplicate.schedule(runner.copy());
            runner = runner.getNext();
        }        
        runner = null;
        duplicateList = duplicate;  
        return duplicateList;// returns a new reference to a duplicate copy of the list

    }

    public String toString() {
        //        if(isEmpty()) {
        //            System.out.print("Job List is empty: true\n" + 
        //                "The size is: 0  job(s).");
        //        }else {
        //            System.out.print("Job List is empty: false\n" + 
        //                "The size is: <"+size()+"> job(s).\n" );
        //            while(head !=null)
        //            {
        //                head=head.getNext();
        //
        //                System.out.println("job #<"+head.getJobId()+"> : <"+head.getDescription()+"> (UID <"+head.getUserId()+">) <"+head.getPriority()+">");
        //            }
        //
        //        }
        //        return "";
        String listDescribed = "";
        String list = "";
        JobNode runner = this.head;
        if(runner != null) {
            listDescribed = this.getIsEmpty() + this.getSize();
            while(runner != null) {
                list = list + "job #" + runner.getJobId() + " : " + runner.getDescription() +
                    " (UID " + runner.getUserId() + ") " + runner.getPriority() +"\n";
                runner = runner.getNext();
            }
            listDescribed = (listDescribed + list).trim() + "\n";
            return listDescribed;
        } else {
            listDescribed = (this.getIsEmpty() + this.getSize()).trim() + "\n";
            return listDescribed;
        }
    }


    public void clear() {
        head = null; // removes all the items in the waiting list
        size = 0;
    }
    public String getIsEmpty() {
        return "Job List is empty: " + this.isEmpty() + "\n";
    }
    public String getSize() {
        return "The size is: " + this.size() + " job(s).\n";
    }


}


