package com.oodd.spring.manytomanybidirectionalwithjoinattribute.entity;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "MANUSCRIPT_AUTHOR")
@AssociationOverrides({
        @AssociationOverride(name = "manuscriptAuthorId.manuscript", joinColumns = @JoinColumn(name = "MANUSCRIPT_ID", referencedColumnName="ID")),
        @AssociationOverride(name = "manuscriptAuthorId.author", joinColumns = @JoinColumn(name = "AUTHOR_ID", referencedColumnName="ID")) })
public class ManuscriptAuthor implements Serializable {
	private static final long serialVersionUID = 3182293188081684002L;
	
	private ManuscriptAuthorId manuscriptAuthorId = new ManuscriptAuthorId();
	private String publisher;

	@EmbeddedId
	public ManuscriptAuthorId getManuscriptAuthorId() {
		return manuscriptAuthorId;
	}

	public void setManuscriptAuthorId(ManuscriptAuthorId manuscriptAuthorId) {
		this.manuscriptAuthorId = manuscriptAuthorId;
	}
	
	@Transient
	public Manuscript getManuscript() {
		return getManuscriptAuthorId().getManuscript();
	}
	
	public void setManuscript(Manuscript manuscript) {
		getManuscriptAuthorId().setManuscript(manuscript);
	}
	
	@Transient
	public Author getAuthor() {
		return getManuscriptAuthorId().getAuthor();
	}
	
	public void setAuthor(Author author) {
		getManuscriptAuthorId().setAuthor(author);
	}
	
	@Column(name="PUBLISHER", nullable=false, length=100)
	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ManuscriptAuthor that = (ManuscriptAuthor) o;

		if (getManuscriptAuthorId() != null ? !getManuscriptAuthorId().equals(that.getManuscriptAuthorId())
				: that.getManuscriptAuthorId() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getManuscriptAuthorId() != null ? getManuscriptAuthorId().hashCode() : 0);
	}
}