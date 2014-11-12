package com.oodd.spring.manytomanyselfreferencewithjoinattribute.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class WorkerWorkerId implements Serializable {
	
	private static final long serialVersionUID = -2955127866598725414L;
	
	private Worker worker1;
	private Worker worker2;	
	
	@ManyToOne
	public Worker getWorker1() {
		return worker1;
	}
	public void setWorker1(Worker worker1) {
		this.worker1 = worker1;
	}
	
	@ManyToOne
	public Worker getWorker2() {
		return worker2;
	}

	public void setWorker2(Worker worker2) {
		this.worker2 = worker2;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkerWorkerId that = (WorkerWorkerId) o;

        if (worker1 != null ? !worker1.equals(that.worker1) : that.worker1 != null) return false;
        if (worker2 != null ? !worker2.equals(that.worker2) : that.worker2 != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (worker1 != null ? worker1.hashCode() : 0);
        result = 31 * result + (worker2 != null ? worker2.hashCode() : 0);
        return result;
    }
}
