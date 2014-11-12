package com.oodd.spring.manytomanybidirectionalwithjoinattribute.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class ManuscriptAuthorId implements java.io.Serializable {
	
	private static final long serialVersionUID = 8395881050435165891L;
	
	private Manuscript manuscript;
	private Author author;
	
	@ManyToOne
	public Manuscript getManuscript() {
		return manuscript;
	}
	public void setManuscript(Manuscript manuscript) {
		this.manuscript = manuscript;
	}
	
	@ManyToOne
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ManuscriptAuthorId that = (ManuscriptAuthorId) o;

        if (manuscript != null ? !manuscript.equals(that.manuscript) : that.manuscript != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (manuscript != null ? manuscript.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }
}