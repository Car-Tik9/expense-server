package com.expense.expensemanager.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;

import com.expense.expensemanager.audit.DateAudit;

@Entity
@Table(name = "transactions")
public class Transaction extends DateAudit{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionid;
	
	@Size(max = 80)
	@Column(name = "reference_number")
	private String referenceNumber;
	
	@ManyToOne
    @JoinColumn(name = "id")
    private User user;
	
	@NotNull
	@Column(name="cd_div")
	private int cdDiv;
	
	@NotNull
	@Column(name = "date_of_transaction")
	@Temporal(TemporalType.DATE)
	private Date dateOfTransaction;
	
	@NotNull
	@Column(name = "transaction_amount")
	private Double transactionAmount;
	
	
	@Column(name = "transaction_mode")
	private String transactionMode;
	
	@NotNull
	@Column(name = "transaction_mode_type")
	private int transactionModeType;
	
	@Column(name = "money_send_to")
	private String moneySendto;
	
	@Column(name = "notes")
	private String notes;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "category_name" , referencedColumnName = "category_name")
	private Categories categories;
	
	public String getReference_number() {
		return referenceNumber;
	}

	public Long getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(Long transactionid) {
		this.transactionid = transactionid;
	}

	public void setReference_number(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	
	public int getCdDiv() {
		return cdDiv;
	}

	public void setCdDiv(int cdDiv) {
		this.cdDiv = cdDiv;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(Date dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}

	public int getTransactionModeType() {
		return transactionModeType;
	}

	public void setTransactionModeType(int transactionModeType) {
		this.transactionModeType = transactionModeType;
	}

	public String getMoneySendto() {
		return moneySendto;
	}

	public void setMoneySendto(String moneySendto) {
		this.moneySendto = moneySendto;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public Categories getCategories() {
		return categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}
}
