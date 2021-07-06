package com.tenniscourts.config.persistence;

import com.tenniscourts.audit.CustomAuditEntityListener;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
@EntityListeners(CustomAuditEntityListener.class)
public class BaseEntity<ID> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    @Column
    private String ipNumberUpdate;

    @Column
    private Long userCreate;

    @Column
    private Long userUpdate;

    @Column
    @LastModifiedDate
    private LocalDateTime dateUpdate;

    @Column
    private String ipNumberCreate;

    @Column
    @CreatedDate
    private LocalDateTime dateCreate;
    

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public String getIpNumberUpdate() {
		return ipNumberUpdate;
	}

	public void setIpNumberUpdate(String ipNumberUpdate) {
		this.ipNumberUpdate = ipNumberUpdate;
	}

	public Long getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(Long userCreate) {
		this.userCreate = userCreate;
	}

	public Long getUserUpdate() {
		return userUpdate;
	}

	public void setUserUpdate(Long userUpdate) {
		this.userUpdate = userUpdate;
	}

	public LocalDateTime getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(LocalDateTime dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public String getIpNumberCreate() {
		return ipNumberCreate;
	}

	public void setIpNumberCreate(String ipNumberCreate) {
		this.ipNumberCreate = ipNumberCreate;
	}

	public LocalDateTime getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(LocalDateTime dateCreate) {
		this.dateCreate = dateCreate;
	}
    

}
